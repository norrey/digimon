package com.norrey.puzzle.game.provider;

import com.norrey.puzzle.model.Player;
import java.util.Optional;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 3:05:31 PM
 */
public interface PlayerProvider {

    Optional<Player> loadPlayer();

    void savePlayer(final Player player);

    boolean deleteIfExists();

}
