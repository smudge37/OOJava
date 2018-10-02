package battleships;

public class BattleshipsApplication {
    public static void main(String[] args) {

        GridManager gm = new GridManager();
        UserInterface ui = new UserInterface(gm);

        try {
            ui.shipPlacement();
        } catch (QuitException e) {
            System.out.println("Quitting application.");
            return;
        }


    }
}
