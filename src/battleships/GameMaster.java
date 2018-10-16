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
        this.ui = new UserInterface(this.bu);

    }

    public void run(boolean bypassPlacement) {
        this.placementPhase(bypassPlacement);
        this.battlePhase();
        this.endGamePhase();
    }

    private void placementPhase(boolean bypassPlacement) {
        if (this.player1.isCPU()) {
            this.player1.generateRandomBattlefield(rg);
        } else {
            this.ui.shipPlacement(this.player1, bypassPlacement);
        }

        if (this.player2.isCPU()) {
            this.player2.generateRandomBattlefield(rg);
        } else {
            this.ui.shipPlacement(this.player2, bypassPlacement);
        }
    }

    private void battlePhase() {
        boolean combatantDead = false;
        String result;

        while (!combatantDead) {
            this.ui.displayBattleStatus(this.player1);
            result = fire(this.player1, this.player2);
            if (!this.player1.isCPU()) {
                this.ui.returnResult(result);
            }

            this.ui.displayBattleStatus(this.player2);
            result = fire(this.player2, this.player1);
            if (!this.player2.isCPU()) {
                this.ui.returnResult(result);
            }

            combatantDead = this.player1.isDead() || this.player2.isDead();
        }
    }

    private String fire(Combatant attacker, Combatant defender) {
        int[] target;
        if (attacker.isCPU()) {
            target = attacker.generateTarget(rg);
        } else {
            target = ui.selectTarget();
        }
        String result = defender.receiveFire(target);
        attacker.recordAttackResult(result, target);
        return result;
    }

    private void endGamePhase() {

    }
}
