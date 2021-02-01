package cinema;

import java.util.Scanner;

public class Cinema
{
    public static void main(String[] args)
    {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        final int ROWS = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the number of seats in each row:");
        final int COLUMNS = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Total income:");

        final int TOTAL_SEATS = ROWS * COLUMNS;
        final int PRICE_PER_TICKET_FRONT = 10;
        final int PRICE_PER_TICKET_BACK;

        final int TOTAL_REVENUE;

        final int frontRows = ROWS / 2;
        final int backRows = ROWS - frontRows;

        if (TOTAL_SEATS < 60)
        {
            TOTAL_REVENUE = TOTAL_SEATS * PRICE_PER_TICKET_FRONT;
        }
        else
        {
            PRICE_PER_TICKET_BACK = 8;
            TOTAL_REVENUE = COLUMNS * (PRICE_PER_TICKET_FRONT * frontRows) + COLUMNS * (PRICE_PER_TICKET_BACK * backRows);
        }

        System.out.println("$" + TOTAL_REVENUE);
    }

    private void printArrangement()
    {
        final int ROW_SIZE = 7;
        final int COL_SIZE = 8;

        System.out.println("Cinema:");
        for (int row = 0; row <= ROW_SIZE; ++row)
        {
            for (int col = 0; col <= COL_SIZE; ++col)
            {
                if (row == 0)
                {
                    if (col == 0)
                    {
                        System.out.print("  ");
                    }
                    else
                    {
                        System.out.print(col + " ");
                    }
                }
                else
                {
                    if (col == 0)
                    {
                        System.out.print(row + " ");
                    }
                    else
                    {
                        System.out.print("S ");
                    }
                }

            }
            System.out.println();
        }
    }
}