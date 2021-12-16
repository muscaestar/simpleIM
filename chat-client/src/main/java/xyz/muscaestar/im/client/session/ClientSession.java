package xyz.muscaestar.im.client.session;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import lombok.Data;
import org.springframework.stereotype.Service;
import xyz.muscaestar.im.common.bean.User;

/**
 * 有状态的单例
 * 维护User与Channel的对应关系
 *
 * Created by muscaestar on 12/17/21
 *
 * @author muscaestar
 */
@Service
@Data
public class ClientSession {
    public static final AttributeKey<ClientSession> SESSION_KEY = AttributeKey.newInstance("SESSION_KEY");

    private Channel channel;
    private User user;

    public ClientSession(Channel channel) {
        // 建立双向绑定
        this.channel = channel;
        channel.attr(SESSION_KEY).set(this);
    }

    public void close() {

        ChannelFuture close = channel.close();
        close.addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("close successfully");
            }
        });
    }

}
