package battleships;

import java.util.Arrays;
import java.util.logging.Logger;

public class GridManager {

    private Logger logger = Logger.getLogger(GridManager.class.getName());
    private char[][] playerGridArray;
    private char[][] computerGridArray;

    public GridManager() {
        this.drawGrid();
    }

    private void drawGrid() {
        this.playerGridArray = new char[14][14];
        for (int i = 0; i < 14; i++) {
            if (i < 2 || i >= 12) {
                this.playerGridArray[0][i] = ' ';
                this.playerGridArray[1][i] = ' ';
                if (i == 0 || i == 13) {
                    this.playerGridArray[2][i] = '0';
                } else {
                    this.playerGridArray[2][i] = '|';
                }
            } else {
                this.playerGridArray[0][i] = Integer.toString(i - 2).toCharArray()[0];
                this.playerGridArray[1][i] = '-';
                this.playerGridArray[2][i] = ' ';
            }
        }
        this.playerGridArray[12] = Arrays.copyOf(this.playerGridArray[1], 14);
        this.playerGridArray[13] = Arrays.copyOf(this.playerGridArray[0], 14);
        for (int i = 3; i < 12; i++) {
            this.playerGridArray[i] = Arrays.copyOf(this.playerGridArray[2], 14);
            this.playerGridArray[i][0] = Integer.toString(i-2).toCharArray()[0];
            this.playerGridArray[i][13] = Integer.toString(i-2).toCharArray()[0];
        }
    }

    private boolean playerCellFree(int row, int column) {
        return playerGridArray[row][column] == ' ';
    }

    public boolean coordsValid(int shipNumber, int[] coords) {
        if (coords.length != 4) {
            return false;
        } else {
            int[] shipLength = {0, 2, 3, 3, 4, 5};
            boolean firstCase = (coords[1] == coords[3] && (coords[0] + shipLength[shipNumber] - 1 == coords[2] ||
                    coords[2] + shipLength[shipNumber] - 1 == coords[0]) );
            boolean secondCase = (coords[0] == coords[2] && (coords[1] + shipLength[shipNumber] - 1 == coords[3] ||
                    coords[3] + shipLength[shipNumber] - 1 == coords[1]) );
            return firstCase || secondCase;
        }
    }

    public void addPlayerShip(int[] coords) {
        if (coords[0] == coords[2]) {
            if (coords[1] < coords[3]) {
                for (int i = coords[1]; i <= coords[3]; i++) {
                    if (playerCellFree(2 +coords[0], 2 + i)) {
                        playerGridArray[2 + coords[0]][2 + i] = '=';
                    } else {
                        throw new CellCollisionException();
                    }
                }
            } else {
                for (int i = coords[3]; i <= coords[1]; i++) {
                    playerGridArray[2 + coords[0]][2 + i] = '=';
                }
            }
        } else if (coords[1] == coords[3]) {
            if (coords[0] < coords[2]) {
                for (int i = coords[0]; i <= coords[2]; i++) {
                    playerGridArray[2 + i][2 + coords[1]] = '|';
                }
            } else {
                for (int i = coords[2]; i <= coords[0]; i++) {
                    playerGridArray[2 + i][2 + coords[1]] = '|';
                }
            }
        } else {
            throw new CoordsInvalidException();
        }
    }

    public char[][] getGrid() {
        return this.playerGridArray;
    }

    public void printGrid() {

        for (char[] row: playerGridArray) {
            System.out.println(new String(row));
        }

    }
}
