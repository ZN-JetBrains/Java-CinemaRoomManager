package cinema;

public class Cinema
{
    public static void main(String[] args)
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