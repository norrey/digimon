package com.norrey.puzzle.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 20, 2018, 10:28:56 AM
 */
public class GameIOTest {

    private final ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
    private ByteArrayInputStream inSpy;
    private GameIO gameIO;

    @BeforeClass
    public void setup() {
        inSpy = new ByteArrayInputStream("1".getBytes());
        System.setIn(inSpy);

        System.setOut(new PrintStream(outSpy));
        gameIO = new GameIO(System.out, System.in);

    }

    @AfterClass
    public void tearDown() {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    public void testPrintsTheCorrectMessage() {
        gameIO.print("test");
        Assert.assertEquals("test\n", outSpy.toString());
    }

    @Test(dependsOnMethods = "testPrintsTheCorrectMessage")
    public void testCorrectInputFromCLI() {

        final String output = gameIO.promptAndReadUserInput("Please choose from the options below");
        Assert.assertEquals(output, "1");
    }

}
