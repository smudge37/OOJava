package battleships;

import java.util.Arrays;

public class BattleshipsUtil {

    private char[][] playerGridArray;
    private char[][] computerGridArray;
    private char[][] computerMaskedGridArray;

    // Initialising grid
    public BattleshipsUtil() {
        this.initialiseGrids();
    }

    private void initialiseGrids() {
        this.playerGridArray = createBattlefield();
        this.computerGridArray = createBattlefield();
        this.computerMaskedGridArray = createBattlefield();
    }

    public char[][] createBattlefield() {
        char[][] battlefield = new char[14][14];
        for (int i = 0; i < 14; i++) {
            if (i < 2 || i >= 12) {
                battlefield[0][i] = ' ';
                battlefield[1][i] = ' ';
                if (i == 0 || i == 13) {
                    battlefield[2][i] = '0';
                } else {
                    battlefield[2][i] = '|';
                }
            } else {
                battlefield[0][i] = Integer.toString(i - 2).toCharArray()[0];
                battlefield[1][i] = '-';
                battlefield[2][i] = ' ';
            }
        }
        battlefield[12] = Arrays.copyOf(battlefield[1], 14);
        battlefield[13] = Arrays.copyOf(battlefield[0], 14);
        for (int i = 3; i < 12; i++) {
            battlefield[i] = Arrays.copyOf(battlefield[2], 14);
            battlefield[i][0] = Integer.toString(i-2).toCharArray()[0];
            battlefield[i][13] = Integer.toString(i-2).toCharArray()[0];
        }
        return battlefield;
    }

    // Printing
    public void printPlayerGrid() {

        for (char[] row: this.playerGridArray) {
            System.out.println(new String(row));
        }

    }

    public void printComputerGrid() {
        for (char[] row: this.computerGridArray) {
            System.out.println(new String(row));
        }
    }

    public void printComputerMaskedGrid() {

        for (char[] row: this.computerMaskedGridArray) {
            System.out.println(new String(row));
        }
    }

    // Utility
    public int shipLength(int shipNumber) {
        int[] shipLengths = {2, 3, 3, 4, 5};
        return shipLengths[shipNumber - 1];
    }

    // Ship Placement
    public char[][] addShip(char[][] battlefield, Ship ship) {
        int[] coordinates = ship.getCoordinates();
        if (isNoCollision(battlefield, ship)) {
            if (ship.isHorizontal()) {
                int gridRow = 2 + coordinates[0];
                int startCol = 2 + coordinates[1];
                int endCol = 2 + coordinates[3];
                battlefield[gridRow][startCol] = '<';
                battlefield[gridRow][endCol] = '>';
                for (int gridCol = startCol + 1; gridCol < endCol; gridCol++) {
                    battlefield[gridRow][gridCol] = '=';
                }
            } else {
                int gridCol = 2 + coordinates[1];
                int startRow = 2 + coordinates[0];
                int endRow = 2 + coordinates[2];
                battlefield[startRow][gridCol] = '^';
                battlefield[endRow][gridCol] = 'v';
                for (int gridRow = startRow + 1; gridRow < endRow; gridRow++) {
                    battlefield[gridRow][gridCol] = '|';
                }
            }
        } else {
            throw new CellCollisionException();
        }
        return battlefield;
    }

    public void addPlayerShip(Ship ship) {
        this.playerGridArray = addShip(this.playerGridArray, ship);
    }

    public void addComputerShip(Ship ship) {
        this. computerGridArray = addShip(this.computerGridArray, ship);
    }


    // Check validity
    public boolean coordsValid(int shipNumber, int[] coords) {
        int shipLength = this.shipLength(shipNumber);
        if (coords.length != 4) {
            return false;
        } else {
            boolean firstCase = (coords[1] == coords[3] && (coords[0] + shipLength - 1 == coords[2] ||
                    coords[2] + shipLength - 1 == coords[0]) );
            boolean secondCase = (coords[0] == coords[2] && (coords[1] + shipLength - 1 == coords[3] ||
                    coords[3] + shipLength - 1 == coords[1]) );
            return firstCase || secondCase;
        }
    }

    public boolean isNoCollision(char[][] battlefield, Ship ship) {
        int[] coordinates = ship.getCoordinates();
        if (ship.isHorizontal()) {
            int row = 2 + coordinates[0];
            for (int col = 2 + coordinates[1]; col <= 2 + coordinates[3]; col++) {
                if (battlefield[row][col] != ' ') {
                    System.out.println("Collison Coordinates: ("
                            + row + "," + col + ")");
                    return false;
                }
            }
        } else {
            int col = 2 + coordinates[1];
            for (int row = 2 + coordinates[0]; row <= 2 + coordinates[2]; row++) {
                if (battlefield[row][col] != ' ') {
                    System.out.println("Collison Coordinates: ("
                            + row + "," + col + ")");
                    return false;
                }
            }
        }
        return true;
    }

    // Getters
    public char[][] getPlayerGridArray() {
        return this.playerGridArray;
    }

    public char[][] getComputerGridArray() {
        return computerGridArray;
    }
}
