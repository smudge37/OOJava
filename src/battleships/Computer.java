package battleships;

public class Computer {
    private BattleshipsUtil bu;
    private char[][] battlefield;

    public Computer(BattleshipsUtil bu) {
        this.bu = bu;
        generateBattlefield();
    }

    public void generateBattlefield() {
        this.battlefield = bu.createBattlefield();
        for (int i = 0; i < 5; i++) {
            Ship ship;
            do {
                ship = this.generateShip(i + 1);
            } while (!bu.isNoCollision(battlefield, ship));
            battlefield = bu.addShip(battlefield, ship);
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
