package xyz.muscaestar.im.client.clicommand;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.channel.socket.SocketChannel;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import xyz.muscaestar.im.client.sender.BaseSender;
import xyz.muscaestar.im.client.sender.LoginSender;
import xyz.muscaestar.im.common.bean.msg.ProtoMsg;
import xyz.muscaestar.im.common.codec.SimpleProtoDecoder;
import xyz.muscaestar.im.common.codec.SimpleProtoEncoder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by muscaestar on 11/28/21
 *
 * @author muscaestar
 */
@RunWith(JMockit.class)
public class LoginCliExecutorTest {

    @Tested
    LoginCliExecutor loginCliExecutor;

    @Injectable
    LoginSender loginSender;

    EmbeddedChannel embeddedChannel = new EmbeddedChannel(new ChannelInitializer<EmbeddedChannel>() {
        @Override
        protected void initChannel(EmbeddedChannel ch) throws Exception {
            ch.pipeline().addLast("encoder", new SimpleProtoEncoder());
            ch.pipeline().addLast("decoder", new SimpleProtoDecoder());
        }
    });

    @Test
    public void testLoginExecutor() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        new MockUp<BaseSender>() {
            @Mock
            void send(ProtoMsg.Message msg) {
                embeddedChannel.writeInbound(msg);
                embeddedChannel.flush();
            }
        };
        Method doLogin = LoginCliExecutor.class.getDeclaredMethod("doLogin", String.class, String.class);
        doLogin.setAccessible(true);
        doLogin.invoke(loginCliExecutor, "rika", "secret");

        ProtoMsg.Message msg = (ProtoMsg.Message) embeddedChannel.readInbound();
        assertEquals("rika", msg.getLoginReq().getUid());
        assertEquals("secret", msg.getLoginReq().getToken());
    }
}