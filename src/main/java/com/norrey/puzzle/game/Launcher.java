package com.norrey.puzzle.game;

import com.norrey.puzzle.game.context.GameContext;
import com.norrey.puzzle.game.context.DIContext;
import com.norrey.puzzle.game.menu.item.MainMenuItem;
import com.norrey.puzzle.game.manager.MainMenuManager;
import com.norrey.puzzle.io.GameIO;
import com.norrey.puzzle.util.Resource;

import static com.norrey.puzzle.game.menu.item.MainMenuItem.EXIT;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 8:04:46 AM
 */
public class Launcher {

    private final static MainMenuManager MENU_MANAGER = DIContext.getBean(MainMenuManager.class);
    private final static GameIO GAME_IO = DIContext.getBean(GameIO.class);

    public static void startGame() {
        introduceGame();
        MainMenuItem choice;
        do {
            choice = MENU_MANAGER.readUserChoice();
            GameContext.play(choice);
        } while (choice != EXIT);
    }

    private static void introduceGame() {
        GAME_IO.printWithSpace(Resource.MESSAGES.getString("GAME_INTRODUCTION"));
    }

}
