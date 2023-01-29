package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = in.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = in.nextInt();

        int numOfSeats = rows * cols;
        System.out.println("Total income:");
        if (numOfSeats <= 60) {
            System.out.println("$" + numOfSeats * 10);
        } else {
            int front = rows / 2;
            System.out.println("$" + (front * cols * 10 + (rows - front) * cols * 8));
        }
    }
}