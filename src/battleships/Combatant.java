package battleships;

public class Combatant {
    BattleshipsUtil bu;
    private char[][] battlefield;
    private char[][] targetBattlefield;

    public Combatant(BattleshipsUtil bu) {
        this.bu = bu;
        this.battlefield = bu.createBattlefield();
        this.targetBattlefield = bu.createBattlefield();
    }

    public Combatant(BattleshipsUtil bu, char[][] battlefield) {
        this(bu);
        this.battlefield = battlefield;
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
        if (this.battlefield[target[0]][target[1]] != ' ') {
            this.battlefield[target[0]][target[1]] = 'x';
            return "hit";
        } else {
            return "miss";
        }
    }

    public void enterResult(String result, int[] target) {
        if (target.length != 2) {
            throw new CoordsInvalidException();
        }
        if (result.equals("hit")) {
            this.targetBattlefield[target[0]][target[1]] = 'x';
        } else {
            this.targetBattlefield[target[0]][target[1]] = 'o';
        }
    }

    public int[] generateTarget(RandomGenerator rg) {
        return rg.generateTarget(this.targetBattlefield);
    }

    public boolean isDead() {
        return this.bu.countHits(this.battlefield) == 17;
    }
}
