package cinema;

import java.util.Scanner;

public class Input
{
    private static final Scanner scanner = new Scanner(System.in);

    public static int getInt()
    {
        final int userInput = scanner.nextInt();
        scanner.nextLine();
        return userInput;
    }
}
