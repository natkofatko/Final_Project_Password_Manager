package RegisterForm;

import database.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegisterController implements Initializable
{
///

    @FXML private TextField username, email;
    @FXML private PasswordField password, confirmpassword;
    @FXML private Button xclose, registernow;
    @FXML private Hyperlink signinhyperlink;
    @FXML private CheckBox checkbox;
    private dbConnection database;
private Connection conn;
private Alert alert;

    public void initialize(URL url, ResourceBundle rs)
    {

    }
    ////code for registration
        @FXML
        private void addNewUser(ActionEvent event)
        {
         boolean comparePassword = password.getText().equalsIgnoreCase(confirmpassword.getText());
         boolean emailFormat = email.getText().contains("@");

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
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("MISSING SOME INFORMATION!");
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.getDialogPane().setBackground((new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY))));
                    alert.showAndWait();

                }
                else if (emailFormat!=true){

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("EMAIL WRONG FORMAT!");
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.getDialogPane().setBackground((new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY))));
                    alert.showAndWait();
                }
////////////

                else if(comparePassword!=true) {

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("PASSWORDS DO NOT MATCH!");
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.getDialogPane().setBackground((new Background(new BackgroundFill(Color.DEEPPINK, CornerRadii.EMPTY, Insets.EMPTY))));
                    alert.showAndWait();

                    //alert.getDialogPane().setStyle("-fx-background-color: #FFFFF");
                 // alert.setStyle("-fx-background-color: #FFFFFF;");
                }

                 else
                     if (checkbox.isSelected()) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("REGISTRATION SUCCESFUL");
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.getDialogPane().setBackground((new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY))));
                    alert.showAndWait();
                    Stage stage1 = (Stage) this.registernow.getScene().getWindow();
                    stage1.close();
                }

                else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("PLESAE ACCEPT THR TERMS OF USE \n AND PRIVACY POLICY");
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.getDialogPane().setBackground((new Background(new BackgroundFill(Color.DEEPPINK, CornerRadii.EMPTY, Insets.EMPTY))));

                    //alert1.showAndWait();
                    Optional<ButtonType> result1 = alert.showAndWait();

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
