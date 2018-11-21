package iTravelSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

public class ServerLogAdapter {
    Connection connection;

    public ServerLogAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                // We drop Matches first because it references the table Teams
                stmt.execute("DROP TABLE ServerLog");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of teams
                stmt.execute("CREATE TABLE ServerLog (\n"
                        + "ServerID CHAR(15) NOT NULL PRIMARY KEY,"
                        + "ErrorLogs CHAR(15) NOT NULL,"
                        + "HardwareLogs CHAR(15) NOT NULL"
                        + ")");
                populateSampls();
            }
        }
    }
    public void insertServerLog(String serverIDInput, String errorLogsInput, String hardwareLogsInput) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO ServerLog (ServerID, ErrorLogs, HardwareLogs)"
                + " VALUES ('" + serverIDInput + "','" + errorLogsInput + "','" + hardwareLogsInput + "')");

    }

    private void populateSampls() throws SQLException {

        this.insertServerLog("42","Error line 42","Hardware malfunction");

    }

    public ObservableList<ServerLog> getServerLogList() throws SQLException {
        ObservableList<ServerLog> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM ServerLog";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        while (rs.next()) {
            list.add(new ServerLog(
                    rs.getString("ServerID"),
                    rs.getString("ErrorLogs"),
                    rs.getString("HardwareLogs")
            ));
        }
        return list;
    }

}
