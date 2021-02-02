package cinema;

import java.util.Locale;
import java.util.Scanner;

/**
 * This class is responsible for running the program that simulates a Cinema Room Manager
 *
 * @author Zaid Neurothrone
 */
public class Cinema
{
    public static void main(String[] args)
    {
        run();
    }

    /**
     * The application loop
     */
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

        int ticketsSold = 0;
        final int totalTickets = (ROWS - 1) * (COLUMNS - 1);
        int currentIncome = 0;
        final int totalIncome = getTotalIncome(ROWS - 1, COLUMNS - 1);

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
                    currentIncome += buyTicket(matrix, ROWS, COLUMNS);
                    ++ticketsSold;
                    break;
                case 3:
                    printStatistics(ticketsSold, totalTickets, currentIncome, totalIncome);
                    break;
            }
        }
    }

    /**
     * Returns the total possible income based on max seats
     *
     * @param aRows int: the number of rows
     * @param aColumns int: the number of seats per row
     * @return int: the total possible income
     */
    private static int getTotalIncome(int aRows, int aColumns)
    {
        int totalTickets = aRows * aColumns;

        if (totalTickets <= 60)
        {
            return totalTickets * 10;
        }

        int frontSeatsIncome = (aRows / 2) * aColumns * 10;
        int backSeatsIncome = (aRows - (aRows / 2)) * aColumns * 8;
        return frontSeatsIncome + backSeatsIncome;
    }

    /**
     * Prints the statistics pertintent to the cinema business
     *
     * @param aTicketsSold int: tickets currently sold
     * @param aTotalTickets int: max tickets that can be sold
     * @param aCurrentIncome int: current revenue
     * @param aTotalIncome int: max revenue that can be earned if all tickets are sold
     */
    private static void printStatistics(int aTicketsSold, int aTotalTickets,
                                        int aCurrentIncome, int aTotalIncome)
    {
        System.out.println("Number of purchased tickets: " + aTicketsSold);
        final double percentage = (double) aTicketsSold / aTotalTickets;
        System.out.printf(Locale.ENGLISH,"Percentage: %.2f%%\n", percentage * 100);
        System.out.println("Current income: $" + aCurrentIncome);
        System.out.println("Total income: $" + aTotalIncome);
    }

    /**
     * Returns the ticket price after purchase
     *
     * @param aMatrix int[][]: the seats on the 2D array
     * @param aRows int: the number of rows
     * @param aColumns int: the number of columns or seats per row
     * @return int: the price of the ticket
     */
    private static int buyTicket(int[][] aMatrix, int aRows, int aColumns)
    {
        boolean isValid = false;
        int rowNumber = -1;
        int colNumber = -1;

        while (!isValid)
        {
            System.out.println("Enter a row number:");
            rowNumber = Input.getInt();
            System.out.println("Enter a seat number in that row:");
            colNumber = Input.getInt();

            if (rowNumber >= 0 && rowNumber < aRows && colNumber >= 0 && colNumber < aColumns)
            {
                if (aMatrix[rowNumber][colNumber] == -1)
                {
                    isValid = true;
                }
                else
                {
                    System.out.println("\nThat ticket has already been purchased!\n");
                }
            }
            else
            {
                System.out.println("Wrong input!");
            }
        }

        final int ticketPrice = getPrice(aRows - 1, aColumns - 1, rowNumber);
        System.out.println("\nTicket price: $" + ticketPrice);

        aMatrix[rowNumber][colNumber] = -2;
        return ticketPrice;
    }

    /**
     * Outputs the possible menu choices of this program
     */
    private static void printMenu()
    {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    /**
     * Returns the total number of seats
     *
     * @param aRows int: number of rows
     * @param aColumns int: number of columns
     * @return int: the total seats
     */
    private static int getTotalSeats(int aRows, int aColumns)
    {
        return aRows * aColumns;
    }

    /**
     * Returns the price of the ticket based on row and user input
     *
     * @param aRows int: the number of rows
     * @param aColumns int: the number of columns or seats per row
     * @param aSelectedRow int: the row selected by the user
     * @return int: price of the ticket
     */
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

    /**
     * Fills the matrix with numbers to represent a cinema
     *
     * @param aMatrix int[][]: the 2D array
     * @param aRows int: the number of rows in the matrix
     * @param aColumns int: the number of columns or seats per row in the matrix
     */
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

    /**
     * Prints the appropriate symbol based on the contents of the matrix
     *
     * @param aMatrix int[][]: the 2D array to represent seatings in a cinema
     * @param aRows int: the number of rows
     * @param aColumns int: the number of columns or seats per row
     */
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