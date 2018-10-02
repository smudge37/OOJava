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

    private int shipLength(int shipNumber) {
        int[] shipLengths = {2, 3, 3, 4, 5};
        return shipLengths[shipNumber - 1];
    }

    public void addPlayerShip(int[] shipCoordinates) {
        if (shipCoordinates[0] == shipCoordinates[2]) {
            if (shipCoordinates[1] < shipCoordinates[3]) {
                if (arePlayerCellsFree(true,2 +shipCoordinates[0],
                        2 + shipCoordinates[1],2 + shipCoordinates[3])) {
                    for (int i = shipCoordinates[1]; i <= shipCoordinates[3]; i++) {
                        this.playerGridArray[2 + shipCoordinates[0]][2 + i] = '=';
                    }
                } else {
                    throw new CellCollisionException();
                }
            } else {
                if (arePlayerCellsFree(true,2 + shipCoordinates[0],
                        2 + shipCoordinates[3],2+shipCoordinates[1])) {
                    for (int i = shipCoordinates[3]; i <= shipCoordinates[1]; i++) {
                        this.playerGridArray[2 + shipCoordinates[0]][2 + i] = '=';
                    }
                } else {
                    throw new CellCollisionException();
                }
            }
        } else if (shipCoordinates[1] == shipCoordinates[3]) {
            if (shipCoordinates[0] < shipCoordinates[2]) {
                if (arePlayerCellsFree(false,2 + shipCoordinates[0],
                        2 + shipCoordinates[1],2 + shipCoordinates[2])) {
                    for (int i = shipCoordinates[0]; i <= shipCoordinates[2]; i++) {
                        this.playerGridArray[2 + i][2 + shipCoordinates[1]] = '|';
                    }
                } else {
                    throw new CellCollisionException();
                }
            } else {
                if (arePlayerCellsFree(false,2 + shipCoordinates[2],
                        2 + shipCoordinates[1],2 + shipCoordinates[0])) {
                    for (int i = shipCoordinates[2]; i <= shipCoordinates[0]; i++) {
                        this.playerGridArray[2 + i][2 + shipCoordinates[1]] = '|';
                    }
                } else {
                    throw new CellCollisionException();
                }
            }
        } else {
            throw new CoordsInvalidException();
        }
    }

    public void printGrid() {

        for (char[] row: playerGridArray) {
            System.out.println(new String(row));
        }

    }

    // Getters
    public char[][] getPlayerGridArray() {
        return this.playerGridArray;
    }

    public char[][] getComputerGridArray() {
        return computerGridArray;
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

    private boolean arePlayerCellsFree(boolean isHorizontal, int startRow, int startCol, int endCoordinate) {
        if (isHorizontal) {
            for (int col = startCol; col <= endCoordinate; col++) {
                if (this.playerGridArray[startRow][col] == ' ') {
                    return false;
                }
            }
        } else {
            for (int row = startRow; row <= endCoordinate; row++) {
                if (this.playerGridArray[row][startCol] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean areComputerCellsFree(boolean isHorizontal, int startRow, int startCol, int endCoordinate) {
        if (isHorizontal) {
            for (int col = startCol; col <= endCoordinate; col++) {
                if (this.computerGridArray[startRow][col] == ' ') {
                    return false;
                }
            }
        } else {
            for (int row = startRow; row <= endCoordinate; row++) {
                if (this.computerGridArray[row][startCol] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isComputerCollision(int[] shipCoordinates) {
        if (shipCoordinates[0] == shipCoordinates[2]) {
            if (shipCoordinates[1] < shipCoordinates[3]) {
                if (!areComputerCellsFree(true,2 + shipCoordinates[0],
                        2 + shipCoordinates[1],2 + shipCoordinates[3])) {
                    return true;
                }
            } else if (!areComputerCellsFree(true,2 + shipCoordinates[0],
                    2 + shipCoordinates[3],2 + shipCoordinates[1])) {
                return true;
            }
        } else if (shipCoordinates[1] == shipCoordinates[3]) {
            if (shipCoordinates[0] < shipCoordinates[2]) {
                if (!areComputerCellsFree(false,2 + shipCoordinates[0],
                        2 + shipCoordinates[1],2 + shipCoordinates[2])) {
                    return true;
                }
            } else if (!areComputerCellsFree(false,2 + shipCoordinates[2],
                    2 + shipCoordinates[1],2 + shipCoordinates[0])) {
                return true;
            }

        }
        return false;
    }

    // Computer grid generation
    public void populateComputerGrid() {
        for (int i = 0; i < 5; i++) {
            int[] shipCoordinates;
            do {
                shipCoordinates = this.generateShip(i + 1);
            } while (this.isComputerCollision(shipCoordinates));
            addComputerShip(shipCoordinates);
        }

    }

    private void addComputerShip(int[] shipCoordinates) {
        if (shipCoordinates[0] == shipCoordinates[2]) {
            if (shipCoordinates[1] < shipCoordinates[3]) {
                for (int i = shipCoordinates[1]; i <= shipCoordinates[3]; i++) {
                    this.computerGridArray[2 + shipCoordinates[0]][2 + i] = '=';
                }
            } else {
                for (int i = shipCoordinates[3]; i <= shipCoordinates[1]; i++) {
                    this.computerGridArray[2 + shipCoordinates[0]][2 + i] = '=';
                }
            }
        } else if (shipCoordinates[1] == shipCoordinates[3]) {
            if (shipCoordinates[0] < shipCoordinates[2]) {
                for (int i = shipCoordinates[0]; i <= shipCoordinates[2]; i++) {
                    this.computerGridArray[2 + i][2 + shipCoordinates[1]] = '|';
                }
            } else {
                for (int i = shipCoordinates[2]; i <= shipCoordinates[0]; i++) {
                    this.computerGridArray[2 + i][2 + shipCoordinates[1]] = '|';
                }
            }
        }
    }

    private int[] generateShip(int shipNumber) {
        double rand = Math.random();

        if (rand < 0.5) {
            return this.generateHorizontalShip(shipNumber);
        } else {
            return this.generateVerticalShip(shipNumber);
        }
    }

    private int[] generateHorizontalShip(int shipNumber) {
        int shipLength = this.shipLength(shipNumber);
        int[] shipPosition = new int[4];
        shipPosition[0] = 2 + new Double(Math.floor( (10) * Math.random() )).intValue();
        shipPosition[1] = 2 + new Double(Math.floor( (11 - shipLength) * Math.random() )).intValue();
        shipPosition[2] = 2 + shipPosition[0];
        shipPosition[3] = 2 + shipPosition[1] + shipLength - 1;
        return shipPosition;
    }

    private int[] generateVerticalShip(int shipNumber) {
        int shipLength = this.shipLength(shipNumber);
        int[] shipPosition = new int[4];
        shipPosition[0] = 2 + new Double(Math.floor( (11 - shipLength) * Math.random() )).intValue();
        shipPosition[1] = 2 + new Double(Math.floor( (10) * Math.random() )).intValue();
        shipPosition[2] = 2 + shipPosition[0] + shipLength - 1;
        shipPosition[3] = 2 + shipPosition[1];
        return shipPosition;
    }
}
