package com.norrey.puzzle.game.navigation;

import com.norrey.puzzle.game.context.DIContext;
import com.norrey.puzzle.game.manager.AttackManager;
import com.norrey.puzzle.game.manager.KeyExchangeManager;
import com.norrey.puzzle.game.manager.LearnManager;
import com.norrey.puzzle.game.manager.MiningManager;
import com.norrey.puzzle.io.GameIO;
import com.norrey.puzzle.io.exception.InvalidInputException;
import com.norrey.puzzle.map.Coordinate;
import com.norrey.puzzle.map.GameMap;
import com.norrey.puzzle.map.Location;
import com.norrey.puzzle.map.MapView;
import com.norrey.puzzle.model.BitMine;
import com.norrey.puzzle.model.BlackBox;
import com.norrey.puzzle.model.DormantCharacter;
import com.norrey.puzzle.model.EnemyRobot;
import com.norrey.puzzle.model.FriendRobot;
import com.norrey.puzzle.model.Player;

import static com.norrey.puzzle.util.ColorFormatter.red;
import static com.norrey.puzzle.util.ColorFormatter.yellow;
import static java.util.Objects.requireNonNull;

/**
 * TODO This interface has too many default methods, needs separation, (try strategy pattern)
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 9:34:35 PM
 */
public interface Navigator {

    static final GameIO GAME_IO = DIContext.getBean(GameIO.class);

    void navigate(final Player player, final GameMap gameMap);

    default void showMap(final Player player, final GameMap gameMap) {
        final MapView mapView = new MapView(gameMap, player);
        GAME_IO.print(mapView.mapView());
    }

    default void move(final Coordinate coordinate, final Player player, final GameMap gameMap) {
        requireNonNull(coordinate, "The coordinate must not not be null");
        requireNonNull(player, "The player must not not be null");
        requireNonNull(gameMap, "The gameMap must not not be null");
        try {
            final Location newLocation = gameMap.getLocation(coordinate);

            if (newLocation.containsCharacter()) {
                interactWithOtherCharacters(newLocation, player, gameMap);
            } else {
                moveToEmptySpace(newLocation, player);
            }
        } catch (final InvalidInputException e) {
            GAME_IO.print(e.getMessage());
        }
        showMap(player, gameMap);
    }

    default void interactWithOtherCharacters(final Location location, final Player player, final GameMap gameMap) {
        requireNonNull(location, "The location must not be null");
        requireNonNull(player, "The location must not be null");
        requireNonNull(gameMap, "The location must not be null");

        final DormantCharacter dormantCharacter = location.getDormantCharacter();
        GAME_IO.print(dormantCharacter.toString());

        if (dormantCharacter instanceof EnemyRobot) {
            fight(location, player);
            if (gameMap.hasNoRemainingEnemies()) {
                GAME_IO.print(yellow("Congratulations!!!! You won"));
                return;
            }

            if (!player.isAlive()) {
                GAME_IO.printWithSpace(red("Sorry mate you died. See you in the next generation!"));
                return;
            }
        } else if (dormantCharacter instanceof BlackBox) {
            learn(location, player);
        } else if (dormantCharacter instanceof BitMine) {
            mine(location, player);
        } else if (dormantCharacter instanceof FriendRobot) {
            exchangeKeys(location, player);
        } else {
            GAME_IO.print("Unknown");
        }
    }

    default void fight(final Location location, final Player player) {
        requireNonNull(location, "The location must not be null.");
        requireNonNull(player, "The player must not be null.");
        new AttackManager(player, location, GAME_IO).fight();
    }

    default void learn(final Location location, final Player player) {
        requireNonNull(location, "The location must not be null.");
        requireNonNull(player, "The player must not be null.");
        new LearnManager(player, location, GAME_IO).learn();
    }

    default void mine(final Location location, final Player player) {
        requireNonNull(location, "The location must not be null.");
        requireNonNull(player, "The player must not be null.");
        new MiningManager(player, location, GAME_IO).mine();
    }

    default void exchangeKeys(final Location location, final Player player) {
        requireNonNull(location, "The location must not be null.");
        requireNonNull(player, "The player must not be null.");
        new KeyExchangeManager(player, location, GAME_IO).exchange();
    }

    default void moveToEmptySpace(final Location newLocation, final Player player) {
        requireNonNull(player, "The player must not be null.");
        player.setCoordinate(newLocation.getCoordinate());
    }

    default void upLevel(final Player player) {
        requireNonNull(player, "The player must not be null");

    }

}
