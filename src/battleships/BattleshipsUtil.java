package battleships;

import java.util.Arrays;

class BattleshipsUtil {

    // Creation
    static char[][] createBattlefield() {
        char[][] battlefield = new char[14][14];
        for (int i = 0; i < 14; i++) {
            if (i < 2 || i >= 12) {
                battlefield[0][i] = ' ';
                battlefield[1][i] = ' ';
                if (i == 0 || i == 13) {
                    battlefield[2][i] = '0';
                } else {
                    battlefield[2][i] = '|';
                }
            } else {
                battlefield[0][i] = Integer.toString(i - 2).toCharArray()[0];
                battlefield[1][i] = '-';
                battlefield[2][i] = ' ';
            }
        }
        battlefield[12] = Arrays.copyOf(battlefield[1], 14);
        battlefield[13] = Arrays.copyOf(battlefield[0], 14);
        for (int i = 3; i < 12; i++) {
            battlefield[i] = Arrays.copyOf(battlefield[2], 14);
            battlefield[i][0] = Integer.toString(i-2).toCharArray()[0];
            battlefield[i][13] = Integer.toString(i-2).toCharArray()[0];
        }
        return battlefield;
    }

    // Ship Placement
    static char[][] addShip(char[][] battlefield, Ship ship) {
        int[][] cells = ship.getCellsFilled();
//        System.out.println("Writing new ship to Battlefield: " + battlefield);
//        System.out.println("Ship has coordinates: " + Arrays.toString(ship.getCoordinates()));
//        System.out.println("Ship claims to fill: from " + Arrays.toString(cells[0])
//         + " to " + Arrays.toString(cells[cells.length-1]));
        if (ship.isHorizontal()) {
            battlefield[cells[0][0]][cells[0][1]] = '<';
            battlefield[cells[cells.length-1][0]][cells[cells.length-1][1]] = '>';
            for (int cellNum = 1; cellNum < cells.length - 1; cellNum++) {
                battlefield[cells[cellNum][0]][cells[cellNum][1]] = '-';
            }
        } else {
            battlefield[cells[0][0]][cells[0][1]] = '^';
            battlefield[cells[cells.length-1][0]][cells[cells.length-1][1]] = 'v';
            for (int cellNum = 1; cellNum < cells.length - 1; cellNum++) {
                battlefield[cells[cellNum][0]][cells[cellNum][1]] = '|';
            }
        }
        return battlefield;
    }

    static boolean isCollision(char[][] battlefield, Ship ship) {
        int[][] cellsFilled = ship.getCellsFilled();
        for (int[] cell: cellsFilled) {
            if (battlefield[cell[0]][cell[1]] != ' ') {
                return true;
            }
        }
        return false;
    }

    // CountHits

    static int countShots(char[][] battlefield) {
        int count = 0;
        for (char[] row: battlefield) {
            for (char entry: row) {
                if (entry == 'x' || entry == 'o') {
                    count++;
                }
            }
        }
        return count;
    }

    static int countHits(char[][] battlefield) {
        int count = 0;
        for (char[] row: battlefield) {
            for (char entry: row) {
                if (entry == 'x') {
                    count++;
                }
            }
        }
        return count;
    }

}
