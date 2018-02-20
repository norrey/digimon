package com.norrey.puzzle.game.menu.menuitem;

import com.norrey.puzzle.game.menu.item.LearnMenuItem;
import java.util.Optional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 20, 2018, 12:36:17 PM
 */
public class LearnMenuItemTest {

    @BeforeClass
    public void setup() {
    }

    @Test
    public void testCorrectStringToLearnMenuItem() {
        final Optional<LearnMenuItem> menuItem = LearnMenuItem.fromString("1");
        Assert.assertTrue(menuItem.isPresent());
        Assert.assertEquals(LearnMenuItem.LEARN, menuItem.get());
    }

    @Test
    public void testWrongStringToLearnMenuItem() {
        final Optional<LearnMenuItem> menuItem = LearnMenuItem.fromString("?");
        Assert.assertFalse(menuItem.isPresent());
    }

    @Test
    public void testNullToLearnMenuItem() {
        final Optional<LearnMenuItem> menuItem = LearnMenuItem.fromString(null);
        Assert.assertFalse(menuItem.isPresent());
    }

    @Test
    public void testEmptyStringToLearnMenuItem() {
        final Optional<LearnMenuItem> menuItem = LearnMenuItem.fromString(" ");
        Assert.assertFalse(menuItem.isPresent());
    }

}
