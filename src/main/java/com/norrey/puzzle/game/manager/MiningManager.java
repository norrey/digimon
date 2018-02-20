package com.norrey.puzzle.game.manager;

import com.norrey.puzzle.game.context.DIContext;
import com.norrey.puzzle.game.menu.item.MineMenuItem;
import com.norrey.puzzle.io.GameIO;
import com.norrey.puzzle.map.Location;
import com.norrey.puzzle.model.BitMine;
import com.norrey.puzzle.model.DormantCharacter;
import com.norrey.puzzle.model.Player;
import java.util.Optional;

import static com.norrey.puzzle.util.ColorFormatter.red;
import static com.norrey.puzzle.util.ColorFormatter.yellow;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 18, 2018, 10:43:13 AM
 */
public class MiningManager {

    private static final ExperienceManager EXPERIENCE_MANAGER = DIContext.getBean(ExperienceManager.class);

    private final Player player;
    private final DormantCharacter dormantCharacter;
    private final GameIO gameIO;

    public MiningManager(final Player player, final Location location, final GameIO gameIO) {
        this.player = requireNonNull(player, "The player cannot be null.");
        this.dormantCharacter = requireNonNull(location.getDormantCharacter(), "The dormant character must not be null");
        this.gameIO = requireNonNull(gameIO, "The reader must not be null");
    }

    public void mine() {
        startMining();
    }

    private MineMenuItem displayMenu() {
        printAttackMenuOptions();
        return readUserChoice();
    }

    private MineMenuItem readUserChoice() {
        final Optional<MineMenuItem> chosenItem = MineMenuItem.fromString(gameIO.readInputWithoutPrompt());
        if (!chosenItem.isPresent()) {
            gameIO.print("Invalid input. Please try again.");
            return MineMenuItem.IGNORE;
        }
        return chosenItem.get();
    }

    private void printAttackMenuOptions() {
        gameIO.print("Choose from the options below:");
        for (MineMenuItem value : MineMenuItem.values) {
            gameIO.print((value.getKeyBinding()) + ". " + value.getDescription());
        }
    }

    void startMining() {
        MineMenuItem learnMenuItem = displayMenu();
        do {
            switch (learnMenuItem) {
                case MINE:
                    mine(player, dormantCharacter);
                    gameIO.print(player.toString());
                    gameIO.print(dormantCharacter.toString());
                    break;
                case IGNORE:
                    gameIO.print("You ran away");
                    return;
                default:
                    throw new RuntimeException();
            }

            if (dormantCharacter.isAlive()) {
                learnMenuItem = displayMenu();
            }

        } while (learningGoesOn(dormantCharacter, learnMenuItem));
    }

    boolean learningGoesOn(final DormantCharacter dormantCharacter, final MineMenuItem menuItem) {
        requireNonNull(dormantCharacter, "The dormant character must not be null");
        requireNonNull(menuItem, "The attack menu item must not be null");
        return ((BitMine) dormantCharacter).getAvailableBitcoins() >= 0;
    }

    void mine(final Player attacker, final DormantCharacter teacher) {
        requireNonNull(attacker, "The attacker must not be null");
        requireNonNull(teacher, "The teacher must not be null");

        final int costOfMining = EXPERIENCE_MANAGER.costOfMining(player, (BitMine) teacher);
        if (costOfMining > 0) {
            gameIO.print(yellow("Cost of mining: ") + costOfMining);
            return;
        }
        gameIO.print(red("Seems there are no extra bitcoins to mine."));

    }

}
