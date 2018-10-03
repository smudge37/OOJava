package battleships;

public class GameMaster {
    BattleshipsUtil bu = new BattleshipsUtil();
    private RandomGenerator rg = new RandomGenerator();
    private Combatant computer = new Combatant(this.bu, this.rg, true);
    private Combatant player = new Combatant(this.bu, this.rg, false);
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
        if (attacker.isCPU()) {
            target = attacker.generateTarget(rg);
        } else {
            target = ui.selectTarget(attacker);
        }
        String result = defender.receiveFire(target);
        attacker.enterResult(result, target);
    }

    private void endGamePhase() {

    }
}
