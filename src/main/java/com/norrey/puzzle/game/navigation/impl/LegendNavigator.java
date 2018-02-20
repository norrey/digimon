package com.norrey.puzzle.game.navigation.impl;

import com.norrey.puzzle.map.LocationType;

import static com.norrey.puzzle.game.navigation.Navigator.GAME_IO;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 9:38:51 PM
 */
public class LegendNavigator extends InformationalNavigator {

    @Override
    public void navigate() {
        displayMapLegend();
    }

    /*
     * TODO format this like a gentleman
     */
    private void displayMapLegend() {
        GAME_IO.print("");
        for (LocationType value : LocationType.values) {
            GAME_IO.print(value.getMapMark() + " => " + value.getDescription());
        }
        GAME_IO.print("");
    }

}
