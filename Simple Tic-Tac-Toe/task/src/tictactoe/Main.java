package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static ArrayList<Integer> X = new ArrayList<>();
    static ArrayList<Integer> O = new ArrayList<>();
    static ArrayList<Integer> empty = new ArrayList<>();
    static StringBuilder input = new StringBuilder("         ");;
    static int turn = 0;

    public static void main(String[] args) {
        prepareGame();

        while (empty.size() >= 0) {
            if (won(X)) {
                System.out.println("X wins");
                break;
            } else if (won(O)) {
                System.out.println("O wins");
                break;
            } else if (empty.size() == 0) {
                System.out.println("Draw");
                break;
            } else {
                userplay();
            }
        }
    }

    static void prepareGame() {
        for (int i = 0; i < 9; i++) {
            empty.add(i);
        }
        printArray();
    }

    static void printArray() {
        System.out.println("---------");
        int i = 0;
        while (i < 9) {
            if (i % 3 == 0) {
                System.out.print("| " + input.charAt(i) + " ");
            } else if (i % 3 == 2) {
                System.out.println(input.charAt(i) + " |");
            } else {
                System.out.print(input.charAt(i) + " ");
            }
            i++;
        }
        System.out.println("---------");
    }

    static boolean won(ArrayList<Integer> S) {
        int[] mod = new int[3];
        Arrays.fill(mod, 0);
        int[] row = new int[3];
        Arrays.fill(row, 0);
        int diagLeft = 0;
        int diagRight = 0;
        for (int i : S) {
            mod[i % 3] ++;
            row[i / 3] ++;
            if (i % 4 == 0){
                diagLeft++;
            }
            if (i == 2 || i == 4 || i == 6){
                diagRight++;
            }
        }
        if (diagLeft == 3 || diagRight == 3) {
            return true;
        }
        for (int i = 0; i < 3; i++) {
            if (row[i] == 3 || mod[i] == 3){
                return true;
            }
        }
        return false;
    }

    static void userplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates: ");
        int row = scanner.nextInt();
        int mod = scanner.nextInt();
        if (row > 3 || row < 1 || mod > 3 || mod < 1) {
            System.out.println("Coordinates should be from 1 to 3!");
            userplay();
        } else if (input.charAt((row - 1) * 3 + mod - 1) == 'X' ||
                input.charAt((row - 1) * 3 + mod - 1) == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            userplay();
        } else {
            if (turn % 2 == 0) {
                input.setCharAt((row - 1) * 3 + mod - 1, 'X');
                X.add((row - 1) * 3 + mod - 1);
                printArray();
            } else {
                input.setCharAt((row - 1) * 3 + mod - 1, 'O');
                O.add((row - 1) * 3 + mod - 1);
                printArray();
            }
            empty.remove(new Integer((row - 1) * 3 + mod - 1));
            turn++;
        }
    }
}
