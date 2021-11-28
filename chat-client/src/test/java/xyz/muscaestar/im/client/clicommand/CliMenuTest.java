package xyz.muscaestar.im.client.clicommand;

import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

/**
 * Created by muscaestar on 11/28/21
 *
 * @author muscaestar
 */
public class CliMenuTest {

    CliMenu cliMenu = new CliMenu();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCliMenu() {
        Scanner scanner = new Scanner(System.in);
        cliMenu.exec(scanner);
    }
}