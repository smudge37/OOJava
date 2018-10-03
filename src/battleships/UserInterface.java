package battleships;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private BattleshipsUtil bu;

    public UserInterface(BattleshipsUtil battleshipsUtil) {
        this.bu = battleshipsUtil;
    }

    public void shipPlacement(Player player) {
        System.out.println("***** Welcome to Battleships! *****");
        System.out.println("At any point you may enter q to quit.");
        System.out.println("You must first place your ships.");
        this.bu.printPlayerGrid();

        for (int i = 1; i < 6; i++) {
            int [] coordinates = listenForCoordinates(i);
            try {
                this.bu.addPlayerShip(new Ship(coordinates));
            } catch (CellCollisionException e) {
                System.out.println("This placement collides with another ship. " +
                        "You must choose different coordinates.");
                i--;
            } catch (CoordsInvalidException e) {
                System.out.println("Your coordinates were not valid for this ship. " +
                        "You must choose different coordinates.");
                i--;
            }
            this.bu.printPlayerGrid();
        }
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

    private void coordsInstructions() {
        System.out.println("Please enter the desired start and end points in the form \"(a,b);(c,d)\".");
        System.out.println("where a, b, c, and d are digits between 0 and 9 inclusive.");
        System.out.println("Ships can only be placed horizontally or vertically.");
        System.out.println("Enter the desired row number first, then the column number.");
    }

    private int[] listenForCoordinates(int shipNumber) {
        this.printShipMessage(shipNumber);
        coordsInstructions();
        String coordPattern = "\\(\\d,\\d\\);\\(\\d,\\d\\)";
        int[] coordPositions = {1, 3, 7, 9};
        String coordString;

        while (true) {
            String input = scanner.next();
            if (input.trim().toLowerCase().equals("q")) {
                throw new QuitException();
            }

            if (!input.trim().matches(coordPattern)) {
                System.out.println("Your input does not match the required format. Try again.");
                scanner.nextLine();
            } else {
                coordString = input;
                int[] coords = new int[4];
                for (int i = 0; i < 4; i++) {
                    coords[i] = Integer.parseInt(coordString.substring(coordPositions[i], coordPositions[i] + 1));
                }
                if (this.bu.coordsValid(shipNumber, coords)) {
                    return coords;
                } else {
                    System.out.println("Your coordinates are not valid for this ship. Try again.");
                }
            }
        }
    }
}
