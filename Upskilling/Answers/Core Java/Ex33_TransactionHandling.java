import java.sql.*;

public class Ex33_TransactionHandling {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bankdb";
        String user = "root";
        String password = "password";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false); // start transaction

            try {
                // debit from account 1
                PreparedStatement debit = conn.prepareStatement(
                    "UPDATE accounts SET balance = balance - ? WHERE id = ?");
                debit.setDouble(1, 500.0);
                debit.setInt(2, 1);
                debit.executeUpdate();

                // credit to account 2
                PreparedStatement credit = conn.prepareStatement(
                    "UPDATE accounts SET balance = balance + ? WHERE id = ?");
                credit.setDouble(1, 500.0);
                credit.setInt(2, 2);
                credit.executeUpdate();

                conn.commit(); // both succeeded
                System.out.println("Transfer successful!");

            } catch (SQLException e) {
                conn.rollback(); // something went wrong, undo everything
                System.out.println("Transfer failed, rolled back: " + e.getMessage());
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}
