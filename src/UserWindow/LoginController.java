package UserWindow;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample.dbStatus;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;



public class LoginController implements Initializable{
    @FXML private TextField search;
    @FXML private JFXButton addnewentry, deleteentry, editentry, generatepassword, scorethestrength;

    @FXML private JFXButton logout,exit, analyzepassword;
    @FXML TableView<UserData> usertable;
    @FXML
    TableColumn<UserData, String> usernamecollumn;
    @FXML
    TableColumn<UserData, String> addresscollumn;
    @FXML
    TableColumn<UserData, String> categorycollumn;
    @FXML
    TableColumn<UserData, String> passwordcollumn;


    public void initialize(URL url, ResourceBundle rs)
    {

    }

@FXML
    private void addNewEntry(ActionEvent event) throws Exception
{

    Stage userStage = new Stage();
    FXMLLoader loader = new FXMLLoader();
    Pane root = (Pane) loader.load(getClass().getResource("/NewEntry/NewEntryFXML.fxml"));
    Scene scene= new Scene(root);
    userStage.initStyle(StageStyle.UNDECORATED);
    userStage.setScene(scene);
    userStage.setResizable(false);

    userStage.show();

}


    @FXML
    private void logout(ActionEvent event) throws Exception
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to log out?");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().setBackground((new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY))));
        //alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) this.search.getScene().getWindow();
            stage.close();
            Thread.sleep(1000);

            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/sample/sample.fxml"));
            Scene scene= new Scene(root);
            userStage.initStyle(StageStyle.UNDECORATED);
            userStage.setScene(scene);
            userStage.setResizable(false);

            userStage.show();


        }
        if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }




    }
