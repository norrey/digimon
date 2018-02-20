package com.norrey.puzzle.map;

import com.norrey.puzzle.game.provider.level.LevelConfiguration;
import com.norrey.puzzle.io.exception.InvalidInputException;
import com.norrey.puzzle.model.BitMine;
import com.norrey.puzzle.model.BlackBox;
import com.norrey.puzzle.model.DormantCharacter;
import com.norrey.puzzle.model.Robot;
import com.norrey.puzzle.util.GameMapUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 16, 2018, 7:09:24 PM
 */
public class GameMap implements Serializable {

    private static final long serialVersionUID = 8617891012820134283L;

    private final String name;
    private final Map<Coordinate, Robot> friends;
    private final Map<Coordinate, Robot> enemies;
    private final Map<Coordinate, BitMine> bitmines;
    private final Map<Coordinate, BlackBox> blackboxes;
    private final Location[][] mapArea;

    public GameMap(final LevelConfiguration levelConfiguration) {
        requireNonNull(levelConfiguration, "The level configuration must not be null");

        this.friends = new HashMap<>();
        this.enemies = new HashMap<>();
        this.bitmines = new HashMap<>();
        this.blackboxes = new HashMap<>();
        this.mapArea = new Location[levelConfiguration.getMapSize()][levelConfiguration.getMapSize()];
        this.name = levelConfiguration.getName();

        this.initializeMap(levelConfiguration);
    }

    private void initializeMap(final LevelConfiguration levelConfiguration) {
        initializeFriends(levelConfiguration);
        initializeEnemies(levelConfiguration);
        initializeBitmines(levelConfiguration);
        initializeBlackBoxes(levelConfiguration);
        initializeMapArea();
    }

    private void initializeFriends(final LevelConfiguration levelConfiguration) {
        levelConfiguration.getCharacterConfiguration()
                .getFriends()
                .stream()
                .forEach(friend -> friends.put(randomEmptyCoordinate(), friend));
    }

    private void initializeEnemies(final LevelConfiguration levelConfiguration) {
        levelConfiguration.getCharacterConfiguration()
                .getEnemies()
                .stream()
                .forEach(enemy -> enemies.put(randomEmptyCoordinate(), enemy));
    }

    private void initializeBitmines(final LevelConfiguration levelConfiguration) {
        levelConfiguration.getCharacterConfiguration()
                .getBitmines()
                .stream()
                .forEach(bitmine -> bitmines.put(randomEmptyCoordinate(), bitmine));
    }

    public void initializeBlackBoxes(final LevelConfiguration levelConfiguration) {
        levelConfiguration.getCharacterConfiguration()
                .getBlackboxes()
                .stream()
                .forEach(blackbox -> blackboxes.put(randomEmptyCoordinate(), blackbox));
    }

    void initializeMapArea() {
        for (int x = 0; x < mapArea.length; x++) {
            Location[] column = mapArea[x];
            for (int y = 0; y < column.length; y++) {

                final Coordinate currentCoordinate = new Coordinate(x, y);
                final DormantCharacter someone = searchForDormantCharacters(currentCoordinate);

                if (null != someone) {
                    mapArea[x][y] = locationWithDormantCharacter(currentCoordinate, someone);
                } else {
                    mapArea[x][y] = emptyLocation(currentCoordinate);
                }

            }
        }
    }

    private DormantCharacter searchForDormantCharacters(final Coordinate currentCoordinates) {
        requireNonNull(currentCoordinates, "The coordinate must not be null");

        if (enemies.containsKey(currentCoordinates)) {
            return enemies.get(currentCoordinates);
        } else if (bitmines.containsKey(currentCoordinates)) {
            return bitmines.get(currentCoordinates);
        } else if (blackboxes.containsKey(currentCoordinates)) {
            return blackboxes.get(currentCoordinates);
        } else {
            return friends.get(currentCoordinates);
        }
    }

    private Location locationWithDormantCharacter(final Coordinate coordinates, final DormantCharacter dormantCharacter) {
        requireNonNull(coordinates, "The coordinate must not be null");
        requireNonNull(dormantCharacter, "The dormant character must not be null");
        return new Location(coordinates, dormantCharacter);
    }

    private Location emptyLocation(final Coordinate coordinate) {
        requireNonNull(coordinate, "The coordinate must not be null");
        return new Location(coordinate);
    }

    public Location getLocation(final Coordinate coordinate) {
        requireNonNull(coordinate, "The coordinate must not be null.");
        return getLocation(coordinate.getX(), coordinate.getY());
    }

    public Location getLocation(final int x, final int y) {
        validateCoordinates(x, y);
        return mapArea[x][y];
    }

    private void validateCoordinates(final int x, final int y) {
        validateCoordinate(x);
        validateCoordinate(y);
    }

    private void validateCoordinate(int index) throws InvalidInputException {
        if (index < 0 || index >= mapSize()) {
            throw new InvalidInputException("Oops cannot go out of this world. Ask Elon Musk.");
        }
    }

    private int mapSize() {
        return mapArea.length;
    }

    public Coordinate randomEmptyCoordinate() {
        Coordinate coordinate;
        do {
            coordinate = GameMapUtil.randomCoordinates(mapArea.length);
        } while (occupied(coordinate));

        return coordinate;
    }

    private boolean occupied(final Coordinate coordinate) {
        requireNonNull(coordinate, "The coordinate must not be null");
        return enemies.containsKey(coordinate)
               || friends.containsKey(coordinate)
               || blackboxes.containsKey(coordinate)
               || bitmines.containsKey(coordinate);
    }

    public String getName() {
        return name;
    }

    public boolean hasNoRemainingEnemies() {
        return enemies.values()
                .stream()
                .filter(enemy -> enemy.isAlive())
                .count() <= 0L;
    }

    public Map<Coordinate, Robot> getFriends() {
        return new HashMap<>(friends);
    }

    public Map<Coordinate, Robot> getEnemies() {
        return new HashMap<>(enemies);
    }

    public Location[][] getMapArea() {
        return mapArea;
    }

    public Map<Coordinate, BitMine> getBitmines() {
        return new HashMap<>(bitmines);
    }

    public Map<Coordinate, BlackBox> getBlackboxes() {
        return new HashMap<>(blackboxes);
    }

}
