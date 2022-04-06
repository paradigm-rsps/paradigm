package org.paradigm.engine.net.packet.server

import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.list.PlayerList
import org.paradigm.engine.net.Session
import org.paradigm.engine.net.game.Codec
import org.paradigm.engine.net.game.Packet
import org.paradigm.engine.net.game.PacketType
import org.paradigm.engine.net.game.ServerPacket
import org.paradigm.util.buffer.*

@ServerPacket(opcode = 0, type = PacketType.VARIABLE_SHORT)
class RebuildRegionNormal(
    val player: Player,
    val gpi: Boolean = false
) : Packet {
    companion object : Codec<RebuildRegionNormal> {
        override fun encode(session: Session, packet: RebuildRegionNormal, out: JagByteBuf) {
            if(packet.gpi) {
                out.switchWriteMode(BIT_MODE)
                out.writeBits(packet.player.tile.to30BitInteger(), 30)
                for(i in 1 until PlayerList.MAX_PLAYERS) {
                    if(i == packet.player.index) continue
                    out.writeBits(packet.player.gpi.tiles[i], 18)
                }
                out.switchWriteMode(BYTE_MODE)
            }

            val chunk = packet.player.scene.baseTile.toChunk()
            val regions = packet.player.scene.regions

            out.writeShort(chunk.x, transform = ADD)
            out.writeShort(chunk.y, transform = ADD)

            out.writeShort(regions.size)
            regions.forEach { region ->
                region.xteas.forEach { out.writeInt(it) }
            }
        }
    }
}