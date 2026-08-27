package Customer;

import javax.naming.Name;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;

public class CustomerUtil {

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

    public static int updateContactInDatabase(long Id, long contactNumber) {
        String url = "jdbc:mysql://localhost:3306/Bank";
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        // The SQL query to permanently update the primary key ID
        String updateQuery = "UPDATE bank.customers SET contact_number = ? WHERE id = ?;";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            // Set the placeholders (?)
            preparedStatement.setLong(1, contactNumber); // First ? is the new ID
            preparedStatement.setLong(2, Id); // Second ? is the old ID

            // executeUpdate() returns the number of rows affected (should be 1)
            return preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.err.println("Database update failed: " + e.getMessage());
            return 0; // Return 0 if the database update failed
        }
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
            boolean IdValidation = CustomerUtil.validateId(c, Id, name);

            if (IdValidation) {
                System.out.println("Enter contact number to be updated: ");
                while (true) {
                    long contactNumber = Long.parseLong(sc.nextLine());
                    boolean contactValidation = CustomerUtil.validateContactNumber(contactNumber);
                    if (contactValidation) {
                        long status = CustomerUtil.updateContactInDatabase(Id, contactNumber);
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


}
