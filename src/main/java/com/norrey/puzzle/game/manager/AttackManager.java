package com.norrey.puzzle.game.manager;

import com.norrey.puzzle.game.context.DIContext;
import com.norrey.puzzle.game.menu.item.AttackMenuItem;
import com.norrey.puzzle.io.GameIO;
import com.norrey.puzzle.map.Coordinate;
import com.norrey.puzzle.map.Location;
import com.norrey.puzzle.model.DormantCharacter;
import com.norrey.puzzle.model.EnemyRobot;
import com.norrey.puzzle.model.Player;
import com.norrey.puzzle.model.Robot;
import java.util.Collections;
import java.util.Optional;

import static com.norrey.puzzle.game.menu.item.AttackMenuItem.RUN_AWAY;
import static com.norrey.puzzle.util.ColorFormatter.green;
import static com.norrey.puzzle.util.ColorFormatter.yellow;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 18, 2018, 10:43:13 AM
 */
public class AttackManager {

    private static final ExperienceManager EXPERIENCE_MANAGER = DIContext.getBean(ExperienceManager.class);

    private final Player player;
    private final Coordinate coordinate;
    private final DormantCharacter dormantCharacter;
    private final GameIO gameIO;

    public AttackManager(final Player player, final Location location, final GameIO gameIO) {
        this.player = requireNonNull(player, "The player cannot be null.");
        this.dormantCharacter = requireNonNull(location.getDormantCharacter(), "The dormant character must not be null");
        this.coordinate = requireNonNull(location.getCoordinate());
        this.gameIO = requireNonNull(gameIO, "The reader must not be null");
    }

    public void fight() {
        startFighting();
    }

    private AttackMenuItem diplayAttackMenuAndRequestInput() {
        printAttackMenuOptions();
        return readUserAttackMenuChoice();
    }

    private AttackMenuItem readUserAttackMenuChoice() {
        final Optional<AttackMenuItem> chosenItem = AttackMenuItem.fromString(gameIO.readInputWithoutPrompt());
        if (!chosenItem.isPresent()) {
            gameIO.print("Invalid input.");
            return AttackMenuItem.RUN_AWAY;
        }
        return chosenItem.get();
    }

    private void printAttackMenuOptions() {
        gameIO.print("Choose from the options below:");
        for (AttackMenuItem value : AttackMenuItem.values) {
            gameIO.print((value.getKeyBinding()) + ". " + value.getDescription());
        }
    }

    void startFighting() {
        AttackMenuItem attackMenuItem = diplayAttackMenuAndRequestInput();
        do {
            switch (attackMenuItem) {
                case ATTACK:
                    attack(player, dormantCharacter);
                    if (!dormantCharacter.isAlive()) {
                        gameIO.print(yellow("Whoa!!!!!! Robot killed."));
                        player.setCoordinate(coordinate);
                        gameIO.print(player.toString());
                        return;
                    }
                    gameIO.print(player.toString());
                    gameIO.print(dormantCharacter.toString());
                    break;
                case DECRYPT:
                    decrypt(player, dormantCharacter);
                    gameIO.print(player.toString());
                    gameIO.print(dormantCharacter.toString());
                    break;
                case RUN_AWAY:
                    gameIO.print("You ran away");
                    gameIO.print(player.toString());
                    gameIO.print(dormantCharacter.toString());
                    break;
                default:
                    throw new RuntimeException();
            }

            if (dormantCharacter.isAlive()) {
                attackMenuItem = diplayAttackMenuAndRequestInput();
            }

        } while (fightGoesOn(dormantCharacter, attackMenuItem));
    }

    boolean fightGoesOn(final DormantCharacter dormantCharacter, final AttackMenuItem attackMenuItem) {
        requireNonNull(dormantCharacter, "The dormant character must not be null");
        return dormantCharacter.isAlive() && player.isAlive() && null != attackMenuItem && !RUN_AWAY.equals(attackMenuItem);
    }

    int attack(final Player attacker, final DormantCharacter defender) {
        requireNonNull(attacker, "The attacker must not be null");
        requireNonNull(defender, "The defender must not be null");
        return EXPERIENCE_MANAGER.costOfAttack(player, (EnemyRobot) defender);
    }

    int decrypt(final Player attacker, final DormantCharacter defender) {
        return EXPERIENCE_MANAGER.costOfDecrypt(player, (EnemyRobot) defender);
    }

}
