import java.io.IOException;
import java.math.BigInteger;

public class class65 {
	static final BigInteger field861;
	static final BigInteger field862;

	static {
		field861 = new BigInteger("80782894952180643741752986186714059433953886149239752893425047584684715842049");
		field862 = new BigInteger("7237300117305667488707183861728052766358166655052137727439795191253340127955075499635575104901523446809299097934591732635674173519120047404024393881551683");
	}

	public static boolean method1875() {
		long var0 = WorldMapSprite.cycleTimer();
		int var2 = (int)(var0 - NetCache.idleNetTime);
		NetCache.idleNetTime = var0;
		if (var2 > 200) {
			var2 = 200;
		}

		NetCache.NetCache_loadTime += var2;
		if (NetCache.NetCache_pendingResponsesCount == 0 && NetCache.NetCache_pendingPriorityResponsesCount == 0 && NetCache.NetCache_pendingWritesCount == 0 && NetCache.NetCache_pendingPriorityWritesCount == 0) {
			return true;
		} else if (NetCache.NetCache_socket == null) {
			return false;
		} else {
			try {
				if (NetCache.NetCache_loadTime > 30000) {
					throw new IOException();
				} else {
					NetFileRequest js5ResponseMsg;
					Buffer responseBuf;
					while (NetCache.NetCache_pendingPriorityResponsesCount < 200 && NetCache.NetCache_pendingPriorityWritesCount > 0) {
						js5ResponseMsg = (NetFileRequest)NetCache.NetCache_pendingPriorityWrites.first();
						responseBuf = new Buffer(4);
						responseBuf.writeByte(1);
						responseBuf.writeMedium((int)js5ResponseMsg.key);
						NetCache.NetCache_socket.write(responseBuf.array, 0, 4);
						NetCache.NetCache_pendingPriorityResponses.put(js5ResponseMsg, js5ResponseMsg.key);
						--NetCache.NetCache_pendingPriorityWritesCount;
						++NetCache.NetCache_pendingPriorityResponsesCount;
					}

					while (NetCache.NetCache_pendingResponsesCount < 200 && NetCache.NetCache_pendingWritesCount > 0) {
						js5ResponseMsg = (NetFileRequest)NetCache.NetCache_pendingWritesQueue.removeLast();
						responseBuf = new Buffer(4);
						responseBuf.writeByte(0);
						responseBuf.writeMedium((int)js5ResponseMsg.key);
						NetCache.NetCache_socket.write(responseBuf.array, 0, 4);
						js5ResponseMsg.removeDual();
						NetCache.NetCache_pendingResponses.put(js5ResponseMsg, js5ResponseMsg.key);
						--NetCache.NetCache_pendingWritesCount;
						++NetCache.NetCache_pendingResponsesCount;
					}

					for (int var15 = 0; var15 < 100; ++var15) {
						int availableBytes = NetCache.NetCache_socket.available();
						if (availableBytes < 0) {
							throw new IOException();
						}

						if (availableBytes == 0) {
							break;
						}

						NetCache.NetCache_loadTime = 0;
						byte responseLength = 0;
						if (NetCache.NetCache_currentResponse == null) {
							responseLength = 8;
						} else if (NetCache.currentBlockOffset == 0) {
							responseLength = 1;
						}

						int responseHeaderLength;
						int archive;
						int group;
						int compressedLength;
						byte[] responseHeaderBytes;
						int responseHeaderOffset;
						Buffer responseHeaderBuf;
						if (responseLength > 0) {
							responseHeaderLength = responseLength - NetCache.NetCache_responseHeaderBuffer.offset;
							if (responseHeaderLength > availableBytes) {
								responseHeaderLength = availableBytes;
							}

							NetCache.NetCache_socket.read(NetCache.NetCache_responseHeaderBuffer.array, NetCache.NetCache_responseHeaderBuffer.offset, responseHeaderLength);
							if (NetCache.NetCache_xorValue != 0) {
								for (archive = 0; archive < responseHeaderLength; ++archive) {
									responseHeaderBytes = NetCache.NetCache_responseHeaderBuffer.array;
									responseHeaderOffset = archive + NetCache.NetCache_responseHeaderBuffer.offset;
									responseHeaderBytes[responseHeaderOffset] ^= NetCache.NetCache_xorValue;
								}
							}

							responseHeaderBuf = NetCache.NetCache_responseHeaderBuffer;
							responseHeaderBuf.offset += responseHeaderLength;
							if (NetCache.NetCache_responseHeaderBuffer.offset < responseLength) {
								break;
							}

							if (NetCache.NetCache_currentResponse == null) {
								NetCache.NetCache_responseHeaderBuffer.offset = 0;
								archive = NetCache.NetCache_responseHeaderBuffer.readUnsignedByte();
								group = NetCache.NetCache_responseHeaderBuffer.readUnsignedShort();
								int compressionType = NetCache.NetCache_responseHeaderBuffer.readUnsignedByte();
								compressedLength = NetCache.NetCache_responseHeaderBuffer.readInt();
								long packedIndex = group + (archive << 16);
								NetFileRequest var13 = (NetFileRequest) NetCache.NetCache_pendingPriorityResponses.get(packedIndex);
								ClanChannel.field1673 = true;
								if (var13 == null) {
									var13 = (NetFileRequest) NetCache.NetCache_pendingResponses.get(packedIndex);
									ClanChannel.field1673 = false;
								}

								if (var13 == null) {
									throw new IOException();
								}

								int compressionHeaderLength = compressionType == 0 ? 5 : 9;
								NetCache.NetCache_currentResponse = var13;
								class291.NetCache_responseArchiveBuffer = new Buffer(compressionHeaderLength + compressedLength + NetCache.NetCache_currentResponse.padding);
								class291.NetCache_responseArchiveBuffer.writeByte(compressionType);
								class291.NetCache_responseArchiveBuffer.writeInt(compressedLength);
								NetCache.currentBlockOffset = 8;
								NetCache.NetCache_responseHeaderBuffer.offset = 0;
							} else if (NetCache.currentBlockOffset == 0) {
								if (NetCache.NetCache_responseHeaderBuffer.array[0] == -1) {
									NetCache.currentBlockOffset = 1;
									NetCache.NetCache_responseHeaderBuffer.offset = 0;
								} else {
									NetCache.NetCache_currentResponse = null;
								}
							}
						} else {
							responseHeaderLength = class291.NetCache_responseArchiveBuffer.array.length - NetCache.NetCache_currentResponse.padding;
							archive = 512 - NetCache.currentBlockOffset;
							if (archive > responseHeaderLength - class291.NetCache_responseArchiveBuffer.offset) {
								archive = responseHeaderLength - class291.NetCache_responseArchiveBuffer.offset;
							}

							if (archive > availableBytes) {
								archive = availableBytes;
							}

							NetCache.NetCache_socket.read(class291.NetCache_responseArchiveBuffer.array, class291.NetCache_responseArchiveBuffer.offset, archive);
							if (NetCache.NetCache_xorValue != 0) {
								for (group = 0; group < archive; ++group) {
									responseHeaderBytes = class291.NetCache_responseArchiveBuffer.array;
									responseHeaderOffset = class291.NetCache_responseArchiveBuffer.offset + group;
									responseHeaderBytes[responseHeaderOffset] ^= NetCache.NetCache_xorValue;
								}
							}

							responseHeaderBuf = class291.NetCache_responseArchiveBuffer;
							responseHeaderBuf.offset += archive;
							NetCache.currentBlockOffset += archive;
							if (responseHeaderLength == class291.NetCache_responseArchiveBuffer.offset) {
								if (NetCache.NetCache_currentResponse.key == 16711935L) {
									class122.NetCache_reference = class291.NetCache_responseArchiveBuffer;

									for (group = 0; group < 256; ++group) {
										Archive var17 = NetCache.NetCache_archives[group];
										if (var17 != null) {
											class122.NetCache_reference.offset = group * 8 + 5;
											compressedLength = class122.NetCache_reference.readInt();
											int var18 = class122.NetCache_reference.readInt();
											var17.loadIndex(compressedLength, var18);
										}
									}
								} else {
									NetCache.NetCache_crc.reset();
									NetCache.NetCache_crc.update(class291.NetCache_responseArchiveBuffer.array, 0, responseHeaderLength);
									group = (int) NetCache.NetCache_crc.getValue();
									if (group != NetCache.NetCache_currentResponse.crc) {
										try {
											NetCache.NetCache_socket.close();
										} catch (Exception var20) {
										}

										++NetCache.NetCache_crcMismatches;
										NetCache.NetCache_socket = null;
										NetCache.NetCache_xorValue = (byte) ((int) (Math.random() * 255.0D + 1.0D));
										return false;
									}

									NetCache.NetCache_crcMismatches = 0;
									NetCache.NetCache_ioExceptions = 0;
									NetCache.NetCache_currentResponse.archive.write((int)(NetCache.NetCache_currentResponse.key & 65535L), class291.NetCache_responseArchiveBuffer.array, (NetCache.NetCache_currentResponse.key & 16711680L) == 16711680L, ClanChannel.field1673);
								}

								NetCache.NetCache_currentResponse.remove();
								if (ClanChannel.field1673) {
									--NetCache.NetCache_pendingPriorityResponsesCount;
								} else {
									--NetCache.NetCache_pendingResponsesCount;
								}

								NetCache.currentBlockOffset = 0;
								NetCache.NetCache_currentResponse = null;
								class291.NetCache_responseArchiveBuffer = null;
							} else {
								if (NetCache.currentBlockOffset != 512) {
									break;
								}

								NetCache.currentBlockOffset = 0;
							}
						}
					}

					return true;
				}
			} catch (IOException var21) {
				try {
					NetCache.NetCache_socket.close();
				} catch (Exception var19) {
				}

				++NetCache.NetCache_ioExceptions;
				NetCache.NetCache_socket = null;
				return false;
			}
		}
	}

