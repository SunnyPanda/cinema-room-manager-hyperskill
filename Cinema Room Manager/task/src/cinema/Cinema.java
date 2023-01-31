package cinema;

import java.util.Scanner;

public class Cinema {
    private static int purchasedTickets = 0;
    private static double percentage = 0;
    private static int currentIncome = 0;
    private static int totalIncome = 0;
    private static int totalSeats = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = in.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = in.nextInt();
        totalSeats = rows * cols;

        char[][] seats = fillSeats(rows, cols);
        int[][] prices = calcPrices(rows, cols);

        int action = askForAction(in);
        while (action != 0) {
            switch (action) {
                case 1 -> printSeats(seats);
                case 2 -> buyTicket(in, prices, seats);
                case 3 -> printStatistics();
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
                int price = calcPrice(i, rows, cols);
                prices[i][j] = price;
                totalIncome += price;
            }
        }
        return prices;
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

    private static int askForAction(Scanner in) {
        System.out.println("""
                
                1. Show the seats
                2. Buy a ticket
                3. Statistics
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

        while (!isValidSeat(seats, chosenRow, chosenCol)) {
            System.out.println("\nEnter a row number:");
            chosenRow = in.nextInt();
            System.out.println("Enter a seat number in that row:");
            chosenCol = in.nextInt();
        }

        int price = prices[chosenRow - 1][chosenCol - 1];
        System.out.printf("Ticket price: $%d\n", price);
        seats[chosenRow - 1][chosenCol - 1] = 'B';
        purchasedTickets += 1;
        percentage = purchasedTickets * 100.0 / totalSeats;
        currentIncome += price;
    }

    private static boolean isValidSeat(char[][] seats, int row, int col) {
        if (row < 1 || row > seats.length || col < 1 || col > seats[0].length) {
            System.out.println("Wrong input!");
            return false;
        }
        if (seats[row - 1][col - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            return false;
        }
        return true;
    }

    private static void printStatistics() {
        System.out.printf("""
                Number of purchased tickets: %d
                Percentage: %.2f%%
                Current income: $%d
                Total income: $%d%n""", purchasedTickets, percentage, currentIncome, totalIncome);
    }
}