package xyz.muscaestar.im.client.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import xyz.muscaestar.im.client.clicommand.CliMenu;

import java.util.Scanner;

/**
 * Created by muscaestar on 11/28/21
 *
 * @author muscaestar
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("xyz.muscaestar.im.client")
@SpringBootApplication
public class ClientApp {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ClientApp.class, args);

        startChatClient(context);
    }

    private static void startChatClient(ApplicationContext context) {
        CliMenu cliMenu = context.getBean(CliMenu.class);
        Scanner scanner = new Scanner(System.in);
        cliMenu.exec(scanner);
    }
}
