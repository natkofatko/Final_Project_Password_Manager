package NewEntry;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.dbStatus;


import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class NewEntryController implements Initializable{

    @FXML private JFXButton addentry, clear, cancel;
    @FXML private JFXTextField username, urladdress;
    @FXML private JFXPasswordField password;
    @FXML private JFXComboBox<groupOption> groups;

    dbStatus dataBase= new dbStatus();
    public void initialize(URL url, ResourceBundle rb) {
        if (dataBase.isDatabaseConnected()) {
            System.out.print("Database connected succesfully");
        } else {
            System.out.print("Problem with daatabase connection");
        }
        this.groups.setItems(FXCollections.observableArrayList(groupOption.values()));
    }


    @FXML
    private void clearFields(ActionEvent event)
    {
        username.setText("");
        urladdress.setText("");
        password.setText("");


    }

    @FXML
    private void Exit(ActionEvent event)
    {

       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to close?");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().setBackground((new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY))));
        //alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()== ButtonType.OK)
        {
        Stage stage = (Stage) this.username.getScene().getWindow();
        stage.close();
        }
        if(result.get()==ButtonType.CANCEL){
            alert.close();
        }



}}
