package com.norrey.puzzle.map;

import com.norrey.puzzle.game.context.DIContext;
import com.norrey.puzzle.game.provider.level.GameLevelConfigurationProviderImpl;
import com.norrey.puzzle.game.provider.level.LevelConfiguration;
import com.norrey.puzzle.io.exception.InvalidInputException;
import java.util.Optional;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 23, 2018, 9:25:13 AM
 */
public class GameMapTest {

    private final static String GAME_LEVEL = "level1";
    private final GameLevelConfigurationProviderImpl gameLevelConfigurationProvider = DIContext.getBean(GameLevelConfigurationProviderImpl.class);
    private GameMap map;

    @BeforeClass
    public void setup() {
        final Optional<LevelConfiguration> levelConfiguration = gameLevelConfigurationProvider.loadConfiguration(GAME_LEVEL);
        levelConfiguration.ifPresent(levelConfig -> {
            map = new GameMap(levelConfig);
        });
    }

    @AfterClass
    public void tearDown() {
    }

    @Test
    public void testBitMinesInitializedSuccessfully() {
        Assert.assertEquals(map.getBitmines().size(), 1);
    }

    @Test
    public void testBlackBoxesInitializedSuccessfully() {
        Assert.assertEquals(map.getBlackboxes().size(), 1);
    }

    @Test
    public void testEnemiesInitializedSuccessfully() {
        Assert.assertEquals(map.getEnemies().size(), 1);
    }

    @Test
    public void testFriendsInitializedSuccessfully() {
        Assert.assertEquals(map.getFriends().size(), 1);
    }

    @Test(expectedExceptions = InvalidInputException.class)
    public void testInvalidCoordinates() {
        map.getLocation(50, 50);
    }

    @Test
    public void testWeCanGetRandomEmptyCoordinate() {
        final Coordinate coordinate = map.randomEmptyCoordinate();
        Assert.assertNotNull(coordinate);
    }

}
