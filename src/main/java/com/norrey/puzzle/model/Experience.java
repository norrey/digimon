package com.norrey.puzzle.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 11:03:38 AM
 */
public class Experience implements Serializable {

    private static final long serialVersionUID = 1375951322137010766L;

    private int bitcoins;
    private int money;
    private int level;
    private int aiKnowledge;
    private int energy;
    private int damageFactor;
    private int decryptEnergyDivideThreshold;

    final private List<String> cryptokeys = new ArrayList<>();

    public int getBitcoins() {
        return bitcoins;
    }

    public void setBitcoins(int bitcoins) {
        this.bitcoins = bitcoins;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(final int level) {
        this.level = level;
    }

    public int getAiKnowledge() {
        return aiKnowledge;
    }

    public void setAiKnowledge(final int aiKnowledge) {
        this.aiKnowledge = aiKnowledge;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(final int energy) {
        this.energy = energy;
    }

    public int getDamageFactor() {
        return damageFactor;
    }

    public void setDamageFactor(int damageFactor) {
        this.damageFactor = damageFactor;
    }

    public int getDecryptEnergyDivideThreshold() {
        return decryptEnergyDivideThreshold;
    }

    public void setDecryptEnergyDivideThreshold(int decryptEnergyDivideThreshold) {
        this.decryptEnergyDivideThreshold = decryptEnergyDivideThreshold;
    }

    public List<String> getCryptokeys() {
        return Collections.unmodifiableList(cryptokeys);
    }

    public void setCryptokeys(final List<String> cryptokeys) {
        this.cryptokeys.addAll(cryptokeys);
    }

    public void reduceBitCoins(final int bitcoins) {
        this.bitcoins -= bitcoins;
    }

    public void increaseBitCoins(final int bitcoins) {
        this.bitcoins += bitcoins;
    }

    public void reduceMoney(final int money) {
        this.money -= money;
    }

    public void increaseMoney(final int money) {
        this.money += money;
    }

    public void reduceAiKnowledge(final int knowledge) {
        this.aiKnowledge -= knowledge;
    }

    public void increaseAiKnowledge(final int knowledge) {
        this.aiKnowledge += knowledge;
    }

    public void increaseEnergy(final int energy) {
        this.energy += energy;
    }

    public void reduceEnergy(final int energy) {
        this.energy -= energy;
    }

    public void levelUp() {
        this.level++;
    }

    public void addCrypoKeys(final List<String> cryptoKeys) {
        this.cryptokeys.addAll(cryptoKeys);
    }

    @Override
    public String toString() {
        return "Experience{" + "bitcoins=" + bitcoins + ", "
               + "money=" + money + ", "
               + "level=" + level + ", "
               + "aiKnowledge=" + aiKnowledge + ", "
               + "energy=" + energy + ", "
               + "cryptokeys=" + cryptokeys + '}';
    }

}
