package com.norrey.puzzle.game.menu.item;

import java.util.Optional;

import static com.norrey.puzzle.util.ColorFormatter.green;
import static com.norrey.puzzle.util.ColorFormatter.yellow;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 18, 2018, 11:13:24 AM
 */
public enum MineMenuItem {

    MINE("1", green("Mine Bitcoins") + "The good and bad robots survive on bitcoins"),
    IGNORE("2", yellow("Not now") + ", come back any time you feel you need them");

    private final String description;
    private final String keyBinding;

    public static final MineMenuItem values[] = values();

    MineMenuItem(final String keyBinding, final String description) {
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

    public static Optional<MineMenuItem> fromString(String input) {
        if (input == null) {
            return Optional.empty();
        }

        for (MineMenuItem item : MineMenuItem.values) {
            if (item.getKeyBinding().equalsIgnoreCase(input)) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

}
