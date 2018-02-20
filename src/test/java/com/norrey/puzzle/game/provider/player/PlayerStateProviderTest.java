package com.norrey.puzzle.game.provider.player;

import com.norrey.puzzle.game.context.DIContext;
import com.norrey.puzzle.game.provider.PlayerProvider;
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
public class PlayerStateProviderTest {

    private final PlayerProvider playerProvider = DIContext.getBean(PlayerProvider.class);

    @BeforeClass
    public void setup() {
        playerProvider.deleteIfExists();
    }

    @Test
    public void testReadEmptyPlayer() throws IOException, ClassNotFoundException {
        final Optional<Player> readPlayer = playerProvider.loadPlayer();
        Assert.assertFalse(readPlayer.isPresent());
    }

    @Test(dependsOnMethods = "testReadEmptyPlayer")
    public void testWriteAndReadPlayer() throws IOException, ClassNotFoundException {
        final Player player = new Player();
        player.setName("test_player");

        playerProvider.savePlayer(player);

        final Optional<Player> readPlayer = playerProvider.loadPlayer();
        Assert.assertTrue(readPlayer.isPresent());
        Assert.assertEquals(readPlayer.get().getName(), player.getName());

    }

    @AfterClass
    public void tearDown() {
        playerProvider.deleteIfExists();
    }

}
