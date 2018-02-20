package com.norrey.puzzle.game.manager;

import com.norrey.puzzle.game.menu.item.*;
import com.norrey.puzzle.io.GameIO;
import com.norrey.puzzle.util.Resource;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 7:28:26 AM
 */
public class MainMenuManager {

    private final GameIO gameIO;

    public MainMenuManager(final GameIO gameIO) {
        this.gameIO = requireNonNull(gameIO, "The gameIO must not be null");
    }

    public MainMenuItem readUserChoice() {

        gameIO.print(Resource.MESSAGES.getString("CHOOSE_OPTIONS"));
        for (MainMenuItem item : MainMenuItem.values) {
            gameIO.print(item.getKeyBinding() + ". " + item.getDescription());
        }
        return tryReadingFromUserInput();
    }

    public MainMenuItem selectMenuItem() {
        final MainMenuItem menuItem = tryReadingFromUserInput();
        return menuItem;
    }

    protected MainMenuItem tryReadingFromUserInput() {
        final Optional<MainMenuItem> chosenItemOptional = MainMenuItem.fromString(gameIO.readInputWithoutPrompt());
        if (!chosenItemOptional.isPresent()) {
            gameIO.printWithSpace(Resource.MESSAGES.getString("ERROR_INVALID_INPUT"));
            return readUserChoice();
        }
        return chosenItemOptional.get();
    }

}
