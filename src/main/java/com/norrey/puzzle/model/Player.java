package com.norrey.puzzle.model;

import com.norrey.puzzle.map.Coordinate;
import java.util.Collections;

import static com.norrey.puzzle.util.ColorFormatter.red;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 8:30:33 AM
 */
public class Player extends Character {

    private static final long serialVersionUID = -3885828134282764813L;

    private Coordinate coordinate;
    private Experience experience;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(final Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(final Experience experience) {
        this.experience = experience;
    }

    public Coordinate up() {
        return coordinate.up();
    }

    public Coordinate down() {
        return coordinate.down();
    }

    public Coordinate left() {
        return coordinate.left();
    }

    public Coordinate right() {
        return coordinate.right();
    }

    public String runAway() {
        this.experience.reduceEnergy(1);
        return red("Running away so fast only reduces your energery");
    }

    @Override
    public String toString() {
        return "Player{" + "coordinate=" + coordinate + ", experience=" + experience + '}';
    }

}
