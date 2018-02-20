package com.norrey.puzzle.game.menu.item;

import java.util.Optional;

import static com.norrey.puzzle.util.ColorFormatter.green;
import static com.norrey.puzzle.util.ColorFormatter.yellow;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 18, 2018, 11:13:24 AM
 */
public enum LearnMenuItem {

    LEARN("1", green("Gain knowledge. Knowledge is power") + "It will be handy"),
    IGNORE("2", yellow("Not now") + ", com back any time you feel rusty");

    private final String description;
    private final String keyBinding;

    public static final LearnMenuItem values[] = values();

    LearnMenuItem(final String keyBinding, final String description) {
        this.keyBinding = keyBinding;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getKeyBinding() {
        return keyBinding;
    }

    @Override
    public String toString() {
        return description;
    }

    public static Optional<LearnMenuItem> fromString(String input) {
        if (input == null) {
            return Optional.empty();
        }

        for (LearnMenuItem item : LearnMenuItem.values) {
            if (item.getKeyBinding().equalsIgnoreCase(input)) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

}
