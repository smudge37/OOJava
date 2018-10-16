package battleships;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Ship {
    static int[] SHIP_LENGTHS = {2,3,3,4,5};
    private boolean isHorizontal;
    private int[] coordinates;

    Ship(@NotNull int[] coordinates) {
        if (coordinates.length != 4) {
            throw new CoordsInvalidException();
        } else {
            if (coordinates[0] == coordinates[2]) {
                this.isHorizontal = true;
            } else if (coordinates[1] == coordinates[3]){
                this.isHorizontal = false;
            } else {
                throw new CoordsInvalidException();
            }
            this.coordinates = this.giveOrderedCoordinates(this.isHorizontal, coordinates);
        }
    }

    static int shipLength(int shipNumber) {
        return SHIP_LENGTHS[shipNumber - 1];
    }

    int[] coordinatesFilledByShip() {
        int[] cFilled;
        if (this.isHorizontal) {
            int numSpaces = this.coordinates[3] - this.coordinates[1];
            cFilled = new int [2*(numSpaces)];
            for (int i = 0; i < numSpaces; i++) {
                cFilled[2*i] = this.coordinates[0];
                cFilled[2*i + 1] = this.coordinates[1] + i;
            }
        } else {
            int numSpaces = this.coordinates[2] - this.coordinates[0];
            cFilled = new int [2*(numSpaces)];
            for (int i = 0; i < numSpaces; i++) {
                cFilled[2*i] = this.coordinates[1];
                cFilled[2*i + 1] = this.coordinates[0] + i;
            }
        }
        return cFilled;
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

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public int[] getCoordinates() {
        return coordinates;
    }
}
