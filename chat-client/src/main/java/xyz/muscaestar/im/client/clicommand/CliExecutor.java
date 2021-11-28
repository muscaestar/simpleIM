package xyz.muscaestar.im.client.clicommand;

import java.util.Scanner;

/**
 * Created by muscaestar on 11/28/21
 *
 * @author muscaestar
 */
public interface CliExecutor {
    /**
     *
     * @param scanner
     * @return end the program
     */
    boolean exec(Scanner scanner);
}
