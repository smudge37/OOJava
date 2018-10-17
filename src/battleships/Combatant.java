package battleships;

import java.util.Optional;

import static java.util.Objects.isNull;

public class Combatant {
    private boolean isCPU;
    private String name;
    private Ship[] shipArray;
    private char[][] battlefield;
    private char[][] targetBattlefield;
    private boolean isDead;

    public Combatant(String name, boolean isCPU) {
        this.isCPU = isCPU;
        this.name = name;
        this.shipArray = new Ship[5];
        this.battlefield = BattleshipsUtil.createBattlefield();
        this.targetBattlefield = BattleshipsUtil.createBattlefield();
        this.isDead = false;
    }

    // Getters
    boolean isCPU() {
        return this.isCPU;
    }

    public String getName() {
        return name;
    }

    boolean isDead() {
        return this.isDead;
    }

    // Find ship
    private Ship findShip(int gridRow, int gridCol) {
        for (Ship ship: this.shipArray) {
            for (int[] cell: ship.getCellsFilled()) {
                if (cell[0] == gridRow && cell[1] == gridCol) {
                    return ship;
                }
            }
        }
        return null;
    }

    // Printing
    public void printBattlefield() {
        for (char[] row : battlefield) {
            System.out.println(new String(row));
        }
    }

    public void printTargetBattlefield() {
        for (char[] row : targetBattlefield) {
            System.out.println(new String(row));
        }
    }

    public void printBothBattlefields() {
        char[][] doubleGrid = this.concatGrids(10);
        String introString = "Your ship status | Enemy's ship status";
        System.out.println(introString);
        for (char[] row : doubleGrid) {
            System.out.println(new String(row));
        }
    }

    private char[][] concatGrids(int separation) {
        char[][] cctedFields = new char[14][separation + 2 * this.battlefield.length];
        int height = cctedFields.length;
        int standardWidth = this.battlefield[0].length;
        for (int row = 0; row < height; row++) {
            System.arraycopy(this.battlefield[row], 0, cctedFields[row], 0, standardWidth);
            for (int cell = standardWidth; cell < standardWidth + separation; cell++) {
                cctedFields[row][cell] = ' ';
            }
            for (int i = 0; i < standardWidth; i++) {
                cctedFields[row][standardWidth + separation + i]
                        = this.targetBattlefield[row][i];
            }
        }
        return cctedFields;
    }

    // Placement Phase
    void generateRandomBattlefield() {
        for (int i = 0; i < 5; i++) {
            Ship newShip;
            do {
                newShip = RandomGenerator.generateShip(i + 1);
            } while (BattleshipsUtil.isCollision(this.battlefield, newShip));
            try{
                this.addShip(newShip);
            } catch (Exception e) {
                System.out.println("There was a problem generating the CPU's ships.");
                this.printBattlefield();
                System.exit(1);
            }
        }
    }

    void addShip(Ship ship) {
        for (int i = 0; i < 5; i++) {
            if (isNull(this.shipArray[i])) {
                this.shipArray[i] = ship;
                this.battlefield = BattleshipsUtil.addShip(this.battlefield, ship);
                return;
            }
        }
    }

    // Battle Phase
    int[] generateTarget() {
        return RandomGenerator.generateTarget(this.targetBattlefield);
    }

    String receiveFire(int[] target) {
        if (target.length != 2) {
            throw new CoordsInvalidException();
        }
        int gridRow = 2 + target[0];
        int gridCol = 2 + target[1];

        Optional<Ship> opTargetShip = Optional.ofNullable(this.findShip(gridRow, gridCol));

        if (opTargetShip.isPresent()) {
            opTargetShip.ifPresent(ship -> {
                boolean shipSunk = ship.incrementHitCount();
                if (shipSunk) {
                    UserInterface.announceShipSunk(this, ship);
                }
            });
            this.battlefield[gridRow][gridCol] = 'x';
            return "Hit!";
        } else {
            this.battlefield[gridRow][gridCol] = 'o';
            return "Miss.";
        }
    }

    void recordAttackResult(String result, int[] target) {
        if (target.length != 2) {
            throw new CoordsInvalidException();
        }
        int gridRow = 2 + target[0];
        int gridCol = 2 + target[1];
        if (result.equals("Hit!")) {
            this.targetBattlefield[gridRow][gridCol] = 'x';
        } else {
            this.targetBattlefield[gridRow][gridCol] = 'o';
        }
    }

    boolean checkIfDead() {
        boolean isDead = true;
        for (Ship ship: this.shipArray) {
            if (!ship.isSunk()) {
                isDead = false;
            }
        }
        this.isDead = isDead;
        return this.isDead;
    }
}
