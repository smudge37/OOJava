package battleships;

public class GameMaster {
    private Combatant player1;
    private Combatant player2;

    public GameMaster(boolean player1IsCPU, boolean player2IsCPU) {
        this.player1 = new Combatant("User", player1IsCPU);
        this.player2 = new Combatant("Computer", player2IsCPU);
    }

    public void run(boolean bypassPlacement) {
        this.placementPhase(bypassPlacement);
        this.battlePhase();
        this.endGamePhase();
    }

    private void placementPhase(boolean bypassPlacement) {
        if (this.player1.isCPU()) {
            this.player1.generateRandomBattlefield();
        } else {
            UserInterface.shipPlacement(this.player1, bypassPlacement);
        }

        if (this.player2.isCPU()) {
            this.player2.generateRandomBattlefield();
        } else {
            UserInterface.shipPlacement(this.player2, bypassPlacement);
        }
    }

    private void battlePhase() {
        boolean combatantDead = false;
        String result;

        while (!combatantDead) {
            UserInterface.displayBattleStatus(this.player1);
            result = fire(this.player1, this.player2);
            if (!this.player1.isCPU()) {
                UserInterface.printResult(result);
            }

            UserInterface.displayBattleStatus(this.player2);
            result = fire(this.player2, this.player1);
            if (!this.player2.isCPU()) {
                UserInterface.printResult(result);
            }

            combatantDead = this.player1.checkIfDead() || this.player2.checkIfDead();
        }
    }

    private String fire(Combatant attacker, Combatant defender) {
        int[] target;
        if (attacker.isCPU()) {
            target = attacker.generateTarget();
        } else {
            target = UserInterface.selectTarget();
        }
        UserInterface.declareShot(target, attacker);
        String result = defender.receiveFire(target);
        attacker.recordAttackResult(result, target);
        return result;
    }

    private void endGamePhase() {
        UserInterface.announceGameResult(this.player1, this.player2);
    }
}
