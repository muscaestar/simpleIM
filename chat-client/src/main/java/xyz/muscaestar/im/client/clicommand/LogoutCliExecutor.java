package xyz.muscaestar.im.client.clicommand;

import java.util.Scanner;

/**
 * Created by muscaestar on 11/28/21
 *
 * @author muscaestar
 */
public class LogoutCliExecutor implements CliExecutor {
    @Override
    public boolean exec(Scanner scanner) {
        System.out.println("Logout");
        return true;
    }
}
