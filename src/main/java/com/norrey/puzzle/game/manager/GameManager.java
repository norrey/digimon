package com.norrey.puzzle.game.manager;

import com.norrey.puzzle.game.provider.GameLevelConfigurationProvider;
import com.norrey.puzzle.game.provider.level.LevelConfiguration;
import com.norrey.puzzle.game.provider.GameStateProvider;
import com.norrey.puzzle.game.provider.PlayerProvider;
import com.norrey.puzzle.game.provider.state.GameState;
import com.norrey.puzzle.io.GameIO;
import com.norrey.puzzle.map.GameMap;
import com.norrey.puzzle.model.Player;
import com.norrey.puzzle.model.PlayerInfoBuilder;
import com.norrey.puzzle.util.Resource;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 16, 2018, 5:25:59 PM
 */
public class GameManager {

    private final GameIO gameIO;
    private final GameLevelConfigurationProvider gameLevelConfigurationProvider;
    private final GameStateProvider gameStateProvider;
    private final PlayerProvider playerProvider;

    public GameManager(final GameIO reader,
                       final GameLevelConfigurationProvider gameLevelConfigurationProvider,
                       final GameStateProvider gameStateProvider,
                       final PlayerProvider playerProvider) {
        this.gameIO = requireNonNull(reader, "The reader must not be null");
        this.gameLevelConfigurationProvider = requireNonNull(gameLevelConfigurationProvider,
                                                             "The gameLevelConfigurationProvider must not be null");
        this.gameStateProvider = requireNonNull(gameStateProvider);
        this.playerProvider = requireNonNull(playerProvider);
    }

    private GameMap initializeMap() {
        final Optional<LevelConfiguration> levelConfiguration = gameLevelConfigurationProvider.loadConfiguration("level1");
        if (!levelConfiguration.isPresent()) {
            throw new RuntimeException("Could not load level configuration");
        }
        return new GameMap(levelConfiguration.get());
    }

    private Player initializePlayer(final GameMap map) {
        requireNonNull(map, "The map must not be null.");
        return new PlayerInfoBuilder(gameIO, map, playerProvider).loadPlayer();

    }

    private void introducePlayer(final Player player) {
        gameIO.printWithSpace(String.format(Resource.MESSAGES.getString("PLAYER_INTRODUCTION"), player.getName()));
    }

    public void startNewGame() {
        final GameMap map = initializeMap();
        final Player player = initializePlayer(map);
        final NavigationManager navigationManager = new NavigationManager(gameIO, map, player);
        initializeNavigator(navigationManager, player);
    }

   
    public void startSavedGame() {
        final Optional<GameState> gameStateOptional = gameStateProvider.loadGameState();
        if (!gameStateOptional.isPresent()) {
            gameIO.printWithSpace(Resource.MESSAGES.getString("NO_SAVED_GAMES"));
            return;
        }

        final NavigationManager navigationManager = new NavigationManager(gameIO,
                                                                          gameStateOptional.get().getGameMap(),
                                                                          gameStateOptional.get().getPlayer());
        initializeNavigator(navigationManager, gameStateOptional.get().getPlayer());

    }

    private void initializeNavigator(final NavigationManager navigationManager, final Player player) {
        requireNonNull(navigationManager, "The navigation manager must not be null");
        requireNonNull(player, "the player must not be null.");

        introducePlayer(player);
        navigationManager.showMap();
        navigationManager.navigate();
    }

}
