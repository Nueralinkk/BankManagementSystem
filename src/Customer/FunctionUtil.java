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

    public static boolean validateId(Customer[] c, long id, String name) {
        long[][] account = CustomerUtil.getAccountNo(c, name);
        if (account != null) {
            for (int i = 0; i < account[0].length; i++) {
                if (id == account[0][i]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean validateContactNumber(long inputContactNumber) {
        return inputContactNumber > 999999999 && 10000000000L > inputContactNumber;
    }

    public static boolean validateAccountNo(Customer[] c, long inputAccountNumber, String name) {
        long[][] account = CustomerUtil.getAccountNo(c, name);
        if (inputAccountNumber > 99999999999999L && 1000000000000000L > inputAccountNumber && account != null) {
                for (int i = 0; i < account[1].length; i++) {
                    if (inputAccountNumber == account[1][i]) {
                        return true;
                    }
                }
            }
        return false;
    }
}

