public class PacketBufferNode extends Node {
	static PacketBufferNode[] PacketBufferNode_packetBufferNodes;
	static int PacketBufferNode_packetBufferNodeCount;
	ClientPacket clientPacket;
	int clientPacketLength;
	public PacketBuffer packetBuffer;
	public int index;

	static {
		PacketBufferNode_packetBufferNodes = new PacketBufferNode[300];
		PacketBufferNode_packetBufferNodeCount = 0;
	}

	PacketBufferNode() {
	}

	public void release() {
		if (PacketBufferNode_packetBufferNodeCount < PacketBufferNode_packetBufferNodes.length) {
			PacketBufferNode_packetBufferNodes[++PacketBufferNode_packetBufferNodeCount - 1] = this;
		}
	}
}
