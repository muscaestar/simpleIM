package xyz.muscaestar.im.client.msgbuilder;

import xyz.muscaestar.im.common.bean.User;
import xyz.muscaestar.im.common.bean.msg.ProtoMsg;

import static xyz.muscaestar.im.common.bean.msg.ProtoMsg.*;

/**
 * Created by muscaestar on 11/28/21
 *
 * @author muscaestar
 */
public class LoginMsgBuilder extends ProtoMsgBuilder {

    public static LoginMsgBuilder of(User user) {
        return new LoginMsgBuilder(user);
    }

    public LoginMsgBuilder(User user) {
        super(HeadType.LOGIN_REQ, user);
        this.user = user;
    }

    @Override
    public Message.Builder addBuilderAttri(Message.Builder baseBuilder) {
        User user = super.user;
        LoginReq.Builder lb = LoginReq.newBuilder()
                .setDeviceId(user.getDevId())
                .setPlatform(user.getPlatform().ordinal())
                .setToken(user.getToken())
                .setUid(user.getUid());
        Message.Builder mb = baseBuilder.setLoginReq(lb);
        return mb;
    }
}
