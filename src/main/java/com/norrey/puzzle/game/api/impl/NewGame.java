package com.norrey.puzzle.game.api.impl;

import com.norrey.puzzle.game.api.Game;
import com.norrey.puzzle.game.manager.GameManager;
import com.norrey.puzzle.game.context.DIContext;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 12:02:48 AM
 */
public class NewGame implements Game {

    final GameManager gameManager = DIContext.getBean(GameManager.class);

    @Override
    public void play() {
        gameManager.startNewGame();
    }

}
