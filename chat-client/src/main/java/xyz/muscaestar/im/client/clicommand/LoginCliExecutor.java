package xyz.muscaestar.im.client.clicommand;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import xyz.muscaestar.im.client.msgbuilder.LoginMsgBuilder;
import xyz.muscaestar.im.client.sender.LoginSender;
import xyz.muscaestar.im.common.bean.User;
import xyz.muscaestar.im.common.bean.msg.ProtoMsg;

import java.util.Scanner;

/**
 * 会被反复使用 应该无状态
 *
 * Created by muscaestar on 11/28/21
 *
 * @author muscaestar
 */
public class LoginCliExecutor implements CliExecutor {
    @Autowired
    private LoginSender loginSender;

    @Override
    public boolean exec(Scanner scanner) {
        System.out.println("输入：用户名@密码");
        String line = scanner.nextLine();
        if (StringUtils.isNotBlank(line)) {
            String[] split = line.split("@");
            doLogin(split[0], split[1]);
        }
        return false;
    }

    private void doLogin(String username, String password) {
        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);


        // todo 后续用消息发送模块替代
        // 消息构造
        User user = new User();
        user.setUid(username);
        user.setToken(password);
        ProtoMsg.Message protoMsg = LoginMsgBuilder.of(user).build(-1);

        // 消息发送
        loginSender.send(protoMsg);
    }
}
