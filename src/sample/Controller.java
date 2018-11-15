package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button submit, cancel, signup;
    @FXML
    private Button exit;
    @FXML
    private Label xclose, signinlable;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;


    public void initialize(URL url, ResourceBundle rb) {

    }

    //this will close the current window
    @FXML
    private void cancel(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EXIT");
        alert.setHeaderText("Click OK to close the window");
        //alert.setContentText("Are you sure you want to close?");
        //alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) this.cancel.getScene().getWindow();
            stage.close();
        }

        if (result.get() == ButtonType.CANCEL) {
            alert.close();

        }


    }

    dbStatus db = new dbStatus();

    //check for credentials, login to your account, open another window
    @FXML
    private void logintoAccount(ActionEvent event) throws Exception {
        if (db.isUserLogIn(username.getText(), this.password.getText())) {
            // if(this.username.getText().equalsIgnoreCase("123") && password.getText().equalsIgnoreCase("123")) {
            try {
                Stage stage = (Stage) this.username.getScene().getWindow();
                stage.close();
                Stage userStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = (Pane) loader.load(Main.class.getResource("/UserWindow/Login.fxml"));
                // LoginController loginContr = (LoginController) loader.getController();
                //Scene scene = new Scene(root);
                userStage.initStyle(StageStyle.UNDECORATED);
                userStage.setScene(new Scene(root, 1100, 750));

                //  userStage.setScene(scene);
                // userStage.initStyle(StageStyle.UNDECORATED);

                userStage.setTitle("Welcome to Password Manager");
                userStage.setResizable(false);
                userStage.show();
                dbStatus db = new dbStatus();
                System.out.print(db.isDatabaseConnected());


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {


            signinlable.setFont(new Font("Arial", 35));
            signinlable.setTextFill(Color.web(("#FE000D")));
            Text t = new Text("Wrong credentials");
            signinlable.setText(t.getText());
            username.getStyleClass().add("wrong-credentials");
            password.getStyleClass().add("wrong-credentials");
            username.setText("");
            password.setText("");


        }


    }

    //change to MouseEvent if ActionEvent doesnt work
    @FXML
    private void forgotPassword(ActionEvent event) {
        //Code for resseting password
    }

    //Register new user
    @FXML
    private void SignUp(ActionEvent event) throws IOException {
        // Stage stage = (Stage) this.signup.getScene().getWindow();
        //stage.close();

        Stage registerFormStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = (Pane) loader.load(getClass().getResource("/RegisterForm/RegisterFXML.fxml"));
        Scene scene = new Scene(root);
        registerFormStage.initStyle(StageStyle.UNDECORATED);
        registerFormStage.setScene(scene);
        registerFormStage.show();
        registerFormStage.setResizable(false);


    }


}
