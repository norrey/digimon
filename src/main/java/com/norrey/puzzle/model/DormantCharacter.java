package com.norrey.puzzle.model;

import com.norrey.puzzle.map.LocationType;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 8:32:02 AM
 */
public abstract class DormantCharacter extends Character {

    private static final long serialVersionUID = 4159736181363010147L;

    private int energyDamageFactor;

    public LocationType locationType() {
        if (!this.isAlive()) {
            return LocationType.DEAD;
        } else {
            return locationTypeSpecificForDormantCharacter();
        }
    }

    protected abstract LocationType locationTypeSpecificForDormantCharacter();

    public abstract boolean isEnemy();

    public int getEnergyDamageFactor() {
        return energyDamageFactor;
    }

    public void setEnergyDamageFactor(final int energyDamageFactor) {
        this.energyDamageFactor = energyDamageFactor;
    }

    @Override
    public String toString() {
        return "DormantCharacter{" + "energyDamageFactor=" + energyDamageFactor + '}';
    }

}
