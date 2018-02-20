package com.norrey.puzzle.game.api.impl;

import com.norrey.puzzle.game.api.Game;
import com.norrey.puzzle.game.context.DIContext;
import com.norrey.puzzle.game.manager.GameManager;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 12:03:36 AM
 */
public class OldGame implements Game {

    final GameManager gameManager = DIContext.getBean(GameManager.class);

    @Override
    public void play() {
        gameManager.startSavedGame();
    }

}
