package com.norrey.puzzle.game.provider.level;

import java.io.Serializable;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 19, 2018, 9:18:25 AM
 */
public class LevelConfiguration implements Serializable {

    private static final long serialVersionUID = 6240294016309172630L;

    private final String name;
    private final int mapSize;
    private final CharacterConfiguration characterConfiguration;

    public LevelConfiguration(final LevelConfigurationBuilder levelConfigurationBuilder) {
        this.name = levelConfigurationBuilder.name;
        this.mapSize = levelConfigurationBuilder.mapSize;
        this.characterConfiguration = levelConfigurationBuilder.characterConfiguration;
    }

    public String getName() {
        return name;
    }

    public int getMapSize() {
        return mapSize;
    }

    public CharacterConfiguration getCharacterConfiguration() {
        return characterConfiguration;
    }

    public static class LevelConfigurationBuilder {

        private String name;
        private int mapSize;
        private CharacterConfiguration characterConfiguration;

        public LevelConfigurationBuilder withName(final String name) {
            this.name = name;
            return this;
        }

        public LevelConfigurationBuilder withMapSize(final int mapSize) {
            this.mapSize = mapSize;
            return this;
        }

        public LevelConfigurationBuilder withCharacterConfiguration(final CharacterConfiguration characterConfiguration) {
            this.characterConfiguration = characterConfiguration;
            return this;
        }

        public LevelConfiguration build() {
            return new LevelConfiguration(this);
        }
    }

}
