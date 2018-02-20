package com.norrey.puzzle.game.manager;

import com.norrey.puzzle.game.context.GameContext;
import com.norrey.puzzle.game.menu.item.NavigationMenuItem;
import com.norrey.puzzle.io.GameIO;
import com.norrey.puzzle.map.GameMap;
import com.norrey.puzzle.map.LocationType;
import com.norrey.puzzle.map.MapView;
import com.norrey.puzzle.model.Player;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 11:39:58 AM
 */
public class NavigationManager {

    private final GameIO gameIO;
    private final GameMap gameMap;
    private final Player player;

    public NavigationManager(final GameIO gameIO,
                             final GameMap gameMap,
                             final Player player) {
        this.gameIO = requireNonNull(gameIO, "The gameIO must not be null");
        this.gameMap = requireNonNull(gameMap, "The gameMap must not be null");
        this.player = requireNonNull(player, "The player must not be null");
    }

    public NavigationMenuItem diplayMenuAndRequestInput() {
        printMenuOptions();
        return readUserChoice();
    }

    private void printMenuOptions() {

        gameIO.print("Choose from the options below:");
        for (NavigationMenuItem item : NavigationMenuItem.values) {
            gameIO.print(item.getKeyBinding() + ". " + item.getDescription());
        }
    }

    private void displayMapLegend() {
        gameIO.print("");
        for (LocationType value : LocationType.values()) {
            gameIO.print(value.getMapMark() + " => " + value.getDescription());
        }
        gameIO.print("");
    }

    private NavigationMenuItem selectMenuItem() {
        NavigationMenuItem menuItem = readUserChoice();
        return menuItem;
    }

    private NavigationMenuItem readUserChoice() {

        final Optional<NavigationMenuItem> optionalChosenItem = NavigationMenuItem.fromString(gameIO.readInputWithoutPrompt());

        if (!optionalChosenItem.isPresent()) {
            gameIO.print("Invalid input.");
            return NavigationMenuItem.COMMANDS;
        }
        return optionalChosenItem.get();
    }

    public void navigate() {
        displayMapLegend();
        NavigationMenuItem choice = diplayMenuAndRequestInput();

        while (NavigationMenuItem.EXIT != choice) {
            GameContext.navigate(choice, player, gameMap);
            choice = selectMenuItem();
        }

    }

    public void showMap() {
        final MapView mapView = new MapView(gameMap, player);
        gameIO.print(mapView.mapView());
    }

}
