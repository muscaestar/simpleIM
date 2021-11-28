package xyz.muscaestar.im.client.msgbuilder;

import xyz.muscaestar.im.common.bean.User;
import xyz.muscaestar.im.common.bean.msg.ProtoMsg;

import static xyz.muscaestar.im.common.bean.msg.ProtoMsg.*;

/**
 * Created by muscaestar on 11/28/21
 *
 * @author muscaestar
 */
public abstract class ProtoMsgBuilder {
    protected HeadType type;
    protected long seqId;
//    private ClientSession session;
    protected User user;

    public ProtoMsgBuilder(HeadType type, User user) {
        this.type = type;
        this.user = user;
    }

    public final Message build(long seqId) {
        Message.Builder baseBuilder = Message.newBuilder()
                .setType(type)
//                .setSessionId()
                .setSequence(seqId);

        baseBuilder = addBuilderAttri(baseBuilder);
        return baseBuilder.build();
    }

    protected abstract Message.Builder addBuilderAttri(Message.Builder baseBuilder);
}
