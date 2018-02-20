package com.norrey.puzzle.game.navigation.impl;

import com.norrey.puzzle.game.menu.item.NavigationMenuItem;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 9:38:51 PM
 */
public class CommandsNavigator extends InformationalNavigator {

    @Override
    public void navigate() {
        printMenuOptions();
    }

    private void printMenuOptions() {
        GAME_IO.print("Choose from the options below:");
        for (NavigationMenuItem item : NavigationMenuItem.values) {
            GAME_IO.print(item.getKeyBinding() + ". " + item.getDescription());
        }
    }

}
