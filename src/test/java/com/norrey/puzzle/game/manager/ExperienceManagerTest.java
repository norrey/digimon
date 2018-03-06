package com.norrey.puzzle.game.manager;

import com.norrey.puzzle.game.context.DIContext;
import com.norrey.puzzle.map.Coordinate;
import com.norrey.puzzle.model.BitMine;
import com.norrey.puzzle.model.BlackBox;
import com.norrey.puzzle.model.EnemyRobot;
import com.norrey.puzzle.model.Experience;
import com.norrey.puzzle.model.FriendRobot;
import com.norrey.puzzle.model.Player;
import com.norrey.puzzle.model.Robot;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 23, 2018, 8:07:00 AM
 */
public class ExperienceManagerTest {

    private final ExperienceManager experienceManager = DIContext.getBean(ExperienceManager.class);
    private Player player;
    private Robot robot;
    private BitMine bitMine;
    private BlackBox blackBox;
    private FriendRobot friendRobot;

    @BeforeClass
    public void setup() {
        setupTestPlayer();
        setupTestEnemyRobot();
        setupTestBitMine();
        setupTestBlackBox();
        setupTestFriendRobot();
    }

    @AfterClass
    public void tearDown() {
    }

    /**
     * The cost of attack is equivalent to the energy lost during an attack
     */
    @Test
    public void testCostOfAttack() {
        final int energyDamage = experienceManager.costOfAttack(player, (EnemyRobot) robot);
        Assert.assertEquals(energyDamage, 10);
        Assert.assertTrue(player.isAlive());
        Assert.assertTrue(robot.isAlive());
    }

    @Test
    public void testCostOfDcrypt() {
        final int decryptEnergyDamage = experienceManager.costOfDecrypt(player, (EnemyRobot) robot);
        Assert.assertEquals(decryptEnergyDamage, 10);
        Assert.assertTrue(player.isAlive());
        Assert.assertTrue(robot.isAlive());
    }

    @Test
    public void testCostOfMine() {
        final int mineDamage = experienceManager.costOfMining(player, bitMine);
        Assert.assertEquals(mineDamage, 5);
        Assert.assertTrue(player.isAlive());
    }

    @Test
    public void testCostOfLearning() {
        final int learnDamage = experienceManager.costOfLearning(player, blackBox);
        Assert.assertEquals(learnDamage, 1000);
        Assert.assertTrue(player.isAlive());
    }

    @Test
    public void testUnsuccessfulKeyExchange() {
        final int keyExchangeDamage = experienceManager.costOfkeyEchnage(player, friendRobot);
        Assert.assertEquals(keyExchangeDamage, -1);
        Assert.assertTrue(player.isAlive());
    }

    private void setupTestPlayer() {

        player = new Player();
        player.setName("Test Player");
        player.setDescription("Test Description");
        player.setExperience(setupTestPlayerExperience());
        player.setCoordinate(new Coordinate(5, 5));
        player.setAlive(true);
    }

    private void setupTestEnemyRobot() {
        robot = new EnemyRobot();
        robot.setName("Abraham");
        robot.setDescription("Named after the director of Bad Robots. Abraham himself is a good guy. This robot is a real good bit conner");

        robot.setBitcoinsDamageValue(2);
        robot.setCryptoKeys(Arrays.asList("1234"));
        robot.setEnergyDamageFactor(10);
        robot.setEnergyLevel(100);
        robot.setIntroduction("I can see you have some bitcoins on your network. That is my food. Let me crack your security first.");

        robot.setAlive(true);

    }

    private Experience setupTestPlayerExperience() {
        final Experience experience = new Experience();

        experience.setMoney(5000);
        experience.setAiKnowledge(5);
        experience.setBitcoins(1);
        experience.setEnergy(100);
        experience.setLevel(1);
        experience.setDecryptEnergyDivideThreshold(50);
        experience.setCryptokeys(Arrays.asList("1234"));

        return experience;
    }

    private void setupTestBitMine() {
        bitMine = new BitMine();
        bitMine.setName("Bit Mine 1");
        bitMine.setDescription("Mine all your bitcoins here.");

        bitMine.setBitcoinGain(1);
        bitMine.setEnergyDamageFactor(5);
        bitMine.setAvailableBitcoins(20);
        bitMine.setIntroduction("Welcome to the mine. Get all your bit coins here.");

        bitMine.setAlive(true);

    }

    private void setupTestBlackBox() {
        blackBox = new BlackBox();
        blackBox.setName("BlackBox 1");
        blackBox.setDescription("Test description");

        blackBox.setAiLevelGain(1);
        blackBox.setCostOfLearning(1000);
        blackBox.setIntroduction("Test Introduction");
        blackBox.setEnergyDamageFactor(10);
        blackBox.setAvailableAi(6);

        blackBox.setAlive(true);
    }

    private void setupTestFriendRobot() {
        friendRobot = new FriendRobot();
        friendRobot.setName("Sophia");
        friendRobot.setDescription("Test description");

        friendRobot.setBitcoinsDamageValue(1);
        friendRobot.setCryptoKeys(Arrays.asList("1234"));
        friendRobot.setEnergyDamageFactor(1);
        friendRobot.setIntroduction("test introduction");
        friendRobot.setEnergyLevel(100);

        friendRobot.setAlive(true);

    }

}
