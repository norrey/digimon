package com.norrey.puzzle.map;

import static com.norrey.puzzle.util.ColorFormatter.blue;
import static com.norrey.puzzle.util.ColorFormatter.cyan;
import static com.norrey.puzzle.util.ColorFormatter.green;
import static com.norrey.puzzle.util.ColorFormatter.red;
import static com.norrey.puzzle.util.ColorFormatter.yellow;
import static java.util.Objects.requireNonNull;

public enum LocationType {

    FREEPATH("Blank. Explore in this space", " "),
    FRIEND("Friendly Robot. Get cryptographic keys from her in exchange of bitcoins", green("F")),
    ENEMY("Enemy Robot. You must have cryptographic keys to unlock this robot", red("E")),
    MINE("Bit Mine. All your bitcoin source", cyan("M")),
    BLACK_BOX("Black Box. The knwoledgebase for AI, Machine Learning and IOT", yellow("B")),
    PLAYER("Player. Your position. ", blue("P")),
    DEAD("A character died here.", red("X"));

    private final String description;
    private final String mapMark;

    LocationType(final String description, final String mapMark) {
        this.description = requireNonNull(description);
        this.mapMark = requireNonNull(mapMark);
    }

    public static final LocationType values[] = values();

    public String getDescription() {
        return description;
    }

    public String getMapMark() {
        return mapMark;
    }

}
