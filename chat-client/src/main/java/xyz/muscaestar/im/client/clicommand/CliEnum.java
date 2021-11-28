package xyz.muscaestar.im.client.clicommand;

import java.util.Scanner;

/**
 * Created by muscaestar on 11/28/21
 *
 * @author muscaestar
 */
public enum CliEnum {
    Login(1, "[1]-登陆", new LoginCliExecutor()) {},
    Chat(9, "[9]-聊天", new ChatCliExecutor()) {},
    Logout(0, "[0]-退出", new LogoutCliExecutor()) {},

    ;

    private int key;
    private String tip;
    private CliExecutor executor;


    CliEnum(int key, String tip, CliExecutor executor) {
        this.key = key;
        this.tip = tip;
        this.executor = executor;
    }

    public static CliEnum find(int key) {
        for (CliEnum cli : CliEnum.values()) {
            if (cli.key == key) return cli;
        }
        return null;
    }

    public boolean exec(Scanner scanner) {
        return this.executor.exec(scanner);
    }

    public int getKey() {
        return key;
    }

    public String getTip() {
        return tip;
    }
}
