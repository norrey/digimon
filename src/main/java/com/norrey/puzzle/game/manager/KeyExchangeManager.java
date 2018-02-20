package com.norrey.puzzle.game.manager;

import com.norrey.puzzle.game.context.DIContext;
import com.norrey.puzzle.game.menu.item.KeyExchangeMenuItem;
import com.norrey.puzzle.io.GameIO;
import com.norrey.puzzle.map.Location;
import com.norrey.puzzle.model.DormantCharacter;
import com.norrey.puzzle.model.FriendRobot;
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
public class KeyExchangeManager {

    private static final ExperienceManager EXPERIENCE_MANAGER = DIContext.getBean(ExperienceManager.class);

    private final Player player;
    private final DormantCharacter dormantCharacter;
    private final GameIO gameIO;

    public KeyExchangeManager(final Player player, final Location location, final GameIO gameIO) {
        this.player = requireNonNull(player, "The player cannot be null.");
        this.dormantCharacter = requireNonNull(location.getDormantCharacter(), "The dormant character must not be null");
        this.gameIO = requireNonNull(gameIO, "The reader must not be null");
    }

    public void exchange() {
        startKeyExchange();
    }

    private KeyExchangeMenuItem displayMenu() {
        printMenuOptions();
        return readUserChoice();
    }

    private KeyExchangeMenuItem readUserChoice() {
        final Optional<KeyExchangeMenuItem> chosenItem = KeyExchangeMenuItem.fromString(gameIO.readInputWithoutPrompt());
        if (!chosenItem.isPresent()) {
            gameIO.print("Invalid input. Please try again.");
            return KeyExchangeMenuItem.IGNORE;
        }
        return chosenItem.get();
    }

    private void printMenuOptions() {
        gameIO.print("Choose from the options below:");
        for (KeyExchangeMenuItem value : KeyExchangeMenuItem.values) {
            gameIO.print((value.getKeyBinding()) + ". " + value.getDescription());
        }
    }

    void startKeyExchange() {
        KeyExchangeMenuItem learnMenuItem = displayMenu();
        do {
            switch (learnMenuItem) {
                case EXCHANGE_KEY:
                    exchangeKey(player, dormantCharacter);
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

        } while (exchangeGoesOn(dormantCharacter));
    }

    /**
     * Replace with configurable values
     *
     * @param dormantCharacter
     * @param menuItem
     * @return
     */
    boolean exchangeGoesOn(final DormantCharacter dormantCharacter) {
        requireNonNull(dormantCharacter, "The dormant character must not be null");
        long uniqueKeys = ((FriendRobot) dormantCharacter).getCryptoKeys()
                .stream()
                .filter(key -> !player.getExperience().getCryptokeys().contains(key))
                .count();

        return uniqueKeys > 0 && player.getExperience().getEnergy() > 5 && player.getExperience().getBitcoins() > 0;
    }

    void exchangeKey(final Player attacker, final DormantCharacter friend) {
        final int costOfLearning = EXPERIENCE_MANAGER.costOfkeyEchnage(player, (FriendRobot) friend);
        if (costOfLearning > 0) {
            gameIO.print(yellow("Cost of key exchange: ") + costOfLearning);
            return;
        }
        gameIO.print(red("No exchange taking place"));

    }

}
