package com.norrey.puzzle.game.provider.level;

import com.norrey.puzzle.game.context.DIContext;
import java.io.IOException;
import java.util.Optional;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 20, 2018, 9:36:16 AM
 */
public class GameLevelConfigurationProviderTest {

    private static final String TEST_PATH = "level1";

    private final GameLevelConfigurationProviderImpl gameLevelConfigurationProvider = DIContext.getBean(GameLevelConfigurationProviderImpl.class);

    @BeforeClass
    public void setup() {
    }

    @Test
    public void testWriteAndReadConfiguration() throws IOException, ClassNotFoundException {

        // Just save the sample configuration
        gameLevelConfigurationProvider.saveConfiguration();

        final Optional<LevelConfiguration> readLevelConfiguration = gameLevelConfigurationProvider.loadConfiguration(TEST_PATH);
        Assert.assertTrue(readLevelConfiguration.isPresent());
        Assert.assertEquals(readLevelConfiguration.get().getMapSize(), 30);

    }

    @AfterClass
    public void tearDown() {
        // We are gonna use this same file (; this should never happen
        //gameLevelConfigurationProvider.deleteIfExists(TEST_PATH);
    }

}
