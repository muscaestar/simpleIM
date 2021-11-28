package xyz.muscaestar.im.common.codec;

import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import xyz.muscaestar.im.common.InvalidFrameException;
import xyz.muscaestar.im.common.ProtoInstant;
import xyz.muscaestar.im.common.bean.msg.ProtoMsg;

import java.util.List;

/**
 * Created by muscaestar on 11/28/21
 *
 * @author muscaestar
 */
public class SimpleProtoDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
                          List<Object> out) throws Exception {
        Object outMsg = decode0(ctx, in);
        if (outMsg != null) {
            out.add(outMsg);
        }
    }

    private Object decode0(ChannelHandlerContext ctx, ByteBuf in) throws InvalidFrameException, InvalidProtocolBufferException {
        in.markReaderIndex();

        if (in.readableBytes() < 8) {
            // 不够包头
            return null;
        }

        // 魔数
        short magic = in.readShort();
        if (magic != ProtoInstant.MAGIC_CODE) {
            throw new InvalidFrameException("Wrong Magic Code" + ctx.channel().remoteAddress());
        }

        // 版本
        short version = in.readShort();

        int len = in.readInt();
        if (len < 0) {
            // 非法数据
            ctx.close();
        }

        if (len > in.readableBytes()) {
            in.resetReaderIndex();
            return null;
        }

        byte[] array;
        if (in.hasArray()) {
            array = new byte[len];
            in.readBytes(array, 0, len);
        } else {
            // 直接缓冲
            array = new byte[len];
            in.readBytes(array, 0, len);
        }

        ProtoMsg.Message outMsg = ProtoMsg.Message.parseFrom(array);

        return outMsg;
    }
}
