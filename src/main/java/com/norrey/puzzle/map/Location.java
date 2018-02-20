package com.norrey.puzzle.map;

import com.norrey.puzzle.model.DormantCharacter;
import java.io.Serializable;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 16, 2018, 6:27:36 PM
 */
public class Location implements Serializable {

    private static final long serialVersionUID = -7620206708873308930L;

    private final Coordinate coordinate;
    private LocationType type;
    private DormantCharacter dormantCharacter;

    public Location(final Coordinate coordinate) {
        this.coordinate = requireNonNull(coordinate, "the coordinate must not be null");
        this.type = LocationType.FREEPATH;
    }

    public Location(final Coordinate coordinate, final DormantCharacter dormantCharacter) {
        this.coordinate = coordinate;
        this.dormantCharacter = dormantCharacter;
        this.type = dormantCharacter.locationType();
    }

    public LocationType getType() {
        return type;
    }

    public void setType(final LocationType type) {
        this.type = type;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    private LocationType relevantLocationType() {
        if (null != dormantCharacter) {
            return dormantCharacter.locationType();
        }
        return type;
    }

    public String mapMark() {
        return relevantLocationType().getMapMark();
    }

    public DormantCharacter getDormantCharacter() {
        return dormantCharacter;
    }

    public void setDormantCharacter(DormantCharacter dormantCharacter) {
        this.dormantCharacter = dormantCharacter;
    }

    public boolean containsCharacter() {
        return null != dormantCharacter && dormantCharacter.isAlive();
    }

}
