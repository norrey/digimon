package com.norrey.puzzle.game.menu.item;

import java.util.Optional;

import static com.norrey.puzzle.util.ColorFormatter.blue;
import static com.norrey.puzzle.util.ColorFormatter.red;
import static com.norrey.puzzle.util.ColorFormatter.yellow;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 18, 2018, 11:13:24 AM
 */
public enum AttackMenuItem {

    ATTACK("1", red("Physicaly attack the robot") + " you will still fight as long as both of you have energy"),
    DECRYPT("2", blue("Decrypt robot keys") + ", if you have the keys. The robot will try to delete your keys."),
    RUN_AWAY("3", yellow("Runa away.") + " This will save your life");

    private final String description;
    private final String keyBinding;

    public static final AttackMenuItem values[] = values();

    AttackMenuItem(final String keyBinding, final String description) {
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

    public static Optional<AttackMenuItem> fromString(String input) {
        if (input == null) {
            return Optional.empty();
        }

        for (AttackMenuItem item : AttackMenuItem.values) {
            if (item.getKeyBinding().equalsIgnoreCase(input)) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

}
