package net.md_5.bungee.packet;

import io.netty.buffer.ByteBuf;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = false)
public class Packet0KeepAlive extends Packet
{

    public int id;

    public Packet0KeepAlive(ByteBuf buf)
    {
        super(0x00, buf);
        id = readInt();
    }
}