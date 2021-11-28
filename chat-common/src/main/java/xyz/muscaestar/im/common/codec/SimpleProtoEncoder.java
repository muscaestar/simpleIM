package xyz.muscaestar.im.common.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import xyz.muscaestar.im.common.ProtoInstant;
import xyz.muscaestar.im.common.bean.msg.ProtoMsg;

/**
 * Created by muscaestar on 11/28/21
 *
 * @author muscaestar
 */
public class SimpleProtoEncoder extends MessageToByteEncoder<ProtoMsg.Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ProtoMsg.Message msg,
                          ByteBuf out) throws Exception {
        System.out.println("encode msg to byte");
        encode0(msg, out);
    }

    private void encode0(ProtoMsg.Message msg, ByteBuf out) {
        out.writeShort(ProtoInstant.MAGIC_CODE);
        out.writeShort(ProtoInstant.VERSION_CODE);

        byte[] bytes = msg.toByteArray();

        int len = bytes.length;
        out.writeInt(len);

        out.writeBytes(bytes);
    }
}
