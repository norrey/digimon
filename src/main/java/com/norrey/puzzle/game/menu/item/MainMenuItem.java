package com.norrey.puzzle.game.menu.item;

import java.util.Optional;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 7:31:34 AM
 */
public enum MainMenuItem {

    START_NEW("1", "Start new game"),
    LOAD_SAVED_GAME("2", "Load saved game"),
    EXIT("4", "Exit");

    private final String keyBinding;
    private final String description;

    public static final MainMenuItem values[] = values();

    MainMenuItem(final String keyBinding, final String description) {
        this.keyBinding = keyBinding;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getKeyBinding() {
        return keyBinding;
    }

    public static Optional<MainMenuItem> fromString(final String input) {
        if (input == null) {
            return Optional.empty();
        }

        for (MainMenuItem item : MainMenuItem.values) {
            if (item.getKeyBinding().equalsIgnoreCase(input)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

}
