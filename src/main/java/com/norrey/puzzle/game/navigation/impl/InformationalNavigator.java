package com.norrey.puzzle.game.navigation.impl;

import com.norrey.puzzle.game.navigation.Navigator;
import com.norrey.puzzle.map.GameMap;
import com.norrey.puzzle.model.Player;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 20, 2018, 6:50:29 AM
 */
public abstract class InformationalNavigator implements Navigator {

    @Override
    public void navigate(final Player player, final GameMap gameMap) {
        this.navigate();
    }

    public abstract void navigate();

}
