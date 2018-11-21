package iTravelSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

public class BusinessManagerAdapter {
    Connection connection;

    public BusinessManagerAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                // We drop Matches first because it references the table Teams
                stmt.execute("DROP TABLE BusinessManager");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of teams
                stmt.execute("CREATE TABLE BusinessManager (\n"
                        + "Username CHAR(15) NOT NULL PRIMARY KEY,"
                        + "NameCol CHAR(15) NOT NULL,"
                        + "Password CHAR(15) NOT NULL"
                        + ")");
                populateSampls();
            }
        }
    }
    public void insertBusinessManager(String usernameInput, String nameInput, String passwordInput) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO BusinessManager (Username, NameCol, Password)"
                + " VALUES ('" + usernameInput + "','" + nameInput + "','" + passwordInput + "')");

    }

    private void populateSampls() throws SQLException {

        this.insertBusinessManager("bobDylan36","Bob Dylan", "abbeyRoad123");

    }

    public ObservableList<BusinessManager> getBusinessManagerList() throws SQLException {
        ObservableList<BusinessManager> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM BusinessManager";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        while (rs.next()) {
            list.add(new BusinessManager(
                    rs.getString("Username"),
                    rs.getString("NameCol"),
                    rs.getString("Password")
                    ));
        }
        return list;
    }

}
