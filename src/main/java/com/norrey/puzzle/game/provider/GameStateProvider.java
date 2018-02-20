package com.norrey.puzzle.game.provider;

import com.norrey.puzzle.game.provider.state.GameState;
import java.util.Optional;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 1:15:49 PM
 */
public interface GameStateProvider {

    void saveGameState(final GameState gameState);

    Optional<GameState> loadGameState();

    boolean deleteGameIfExists();

}
