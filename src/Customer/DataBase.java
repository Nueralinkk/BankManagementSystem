package Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    public static Customer[] loadCustomerSFromDatabase(){
        String url = "jdbc:mysql://localhost:3306/Bank";
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        List<Customer> customerList = new ArrayList<>();

        String query = "SELECT * FROM Bank.customer_accounts";

        System.out.println("Fetching data from Server");

        try {
            // 1. Force register driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish connection and prepare statement
            try (Connection connection = DriverManager.getConnection(url, user, password);
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // 3. Loop through every row returned by your MySQL query
                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    long contactNumber = resultSet.getLong("contact_number");
                    long accountNumber = resultSet.getLong("account_number");
                    String accountType = resultSet.getString("account_type");

                    // 4. Instantiate a new Customer object for this row and add it to the list
                    Customer c = new Customer(id, name, address, contactNumber, accountNumber, accountType);
                    customerList.add(c);
                }


                System.out.println("Successfully loaded " + customerList.size() + " customers from database!");

            }
        }
        catch (Exception e) {
            System.err.println("Failed to read database records: " + e.getMessage());
        }
        return customerList.toArray(new Customer[0]);
    }

    public static int updateContactInDatabase(long Id, long contactNumber) {
        String url = "jdbc:mysql://localhost:3306/Bank";
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        // The SQL query to permanently update the primary key ID
        String updateQuery = "UPDATE bank.customer_accounts SET contact_number = ? WHERE id = ?;";

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

    public static int deleteAccountInDatabase(long accountNumber) {
        String url = "jdbc:mysql://localhost:3306/Bank";
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        // The SQL query to permanently update the primary key ID
        String updateQuery = " Delete from Bank.customer_accounts WHERE account_number = ?;";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            // Set the placeholders (?)
            preparedStatement.setLong(1, accountNumber); // First ? is the new ID

            // executeUpdate() returns the number of rows affected (should be 1)
            return preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.err.println("Database DELETION failed: " + e.getMessage());
            return 0; // Return 0 if the database update failed
        }
    }


}
