package battleships;

public class BattleshipsApplication {
    public static void main(String[] args) {

        UserInterface ui = new UserInterface();

        try {
            ui.shipPlacement();
        } catch (QuitException e) {
            System.out.println("Quitting application.");
            return;
        }
    }
}
