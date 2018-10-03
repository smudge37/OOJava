package battleships;

import java.util.Arrays;

public class GridManager {

    private char[][] playerGridArray;
    private char[][] computerGridArray;

    // Initialising grid
    public GridManager() {
        this.drawGrids();
    }

    private void drawGrids() {
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

        this.computerGridArray = new char[14][];
        for (int row = 0; row < 14; row++) {
            this.computerGridArray[row] = Arrays.copyOf(this.playerGridArray[row], 14);
        }
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

    private int shipLength(int shipNumber) {
        int[] shipLengths = {2, 3, 3, 4, 5};
        return shipLengths[shipNumber - 1];
    }

    // Player ship placement
    public void addPlayerShip(Ship ship) {
        int[] coordinates = ship.getCoordinates();
        if (isNoPlayerCollision(ship)) {
            if (ship.isHorizontal()) {
                int gridRow = 2 + coordinates[0];
                int startCol = 2 + coordinates[1];
                int endCol = 2 + coordinates[3];
                this.playerGridArray[gridRow][startCol] = '<';
                this.playerGridArray[gridRow][endCol] = '>';
                for (int gridCol = startCol + 1; gridCol < endCol; gridCol++) {
                    this.playerGridArray[gridRow][gridCol] = '=';
                }
            } else {
                int gridCol = 2 + coordinates[1];
                int startRow = 2 + coordinates[0];
                int endRow = 2 + coordinates[2];
                this.playerGridArray[startRow][gridCol] = '^';
                this.playerGridArray[endRow][gridCol] = 'v';
                for (int gridRow = startRow + 1; gridRow < endRow; gridRow++) {
                    this.playerGridArray[gridRow][gridCol] = '|';
                }
            }
        } else {
            throw new CellCollisionException();
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

    private boolean isNoPlayerCollision(Ship ship) {
        int[] coordinates = ship.getCoordinates();
        if (ship.isHorizontal()) {
            int row = 2 + coordinates[0];
            for (int col = 2 + coordinates[1]; col <= 2 + coordinates[3]; col++) {
                if (this.playerGridArray[row][col] != ' ') {
                    System.out.println("Collison Coordinates: ("
                            + row + "," + col + ")");
                    return false;
                }
            }
        } else {
            int col = 2 + coordinates[1];
            for (int row = 2 + coordinates[0]; row <= 2 + coordinates[2]; row++) {
                if (this.playerGridArray[row][col] != ' ') {
                    System.out.println("Collison Coordinates: ("
                            + row + "," + col + ")");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isNoComputerCollision(Ship ship) {
        int[] coordinates = ship.getCoordinates();
        if (ship.isHorizontal()) {
            int row = 2 + coordinates[0];
            for (int col = 2 + coordinates[1]; col <= 2 + coordinates[3]; col++) {
                if (this.computerGridArray[row][col] != ' ') {
                    return false;
                }
            }
        } else {
            int col = 2 + coordinates[1];
            for (int row = 2 + coordinates[0]; row <= 2 + coordinates[2]; row++) {
                if (this.computerGridArray[row][col] != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Computer grid generation
    public void generateComputerGrid() {
        for (int i = 0; i < 5; i++) {
            Ship ship;
            do {
                ship = this.generateShip(i + 1);
            } while (!this.isNoComputerCollision(ship));
            addComputerShip(ship);
        }

    }

    public void addComputerShip(Ship ship) {
        int[] coordinates = ship.getCoordinates();
        if (isNoComputerCollision(ship)) {
            if (ship.isHorizontal()) {
                int gridRow = 2 + coordinates[0];
                int startCol = 2 + coordinates[1];
                int endCol = 2 + coordinates[3];
                this.computerGridArray[gridRow][startCol] = '<';
                this.computerGridArray[gridRow][endCol] = '>';
                for (int gridCol = startCol + 1; gridCol < endCol; gridCol++) {
                    this.computerGridArray[gridRow][gridCol] = '=';
                }
            } else {
                int gridCol = 2 + coordinates[1];
                int startRow = 2 + coordinates[0];
                int endRow = 2 + coordinates[2];
                this.computerGridArray[startRow][gridCol] = '^';
                this.computerGridArray[endRow][gridCol] = 'v';
                for (int gridRow = startRow + 1; gridRow < endRow; gridRow++) {
                    this.computerGridArray[gridRow][gridCol] = '|';
                }
            }
        } else {
            throw new CellCollisionException();
        }
    }

    private Ship generateShip(int shipNumber) {
        double rand = Math.random();

        if (rand < 0.5) {
            return this.generateHorizontalShip(shipNumber);
        } else {
            return this.generateVerticalShip(shipNumber);
        }
    }

    private Ship generateHorizontalShip(int shipNumber) {
        int shipLength = this.shipLength(shipNumber);
        int[] shipCoordinates = new int[4];
        shipCoordinates[0] = new Double(Math.floor( (10) * Math.random() )).intValue();
        shipCoordinates[1] = new Double(Math.floor( (11 - shipLength) * Math.random() )).intValue();
        shipCoordinates[2] = shipCoordinates[0];
        shipCoordinates[3] = shipCoordinates[1] + shipLength - 1;
        return new Ship(shipCoordinates);
    }

    private Ship generateVerticalShip(int shipNumber) {
        int shipLength = this.shipLength(shipNumber);
        int[] shipCoordinates = new int[4];
        shipCoordinates[0] = new Double(Math.floor( (11 - shipLength) * Math.random() )).intValue();
        shipCoordinates[1] = new Double(Math.floor( (10) * Math.random() )).intValue();
        shipCoordinates[2] = shipCoordinates[0] + shipLength - 1;
        shipCoordinates[3] = shipCoordinates[1];
        return new Ship(shipCoordinates);
    }
}
