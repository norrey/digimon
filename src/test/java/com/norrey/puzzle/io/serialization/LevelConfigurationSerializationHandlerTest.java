package com.norrey.puzzle.io.serialization;

import com.norrey.puzzle.game.provider.level.CharacterConfiguration;
import com.norrey.puzzle.game.provider.level.GameLevelConfigurationProviderImpl;
import com.norrey.puzzle.game.provider.level.LevelConfiguration;
import com.norrey.puzzle.model.Player;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class LevelConfigurationSerializationHandlerTest {

    private static final Path TEST_PATH = Paths.get("level_config_test");

    private LevelConfigurationSerializationHandler levelSerializationHandler;

    @BeforeClass
    public void setup() {
        levelSerializationHandler = new LevelConfigurationSerializationHandler();
    }

    @Test
    public void testReadNonExistingConfiguration() throws IOException, ClassNotFoundException {
        final Optional<LevelConfiguration> readConfig = levelSerializationHandler.readObject(TEST_PATH);
        Assert.assertFalse(readConfig.isPresent());
    }

    @Test(dependsOnMethods = "testReadNonExistingConfiguration")
    public void testWriteAndReadPlayer() throws IOException, ClassNotFoundException {
        final Player player = new Player();
        player.setName("test_player");

        final GameLevelConfigurationProviderImpl gameLevelConfigurationProvider = new GameLevelConfigurationProviderImpl();
        final CharacterConfiguration characterConfiguration = gameLevelConfigurationProvider
                .buildCharacterConfiguration(gameLevelConfigurationProvider.initializeBitmines(),
                                             gameLevelConfigurationProvider.initializeBlackBoxes(),
                                             gameLevelConfigurationProvider.initializeLevelEnemies(),
                                             gameLevelConfigurationProvider.initializeLevelFriends());

        final LevelConfiguration levelConfiguration = gameLevelConfigurationProvider.buildLeveConfiguration(characterConfiguration, 20, "test_level");
        levelSerializationHandler.writeObject(TEST_PATH, levelConfiguration);

        final Optional<LevelConfiguration> readLevelConfiguration = levelSerializationHandler.readObject(TEST_PATH);
        Assert.assertTrue(readLevelConfiguration.isPresent());
        Assert.assertEquals(readLevelConfiguration.get().getMapSize(), 20);

    }

    @AfterClass
    public void tearDown() {
        levelSerializationHandler.deleteIfExists(TEST_PATH);
    }

}
