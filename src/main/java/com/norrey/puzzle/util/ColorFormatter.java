package com.norrey.puzzle.util;

import static com.norrey.puzzle.util.Color.BLUE;
import static com.norrey.puzzle.util.Color.BOLD;
import static com.norrey.puzzle.util.Color.CYAN;
import static com.norrey.puzzle.util.Color.GREEN;
import static com.norrey.puzzle.util.Color.MAGENTA;
import static com.norrey.puzzle.util.Color.RED;
import static com.norrey.puzzle.util.Color.UNDERLINE;
import static com.norrey.puzzle.util.Color.YELLOW;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 */
public class ColorFormatter {

    public static String red(final String input) {
        return RED.format(input);
    }

    public static String blue(final String input) {
        return BLUE.format(input);
    }

    public static String green(final String input) {
        return GREEN.format(input);
    }

    public static String yellow(final String input) {
        return YELLOW.format(input);
    }

    public static String cyan(final String input) {
        return CYAN.format(input);
    }

    public static String bold(final String input) {
        return BOLD.format(input);
    }

    public static String boldMagenta(final String input) {
        return bold(MAGENTA.format(input));
    }

    public static String underlinedBlue(final String input) {
        return UNDERLINE.format(BLUE.format(input));
    }

}
