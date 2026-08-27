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

        String query = "SELECT * FROM bank.customers";

        System.out.println("Fetching data from MySQL...");

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

}
