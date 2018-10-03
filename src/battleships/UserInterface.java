package battleships;

import java.util.Scanner;

public class UserInterface {
    private Combatant player;
    private Combatant computer;
    private BattleshipsUtil bu;
    private Scanner scanner = new Scanner(System.in);

    public UserInterface(Combatant player, Combatant computer,
                         BattleshipsUtil battleshipsUtil) {
        this.player = player;
        this.computer = computer;
        this.bu = battleshipsUtil;
    }

    // Placement Phase
    public void shipPlacement(Combatant currentPlayer) {
        System.out.println("***** Welcome to Battleships! *****");
        System.out.println("At any point you may enter q to quit.");
        System.out.println("You must first place your ships.");
        currentPlayer.printBattlefield();

        for (int i = 1; i < 6; i++) {
            int [] coordinates = listenForCoordinates(i);
            try {
                currentPlayer.addShip(new Ship(coordinates));
            } catch (CellCollisionException e) {
                System.out.println("This placement collides with another ship. " +
                        "You must choose different coordinates.");
                i--;
            } catch (CoordsInvalidException e) {
                System.out.println("Your coordinates were not valid for this ship. " +
                        "You must choose different coordinates.");
                i--;
            }
            currentPlayer.printBattlefield();
        }

        System.out.println("The computer has also placed its ships.");
    }
    private void printShipMessage(int i) {
        switch (i) {
            case 1:
                System.out.println("Ship " + Integer.toString(1) + ": Frigate (2 cells long)");
                break;
            case 2:
                System.out.println("Ship " + Integer.toString(2) + ": Submarine (3 cells long)");
                break;
            case 3:
                System.out.println("Ship " + Integer.toString(3) + ": Cruiser (3 cells long)");
                break;
            case 4:
                System.out.println("Ship " + Integer.toString(4) + ": Battleship (4 cells long)");
                break;
            case 5:
                System.out.println("Ship " + Integer.toString(5) + ": Carrier (5 cells long)");
        }
    }
    private void printPlacementInstructions() {
        System.out.println("Please enter the desired start and end points in the form \"(a,b);(c,d)\".");
        System.out.println("where a, b, c, and d are digits between 0 and 9 inclusive.");
        System.out.println("Ships can only be placed horizontally or vertically.");
        System.out.println("Enter the desired row number first, then the column number.");
    }
    private int[] listenForCoordinates(int shipNumber) {
        this.printShipMessage(shipNumber);
        this.printPlacementInstructions();
        String coordinatePattern = "\\(\\d,\\d\\);\\(\\d,\\d\\)";

        while (true) {
            String input = this.listenForInput(coordinatePattern);
            if (input.toLowerCase().equals("q")) {
                throw new QuitException();
            }
            int[] coords = this.inputToCoordinates(input);
            if (this.bu.coordsValid(shipNumber, coords)) {
                return coords;
            } else {
                System.out.println("Your coordinates are not valid for this ship. Try again.");
            }
        }
    }
    private int[] inputToCoordinates(String input) {
        int[] coordPositions = {1, 3, 7, 9};
        int[] coordinates = new int[4];
        for (int i = 0; i < 4; i++) {
            coordinates[i] = Character.getNumericValue(input.charAt(coordPositions[i]));
        }
        return coordinates;
    }

    private String listenForInput(String format) {
        while(true) {
            String input = scanner.next().trim();
            if (input.toLowerCase().equals("q")) {
                return input;
            }
            if (input.matches(format)) {
                return input;
            } else {
                System.out.println("Your input does not match the required format. Try again.");
                scanner.nextLine();
            }
        }
    }

    // Battle Phase
    public int[] selectTarget(Combatant currentPlayer) {
        System.out.println("Intel so far:");
        currentPlayer.printTargetBattlefield();
        System.out.println("Please select a co-ordinate to fire upon of the form \"(a,b)\".");

        String input = this.listenForInput("\\(\\d,\\d\\)");
        if (input.toLowerCase().equals("q")) {
            throw new QuitException();
        }
        int[] targetPosition = {Character.getNumericValue(input.charAt(1)),
                Character.getNumericValue(input.charAt(3))};
        return targetPosition;
    }
}
