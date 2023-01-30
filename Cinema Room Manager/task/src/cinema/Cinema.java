package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = in.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = in.nextInt();

        char[][] seats = fillSeats(rows, cols);
        int[][] prices = calcPrices(rows, cols);

        int action = askForAction(in);
        while (action != 0) {
            switch (action) {
                case 1 -> printSeats(seats);
                case 2 -> buyTicket(in, prices, seats);
            }
            action = askForAction(in);
        }
    }

    private static char[][] fillSeats(int rows, int cols) {
        char[][] seats = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = 'S';
            }
        }
        return seats;
    }

    private static int[][] calcPrices(int rows, int cols) {
        int[][] prices = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                prices[i][j] = calcPrice(i, rows, cols);
            }
        }
        return prices;
    }

    private static int askForAction(Scanner in) {
        System.out.println("""
                
                1. Show the seats
                2. Buy a ticket
                0. Exit""");
        return in.nextInt();
    }
    private static void printSeats(char[][] seats) {
        System.out.println("\nCinema:");
        for (int i = 0; i <= seats[0].length; i++) {
            System.out.print(i == 0 ? "  " : i + " ");
        }
        System.out.println();
        for (int i = 0; i < seats.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats[0].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void buyTicket(Scanner in, int[][] prices, char[][] seats) {
        System.out.println("\nEnter a row number:");
        int chosenRow = in.nextInt();
        System.out.println("Enter a seat number in that row:");
        int chosenCol = in.nextInt();

        System.out.printf("Ticket price: $%d\n", prices[chosenRow - 1][chosenCol - 1]);
        seats[chosenRow - 1][chosenCol - 1] = 'B';
    }

    private static int calcPrice(int row, int rows, int cols) {
        int numOfSeats = rows * cols;
        if (numOfSeats <= 60) {
            return 10;
        } else {
            int front = rows / 2;
            return row + 1 <= front ? 10 : 8;
        }
    }
}