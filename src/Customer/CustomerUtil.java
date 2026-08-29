package Customer;

import javax.naming.Name;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;

public class CustomerUtil {
    Scanner sc=new Scanner(System.in);

    public static boolean checkCustomerExistence(Customer[] c, String Name) {
        boolean existence = false;
        String cleanInputName = Name.replace(" ", "");
        for (Customer customer : c) {
            if (customer != null && customer.getCustomerName() != null) {
                String cleanDbName = customer.getCustomerName().replace(" ", "");
                if (cleanDbName.equalsIgnoreCase(cleanInputName)) {
                    existence = true;
                    break;
                }
            }
        }
        return existence;
    }

    public static int getNoOFAccounts(Customer[] c, String Name) {
        int count = 0;
        String cleanInputName = Name.replace(" ", "");
        for (Customer customer : c) {
            if (customer != null && customer.getCustomerName() != null) {
                String cleanDbName = customer.getCustomerName().replace(" ", "");
                if (cleanDbName.equalsIgnoreCase(cleanInputName)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static long[][] getAccountNo(Customer[] c, String Name) {
        int count = getNoOFAccounts(c, Name);
        if (count == 0) {
            return null;
        }
        int index = 0;
        long[] accountNumbers = new long[count];
        long[] id = new long[count];
        String cleanInputName = Name.replace(" ", "");
        for (Customer customer : c) {
            if (customer != null && customer.getCustomerName() != null) {
                String cleanDbName = customer.getCustomerName().replace(" ", "");
                if (cleanDbName.equalsIgnoreCase(cleanInputName)) {
                    accountNumbers[index] = customer.getAccountNumber();
                    id[index] = customer.getCustomerId();
                    index++;
                }
            }
        }
        return new long[][]{id, accountNumbers};
    }

    public static void updateContactFunction(Customer[] c,String name) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter ID to update contact Number: ");
        while (true) {
            long Id = Long.parseLong(sc.nextLine());
            boolean IdValidation = FunctionUtil.validateId(c, Id, name);

            if (IdValidation) {
                System.out.println("Enter contact number to be updated: ");
                while (true) {
                    long contactNumber = Long.parseLong(sc.nextLine());
                    boolean contactValidation =FunctionUtil.validateContactNumber(contactNumber);
                    if (contactValidation) {
                        long status = DataBase.updateContactInDatabase(Id, contactNumber);
                        if (status != 0) {
                            System.out.println("Contact Number is been SuccessFully Updated");
                        }
                        break;
                    } else {
                        System.out.println("Enter valid Contact number:");
                    }
                }
                break;
            } else {
                System.out.println("Enter Valid Id for updating the contact number:");
            }
        }
    }

    public static void deleteAccountFunction(Customer[] c,String name) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter AccountNo. to be deleted: ");
        long accountNumber;
        while (true) {
            try {
                 accountNumber = Long.parseLong(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid format. Please enter numbers only:");
                continue; // Jumps directly back to the top of the loop
            }
            boolean accountValidation = FunctionUtil.validateAccountNo(c, accountNumber, name);
            if (accountValidation) {
                System.out.print(" Confirm you want to delete Account No:");
                String userInput = sc.nextLine();
                if (userInput.equalsIgnoreCase("yes")) {
                        long status = DataBase.deleteAccountInDatabase(accountNumber);
                        if (status != 0) {
                            System.out.println("account is been SuccessFully deleted");
                        } else{
                            System.out.println("Account deletion failed");
                        }
                        break;
                } else{
                    System.out.println("Deletion cancelled by the user.");
                    break;
                  }
                }else{
                System.out.print("Invalid Account number or unauthorized access. Please try again:");
                }
            }
        }

}

