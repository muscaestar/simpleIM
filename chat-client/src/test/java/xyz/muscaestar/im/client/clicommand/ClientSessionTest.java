package xyz.muscaestar.im.client.clicommand;

import io.netty.channel.Channel;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import xyz.muscaestar.im.client.session.ClientSession;
import xyz.muscaestar.im.common.bean.User;

/**
 * Created by muscaestar on 12/17/21
 *
 * @author muscaestar
 */
public class ClientSessionTest {

    @Test
    public void testClientSession() {
        // 准备工作
        User sample = new User();
        ClientSession clientSession = new ClientSession(new EmbeddedChannel());
        clientSession.setUser(sample);

        // 通过channel找到绑定的ClientSession
        Channel channel = clientSession.getChannel();
        val theSession = channel.attr(ClientSession.SESSION_KEY).get();

        Assert.assertEquals(sample, theSession.getUser());

        theSession.close();
    }
}
