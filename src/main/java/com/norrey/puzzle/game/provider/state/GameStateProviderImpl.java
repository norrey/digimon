package com.norrey.puzzle.game.provider.state;

import com.norrey.puzzle.game.provider.GameStateProvider;
import com.norrey.puzzle.io.serialization.GameStateSerializationHandler;
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
public class GameStateProviderImpl implements GameStateProvider {

    private static final Path PATH = Paths.get(String.format("%s%s%s",
                                                             Resource.ENV.getString("WORKING_DIRECTORY"),
                                                             File.separator,
                                                             Resource.ENV.getString("SAVED_GAME_FILE")));
    private final GameStateSerializationHandler gameStateSerializationHandler;

    public GameStateProviderImpl(final GameStateSerializationHandler gameStateSerializationHandler) {
        this.gameStateSerializationHandler = requireNonNull(gameStateSerializationHandler, "The game state serialization handler must not be null.");
    }

    /**
     * we are only going to save one game for now and overwrite if one exists
     *
     * @param gameState
     */
    @Override
    public void saveGameState(final GameState gameState) {
        requireNonNull(gameState, "The game state must not be null");
        try {
            gameStateSerializationHandler.writeObject(PATH, gameState);
        } catch (final IOException ex) {
            throw new RuntimeException("Error occured while saving game.", ex);
        }
    }

    @Override
    public Optional<GameState> loadGameState() {
        try {
            return gameStateSerializationHandler.readObject(PATH);
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException("Could not load any saved games.", ex);
        }
    }

    @Override
    public boolean deleteGameIfExists() {
        return gameStateSerializationHandler.deleteIfExists(PATH);
    }

}
