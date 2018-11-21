package iTravelSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServersAdapter {
    Connection connection;

    public ServersAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                // We drop Matches first because it references the table Teams
                stmt.execute("DROP TABLE Servers");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of teams
                stmt.execute("CREATE TABLE Servers (\n"
                        + "ServerID CHAR(15) NOT NULL PRIMARY KEY,"
                        + "PatchVersion CHAR(15) NOT NULL,"
                        + "UpgradeVersion CHAR(15) NOT NULL"
                        + ")");
                populateSampls();
            }
        }
    }
    public void insertServers(String serverIDInput, String patchVersionInput, String upgradeVersionInput) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Servers (ServerID, PatchVersion, UpgradeVersion)"
                + " VALUES ('" + serverIDInput + "','" + patchVersionInput + "','" + upgradeVersionInput + "')");

    }

    private void populateSampls() throws SQLException {

        this.insertServers("46","1.04","2.8.0");

    }

    public ObservableList<Servers> getServersList() throws SQLException {
        ObservableList<Servers> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM Servers";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        while (rs.next()) {
            list.add(new Servers(
                    rs.getString("ServerID"),
                    rs.getString("PatchVersion"),
                    rs.getString("UpgradeVersion")
            ));
        }
        return list;
    }

}
