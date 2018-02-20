package com.norrey.puzzle.game.navigation.impl;

import com.norrey.puzzle.game.context.DIContext;
import com.norrey.puzzle.game.navigation.Navigator;
import com.norrey.puzzle.game.provider.GameStateProvider;
import com.norrey.puzzle.game.provider.state.GameState;
import com.norrey.puzzle.map.GameMap;
import com.norrey.puzzle.model.Player;
import com.norrey.puzzle.util.Resource;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 9:38:51 PM
 */
public class SaveNavigator implements Navigator {

    private final GameStateProvider gameStateProvider = DIContext.getBean(GameStateProvider.class);

    @Override
    public void navigate(final Player player, final GameMap gameMap) {
        requireNonNull(player, "The player must not not be null");
        requireNonNull(gameMap, "The gameMap must not not be null");
        saveGame(player, gameMap);
    }

    private void saveGame(final Player player, final GameMap gameMap) {
        gameStateProvider.saveGameState(new GameState(player, gameMap));
        GAME_IO.print(Resource.MESSAGES.getString("GAME_SAVE_SUCCESS"));
    }

}