	static long method1867(int var0, int var1, int var2) {
		return var2 << 16 | var0 << 8 | var1;
	}

	static PacketBufferNode method1876() {
		return PacketBufferNode.PacketBufferNode_packetBufferNodeCount == 0 ? new PacketBufferNode() : PacketBufferNode.PacketBufferNode_packetBufferNodes[--PacketBufferNode.PacketBufferNode_packetBufferNodeCount];
	}

	public static int[] method1869() {
		int[] var0 = new int[KeyHandler.field132];

		for (int var1 = 0; var1 < KeyHandler.field132; ++var1) {
			var0[var1] = KeyHandler.field139[var1];
		}

		return var0;
	}

	static void resizeInterface(Widget[] var0, int var1, int var2, int var3, boolean var4) {
		for (int var5 = 0; var5 < var0.length; ++var5) {
			Widget var6 = var0[var5];
			if (var6 != null && var6.parentId == var1) {
				class116.alignWidgetSize(var6, var2, var3, var4);
				class162.alignWidgetPosition(var6, var2, var3);
				if (var6.scrollX > var6.scrollWidth - var6.width) {
					var6.scrollX = var6.scrollWidth - var6.width;
				}

				if (var6.scrollX < 0) {
					var6.scrollX = 0;
				}

				if (var6.scrollY > var6.scrollHeight - var6.height) {
					var6.scrollY = var6.scrollHeight - var6.height;
				}

				if (var6.scrollY < 0) {
					var6.scrollY = 0;
				}

				if (var6.type == 0) {
					GrandExchangeEvents.revalidateWidgetScroll(var0, var6, var4);
				}
			}
		}

	}
}
