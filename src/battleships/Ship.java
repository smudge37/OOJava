package battleships;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Ship {
    private boolean isHorizontal;
    private int[] coordinates;

    public Ship(@NotNull int[] coordinates) {
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
