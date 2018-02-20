package com.norrey.puzzle.model;

import com.norrey.puzzle.map.LocationType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 8:31:08 AM
 */
public abstract class Robot extends DormantCharacter {

    private static final long serialVersionUID = 2933579584786180774L;

    private int bitcoinsDamageValue;

    private final List<String> cryptoKeys = new ArrayList<>();

    private int energyLevel;

    @Override
    protected LocationType locationTypeSpecificForDormantCharacter() {
        return LocationType.ENEMY;
    }

    public int getBitcoinsDamageValue() {
        return bitcoinsDamageValue;
    }

    public void setBitcoinsDamageValue(final int bitcoinsDamageValue) {
        this.bitcoinsDamageValue = bitcoinsDamageValue;
    }

    public List<String> getCryptoKeys() {
        return Collections.unmodifiableList(cryptoKeys);
    }

    public void setCryptoKeys(final List<String> cryptoKeys) {
        this.cryptoKeys.addAll(cryptoKeys);
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(final int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public void reduceEnergyLevel(final int value) {
        this.energyLevel -= value;
    }

    public void counterAttack(final Player player) {
        requireNonNull(player, "The player must not be null.");
        int damage = (player.getExperience().getEnergy() * player.getExperience().getAiKnowledge()) / 2;
        if (this.energyLevel <= 0 || this.energyLevel < damage) {
            neautralize();
            return;
        }
        this.energyLevel -= damage;
    }

    public void neautralize() {
        this.energyLevel = 0;
        this.setAlive(false);
    }

    @Override
    public String toString() {
        return "Robot{"
               + "bitcoinsDamageValue=" + bitcoinsDamageValue + ", "
               + "cryptoKeys=" + cryptoKeys + ", "
               + "energyLevel=" + energyLevel + '}';
    }

}
