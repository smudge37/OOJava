package battleships;

import static java.util.Objects.isNull;

public class Combatant {
    private boolean isCPU;
    private BattleshipsUtil bu;
    private Ship[] shipArray;
    private char[][] battlefield;
    private char[][] targetBattlefield;
    private boolean[] shipSunk = {false, false, false, false, false};

    public Combatant(BattleshipsUtil bu, RandomGenerator rg, boolean isCPU) {
        this.bu = bu;
        this.targetBattlefield = bu.createBattlefield();
        this.isCPU = isCPU;
        this.shipArray = new Ship[5];
        this.battlefield = this.bu.createBattlefield();
    }

    // Getters
    boolean isCPU() {
        return this.isCPU;
    }

    boolean isDead() {
        for (boolean isSunk : shipSunk) {
            if (!isSunk) {
                return false;
            }
        }
        return true;
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
    void generateRandomBattlefield(RandomGenerator rg) {
        for (int i = 0; i < 5; i++) {
            Ship newShip;
            do {
                newShip = rg.generateShip(i + 1);
            } while (this.bu.isCollision(this.battlefield, newShip));
            System.out.println("New ship generated with number " + ( i + 1 ) + " and length " + newShip.getLength());
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
                this.battlefield = this.bu.addShip(this.battlefield, ship);
                return;
            }
        }
    }

    // Battle Phase
    int[] generateTarget(RandomGenerator rg) {
        return rg.generateTarget(this.targetBattlefield);
    }

    String receiveFire(int[] target) {
        if (target.length != 2) {
            throw new CoordsInvalidException();
        }
        this.declareShot(target);
        return this.takeDamage(target);
    }

    private void declareShot(int[] target) {
        if (target.length < 2) {
            throw new CoordsInvalidException("Target in Combatant.declareShot has length < 2.");
        }

        if (this.isCPU) {
            System.out.println("Computer fired at position ("
                    + (target[0]) + "," + (target[1]) + ")");
        } else {
            System.out.println("You fired at position ("
                    + (target[0]) + "," + (target[1]) + ")");
        }
    }

    private String takeDamage(int[] target) {
        int gridRow = 2 + target[0];
        int gridCol = 2 + target[1];
        char targetChar = this.battlefield[gridRow][gridCol];
        if (targetChar == ' ' || targetChar == 'o') {
            this.battlefield[gridRow][gridCol] = 'o';
            return "Miss.";
        } else {
            this.battlefield[gridRow][gridCol] = 'x';
            return "Hit!";
        }
    }

    void recordAttackResult(String result, int[] target) {
        if (target.length != 2) {
            throw new CoordsInvalidException();
        }
        int gridRow = 2 + target[0];
        int gridCol = 2 + target[1];
        if (result.equals("hit")) {
            this.targetBattlefield[gridRow][gridCol] = 'x';
        } else {
            this.targetBattlefield[gridRow][gridCol] = 'o';
        }
    }
}
