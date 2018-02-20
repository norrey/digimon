package com.norrey.puzzle.game.provider.state;

import com.norrey.puzzle.map.GameMap;
import com.norrey.puzzle.model.Player;
import java.io.Serializable;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 1:04:19 PM
 */
public class GameState implements Serializable {

    private final Player player;
    private final GameMap gameMap;

    public GameState(final Player player, final GameMap gameMap) {
        this.player = player;
        this.gameMap = gameMap;
    }

    public Player getPlayer() {
        return player;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

}
