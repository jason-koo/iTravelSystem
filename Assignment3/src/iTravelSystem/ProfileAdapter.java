package iTravelSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

public class ProfileAdapter {
    Connection connection;

    public ProfileAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                // We drop Matches first because it references the table Teams
                stmt.execute("DROP TABLE Profile");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of teams
                stmt.execute("CREATE TABLE Profile (\n"
                        //+ "UserNumber INT, "
                        + "FirstName CHAR(15) NOT NULL PRIMARY KEY,"
                        + "LastName CHAR(15) NOT NULL"
                        + ")");
                populateSampls();
            }
        }
    }

    private void populateSampls() throws SQLException {

        this.insertProfile("Bob","Dylan");
        this.insertProfile("Kanye","West");
        this.insertProfile("Paul","Mccartney");
        this.insertProfile("Young","Thug");
    }

    public void insertProfile(String nameFirst, String nameLast) throws SQLException {
        //line 51 is the problem statement?
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Profile (FirstName, LastName)"
                + " VALUES ('" + nameFirst + "','" + nameLast + "')");

        //stmt.executeUpdate("INSERT INTO Profile (UserNumber, FirstName, LastName) VALUES (0,'+ name + ', '+name+')");
    }

    // Get all teams Data
    public ObservableList<Profile> getProfileList() throws SQLException {
        ObservableList<Profile> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM Profile";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        while (rs.next()) {
            list.add(new Profile(
                    rs.getString("FirstName"),
                    rs.getString("LastName")));
        }
        return list;
    }
/*
    // Get all teams names to populate the ComboBoxs used in Task #3.
    public ObservableList<String> getTeamsNames() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();

        ResultSet result;
        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM Teams";
        result = stmt.executeQuery(sqlStatement);

        while (result.next()){
            list.add(result.getString("TeamName"));
        }
        return list;
    }
    */
    // Create a Statement object


    // Create a string with a SELECT statement


    // Execute the statement and return the result


    // loop for the all rs rows and update list
/*

    public void setStatus(String hTeam, String vTeam, int hScore, int vScore) throws SQLException {
        // Create a Statement object
        Statement stmt = connection.createStatement();
        ResultSet rs;

        // Write your code here for Task #4

    }
*/
}
