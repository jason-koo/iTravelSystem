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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DisplayProfileController implements Initializable {

    private ProfileAdapter profileAdapter;

    @FXML
    private TableView<Profile> tableView;
    @FXML
    private TableColumn<Profile, Integer> userNumberColumn;
    @FXML
    private TableColumn<Profile, String> firstNameColumn;
    @FXML
    private TableColumn<Profile, String> lastNameColumn;

    final ObservableList<Profile> data = FXCollections.observableArrayList();

    public void setModel(ProfileAdapter profile) {
        profileAdapter = profile;
        buildData();
    }

    private void displayAlert(String msg) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = (AlertController) loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            //stage.getIcons().add(new Image("file:src/TennisBallGames/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }

    @FXML
    public void buildData() {
        try {
            data.addAll(profileAdapter.getProfileList());
        } catch (SQLException ex) {
            displayAlert("ERROR: "+ ex.getMessage());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userNumberColumn.setCellValueFactory(cellData -> cellData.getValue().UserNumberProperty().asObject());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().FirstNameProperty());
        lastNameColumn.setCellValueFactory(cellData-> cellData.getValue().LastNameProperty());

        tableView.setItems(data);

    }
}
