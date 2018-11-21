package iTravelSystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller implements Initializable {

    private ProfileAdapter profile;
    private FlightsAdapter flights;
    private Connection conn;

    @FXML
    private MenuBar mainMenu;
    @FXML
    public void showAbout() throws Exception {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent About = (Parent) fxmlLoader.load();

        Scene scene = new Scene(About);
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        //stage.getIcons().add(new Image("file:src/TennisBallGames/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void addProfile() throws Exception {
        // Toggle the comments below after you finish the requirement of Task #3
        profile = new ProfileAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddProfile.fxml"));
        Parent addMatch = (Parent) fxmlLoader.load();
        AddProfileController addMatchController = (AddProfileController) fxmlLoader.getController();
        addMatchController.setProfile(profile);

        Scene scene = new Scene(addMatch);
        Stage stage = new Stage();

        stage.setScene(scene);
        //stage.getIcons().add(new Image("file:src/TennisBallGames/WesternLogo.png"));
        stage.setTitle("Add New Profile");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void addFlights() throws Exception {
        // Toggle the comments below after you finish the requirement of Task #3
        flights = new FlightsAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddFlights.fxml"));
        Parent addMatch = (Parent) fxmlLoader.load();
        AddFlightsController addFlightsController = (AddFlightsController) fxmlLoader.getController();
        addFlightsController.setFlights(flights);

        Scene scene = new Scene(addMatch);
        Stage stage = new Stage();

        stage.setScene(scene);
        //stage.getIcons().add(new Image("file:src/TennisBallGames/WesternLogo.png"));
        stage.setTitle("Add New Flights");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void displayProfiles() throws Exception {
        // create Teams model
        profile = new ProfileAdapter(conn, false);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplayProfiles.fxml"));
        Parent profiles = (Parent) fxmlLoader.load();

        DisplayProfileController displayProfileController = (DisplayProfileController) fxmlLoader.getController();
        displayProfileController.setModel(profile);

        Scene scene = new Scene(profiles);
        Stage stage = new Stage();

        stage.setScene(scene);
        //stage.getIcons().add(new Image("file:src/TennisBallGames/WesternLogo.png"));
        stage.setTitle("Current Profiles");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void exit() throws SQLException {
        conn.close();
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }

    private void displayAlert(String msg) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = (AlertController) loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/TennisBallGames/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            String DB_URL = "jdbc:derby:ProfileDB;create=true";

            conn = DriverManager.getConnection(DB_URL);

        } catch (SQLException ex) {
            displayAlert(ex.getMessage());
        }
    }
}
