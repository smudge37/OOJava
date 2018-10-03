package battleships;

public class BattleshipsApplication {
    public static void main(String[] args) {

        GameMaster gameMaster = new GameMaster();

        try {
            gameMaster.run();
        } catch (QuitException e) {
            System.out.println("Game Closing.");
        }
    }

}
