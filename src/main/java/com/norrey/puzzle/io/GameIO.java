package com.norrey.puzzle.io;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 16, 2018, 2:46:59 PM
 */
public class GameIO {

    private final Scanner scanner;
    private final PrintStream out;

    public GameIO(final PrintStream out, final InputStream inputStream) {
        requireNonNull(out, "The printstream must not be null");
        requireNonNull(inputStream, "The input stream must not be null");

        this.scanner = new Scanner(inputStream);
        this.out = out;
    }

    public String promptAndReadUserInput(final String message) {
        out.println(message);
        return scanner.nextLine();
    }

    public String readInputWithoutPrompt() {
        return scanner.nextLine();
    }

    public void print(final String message) {
        requireNonNull(message, "The message must not be null");
        out.println(message);
    }

    public void printWithSpace(final String message) {
        requireNonNull(message, "The message must not be null");
        out.println();
        out.println(message);
        out.println();
    }

}
