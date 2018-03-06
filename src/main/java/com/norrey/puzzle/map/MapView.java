package com.norrey.puzzle.map;

import com.norrey.puzzle.model.Player;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Norrey Okumu <okumu.norrey@gmail.com>
 * @since Feb 17, 2018, 2:24:45 PM
 */
public class MapView {

    private final GameMap map;
    private final Player player;

    public MapView(final GameMap map, final Player player) {
        this.map = requireNonNull(map, "The map must not be null");
        this.player = requireNonNull(player, "The player must not be null.");
    }

    public String mapView() {
        StringBuilder sb = new StringBuilder();
        Location[][] mapArea = map.getMapArea();

        appendColsHeader(sb, mapArea[0].length);

        for (int y = 0; y < mapArea.length; y++) {
            appendRowsHeader(sb);
            for (int x = 0; x < mapArea[y].length; x++) {
                if (playerIsHere(player, x, y)) {
                    appendPlayerMark(sb);
                } else {
                    appendLocationMark(sb, mapArea[x][y]);
                }

            }
            appendRowsHeader(sb);
            sb.append("\n");
        }
        appendColsHeader(sb, mapArea[0].length);

        return sb.toString();

    }

    private void appendColsHeader(StringBuilder sb, int colsNumber) {
        sb.append((" "));
        for (int i = 0; i < colsNumber; i++) {
            sb.append(("*"));
        }
        sb.append("\n");
    }

    private void appendRowsHeader(final StringBuilder sb) {
        requireNonNull(sb, "String builder must not be null.");
        sb.append(("*"));
    }

    private boolean playerIsHere(final Player player, final int x, final int y) {
        return player != null && player.getCoordinate() != null && player.getCoordinate().equals(x, y);
    }

    private void appendPlayerMark(final StringBuilder sb) {
        sb.append((LocationType.PLAYER.getMapMark()));
    }

    private void appendLocationMark(final StringBuilder sb, final Location location) {
        sb.append((location.mapMark()));
    }

}
