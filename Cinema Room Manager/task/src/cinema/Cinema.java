package cinema;

import java.util.Scanner;

public class Cinema
{
    public static void main(String[] args)
    {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        final int ROWS = scanner.nextInt() + 1;
        scanner.nextLine();
        System.out.println("Enter the number of seats in each row:");
        final int COLUMNS = scanner.nextInt() + 1;
        scanner.nextLine();
        System.out.println();

        int[][] matrix = fillMatrix(ROWS, COLUMNS);
        printMatrix(matrix, ROWS, COLUMNS);

        System.out.println("\nEnter a row number:");
        final int ROW_NUMBER = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter a seat number in that row:");
        final int COL_NUMBER = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        final int ticketPrice = getPrice(ROWS - 1, COLUMNS - 1, ROW_NUMBER);

        System.out.println("Ticket price: $" + ticketPrice + "\n");

        matrix[ROW_NUMBER][COL_NUMBER] = -2;
        printMatrix(matrix, ROWS, COLUMNS);
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

    private static int[][] fillMatrix(int aRows, int aColumns)
    {
        int[][] matrix = new int[aRows][aColumns];

        for (int row = 0; row < aRows; ++row)
        {
            for (int col = 0; col < aColumns; ++col)
            {
                if (row == 0)
                {
                    matrix[row][col] = col;
                }
                else
                {
                    if (col == 0)
                    {
                        matrix[row][col] = row;
                    }
                    else
                    {
                        matrix[row][col] = -1;
                    }
                }
            }
        }

        return matrix;
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

                //if (row == 0)
                //{
                //    if (col == 0)
                //    {
                //        System.out.print("  ");
                //    }
                //    else
                //    {
                //        System.out.print(col + " ");
                //    }
                //}
                //else
                //{
                //    if (col == 0)
                //    {
                //        System.out.print(row + " ");
                //    }
                //    else
                //    {
                //        System.out.print("S ");
                //    }
                //}

            }
            System.out.println();
        }
    }
}