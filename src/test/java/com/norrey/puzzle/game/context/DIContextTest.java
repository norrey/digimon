package com.norrey.puzzle.game.context;

import com.norrey.puzzle.game.manager.NavigationManager;
import com.norrey.puzzle.game.provider.GameLevelConfigurationProvider;
import com.norrey.puzzle.game.provider.GameStateProvider;
import com.norrey.puzzle.game.provider.level.GameLevelConfigurationProviderImpl;
import com.norrey.puzzle.game.provider.state.GameStateProviderImpl;
import com.norrey.puzzle.model.Player;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 11:57:34 PM
 */
public class DIContextTest {

    @BeforeClass
    public void setup() {
    }

    @AfterClass
    public void cleanUp() {
    }

    @SuppressWarnings("unchecked")
    @Test(dataProvider = "testSuccessfulClassloadingDataProvider")
    public void testApiLoadedWithImplementation(final Class api, final Class impl) {
        Assert.assertEquals(DIContext.getBean(api), DIContext.getBean(impl));
    }

    @DataProvider(name = "testSuccessfulClassloadingDataProvider")
    public Object[][] testSuccessfulClassloadingDataProvider() {
        return new Object[][]{
            {GameLevelConfigurationProvider.class, GameLevelConfigurationProviderImpl.class},
            {GameStateProvider.class, GameStateProviderImpl.class}
        };
    }

    @SuppressWarnings("unchecked")
    @Test(dataProvider = "testGetUnloadedClassDataProvider", expectedExceptions = RuntimeException.class)
    public void testGetUnloadedClass(final Class clazz) {
        DIContext.getBean(clazz);
    }

    @DataProvider(name = "testGetUnloadedClassDataProvider")
    public Object[][] testGetUnloadedClassDataProvider() {
        return new Object[][]{
            {NavigationManager.class},
            {Player.class}
        };
    }

    @SuppressWarnings("unchecked")
    @Test(dataProvider = "testNullClassRequestedDataProvider", expectedExceptions = NullPointerException.class)
    public void testGetNullProvided(final Class clazz) {
        DIContext.getBean(clazz);
    }

    @DataProvider(name = "testNullClassRequestedDataProvider")
    public Object[][] testNullClassRequestedDataProvider() {
        return new Object[][]{
            {null},
            {null}
        };
    }

}
