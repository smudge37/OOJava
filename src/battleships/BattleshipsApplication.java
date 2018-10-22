package battleships;

public class BattleshipsApplication {
    public static void main(String[] args) {

        GameMaster gameMaster = new GameMaster(true, true);
        gameMaster.run(false);

    }
}
