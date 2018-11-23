package iTravelSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class FlightsAdapter {
    Connection connection;

    public FlightsAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                // We drop Matches first because it references the table Teams
                stmt.execute("DROP TABLE Flights");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of teams
                stmt.execute("CREATE TABLE Flights (\n"
                        + "flightNumber INT PRIMARY KEY, "
                        + "origin CHAR(15) NOT NULL,"
                        + "destination CHAR(15) NOT NULL,"
                        + "departTime CHAR(15) NOT NULL,"
                        + "flightTime CHAR(15) NOT NULL,"
                        + "aircraft CHAR(15) NOT NULL,"
                        + "aircraftNumber INT,"
                        + "seatCapacity INT"
                        + ")");
                populateSampls();
            }
        }
    }

    private void populateSampls() throws SQLException {

        this.insertFlights(1834,"Dallas","Phoenix","7:30","10:45",
                "Boeing 747",990,442);

    }

    public void insertFlights(int flightNumberInput, String originInput, String destinationInput,
                              String departTimeInput, String flightTimeInput, String aircraftInput,
                              int aircraftNumberInput, int seatCapacity) throws SQLException {

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Flights (flightNumber, origin, destination, departTime,flightTime,aircraft,aircraftNumber,seatCapacity)"
                + " VALUES (" + flightNumberInput + ",'" + originInput + "','" + destinationInput + "'," +
                "'" + departTimeInput +"','" + flightTimeInput +"','" + aircraftInput + "'," + aircraftNumberInput + "," + seatCapacity + ")");

        //stmt.executeUpdate("INSERT INTO Profile (UserNumber, FirstName, LastName) VALUES (0,'+ name + ', '+name+')");
    }

    public void removeFlights(int flightNumberInput) throws SQLException {
        Statement stmt = connection.createStatement();
        String sql = "DELETE FROM Flights WHERE FlightNumber=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        //this next line will replace the ? in the above SQL statement
        ps.setInt(1, flightNumberInput);
        ps.executeUpdate();

        //stmt.executeUpdate("DELETE FROM Flights WHERE flightNumber = '" + flightNumberInput + "' ");
    }

    public ObservableList<Flights> getFlightsList() throws SQLException {
        ObservableList<Flights> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM Flights";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        while (rs.next()) {
            list.add(new Flights(
                    rs.getInt("flightNumber"),
                    rs.getString("origin"),
                    rs.getString("destination"),
                    rs.getString("departTime"),
                    rs.getString("flightTime"),
                    rs.getString("aircraft"),
                    rs.getInt("aircraftNumber"),
                    rs.getInt("seatCapacity")
                    ));
        }
        return list;
    }

}
