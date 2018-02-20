package com.norrey.puzzle.game.context;

import com.norrey.puzzle.game.menu.item.MainMenuItem;
import com.norrey.puzzle.game.api.impl.ExitGame;
import com.norrey.puzzle.game.api.Game;
import com.norrey.puzzle.game.api.impl.NewGame;
import com.norrey.puzzle.game.api.impl.OldGame;
import com.norrey.puzzle.game.menu.item.NavigationMenuItem;
import com.norrey.puzzle.game.navigation.Navigator;
import com.norrey.puzzle.game.navigation.impl.CommandsNavigator;
import com.norrey.puzzle.game.navigation.impl.DownNavigator;
import com.norrey.puzzle.game.navigation.impl.LeftNavigator;
import com.norrey.puzzle.game.navigation.impl.LegendNavigator;
import com.norrey.puzzle.game.navigation.impl.MapNavigator;
import com.norrey.puzzle.game.navigation.impl.PlayerNavigator;
import com.norrey.puzzle.game.navigation.impl.RightNavigator;
import com.norrey.puzzle.game.navigation.impl.SaveNavigator;
import com.norrey.puzzle.game.navigation.impl.UpNavigator;
import com.norrey.puzzle.map.GameMap;
import com.norrey.puzzle.model.Player;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 12:04:40 AM
 */
public class GameContext {

    private static final ConcurrentMap<MainMenuItem, Game> MAIN_MENU_CONTEXT = new ConcurrentHashMap<>();
    private static final ConcurrentMap<NavigationMenuItem, Navigator> PLAY_NAVIGATION_CONTEXT = new ConcurrentHashMap<>();

    static {
        MAIN_MENU_CONTEXT.put(MainMenuItem.START_NEW, new NewGame());
        MAIN_MENU_CONTEXT.put(MainMenuItem.LOAD_SAVED_GAME, new OldGame());
        MAIN_MENU_CONTEXT.put(MainMenuItem.EXIT, new ExitGame());
    }

    static {
        PLAY_NAVIGATION_CONTEXT.put(NavigationMenuItem.UP, new UpNavigator());
        PLAY_NAVIGATION_CONTEXT.put(NavigationMenuItem.DOWN, new DownNavigator());
        PLAY_NAVIGATION_CONTEXT.put(NavigationMenuItem.LEFT, new LeftNavigator());
        PLAY_NAVIGATION_CONTEXT.put(NavigationMenuItem.RIGHT, new RightNavigator());
        PLAY_NAVIGATION_CONTEXT.put(NavigationMenuItem.LEGEND, new LegendNavigator());
        PLAY_NAVIGATION_CONTEXT.put(NavigationMenuItem.COMMANDS, new CommandsNavigator());
        PLAY_NAVIGATION_CONTEXT.put(NavigationMenuItem.MAP, new MapNavigator());
        PLAY_NAVIGATION_CONTEXT.put(NavigationMenuItem.PLAYER, new PlayerNavigator());
        PLAY_NAVIGATION_CONTEXT.put(NavigationMenuItem.SAVE, new SaveNavigator());
    }

    public static void play(final MainMenuItem menuItem) {
        requireNonNull(menuItem, "The menuItem must not be null");
        MAIN_MENU_CONTEXT.get(menuItem).play();
    }

    public static void navigate(final NavigationMenuItem navigationMenuItem, final Player player, final GameMap gameMap) {
        requireNonNull(navigationMenuItem, "The navigation menu item cannot be null");
        requireNonNull(player, "The player menu item cannot be null");
        requireNonNull(gameMap, "The gameMap menu item cannot be null");
        PLAY_NAVIGATION_CONTEXT.get(navigationMenuItem).navigate(player, gameMap);
    }

}
