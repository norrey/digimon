package com.norrey.puzzle.game.provider.state;

import com.norrey.puzzle.game.context.DIContext;
import com.norrey.puzzle.game.provider.GameStateProvider;
import com.norrey.puzzle.game.provider.level.CharacterConfiguration;
import com.norrey.puzzle.game.provider.level.GameLevelConfigurationProviderImpl;
import com.norrey.puzzle.game.provider.level.LevelConfiguration;
import com.norrey.puzzle.map.GameMap;
import com.norrey.puzzle.model.Player;
import java.io.IOException;
import java.util.Optional;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 20, 2018, 11:45:10 AM
 */
public class GameStateProviderTest {

    private final GameStateProvider gameStateProvider = DIContext.getBean(GameStateProvider.class);

    @BeforeClass
    public void setup() {
        gameStateProvider.deleteGameIfExists();
    }

    @Test
    public void testReadEmptGameState() throws IOException, ClassNotFoundException {
        final Optional<GameState> readGameState = gameStateProvider.loadGameState();
        Assert.assertFalse(readGameState.isPresent());
    }

    @Test(dependsOnMethods = "testReadEmptGameState")
    public void testWriteAndReadGameStater() throws IOException, ClassNotFoundException {
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

        gameStateProvider.saveGameState(gameState);

        final Optional<GameState> readGameState = gameStateProvider.loadGameState();
        Assert.assertTrue(readGameState.isPresent());
        Assert.assertEquals(readGameState.get().getPlayer().getName(), player.getName());

    }

    @AfterClass
    public void tearDown() {
        gameStateProvider.deleteGameIfExists();
    }

}
