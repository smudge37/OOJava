package battleships;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Ship {
    private static int[] SHIP_LENGTHS = {2,3,3,4,5};
    private static String[] SHIP_NAMES = {"Frigate", "Submarine", "Cruiser", "Battleship", "Carrier"};
    private String name;
    private Combatant owner;
    private boolean isHorizontal;
    private int[][] cellsFilled;
    private boolean isSunk;
    private int hitCount;

    Ship(int shipNumber, @NotNull int[] coordinates) {
        if (!Ship.coordsValid(shipNumber, coordinates)) {
            throw new CoordsInvalidException();
        }
        this.findDirection(coordinates);
        int[] orderedCoordinates = this.giveOrderedCoordinates(this.isHorizontal, coordinates);

        this.name = Ship.SHIP_NAMES[shipNumber - 1];
        this.owner = owner;
        this.cellsFilled = fillCells(orderedCoordinates);
        this.isSunk = false;
        this.hitCount = 0;
    }

    // Static Methods
    static int shipLength(int shipNumber) {
        return SHIP_LENGTHS[shipNumber - 1];
    }

    public static boolean coordsValid(int shipNumber, int[] coords) {
        int shipLength = shipLength(shipNumber);
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

    // Instance Methods
    private void findDirection(int[] coordinates) {
        if (coordinates[0] == coordinates[2]) {
            this.isHorizontal = true;
        } else if (coordinates[1] == coordinates[3]){
            this.isHorizontal = false;
        } else {
            throw new CoordsInvalidException();
        }
    }

    private int[] giveOrderedCoordinates(boolean isHorizontal, int[] coordinatesIn) {
        int[] coordinatesOut = Arrays.copyOf(coordinatesIn, 4);

        if (isHorizontal) {
            if (coordinatesOut[1] > coordinatesOut[3]) {
                int extraCoord = coordinatesOut[1];
                coordinatesOut[1] = coordinatesOut[3];
                coordinatesOut[3] = extraCoord;
            }
        } else {
            if (coordinatesOut[0] > coordinatesOut[2]) {
                int extraCoord = coordinatesOut[0];
                coordinatesOut[0] = coordinatesOut[2];
                coordinatesOut[2] = extraCoord;
            }
        }
        return coordinatesOut;
    }

    int[][] fillCells(int[] orderedCoordinates) {
        int[][] cFilled;
        if (this.isHorizontal) {
            int numSpaces = orderedCoordinates[3] + 1 - orderedCoordinates[1];
            cFilled = new int[numSpaces][2];
            for (int cellNum = 0; cellNum < numSpaces; cellNum++) {
                cFilled[cellNum][0] = 2 + orderedCoordinates[0];
                cFilled[cellNum][1] = 2 + orderedCoordinates[1] + cellNum;
            }
        } else {
            int numSpaces = orderedCoordinates[2] + 1 - orderedCoordinates[0];
            cFilled = new int[numSpaces][2];
            for (int cellNum = 0; cellNum < numSpaces; cellNum++) {
                cFilled[cellNum][1] = 2 + orderedCoordinates[1];
                cFilled[cellNum][0] = 2 + orderedCoordinates[0] + cellNum;
            }
        }
        return cFilled;
    }

    boolean isHorizontal() {
        return isHorizontal;
    }

    int getLength() {
        return this.cellsFilled.length;
    }

    int[][] getCellsFilled() {
        return this.cellsFilled;
    }

    boolean incrementHitCount() {
        this.hitCount++;
        if (this.hitCount == this.getLength()) {
            this.sinkShip();
            return true;
        } else {
            return false;
        }
    }

    private void sinkShip() {
        this.isSunk = true;
    }

    boolean isSunk() {
        return this.isSunk;
    }

    public String getName() {
        return name;
    }
}
