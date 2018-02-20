package com.norrey.puzzle.game.menu.menuitem;

import com.norrey.puzzle.game.menu.item.MineMenuItem;
import java.util.Optional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 20, 2018, 12:36:17 PM
 */
public class MineMenuItemTest {

    @BeforeClass
    public void setup() {
    }

    @Test
    public void testCorrectStringToMineMenuItem() {
        final Optional<MineMenuItem> menuItem = MineMenuItem.fromString("1");
        Assert.assertTrue(menuItem.isPresent());
        Assert.assertEquals(MineMenuItem.MINE, menuItem.get());
    }

    @Test
    public void testWrongStringToMineMenuItem() {
        final Optional<MineMenuItem> menuItem = MineMenuItem.fromString("()");
        Assert.assertFalse(menuItem.isPresent());
    }

    @Test
    public void testNullToMineMenuItem() {
        final Optional<MineMenuItem> menuItem = MineMenuItem.fromString(null);
        Assert.assertFalse(menuItem.isPresent());
    }

    @Test
    public void testEmptyStringToMineMenuItem() {
        final Optional<MineMenuItem> menuItem = MineMenuItem.fromString(" ");
        Assert.assertFalse(menuItem.isPresent());
    }

}
