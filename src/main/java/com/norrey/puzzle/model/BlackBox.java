package com.norrey.puzzle.model;

import com.norrey.puzzle.map.LocationType;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 8:33:07 AM
 */
public class BlackBox extends DormantCharacter {

    private static final long serialVersionUID = 573455284951943675L;

    private int aiLevelGain;
    private int costOfLearning;
    private int availableAi;

    @Override
    protected LocationType locationTypeSpecificForDormantCharacter() {
        return LocationType.BLACK_BOX;
    }

    @Override
    public boolean isEnemy() {
        return false;
    }

    public int getAvailableAi() {
        return availableAi;
    }

    public void setAvailableAi(int availableAi) {
        this.availableAi = availableAi;
    }

    public int getAiLevelGain() {
        return aiLevelGain;
    }

    public void setAiLevelGain(final int aiLevelGain) {
        this.aiLevelGain = aiLevelGain;
    }

    public int getCostOfLearning() {
        return costOfLearning;
    }

    public void setCostOfLearning(final int costOfLearning) {
        this.costOfLearning = costOfLearning;
    }

    public void reduceAvailableAi(final int value) {
        this.availableAi -= value;
    }

    @Override
    public String toString() {
        return "BlackBox{"
               + "aiLevelGain=" + aiLevelGain + ", "
               + "costOfLearning=" + costOfLearning + ", "
               + "availableAi=" + availableAi + '}';
    }

}
