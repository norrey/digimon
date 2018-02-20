package com.norrey.puzzle.game.menu.menuitem;

import com.norrey.puzzle.game.menu.item.MainMenuItem;
import java.util.Optional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 20, 2018, 12:36:17 PM
 */
public class MainMenuItemTest {

    @BeforeClass
    public void setup() {
    }

    @Test
    public void testCorrectStringToMainMenuItem() {
        final Optional<MainMenuItem> menuItem = MainMenuItem.fromString("1");
        Assert.assertTrue(menuItem.isPresent());
        Assert.assertEquals(MainMenuItem.START_NEW, menuItem.get());
    }

    @Test
    public void testWrongStringToMainMenuItem() {
        final Optional<MainMenuItem> menuItem = MainMenuItem.fromString("9000");
        Assert.assertFalse(menuItem.isPresent());
    }

    @Test
    public void testNullToMainMenuItem() {
        final Optional<MainMenuItem> menuItem = MainMenuItem.fromString(null);
        Assert.assertFalse(menuItem.isPresent());
    }

    @Test
    public void testEmptyStringToMainMenuItem() {
        final Optional<MainMenuItem> menuItem = MainMenuItem.fromString("");
        Assert.assertFalse(menuItem.isPresent());
    }

}
