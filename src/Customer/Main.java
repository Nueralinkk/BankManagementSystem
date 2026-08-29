package Customer;
import java.lang.*;
import java.sql.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isFirstRun = true;
        while (true) {
        Customer[] customer = DataBase.loadCustomerSFromDatabase();
        if(customer.length==0) {
            System.out.println("No data available.exiting program");
            return;
        }


            if (!isFirstRun && FunctionUtil.shouldExit(sc)){
                IO.println("Thank you for using our services! Goodbye!");
                break;
            }
            isFirstRun=false;
            // Now you can safely use your 'customer' array or 'customerList' down below!
            System.out.print("Enter Name of the AccountHolder: ");
            String name = sc.nextLine();

            System.out.println("Customer " + name + " has account in the bank: " + CustomerUtil.checkCustomerExistence(customer, name));
            if (CustomerUtil.checkCustomerExistence(customer, name)) {
                System.out.println("No. of Account " + name + " has are:" + CustomerUtil.getNoOFAccounts(customer, name));

                //print account numbers
                long[][] account = CustomerUtil.getAccountNo(customer, name);
                try {
                    if (account == null) {
                        System.out.println("No accounts found for " + name);
                    } else {
                        System.out.println("Accounts found for " + name + ":");

                        // Loop through the length of the columns (the number of matches found)
                        for (int i = 0; i < account[0].length; i++) {
                            long customerId = account[0][i];    // Row 1 is customer IDs
                            long accountNumber = account[1][i]; // Row 0 is account numbers
                            System.out.println("ID: " + customerId + " | Account Number: " + accountNumber);
                        }
                    }

                    boolean running = true;
                    while (running) {
                        FunctionUtil.printMenu();

                        int choice = Integer.parseInt(sc.nextLine());
                        switch (choice) {
                            case 1:
                                CustomerUtil.updateContactFunction(customer, name);
                                break;
                            case 5:
                                CustomerUtil.deleteAccountFunction(customer,name);
                            case 6:
                                running = false;
                                break;
                            default:
                                System.out.println("Unexpected value: " + choice);
                        }
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("Error!=Due to wrong input data type");
                    continue;
                }
            }
        }
    }
}

