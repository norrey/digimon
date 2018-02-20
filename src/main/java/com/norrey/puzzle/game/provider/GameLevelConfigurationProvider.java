package com.norrey.puzzle.game.provider;

import com.norrey.puzzle.game.provider.level.LevelConfiguration;
import java.util.Optional;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 2:12:07 AM
 */
public interface GameLevelConfigurationProvider {

    void saveConfiguration();

    Optional<LevelConfiguration> loadConfiguration(final String level);

    boolean deleteIfExists(final String level);

}
