package battleships;

public class GameMaster {
    private BattleshipsUtil bu = new BattleshipsUtil();
    private UserInterface ui = new UserInterface(this.bu);
    private Computer computer = new Computer(this.bu);
    private Player player = new Player(this.bu);

    public void run() {
        this.placementPhase();
        this.battlePhase();
        this.endGamePhase();
    }

    private void placementPhase() {
        try {
            this.ui.shipPlacement(player);
        } catch (QuitException e) {
            System.out.println("Quitting application.");
            return;
        }
    }

    private void battlePhase() {
        boolean gameOver = false;

        while (!gameOver) {

        }
    }

    private void endGamePhase() {

    }
}
