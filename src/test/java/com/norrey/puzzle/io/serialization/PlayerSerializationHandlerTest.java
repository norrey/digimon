package com.norrey.puzzle.io.serialization;

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
public class PlayerSerializationHandlerTest {

    private static final Path TEST_PATH = Paths.get("player_test");

    private ObjectSerializationHandler<Player> playerHandler;

    @BeforeClass
    public void setup() {
        playerHandler = new PlayerSerializationHandler();
    }

    @Test
    public void testReadNonExistingPlayer() throws IOException, ClassNotFoundException {
        final Optional<Player> readPlayer = playerHandler.readObject(TEST_PATH);
        Assert.assertFalse(readPlayer.isPresent());
    }

    @Test(dependsOnMethods = "testReadNonExistingPlayer")
    public void testWriteAndReadPlayer() throws IOException, ClassNotFoundException {
        final Player player = new Player();
        player.setName("test_player");
        playerHandler.writeObject(TEST_PATH, player);

        final Optional<Player> readPlayer = playerHandler.readObject(TEST_PATH);
        Assert.assertTrue(readPlayer.isPresent());
        Assert.assertEquals(readPlayer.get().getName(), player.getName());

    }

    @AfterClass
    public void tearDown() {
        playerHandler.deleteIfExists(TEST_PATH);
    }

}
