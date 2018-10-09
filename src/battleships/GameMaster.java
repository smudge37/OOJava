package battleships;

public class GameMaster {
    private BattleshipsUtil bu;
    private RandomGenerator rg;
    private Combatant player1;
    private Combatant player2;
    private UserInterface ui;

    public GameMaster(boolean player1IsCPU, boolean player2IsCPU) {
        this.bu = new BattleshipsUtil();
        this.rg = new RandomGenerator();
        this.player1 = new Combatant(this.bu, this.rg, player1IsCPU);
        this.player2 = new Combatant(this.bu, this.rg, player2IsCPU);
        this.ui = new UserInterface(this.player1, this.player2, this.bu);

    }

    public void run() {
        this.placementPhase();
        this.battlePhase();
        this.endGamePhase();
    }

    private void placementPhase() {
        if (!this.player1.isCPU()) this.ui.shipPlacement(this.player1);
        if (!this.player2.isCPU()) this.ui.shipPlacement(this.player2);
    }

    private void battlePhase() {
        boolean combatantDead = false;

        while (!combatantDead) {
            this.ui.displayBattleStatus(this.player1);
            fire(this.player1, this.player2);
            this.ui.displayBattleStatus(this.player2);
            fire(this.player2, this.player1);
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
