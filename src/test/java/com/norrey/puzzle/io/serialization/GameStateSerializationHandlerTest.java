package com.norrey.puzzle.io.serialization;

import com.norrey.puzzle.game.provider.level.CharacterConfiguration;
import com.norrey.puzzle.game.provider.level.GameLevelConfigurationProviderImpl;
import com.norrey.puzzle.game.provider.level.LevelConfiguration;
import com.norrey.puzzle.game.provider.state.GameState;
import com.norrey.puzzle.map.GameMap;
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
public class GameStateSerializationHandlerTest {

    private static final Path TEST_PATH = Paths.get("games_tate_test");

    private GameStateSerializationHandler gameStateSerializationHandler;

    @BeforeClass
    public void setup() {
        gameStateSerializationHandler = new GameStateSerializationHandler();
    }

    @Test
    public void testReadEmptGameState() throws IOException, ClassNotFoundException {
        final Optional<GameState> readGameState = gameStateSerializationHandler.readObject(TEST_PATH);
        Assert.assertFalse(readGameState.isPresent());
    }

    @Test(dependsOnMethods = "testReadEmptGameState")
    public void testWriteAndReadGameState() throws IOException, ClassNotFoundException {
        final Player player = new Player();
        player.setName("test_player");

        final GameLevelConfigurationProviderImpl gameLevelConfigurationProvider = new GameLevelConfigurationProviderImpl();
        final CharacterConfiguration characterConfiguration = gameLevelConfigurationProvider
                .buildCharacterConfiguration(gameLevelConfigurationProvider.initializeBitmines(),
                                             gameLevelConfigurationProvider.initializeBlackBoxes(),
                                             gameLevelConfigurationProvider.initializeLevelEnemies(),
                                             gameLevelConfigurationProvider.initializeLevelFriends());

        final LevelConfiguration levelConfiguration = gameLevelConfigurationProvider.buildLeveConfiguration(characterConfiguration, 20, "test_level");

        final GameState gameState = new GameState(player, new GameMap(levelConfiguration));

        gameStateSerializationHandler.writeObject(TEST_PATH, gameState);

        final Optional<GameState> readGameState = gameStateSerializationHandler.readObject(TEST_PATH);
        Assert.assertTrue(readGameState.isPresent());
        Assert.assertEquals(readGameState.get().getPlayer().getName(), player.getName());

    }

    @AfterClass
    public void tearDown() {
        gameStateSerializationHandler.deleteIfExists(TEST_PATH);
    }

}
