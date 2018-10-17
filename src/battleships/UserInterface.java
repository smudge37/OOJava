package battleships;

import java.util.Scanner;

public class UserInterface {
    private static Scanner scanner = new Scanner(System.in);

    // Placement Phase
    static void shipPlacement(Combatant currentPlayer, boolean bypassPlacement) {
        System.out.println("***** Welcome to Battleships! *****");
        System.out.println("At any point you may enter q to quit.");
        System.out.println("You must first place your ships.");
        currentPlayer.printBattlefield();

        for (int shipNumber = 1; shipNumber < 6; shipNumber++) {
            System.out.println("i = " + shipNumber);
            UserInterface.printShipMessage(shipNumber);
            UserInterface.printPlacementInstructions();
            int [] coordinates;
            if (bypassPlacement) {
                coordinates = TestPositions.POSITIONS[shipNumber-1];
            } else {
                coordinates = listenForCoordinates(shipNumber);
            }

            try {
                currentPlayer.addShip(new Ship(shipNumber, coordinates));
            } catch (CellCollisionException e) {
                System.out.println("This placement collides with another ship. " +
                        "You must choose different coordinates.");
                shipNumber--;
                System.out.println("Changing i to " + shipNumber);
            } catch (CoordsInvalidException e) {
                System.out.println("Your coordinates were not valid for this ship. " +
                        "You must choose different coordinates.");
                shipNumber--;
                System.out.println("Changing i to " + shipNumber);

            }
            currentPlayer.printBattlefield();
        }

        System.out.println("The computer has also placed its ships.");
    }
    private static void printShipMessage(int i) {
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
    private static void printPlacementInstructions() {
        System.out.println("Please enter the desired start and end points in the form \"ab,cd\".");
        System.out.println("where a, b, c, and d are digits between 0 and 9 inclusive.");
        System.out.println("Ships can only be placed horizontally or vertically.");
        System.out.println("Enter the desired row number first, then the column number.");
    }
    private static int[] listenForCoordinates(int shipNumber) {
        String coordinatePattern = "\\d\\d,\\d\\d";

        while (true) {
            String input = UserInterface.listenForInput(coordinatePattern);
            if (input.toLowerCase().equals("q")) {
                System.exit(-1);
            }
            int[] coords = UserInterface.inputToCoordinates(input);
            if (Ship.coordsValid(shipNumber, coords)) {
                return coords;
            } else {
                System.out.println("Your coordinates are not valid for this ship. Try again.");
            }
        }
    }
    private static int[] inputToCoordinates(String input) {
        int[] coordPositions = {0, 1, 3, 4};
        int[] coordinates = new int[coordPositions.length];
        for (int i = 0; i < coordPositions.length; i++) {
            coordinates[i] = Character.getNumericValue(input.charAt(coordPositions[i]));
        }
        return coordinates;
    }

    private static String listenForInput(String format) {
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
    static void displayBattleStatus(Combatant currentAttacker) {
        System.out.println("Current Battle Status:\n");
        currentAttacker.printBothBattlefields();
    }
    static int[] selectTarget() {
        System.out.println("Please select a co-ordinate to fire upon of the form \"ab\".");

        String input = UserInterface.listenForInput("\\d\\d");
        if (input.toLowerCase().equals("q")) {
            System.exit(-1);
        }
        int[] targetPosition = {Character.getNumericValue(input.charAt(0)),
                Character.getNumericValue(input.charAt(1))};
        return targetPosition;
    }
    static void declareShot(int[] target, Combatant combatant) {
        if (target.length < 2) {
            throw new CoordsInvalidException("Target in Combatant.declareShot has length < 2.");
        }

        System.out.println(combatant.getName() + " fired at position ("
                + (target[0]) + "," + (target[1]) + ")");
    }
    static void printResult(String result) {
        System.out.println(result);
    }
    static void announceShipSunk(Combatant player, Ship ship) {
        System.out.println(player.getName() + "'s " + ship.getName() + " has been sunk!");
    }

    // End Game
    static void announceGameResult(Combatant player1, Combatant player2) {
        if (player1.isDead()) {
            if (player2.isDead()) {
                System.out.println("It's a draw!");
            } else {
                System.out.println(player2.getName() + " wins!");
            }
        } else {
            System.out.println(player1.getName() + " wins!");
        }
    }
}
