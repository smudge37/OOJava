package battleships;

public class Computer extends Combatant{

    public Computer(BattleshipsUtil bu, RandomGenerator rg) {
        super(bu,rg.generateBattlefield());
    }
}
