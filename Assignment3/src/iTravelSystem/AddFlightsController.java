package iTravelSystem;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;


public class AddFlightsController implements Initializable {
    @FXML
    Button cancelBtn;
    @FXML
    Button addBtn;
    @FXML
    TextField flightNumberBox;
    @FXML
    TextField originBox;
    @FXML
    TextField destinationBox;
    @FXML
    TextField departTimeBox;
    @FXML
    TextField flightTimeBox;
    @FXML
    TextField aircraftBox;
    @FXML
    TextField aircraftNumberBox;
    @FXML
    TextField seatCapacityBox;

    private FlightsAdapter flightsAdapter;

    public void setFlights(FlightsAdapter flights) {
        flightsAdapter = flights;
    }

    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void add() {
        try {
            flightsAdapter.insertFlights(Integer.parseInt(flightNumberBox.getText()),originBox.getText(),
                    destinationBox.getText(),departTimeBox.getText(),flightTimeBox.getText(),aircraftBox.getText(),
                    Integer.parseInt(aircraftNumberBox.getText()),Integer.parseInt(seatCapacityBox.getText()));
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
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

    }
}
