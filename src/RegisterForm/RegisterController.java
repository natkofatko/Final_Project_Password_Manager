package RegisterForm;

import database.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegisterController implements Initializable
{

    @FXML private TextField username, email;
    @FXML private PasswordField password, confirmpassword;
    @FXML private Button xclose, registernow;
    @FXML private Hyperlink signinhyperlink;
    @FXML private CheckBox checkbox;
    private dbConnection database;
Connection conn;

    public void initialize(URL url, ResourceBundle rs)
    {

    }
    //code for regustration
        @FXML
        private void addNewUser(ActionEvent event)
        {
        boolean comparePassword = password.getText().equalsIgnoreCase(confirmpassword.getText());


            String sqlInsert = "Insert into userpass1 (username, email,password,confirmpassword) values (?,?,?,?)";

            try{
                conn = dbConnection.getCOnnection();
                PreparedStatement stmt = conn.prepareStatement(sqlInsert);
                stmt.setString(1,this.username.getText());
                stmt.setString(2,this.email.getText());
                stmt.setString(3,this.password.getText());
                stmt.setString(4,this.confirmpassword.getText());
                checkbox.isSelected();
                stmt.execute();
                conn.close();
                //Stage stage = (Stage) this.registernow.getScene().getWindow();


                if(username.getText().equalsIgnoreCase("")||email.getText().equalsIgnoreCase("")
                        ||password.getText().equalsIgnoreCase("") ||confirmpassword.getText().equalsIgnoreCase(""))

                {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setContentText("MISSING SOME INFORMATION!");
                    alert2.initStyle(StageStyle.UNDECORATED);
                    alert2.showAndWait();

                }
                else if(comparePassword!=true) {

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setContentText("PASSWORDS DO NOT MATCH!");
                    alert2.initStyle(StageStyle.UNDECORATED);
                    alert2.showAndWait();
                }

                 else
                     if (checkbox.isSelected()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("REGISTRATION SUCCESFUL");
                    alert.initStyle(StageStyle.UNDECORATED);


                    alert.showAndWait();
                    Stage stage1 = (Stage) this.registernow.getScene().getWindow();
                    stage1.close();
                }

                else {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setHeaderText("PLESAE ACCEPT THR TERMS OF USE AND PRIVACY POLICY");
                    alert1.initStyle(StageStyle.UNDECORATED);


                    //alert1.showAndWait();
                    Optional<ButtonType> result1 = alert1.showAndWait();

                }

            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

    @FXML
     private void closeRegistrationForm(ActionEvent event)
    {
        Stage stage = (Stage) this.xclose.getScene().getWindow();
        stage.close();

    }


    @FXML
    private void signInFromRegisterForm(ActionEvent event)
    {
        Stage stage = (Stage) this.signinhyperlink.getScene().getWindow();
        stage.close();

    }



}
