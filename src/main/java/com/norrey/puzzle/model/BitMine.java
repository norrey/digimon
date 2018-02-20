package com.norrey.puzzle.model;

import com.norrey.puzzle.map.LocationType;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 8:33:34 AM
 */
public class BitMine extends DormantCharacter {

    private static final long serialVersionUID = 1672503964479490202L;

    private int bitcoinGain;
    private int availableBitcoins;

    @Override
    protected LocationType locationTypeSpecificForDormantCharacter() {
        return LocationType.MINE;
    }

    @Override
    public boolean isEnemy() {
        return false;
    }

    public int getBitcoinGain() {
        return bitcoinGain;
    }

    public void setBitcoinGain(final int bitcoinGain) {
        this.bitcoinGain = bitcoinGain;
    }

    public int getAvailableBitcoins() {
        return availableBitcoins;
    }

    public void setAvailableBitcoins(final int availableBitcoins) {
        this.availableBitcoins = availableBitcoins;
    }

    public void reduceAvailableBitcoins(final int value) {
        this.availableBitcoins -= value;
    }

    @Override
    public String toString() {
        return "BitMine{" + "bitcoinGain=" + bitcoinGain + '}';
    }

}
