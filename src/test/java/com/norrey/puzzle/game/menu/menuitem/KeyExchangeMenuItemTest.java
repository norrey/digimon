package com.norrey.puzzle.game.menu.menuitem;

import com.norrey.puzzle.game.menu.item.KeyExchangeMenuItem;
import java.util.Optional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 20, 2018, 12:36:17 PM
 */
public class KeyExchangeMenuItemTest {

    @BeforeClass
    public void setup() {
    }

    @Test
    public void testCorrectStringToKeyExchangeMenuItem() {
        final Optional<KeyExchangeMenuItem> menuItem = KeyExchangeMenuItem.fromString("1");
        Assert.assertTrue(menuItem.isPresent());
        Assert.assertEquals(KeyExchangeMenuItem.EXCHANGE_KEY, menuItem.get());
    }

    @Test
    public void testWrongStringToKeyExchangeMenuItem() {
        final Optional<KeyExchangeMenuItem> menuItem = KeyExchangeMenuItem.fromString("$$");
        Assert.assertFalse(menuItem.isPresent());
    }

    @Test
    public void testNullToKeyExchangeMenuItem() {
        final Optional<KeyExchangeMenuItem> menuItem = KeyExchangeMenuItem.fromString(null);
        Assert.assertFalse(menuItem.isPresent());
    }

    @Test
    public void testEmptyStringToKeyExchangeMenuItem() {
        final Optional<KeyExchangeMenuItem> menuItem = KeyExchangeMenuItem.fromString(" ");
        Assert.assertFalse(menuItem.isPresent());
    }

}
