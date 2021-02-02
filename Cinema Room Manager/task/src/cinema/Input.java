package cinema;

import java.util.Scanner;

/**
 * Utility class for input
 *
 * @author Zaid Neurothrone
 */
public class Input
{
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Returns the integer entered by the user and flushes the input buffer
     *
     * @return int: integer entered by user
     */
    public static int getInt()
    {
        final int userInput = scanner.nextInt();
        scanner.nextLine();
        return userInput;
    }
}
