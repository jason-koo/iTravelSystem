package iTravelSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

public class DiscountProgramStatusAdapter {
    Connection connection;

    public DiscountProgramStatusAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                // We drop Matches first because it references the table Teams
                stmt.execute("DROP TABLE DiscountProgramStatus");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of teams
                stmt.execute("CREATE TABLE DiscountProgramStatus (\n"
                        + "PassengerUsername CHAR(15) NOT NULL PRIMARY KEY,"
                        + "DiscountAmount CHAR(15) NOT NULL"
                        + ")");
                populateSampls();
            }
        }
    }
    public void insertDiscountProgramStatus(String passengerUsernameInput, String discountAmountInput) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO DiscountProgramStatus (PassengerUsername, DiscountAmount)"
                + " VALUES ('" + passengerUsernameInput + "','" + discountAmountInput + "')");

    }

    private void populateSampls() throws SQLException {

        this.insertDiscountProgramStatus("BobDylan1967","40%");

    }

    public ObservableList<DiscountProgramStatus> getDiscountProgramStatusList() throws SQLException {
        ObservableList<DiscountProgramStatus> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM DiscountProgramStatus";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        while (rs.next()) {
            list.add(new DiscountProgramStatus(
                    rs.getString("PassengerUsername"),
                    rs.getString("DiscountAmount")
            ));
        }
        return list;
    }

}
