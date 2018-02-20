package com.norrey.puzzle.util;

import com.norrey.puzzle.map.Coordinate;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 18, 2018, 9:51:39 AM
 */




public class GameMapUtil {

    public static Coordinate randomCoordinates(int mapSize) {
        final int randomX = ThreadLocalRandom.current().nextInt(0, mapSize);
        final int randomY = ThreadLocalRandom.current().nextInt(0, mapSize);
        return new Coordinate(randomX, randomY);
    }

}
