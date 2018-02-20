package com.norrey.puzzle.game.menu.menuitem;

import com.norrey.puzzle.game.menu.item.NavigationMenuItem;
import java.util.Optional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 20, 2018, 12:36:17 PM
 */
public class NavigationMenuItemTest {

    @BeforeClass
    public void setup() {

    }

    @Test
    public void testCorrectStringToNavigationMenuItem() {
        final Optional<NavigationMenuItem> menuItem = NavigationMenuItem.fromString("W");
        Assert.assertTrue(menuItem.isPresent());
        Assert.assertEquals(NavigationMenuItem.UP, menuItem.get());
    }

    @Test
    public void testWrongStringToNavigationMenuItem() {
        final Optional<NavigationMenuItem> menuItem = NavigationMenuItem.fromString("Y");
        Assert.assertFalse(menuItem.isPresent());
    }

    @Test
    public void testNullToNavigationMenuItem() {
        final Optional<NavigationMenuItem> menuItem = NavigationMenuItem.fromString(null);
        Assert.assertFalse(menuItem.isPresent());
    }

    @Test
    public void testEmptyStringToAttackMenuItem() {
        final Optional<NavigationMenuItem> menuItem = NavigationMenuItem.fromString("");
        Assert.assertFalse(menuItem.isPresent());
    }

}
