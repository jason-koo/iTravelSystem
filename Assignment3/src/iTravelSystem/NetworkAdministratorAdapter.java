package iTravelSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NetworkAdministratorAdapter {
    Connection connection;

    public NetworkAdministratorAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                // We drop Matches first because it references the table Teams
                stmt.execute("DROP TABLE NetworkAdministrator");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of teams
                stmt.execute("CREATE TABLE NetworkAdministrator (\n"
                        + "Username CHAR(15) NOT NULL PRIMARY KEY,"
                        + "NameCol CHAR(15) NOT NULL,"
                        + "Password CHAR(15) NOT NULL"
                        + ")");
                populateSampls();
            }
        }
    }
    public void insertNetworkAdministrator(String usernameInput, String nameInput, String passwordInput) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO NetworkAdminstrator (Username, NameCol, Password)"
                + " VALUES ('" + usernameInput + "','" + nameInput + "','" + passwordInput + "')");

    }

    private void populateSampls() throws SQLException {

        this.insertNetworkAdministrator("bobDylan36","Bob Dylan", "abbeyRoad123");

    }

    public ObservableList<NetworkAdministrator> getNetworkAdministratorList() throws SQLException {
        ObservableList<NetworkAdministrator> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM NetworkAdministrator";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        while (rs.next()) {
            list.add(new NetworkAdministrator(
                    rs.getString("Username"),
                    rs.getString("NameCol"),
                    rs.getString("Password")
            ));
        }
        return list;
    }

}
