package cinema;

import java.util.Scanner;

public class Cinema
{
    public static void main(String[] args)
    {
        run();
    }

    private static void run()
    {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        final int ROWS = scanner.nextInt() + 1;
        scanner.nextLine();
        System.out.println("Enter the number of seats in each row:");
        final int COLUMNS = scanner.nextInt() + 1;
        scanner.nextLine();

        int[][] matrix = new int[ROWS][COLUMNS];
        fillMatrix(matrix, ROWS, COLUMNS);

        boolean isRunning = true;

        while (isRunning)
        {
            printMenu();

            int menuSelection = Input.getInt();
            System.out.println();

            switch (menuSelection)
            {
                case 0:
                    isRunning = false;
                    break;
                case 1:
                    printMatrix(matrix, ROWS, COLUMNS);
                    break;
                case 2:
                    buyTicket(matrix, ROWS, COLUMNS);
                    break;
            }
        }
    }

    private static void buyTicket(int[][] aMatrix, int aRows, int aColumns)
    {
        System.out.println("Enter a row number:");
        final int ROW_NUMBER = Input.getInt();
        System.out.println("Enter a seat number in that row:");
        final int COL_NUMBER = Input.getInt();

        final int ticketPrice = getPrice(aRows - 1, aColumns - 1, ROW_NUMBER);
        System.out.println("Ticket price: $" + ticketPrice);

        aMatrix[ROW_NUMBER][COL_NUMBER] = -2;
    }

    private static void printMenu()
    {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("0. Exit");
    }

    private static int getTotalSeats(int aRows, int aColumns)
    {
        return aRows * aColumns;
    }

    private static int getPrice(int aRows, int aColumns, int aSelectedRow)
    {
        final int TOTAL_SEATS = getTotalSeats(aRows, aColumns);

        if (TOTAL_SEATS < 60)
        {
            return 10;
        }

        final int frontRows = aRows / 2;

        if (aSelectedRow <= frontRows)
        {
            return 10;
        }

        return 8;
    }

    private static void fillMatrix(int[][] aMatrix, int aRows, int aColumns)
    {
        for (int row = 0; row < aRows; ++row)
        {
            for (int col = 0; col < aColumns; ++col)
            {
                if (row == 0)
                {
                    aMatrix[row][col] = col;
                }
                else
                {
                    if (col == 0)
                    {
                        aMatrix[row][col] = row;
                    }
                    else
                    {
                        aMatrix[row][col] = -1;
                    }
                }
            }
        }
    }

    private static void printMatrix(int[][] aMatrix, int aRows, int aColumns)
    {
        // -2 : B
        // -1 : S
        // 0 : empty space
        // else: echo value

        System.out.println("Cinema:");
        for (int row = 0; row < aRows; ++row)
        {
            for (int col = 0; col < aColumns; ++col)
            {
                switch (aMatrix[row][col])
                {
                    case -2:
                        System.out.print("B");
                        break;
                    case -1:
                        System.out.print("S");
                        break;
                    case 0:
                        System.out.print(" ");
                        break;
                    default:
                        System.out.print(aMatrix[row][col]);
                        break;
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}