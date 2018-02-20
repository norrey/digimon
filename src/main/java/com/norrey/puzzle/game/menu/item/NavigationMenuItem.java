package com.norrey.puzzle.game.menu.item;

import java.util.Optional;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 11:36:50 AM
 */
public enum NavigationMenuItem {

    UP("W", "North"),
    DOWN("S", "South"),
    LEFT("A", "West"),
    RIGHT("D", "East"),
    COMMANDS("1", "Show his menu"),
    MAP("2", "Show map"),
    LEGEND("3", "Show legend"),
    PLAYER("4", "Show player info"),
    SAVE("5", "Save the game"),
    EXIT("0", "Exit to main menu");

    private final String keyBinding;
    private final String description;

    NavigationMenuItem(String keyBinding, String description) {
        this.keyBinding = keyBinding;
        this.description = description;
    }

    public String getKeyBinding() {
        return keyBinding;
    }

    public String getDescription() {
        return description;
    }

    public static final NavigationMenuItem values[] = values();

    public static Optional<NavigationMenuItem> fromString(final String input) {
        if (input == null) {
            return Optional.empty();
        }

        for (NavigationMenuItem item : NavigationMenuItem.values) {
            if (item.getKeyBinding().equalsIgnoreCase(input)) {
                return Optional.of(item);
            }
        }

        return Optional.empty();

    }
}
