package battleships;

import java.util.Arrays;
import java.util.Random;

public class Combatant {
    private boolean isCPU;
    private BattleshipsUtil bu;
    private char[][] battlefield;
    private char[][] targetBattlefield;

    public Combatant(BattleshipsUtil bu, RandomGenerator rg, boolean isCPU) {
        this.bu = bu;
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

    public void printBothBattlefields() {
        char[][] doubleGrid = this.concatGrids(10);
        String introString = "Your ship status | Enemy's ship status";
        System.out.println(introString);
        for (char[] row: doubleGrid) {
            System.out.println(new String(row));
        }
    }

    private char[][] concatGrids(int separation) {
        char[][] cctedFields = new char[14][separation + 2*this.battlefield.length];
        int height = cctedFields.length;
        int standardWidth = this.battlefield[0].length;
        for (int row = 0; row < height; row++) {
            for (int cell = 0; cell < standardWidth; cell++) {
                cctedFields[row][cell] = this.battlefield[row][cell];
            }
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
