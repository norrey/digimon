package com.norrey.puzzle.game.manager;

import com.norrey.puzzle.game.context.DIContext;
import com.norrey.puzzle.game.menu.item.LearnMenuItem;
import com.norrey.puzzle.io.GameIO;
import com.norrey.puzzle.map.Location;
import com.norrey.puzzle.model.BlackBox;
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
public class LearnManager {

    private static final ExperienceManager EXPERIENCE_MANAGER = DIContext.getBean(ExperienceManager.class);

    private final Player player;
    private final DormantCharacter dormantCharacter;
    private final GameIO gameIO;

    public LearnManager(final Player player, final Location location, final GameIO gameIO) {
        this.player = requireNonNull(player, "The player cannot be null.");
        this.dormantCharacter = requireNonNull(location.getDormantCharacter(), "The dormant character must not be null");
        this.gameIO = requireNonNull(gameIO, "The reader must not be null");
    }

    public void learn() {
        startLearning();
    }

    private LearnMenuItem displayLearnMenu() {
        printAttackMenuOptions();
        return readUserChoice();
    }

    private LearnMenuItem readUserChoice() {
        final Optional<LearnMenuItem> chosenItem = LearnMenuItem.fromString(gameIO.readInputWithoutPrompt());
        if (!chosenItem.isPresent()) {
            gameIO.print("Invalid input. Please try again.");
            return LearnMenuItem.IGNORE;
        }
        return chosenItem.get();
    }

    private void printAttackMenuOptions() {
        gameIO.print("Choose from the options below:");
        for (LearnMenuItem value : LearnMenuItem.values) {
            gameIO.print((value.getKeyBinding()) + ". " + value.getDescription());
        }
    }

    void startLearning() {
        LearnMenuItem learnMenuItem = displayLearnMenu();
        do {
            switch (learnMenuItem) {
                case LEARN:
                    learn(player, dormantCharacter);
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
                learnMenuItem = displayLearnMenu();
            }

        } while (learningGoesOn(dormantCharacter, learnMenuItem));
    }

    boolean learningGoesOn(final DormantCharacter dormantCharacter, final LearnMenuItem menuItem) {
        requireNonNull(dormantCharacter, "The dormant character must not be null");
        requireNonNull(menuItem, "The attack menu item must not be null");
        return ((BlackBox) dormantCharacter).getAvailableAi() >= 0;
    }

    void learn(final Player attacker, final DormantCharacter teacher) {
        final int costOfLearning = EXPERIENCE_MANAGER.costOfLearning(player, (BlackBox) teacher);
        if (costOfLearning > 0) {
            gameIO.print(yellow("Cost of learning: ") + costOfLearning);
            return;
        }
        gameIO.print(red("No learning taking place"));

    }

}
