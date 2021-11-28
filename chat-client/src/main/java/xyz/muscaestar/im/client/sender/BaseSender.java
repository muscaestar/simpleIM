package xyz.muscaestar.im.client.sender;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import xyz.muscaestar.im.common.bean.msg.ProtoMsg;

/**
 * Created by muscaestar on 11/28/21
 *
 * @author muscaestar
 */
public abstract class BaseSender {
    public final void send(ProtoMsg.Message msg) {
        ChannelFuture channelFuture = getChannel().writeAndFlush(msg);
        channelFuture.addListener(future -> {
            if (future.isSuccess()) {
                onSucceed(msg);
            } else {
                onFail(msg);
            }
        });
    }

    // todo
    private Channel getChannel() {
        throw new UnsupportedOperationException("getChannel");
    }

    protected void onSucceed(ProtoMsg.Message msg) {
        System.out.println("on send succeed");
    }

    protected void onFail(ProtoMsg.Message msg) {
        System.out.println("on send fail");
    }
}
