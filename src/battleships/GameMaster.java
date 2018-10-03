package battleships;

public class GameMaster {
    BattleshipsUtil bu = new BattleshipsUtil();
    private RandomGenerator rg = new RandomGenerator();
    private Computer computer = new Computer(this.bu, this.rg);
    private Player player = new Player(this.bu);
    private UserInterface ui = new UserInterface(this.player, this.computer, this.bu);

    public void run() {
        this.placementPhase();
        this.battlePhase();
        this.endGamePhase();
    }

    private void placementPhase() {
        try {
            this.ui.shipPlacement(this.player);
        } catch (QuitException e) {
            System.out.println("Quitting application.");
            return;
        }
    }

    private void battlePhase() {
        boolean gameOver = false;

        while (!gameOver) {
            fire(this.player, this.computer);
            fire(this.computer, this.player);

        }
    }

    private void fire(Combatant attacker, Combatant defender) {
        int[] target;
        if (attacker instanceof Player) {
            target = ui.selectTarget(attacker);
        } else {
            target = attacker.generateTarget(rg);
        }
        String result = defender.receiveFire(target);
        attacker.enterResult(result, target);
    }

    private void endGamePhase() {

    }
}
