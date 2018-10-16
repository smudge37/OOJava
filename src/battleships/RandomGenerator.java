package battleships;

public class RandomGenerator {
    private BattleshipsUtil bu = new BattleshipsUtil();

    int[] generateTarget(char[][] targetBattlefield) {
        int targetsLeft = 100 - this.bu.countHits(targetBattlefield);
        int[] target = new int[2];

        int countdown =  Double.valueOf(Math.floor(targetsLeft * Math.random())).intValue();
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (targetBattlefield[i + 2][j + 2] == ' ') {
                    if (countdown == 0) {
                        target[0] = i;
                        target[1] = j;
                        return target;
                    } else {
                        countdown = countdown - 1;
                    }
                }
            }
        }
        return target;
    }

    Ship generateShip(int shipNumber) {
        double rand = Math.random();

        if (rand < 0.5) {
            return this.generateHorizontalShip(shipNumber);
        } else {
            return this.generateVerticalShip(shipNumber);
        }
    }

    private Ship generateHorizontalShip(int shipNumber) {
        int shipLength = Ship.shipLength(shipNumber);
        int[] shipCoordinates = new int[4];
        shipCoordinates[0] = Double.valueOf(Math.floor( (10) * Math.random() )).intValue();
        shipCoordinates[1] = Double.valueOf(Math.floor( (11 - shipLength) * Math.random() )).intValue();
        shipCoordinates[2] = shipCoordinates[0];
        shipCoordinates[3] = shipCoordinates[1] + shipLength - 1;
        return new Ship(shipCoordinates);
    }

    private Ship generateVerticalShip(int shipNumber) {
        int shipLength = Ship.shipLength(shipNumber);
        int[] shipCoordinates = new int[4];
        shipCoordinates[0] = Double.valueOf(Math.floor( (11 - shipLength) * Math.random() )).intValue();
        shipCoordinates[1] = Double.valueOf(Math.floor( (10) * Math.random() )).intValue();
        shipCoordinates[2] = shipCoordinates[0] + shipLength - 1;
        shipCoordinates[3] = shipCoordinates[1];
        return new Ship(shipCoordinates);
    }
}
