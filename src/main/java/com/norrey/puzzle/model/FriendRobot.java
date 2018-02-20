package com.norrey.puzzle.model;

import com.norrey.puzzle.map.LocationType;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 8:31:08 AM
 */
public class FriendRobot extends Robot {

    private static final long serialVersionUID = -8910804776409484669L;

    @Override
    protected LocationType locationTypeSpecificForDormantCharacter() {
        return LocationType.FRIEND;
    }

    @Override
    public boolean isEnemy() {
        return false;
    }

}
