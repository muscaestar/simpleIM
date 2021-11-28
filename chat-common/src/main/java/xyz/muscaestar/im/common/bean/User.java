package xyz.muscaestar.im.common.bean;

import lombok.Data;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by muscaestar on 11/28/21
 *
 * @author muscaestar
 */
@Data
public class User {
    private static final AtomicInteger NO = new AtomicInteger(1);

    private String uid = String.valueOf(NO.getAndIncrement());
    private String devId= UUID.randomUUID().toString();
    private String token= UUID.randomUUID().toString();
    private String nickName = "nickName";
    private PLATTYPE platform = PLATTYPE.MAC;

    public enum PLATTYPE {
        WINDOWS, MAC, ANDROID, IOS, WEB, OTHER;
    }

    private String sessionId;

    public void setPlatform(int platform) {
        PLATTYPE[] values = PLATTYPE.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].ordinal() == platform) {
                this.platform = values[i];
            }
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", devId='" + devId + '\'' +
                ", token='" + token + '\'' +
                ", nickName='" + nickName + '\'' +
                ", platform=" + platform +
                ", sessionId='" + sessionId + '\'' +
                '}';
        /**
         * Created by muscaestar on 11/28/21
         *
         * @author muscaestar
         */
    }
}
