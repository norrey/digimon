package com.norrey.puzzle.game.provider.level;

import com.norrey.puzzle.game.context.DIContext;
import com.norrey.puzzle.game.provider.GameLevelConfigurationProvider;
import com.norrey.puzzle.game.provider.level.CharacterConfiguration.CharacterConfigurationBuilder;
import com.norrey.puzzle.game.provider.level.LevelConfiguration.LevelConfigurationBuilder;
import com.norrey.puzzle.io.serialization.LevelConfigurationSerializationHandler;
import com.norrey.puzzle.model.BitMine;
import com.norrey.puzzle.model.BlackBox;
import com.norrey.puzzle.model.EnemyRobot;
import com.norrey.puzzle.model.FriendRobot;
import com.norrey.puzzle.model.Robot;
import com.norrey.puzzle.util.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 2:13:23 AM
 */
public class GameLevelConfigurationProviderImpl implements GameLevelConfigurationProvider {

    private static final LevelConfigurationSerializationHandler serializationHandler
                                                                = DIContext.getBean(LevelConfigurationSerializationHandler.class);

    public GameLevelConfigurationProviderImpl() {
    }

    public static void main(String... args) {

        GameLevelConfigurationProviderImpl gameLevelConfigurationProvider
                                           = new GameLevelConfigurationProviderImpl();
        gameLevelConfigurationProvider.saveConfiguration();

    }

    public CharacterConfiguration buildCharacterConfiguration(final List<BitMine> bitMines,
                                                              final List<BlackBox> blackBoxes,
                                                              final List<Robot> enemies,
                                                              final List<Robot> friends) {
        return new CharacterConfigurationBuilder()
                .withBitmines(bitMines)
                .withBlackboxes(blackBoxes)
                .withEnemies(enemies)
                .withFriends(friends)
                .build();

    }

    public LevelConfiguration buildLeveConfiguration(final CharacterConfiguration characterConfiguration,
                                                     final int mapSize,
                                                     final String name) {
        return new LevelConfigurationBuilder()
                .withCharacterConfiguration(characterConfiguration)
                .withMapSize(mapSize)
                .withName(name)
                .build();
    }

    @Override
    public void saveConfiguration() {

        CharacterConfiguration characterConfiguration = new CharacterConfigurationBuilder()
                .withBitmines(initializeBitmines())
                .withBlackboxes(initializeBlackBoxes())
                .withEnemies(initializeLevelEnemies())
                .withFriends(initializeLevelFriends())
                .build();

        LevelConfiguration configuration = new LevelConfigurationBuilder()
                .withCharacterConfiguration(characterConfiguration)
                .withMapSize(30)
                .withName("level1")
                .build();

        try {
            final Path levelConfigurationPath = Paths.get(Resource.ENV.getString("LEVEL_CONFIG_DIR_PATH"), configuration.getName());
            serializationHandler.writeObject(levelConfigurationPath, configuration);

        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public Optional<LevelConfiguration> loadConfiguration(final String level) {
        try {

            Optional<LevelConfiguration> theSaved = serializationHandler.readObject(String.format("%s%s%s",
                                                                                                  Resource.ENV.getString("LEVEL_CONFIG_DIR"), File.separator, level));
            return theSaved;
        } catch (final IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Robot> initializeLevelFriends() {
        final Robot robot = new FriendRobot();
        robot.setName("Sophia");
        robot.setDescription("The South Korean national who is a friend of the people and would like to do good stuff for the humans.");

        robot.setBitcoinsDamageValue(1);
        robot.setCryptoKeys(Arrays.asList("1234"));
        robot.setEnergyDamageFactor(1);
        robot.setIntroduction("Hello friend. I know what you are looking for. I have " + robot.getCryptoKeys().size() + "keys. Just give me a bitcoin and rush to unlock the Abrahams before they evolve.");
        robot.setEnergyLevel(100);

        robot.setAlive(true);

        return Arrays.asList(robot);
    }

    public List<Robot> initializeLevelEnemies() {
        final Robot robot = new EnemyRobot();
        robot.setName("Abraham");
        robot.setDescription("Named after the director of Bad Robots. Abraham himself is a good guy. This robot is a real good bit conner");

        robot.setBitcoinsDamageValue(2);
        robot.setCryptoKeys(Arrays.asList("1234"));
        robot.setEnergyDamageFactor(10);
        robot.setEnergyLevel(100);
        robot.setIntroduction("I can see you have some bitcoins on your network. That is my food. Let me crack your security first.");

        robot.setAlive(true);

        return Arrays.asList(robot);
    }

    public List<BitMine> initializeBitmines() {
        final BitMine mine = new BitMine();
        mine.setName("Bit Mine 1");
        mine.setDescription("Mine all your bitcoins here, if you have cash, well and good. If you have the knowldge and power. Great!");

        mine.setBitcoinGain(1);
        mine.setEnergyDamageFactor(5);
        mine.setAvailableBitcoins(20);
        mine.setIntroduction("Welcome to the mine. Get all your bit coins here.");

        mine.setAlive(true);

        return Arrays.asList(mine);
    }

    public List<BlackBox> initializeBlackBoxes() {
        final BlackBox blackbox = new BlackBox();
        blackbox.setName("BlackBox 1");
        blackbox.setDescription("Your knowledge center, AI, IOT, Blockchain");

        blackbox.setAiLevelGain(1);
        blackbox.setCostOfLearning(1000);
        blackbox.setIntroduction("Welcome to our learning centre. Learn AI, ML, IOT.");
        blackbox.setEnergyDamageFactor(10);
        blackbox.setAvailableAi(6);

        blackbox.setAlive(true);

        return Arrays.asList(blackbox);
    }

    @Override
    public boolean deleteIfExists(final String level) {
        final Path levelConfigurationPath = Paths.get(Resource.ENV.getString("LEVEL_CONFIG_DIR_PATH"), level);
        return serializationHandler.deleteIfExists(levelConfigurationPath);
    }

}
