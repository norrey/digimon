package com.norrey.puzzle.game.navigation.impl;

import com.norrey.puzzle.game.navigation.Navigator;
import com.norrey.puzzle.map.GameMap;
import com.norrey.puzzle.model.Player;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 9:38:51 PM
 */
public class PlayerNavigator implements Navigator {

    @Override
    public void navigate(final Player player, final GameMap gameMap) {
        requireNonNull(player, "The player must not be null");
        GAME_IO.print(player.toString());
    }

}
