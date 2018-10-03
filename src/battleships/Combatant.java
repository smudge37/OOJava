package battleships;

import java.util.Random;

public class Combatant {
    private boolean isCPU;
    private BattleshipsUtil bu;
    private RandomGenerator rg;
    private char[][] battlefield;
    private char[][] targetBattlefield;

    public Combatant(BattleshipsUtil bu, RandomGenerator rg, boolean isCPU) {
        this.bu = bu;
        this.rg = rg;
        this.targetBattlefield = bu.createBattlefield();
        this.isCPU = isCPU;
        if (isCPU) {
            this.battlefield = rg.generateBattlefield();
        } else {
            this.battlefield = bu.createBattlefield();
        }
    }

    public void printBattlefield() {
        for (char[] row: battlefield) {
            System.out.println(new String(row));
        }
    }

    public void printTargetBattlefield() {
        for (char[] row: targetBattlefield) {
            System.out.println(new String(row));
        }
    }

    public void addShip(Ship ship) {
        this.battlefield = this.bu.addShip(this.battlefield, ship);
    }

    public String receiveFire(int[] target) {
        if (target.length != 2) {
            throw new CoordsInvalidException();
        }
        int gridRow = 2 + target[0];
        int gridCol = 2 + target[1];
        if (this.battlefield[gridRow][gridCol] != ' ') {
            this.battlefield[gridRow][gridCol] = 'x';
            return "hit";
        } else {
            return "miss";
        }
    }

    public void enterResult(String result, int[] target) {
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

    public int[] generateTarget(RandomGenerator rg) {
        return rg.generateTarget(this.targetBattlefield);
    }

    boolean isCPU() {
        return this.isCPU;
    }

    public boolean isDead() {
        return this.bu.countHits(this.battlefield) == 17;
    }
}
