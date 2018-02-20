package com.norrey.puzzle.map;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 16, 2018, 6:28:00 PM
 */
public class Coordinate implements Serializable {

    private static final long serialVersionUID = 1847742410182531118L;

    private final int x;
    private final int y;

    public Coordinate(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate left() {
        return new Coordinate(x - 1, y);
    }

    public Coordinate right() {
        return new Coordinate(x + 1, y);
    }

    public Coordinate up() {
        return new Coordinate(x, y - 1);
    }

    public Coordinate down() {
        return new Coordinate(x, y + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coordinate)) {
            return false;
        }

        Coordinate otherCordinate = (Coordinate) o;

        return x == otherCordinate.getX() && y == otherCordinate.getY();
    }

    public boolean equals(int x, int y) {
        return this.x == x && this.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinate{" + "x=" + x + ", y=" + y + '}';
    }

}
