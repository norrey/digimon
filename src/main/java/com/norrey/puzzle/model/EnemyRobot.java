package com.norrey.puzzle.model;

import com.norrey.puzzle.map.LocationType;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 8:31:08 AM
 */
public class EnemyRobot extends Robot {

    private static final long serialVersionUID = 2620676608852022496L;

    @Override
    protected LocationType locationTypeSpecificForDormantCharacter() {
        return LocationType.ENEMY;
    }

    @Override
    public boolean isEnemy() {
        return true;
    }

}
