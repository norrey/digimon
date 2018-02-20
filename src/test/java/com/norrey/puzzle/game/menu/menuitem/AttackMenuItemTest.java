package com.norrey.puzzle.game.menu.menuitem;

import com.norrey.puzzle.game.menu.item.AttackMenuItem;
import java.util.Optional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 20, 2018, 12:36:17 PM
 */
public class AttackMenuItemTest {

    @BeforeClass
    public void setup() {
    }

    @Test
    public void testCorrectStringToAttackMenuItem() {
        final Optional<AttackMenuItem> menuItem = AttackMenuItem.fromString("1");
        Assert.assertTrue(menuItem.isPresent());
        Assert.assertEquals(AttackMenuItem.ATTACK, menuItem.get());
    }

    @Test
    public void testWrongStringToAttackMenuItem() {
        final Optional<AttackMenuItem> menuItem = AttackMenuItem.fromString("+");
        Assert.assertFalse(menuItem.isPresent());
    }

    @Test
    public void testNullToAttackMenuItem() {
        final Optional<AttackMenuItem> menuItem = AttackMenuItem.fromString(null);
        Assert.assertFalse(menuItem.isPresent());
    }

    @Test
    public void testEmptyStringToAttackMenuItem() {
        final Optional<AttackMenuItem> menuItem = AttackMenuItem.fromString("");
        Assert.assertFalse(menuItem.isPresent());
    }

}
