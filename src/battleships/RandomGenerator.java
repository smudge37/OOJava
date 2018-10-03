package battleships;

public class RandomGenerator {
    private BattleshipsUtil bu = new BattleshipsUtil();

    int[] generateTarget(char[][] targetBattlefield ) {
        int targetsLeft = 100 - this.bu.countHits(targetBattlefield);
        int[] target = new int[2];

        int countdown =  new Double(Math.floor(targetsLeft * Math.random())).intValue();
        for (int i = 2; i < 12; i++) {
            for (int j = 2; j < 12; j++) {
                if (targetBattlefield[i][j] != 'x') {
                    if (countdown == 0) {
                        target[0] = i;
                        target[1] = j;
                    } else {
                        countdown--;
                    }
                }
            }
        }
        return target;
    }

    char[][] generateBattlefield() {
        char[][] battlefield = this.bu.createBattlefield();
        for (int i = 0; i < 5; i++) {
            Ship ship;
            do {
                ship = this.generateShip(i + 1);
            } while (!this.bu.isNoCollision(battlefield, ship));
            battlefield = this.bu.addShip(battlefield, ship);
        }
        return battlefield;
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
        int shipLength = bu.shipLength(shipNumber);
        int[] shipCoordinates = new int[4];
        shipCoordinates[0] = new Double(Math.floor( (10) * Math.random() )).intValue();
        shipCoordinates[1] = new Double(Math.floor( (11 - shipLength) * Math.random() )).intValue();
        shipCoordinates[2] = shipCoordinates[0];
        shipCoordinates[3] = shipCoordinates[1] + shipLength - 1;
        return new Ship(shipCoordinates);
    }

    private Ship generateVerticalShip(int shipNumber) {
        int shipLength = bu.shipLength(shipNumber);
        int[] shipCoordinates = new int[4];
        shipCoordinates[0] = new Double(Math.floor( (11 - shipLength) * Math.random() )).intValue();
        shipCoordinates[1] = new Double(Math.floor( (10) * Math.random() )).intValue();
        shipCoordinates[2] = shipCoordinates[0] + shipLength - 1;
        shipCoordinates[3] = shipCoordinates[1];
        return new Ship(shipCoordinates);
    }
}
