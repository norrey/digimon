package com.norrey.puzzle.game.api.impl;

import com.norrey.puzzle.game.api.Game;
import com.norrey.puzzle.game.context.DIContext;
import com.norrey.puzzle.io.GameIO;
import com.norrey.puzzle.util.Resource;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 12:21:32 AM
 */
public class ExitGame implements Game {

    final GameIO gameIO = DIContext.getBean(GameIO.class);

    @Override
    public void play() {
        gameIO.print(Resource.MESSAGES.getString("USER_EXIT_SUCCESS"));
    }

}
