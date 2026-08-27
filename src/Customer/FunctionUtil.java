package Customer;
import java.util.*;
public class FunctionUtil {
    public static boolean shouldExit(Scanner sc) {
        System.out.println("Type 'exit' to quit, or press Enter to run again: ");
        return sc.nextLine().trim().equalsIgnoreCase("exit");
    }

    public static void printMenu() {
        IO.println("===============================================================");
        IO.println("||             Select one option from below                  ||");
        IO.println("===============================================================");
        IO.println("||             1.Update Contact Number                       ||");
        IO.println("||             2.Update Address                              ||");
        IO.println("||             3.Kyc Status                                  ||");
        IO.println("||             4.KYC verification                            ||");
        IO.println("||             5.Delete Account                              ||");
        IO.println("||             6.Exit                                        ||");
        IO.println("===============================================================");
    }

}

