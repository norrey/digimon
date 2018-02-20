package com.norrey.puzzle.util;

import com.norrey.puzzle.map.Coordinate;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 20, 2018, 9:19:12 AM
 */
public class GameMapUtilTest {

    @BeforeClass
    public void setup() {
    }

    @AfterClass
    public void tearDown() {
    }

    @Test
    public void testRandomCoordinateWithingRange() {
        final Coordinate coordinate = GameMapUtil.randomCoordinates(10);
        Assert.assertTrue(coordinate.getX() < 10
                          && coordinate.getX() >= 0
                          && coordinate.getY() < 10
                          && coordinate.getY() >= 0, "Within range");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRandomCoordinateGeneratorWithZeroMapSize() {
        GameMapUtil.randomCoordinates(0);
    }

}
