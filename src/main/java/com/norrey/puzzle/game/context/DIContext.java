package com.norrey.puzzle.game.context;

import com.norrey.puzzle.game.manager.ExperienceManager;
import com.norrey.puzzle.game.manager.GameManager;
import com.norrey.puzzle.game.provider.GameLevelConfigurationProvider;
import com.norrey.puzzle.game.provider.level.GameLevelConfigurationProviderImpl;
import com.norrey.puzzle.io.GameIO;
import com.norrey.puzzle.game.manager.MainMenuManager;
import com.norrey.puzzle.game.provider.GameStateProvider;
import com.norrey.puzzle.game.provider.PlayerProvider;
import com.norrey.puzzle.game.provider.player.PlayerProviderImpl;
import com.norrey.puzzle.game.provider.state.GameStateProviderImpl;
import com.norrey.puzzle.io.serialization.GameStateSerializationHandler;
import com.norrey.puzzle.io.serialization.LevelConfigurationSerializationHandler;
import com.norrey.puzzle.io.serialization.PlayerSerializationHandler;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 1:55:06 AM
 */
public class DIContext {

    private static final ConcurrentMap<Class, Object> CONTEXT = new ConcurrentHashMap<>();

    static {
        initialializeGameIO();
        initializeLevelConfigurationProvider();
        initializeMenus();
        initializeManagers();
    }

    /**
     * Nobody should ever instantiate this directly
     */
    private DIContext() {
    }

    public static void initialializeGameIO() {
        final GameIO gameIO = new GameIO(System.out, System.in);
        loadObject(GameIO.class, gameIO);

        final GameStateSerializationHandler gameStateSerializationHandler = new GameStateSerializationHandler();
        loadObject(GameStateSerializationHandler.class, gameStateSerializationHandler);

        final LevelConfigurationSerializationHandler levelConfigurationSerializationHandler = new LevelConfigurationSerializationHandler();
        loadObject(LevelConfigurationSerializationHandler.class, levelConfigurationSerializationHandler);

        final PlayerSerializationHandler playerSerializationHandler = new PlayerSerializationHandler();
        loadObject(PlayerSerializationHandler.class, playerSerializationHandler);

    }

    private static void initializeLevelConfigurationProvider() {
        final GameLevelConfigurationProvider gameLevelConfigurationProvider = new GameLevelConfigurationProviderImpl();
        loadObject(GameLevelConfigurationProvider.class, gameLevelConfigurationProvider);

        final GameStateSerializationHandler gameStateSerializationHandler = getBean(GameStateSerializationHandler.class);
        final GameStateProvider gameStateProvider = new GameStateProviderImpl(gameStateSerializationHandler);
        loadObject(GameStateProvider.class, gameStateProvider);

        final PlayerSerializationHandler playerSerializationHandler = getBean(PlayerSerializationHandler.class);
        final PlayerProvider playerProvider = new PlayerProviderImpl(playerSerializationHandler);
        loadObject(PlayerProvider.class, playerProvider);

    }

    private static void initializeMenus() {
        final GameIO gameIO = getBean(GameIO.class);
        final MainMenuManager mainMenuManager = new MainMenuManager(gameIO);
        loadObject(MainMenuManager.class, mainMenuManager);
    }

    private static void initializeManagers() {
        final GameIO gameIO = getBean(GameIO.class);
        final GameLevelConfigurationProvider gameLevelConfigurationProvider = getBean(GameLevelConfigurationProvider.class);
        final GameStateProvider gameStateProvider = getBean(GameStateProvider.class);
        final PlayerProvider playerProvider = getBean(PlayerProvider.class);
        final GameManager gameManager = new GameManager(gameIO, gameLevelConfigurationProvider, gameStateProvider, playerProvider);
        loadObject(GameManager.class, gameManager);

        final ExperienceManager experienceManager = new ExperienceManager();
        loadObject(ExperienceManager.class, experienceManager);

    }

    private static void loadObject(final Class clazz, final Object object) {
        CONTEXT.put(clazz, object);
        CONTEXT.put(object.getClass(), object);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        requireNonNull(clazz, "The class cannot be null");

        T bean;

        try {
            bean = (T) CONTEXT.get(clazz);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }

        if (null == bean) {
            throw new RuntimeException("Bean could not be loaded");
        }

        return bean;
    }

}
