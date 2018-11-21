package scoreTheStrength;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ScoreController implements Initializable {


    @FXML
    private TextField password, strength;

    @FXML
    private JFXButton checkthestrength, close;

    public void initialize(URL url, ResourceBundle rs) {

    }
//
    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) this.close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clearFields(ActionEvent event) {
    password.setText("");
    strength.setText("");

    }

    @FXML
    private void checkForPasswordStrength(ActionEvent event) {
        String passwordToCheck = this.password.getText();


                //int score = PasswordAssessor.assessPassword(passwordToCheck);
       String str = PasswordAssessor.passwordStrength(passwordToCheck);

                // ((String)score);

       // String strI = Integer.toString(score);

        strength.setText(str);


    }
}
