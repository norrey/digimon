package com.norrey.puzzle.game.provider.level;

import com.norrey.puzzle.model.BitMine;
import com.norrey.puzzle.model.BlackBox;
import com.norrey.puzzle.model.Robot;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 9:32:12 AM
 */
public class CharacterConfiguration implements Serializable {

    private static final long serialVersionUID = -1216304549895420927L;

    private final List<Robot> friends;
    private final List<Robot> enemies;
    private final List<BitMine> bitmines;
    private final List<BlackBox> blackboxes;

    public CharacterConfiguration(final CharacterConfigurationBuilder characterConfigurationBuilder) {
        this.friends = characterConfigurationBuilder.friends;
        this.enemies = characterConfigurationBuilder.enemies;
        this.bitmines = characterConfigurationBuilder.bitmines;
        this.blackboxes = characterConfigurationBuilder.blackboxes;
    }

    public List<Robot> getFriends() {
        return new ArrayList<>(friends);
    }

    public List<Robot> getEnemies() {
        return new ArrayList<>(enemies);
    }

    public List<BitMine> getBitmines() {
        return new ArrayList<>(bitmines);
    }

    public List<BlackBox> getBlackboxes() {
        return new ArrayList<>(blackboxes);
    }

    public static class CharacterConfigurationBuilder {

        private List<Robot> friends;
        private List<Robot> enemies;
        private List<BitMine> bitmines;
        private List<BlackBox> blackboxes;

        public CharacterConfigurationBuilder withFriends(final List<Robot> friends) {
            this.friends = friends;
            return this;
        }

        public CharacterConfigurationBuilder withEnemies(final List<Robot> enemies) {
            this.enemies = enemies;
            return this;
        }

        public CharacterConfigurationBuilder withBitmines(final List<BitMine> bitmines) {
            this.bitmines = bitmines;
            return this;
        }

        public CharacterConfigurationBuilder withBlackboxes(final List<BlackBox> blackboxes) {
            this.blackboxes = blackboxes;
            return this;
        }

        public CharacterConfiguration build() {
            return new CharacterConfiguration(this);
        }

    }

}
