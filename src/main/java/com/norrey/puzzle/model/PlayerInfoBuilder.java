package com.norrey.puzzle.model;

import com.norrey.puzzle.game.provider.PlayerProvider;
import com.norrey.puzzle.io.GameIO;
import com.norrey.puzzle.map.GameMap;
import com.norrey.puzzle.util.Resource;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 10:10:27 AM
 */
public class PlayerInfoBuilder {

    private final GameIO gameIO;
    private final GameMap map;
    private final PlayerProvider playerProvider;

    public PlayerInfoBuilder(final GameIO reader, final GameMap map, final PlayerProvider playerProvider) {
        this.gameIO = requireNonNull(reader, "The reader must not be null");
        this.map = requireNonNull(map, "The map must not be null");
        this.playerProvider = requireNonNull(playerProvider, "The player provider must not be null");

    }

    public Player loadPlayer() {
        final Optional<Player> player = playerProvider.loadPlayer();
        if (player.isPresent()) {
            return player.get();
        }
        return requestPlayerInfo();
    }

    private void saveNewPlayer(final Player player) {
        playerProvider.savePlayer(player);
    }

    public Player requestPlayerInfo() {

        final Player player = new Player();
        player.setName(requestStringInfo(Resource.MESSAGES.getString("PLAYER_QUESTIONS_NAME")));
        player.setDescription(requestStringInfo(Resource.MESSAGES.getString("PLAYER_QUESTIONS_DESCRIPTION")));
        player.setExperience(buildInitialExperience());
        player.setCoordinate(map.randomEmptyCoordinate());
        player.setAlive(true);

        saveNewPlayer(player);

        return player;
    }

    /*
     * TODO this should go into configuration
     */
    private Experience buildInitialExperience() {
        final Experience experience = new Experience();

        experience.setMoney(5000);
        experience.setAiKnowledge(5);
        experience.setBitcoins(1);
        experience.setEnergy(100);
        experience.setLevel(1);
        experience.setDecryptEnergyDivideThreshold(50);

        return experience;
    }

    private String requestStringInfo(final String question) {
        requireNonNull(question, "The quest must not be null");
        return gameIO.promptAndReadUserInput(question);
    }

}
