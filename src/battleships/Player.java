package battleships;

public class Player {
    private BattleshipsUtil bu;
    private char[][] battlefield;

    public Player(BattleshipsUtil bu) {
        this.bu = bu;
        this.battlefield = bu.createBattlefield();
    }

    public void printBattlefield() {
        for (char[] row: battlefield) {
            System.out.println(new String(row));
        }
    }
}
