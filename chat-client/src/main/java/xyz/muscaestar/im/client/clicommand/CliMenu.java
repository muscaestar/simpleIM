package xyz.muscaestar.im.client.clicommand;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

import static xyz.muscaestar.im.client.clicommand.CliEnum.*;

/**
 * clicommand模块的门面, 要保证只有一个线程执行它
 *
 * Created by muscaestar on 11/28/21
 *
 * @author muscaestar
 */
@Controller
public class CliMenu implements CliExecutor {
    private String message;

    public CliMenu() {
        StringBuilder sb = new StringBuilder("输入：");
        for (CliEnum cli : values()) {
            sb.append(cli.getTip());
            sb.append(" | ");
        }
        message = sb.toString();
    }

    @Override
    public boolean exec(Scanner scanner) {
        boolean quit = false;
        while (true) {
            printMessage();
            String line = scanner.nextLine();
            if (StringUtils.isBlank(line)) continue;

            int key = NumberUtils.toInt(line, -1);
            CliEnum theCli = find(key);
            if (theCli != null) {
                quit = theCli.exec(scanner);
            }

            if (quit) break;
        }
        return false;
    }

    private void printMessage() {
        System.out.println(this.message);
    }
}
