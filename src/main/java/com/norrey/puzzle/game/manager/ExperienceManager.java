package com.norrey.puzzle.game.manager;

import com.norrey.puzzle.model.BitMine;
import com.norrey.puzzle.model.BlackBox;
import com.norrey.puzzle.model.FriendRobot;
import com.norrey.puzzle.model.Player;
import com.norrey.puzzle.model.Robot;
import com.norrey.puzzle.model.Character;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * TODO Separate this into individual managers = Attack/Decrypt/KeyExchange/Learning
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 20, 2018, 3:27:18 PM
 */
public class ExperienceManager {

    public int costOfAttack(final Player player, final Robot robot) {
        requireNonNull(player, "The player must not be null");
        requireNonNull(robot, "The robot must not be null");
        if (!proceed(player, robot)) {
            return -1;
        }

        int btc = player.getExperience().getBitcoins();
        int ai = player.getExperience().getAiKnowledge();
        int damageFactor = player.getExperience().getDamageFactor();
        int totalDamage = (btc + ai) * damageFactor;
        int damageDealt = totalDamage < 10 ? 10 : totalDamage;

        robot.reduceEnergyLevel(damageDealt);

        player.getExperience().reduceEnergy(robot.getEnergyDamageFactor());
        bitcoinDamageDuringRobotAttack(player, robot);

        return robot.getEnergyDamageFactor();
    }

    private void bitcoinDamageDuringRobotAttack(final Player player, final Robot robot) {
        requireNonNull(player, "The player must not be null");
        requireNonNull(robot, "The robot must not be null");

        player.getExperience().reduceBitCoins(robot.getBitcoinsDamageValue());
    }

    public int costOfDecrypt(final Player player, final Robot robot) {
        requireNonNull(player, "The player must not be null");
        requireNonNull(robot, "The robot must not be null");

        if (!proceed(player, robot)) {
            return -1;
        }

        if (!robotDecryptible(player, robot)) {
            return -1;
        }

        robot.reduceEnergyLevel(damageDealtToRobotDuringDcrypt(player, robot));
        player.getExperience().reduceBitCoins(robot.getBitcoinsDamageValue());
        player.getExperience().reduceEnergy(robot.getEnergyDamageFactor());

        return robot.getEnergyDamageFactor();
    }

    private boolean robotDecryptible(final Player player, final Robot robot) {
        requireNonNull(player, "The attacker must not be null");
        requireNonNull(robot, "The defender must not be null");
        return !Collections.disjoint(player.getExperience().getCryptokeys(), robot.getCryptoKeys());
    }

    private int damageDealtToRobotDuringDcrypt(final Player player, final Robot robot) {
        requireNonNull(player, "The player must not be null");
        requireNonNull(robot, "The robot must not be null");

        if (robot.getEnergyLevel() > player.getExperience().getDecryptEnergyDivideThreshold()) {
            return robot.getEnergyLevel() / 2;
        }
        return robot.getEnergyLevel();
    }

    public int costOfkeyEchnage(final Player player, final FriendRobot friendRobot) {
        requireNonNull(player, "The player must not be null");
        requireNonNull(friendRobot, "The friend robot must not be null");

        if (player.getExperience().getEnergy() > friendRobot.getEnergyDamageFactor()
            && player.getExperience().getBitcoins() > friendRobot.getBitcoinsDamageValue()
            && haveUniqueKeys(player, friendRobot)) {

            player.getExperience().reduceEnergy(friendRobot.getEnergyDamageFactor());
            btcLossDuringKeyExchange(player, friendRobot);

            return friendRobot.getEnergyDamageFactor();
        }
        return -1;
    }

    private boolean haveUniqueKeys(final Player player, final Robot robot) {
        requireNonNull(player, "The player must not be null");
        requireNonNull(robot, "The robot must not be null");

        long uniqueKeys = ((FriendRobot) robot).getCryptoKeys()
                .stream()
                .filter(key -> !player.getExperience().getCryptokeys().contains(key))
                .count();
        return uniqueKeys > 0;
    }

    private void btcLossDuringKeyExchange(final Player player, Robot robot) {
        requireNonNull(player, "The player must not be null");
        requireNonNull(robot, "The robot must not be null");

        if (robot.getCryptoKeys().isEmpty()) {
            return;
        }
        final List<String> keys = player.getExperience().getCryptokeys();
        final String toAdd = robot.getCryptoKeys().stream().filter(crypto -> !keys.contains(crypto)).findAny().get();

        player.getExperience().addCrypoKeys(Arrays.asList(toAdd));
        player.getExperience().reduceBitCoins(robot.getBitcoinsDamageValue());
    }

    public int costOfMining(final Player player, final BitMine bitMine) {
        requireNonNull(player, "The player must not be null");
        requireNonNull(bitMine, "The bitMine must not be null");

        if (player.getExperience().getEnergy() > bitMine.getEnergyDamageFactor() && bitMine.getAvailableBitcoins() > bitMine.getBitcoinGain()) {
            player.getExperience().reduceEnergy(bitMine.getEnergyDamageFactor());
            btcGainDuringMining(player, bitMine);
            bitMine.reduceAvailableBitcoins(bitMine.getBitcoinGain());
            return bitMine.getEnergyDamageFactor();
        }
        return -1;
    }

    private void btcGainDuringMining(final Player player, final BitMine bitMine) {
        requireNonNull(player, "The player must not be null");
        requireNonNull(bitMine, "The robot must not be null");

        player.getExperience().increaseBitCoins(bitMine.getBitcoinGain());
    }

    public int costOfLearning(final Player player, final BlackBox blackBox) {
        requireNonNull(player, "The player must not be null");
        requireNonNull(blackBox, "The blackbox must not be null");

        if (player.getExperience().getMoney() > blackBox.getCostOfLearning() && blackBox.getAvailableAi() >= blackBox.getAiLevelGain()) {
            player.getExperience().reduceMoney(blackBox.getCostOfLearning());
            learningAIGain(player, blackBox);
            energyDamageDuringLearning(player, blackBox);
            return blackBox.getCostOfLearning();
        }
        return -1;
    }

    private void learningAIGain(final Player player, final BlackBox blackBox) {
        requireNonNull(player, "The player must not be null");
        requireNonNull(blackBox, "The robot must not be null");

        player.getExperience().increaseAiKnowledge(blackBox.getAiLevelGain());
        blackBox.reduceAvailableAi(blackBox.getAiLevelGain());
    }

    private void energyDamageDuringLearning(final Player player, final BlackBox blackBox) {
        requireNonNull(player, "The player must not be null");
        requireNonNull(blackBox, "The robot must not be null");

        player.getExperience().reduceEnergy(blackBox.getEnergyDamageFactor());
    }

    private boolean playerDied(final Player player) {
        requireNonNull(player, "The player must not be null");
        boolean playerDied = player.getExperience().getEnergy() <= 0;
        if (playerDied) {
            killCharacter(player);
        }
        return playerDied;
    }

    private boolean robotDied(final Robot robot) {
        requireNonNull(robot, "The robot must not be null");
        boolean robotDied = robot.getEnergyLevel() <= 0;
        if (robotDied) {
            killCharacter(robot);
        }

        return robotDied;
    }

    private boolean proceed(final Player player, final Robot robot) {
        requireNonNull(player, "The player must not be null");
        requireNonNull(robot, "The robot must not be null");

        System.out.println("Player Died :" + playerDied(player));
        System.out.println("Robot Died :" + robotDied(robot));

        return !playerDied(player) && !robotDied(robot);
    }

    private void killCharacter(final Character character) {
        character.setAlive(false);
    }

}
