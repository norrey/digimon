package com.norrey.puzzle.game.provider.player;

import com.norrey.puzzle.game.provider.PlayerProvider;
import com.norrey.puzzle.io.serialization.PlayerSerializationHandler;
import com.norrey.puzzle.model.Player;
import com.norrey.puzzle.util.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 1:18:09 PM
 */
public class PlayerProviderImpl implements PlayerProvider {

    private static final Path PATH = Paths.get(String.format("%s%s%s",
                                                             Resource.ENV.getString("WORKING_DIRECTORY"),
                                                             File.separator,
                                                             Resource.ENV.getString("PLAYER_FILE")));
    private final PlayerSerializationHandler playerSerializationHandler;

    public PlayerProviderImpl(final PlayerSerializationHandler playerSerializationHandler) {
        this.playerSerializationHandler = requireNonNull(playerSerializationHandler, "The playerSerializationHandler serialization handler must not be null.");
    }

    /**
     * We are only saving one player here
     *
     * @return
     */
    @Override
    public Optional<Player> loadPlayer() {
        try {
            return playerSerializationHandler.readObject(PATH);
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException("Could not load any saved games.", ex);
        }
    }

    @Override
    public void savePlayer(final Player player) {
        try {
            playerSerializationHandler.writeObject(PATH, player);
        } catch (final IOException ex) {
            throw new RuntimeException("Error occured while saving player.", ex);
        }
    }

    @Override
    public boolean deleteIfExists() {
        return playerSerializationHandler.deleteIfExists(PATH);
    }

}
