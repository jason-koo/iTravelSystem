package iTravelSystem;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;


public class RemoveFlightsController implements Initializable {
    @FXML
    Button cancelBtn;
    @FXML
    Button addBtn;
    @FXML
    TextField flightNumberBox;
    //ComboBox<Integer> flightNumberBox;

    final ObservableList<Integer> data = FXCollections.observableArrayList();

    private FlightsAdapter flightsAdapter;

    public void setModel (FlightsAdapter flights) {
        flightsAdapter = flights;
    }


    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void remove() {
        try {
            flightsAdapter.removeFlights(Integer.parseInt(flightNumberBox.getText()));
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
