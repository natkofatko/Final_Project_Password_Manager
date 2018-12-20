package RegisterForm;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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

    @FXML private JFXTextField username, email;
    @FXML private JFXPasswordField password, confirmpassword;
    @FXML private Button xclose, registernow;
    @FXML private Hyperlink signinhyperlink;
    @FXML private CheckBox checkbox;
    private dbConnection database;
private Connection conn;
private Alert alert;

    /**
     *this is text for a privacy policy
     * @param url
     * @param rs
     */
    public void initialize(URL url, ResourceBundle rs)
    {

        Tooltip tooltip1 = new Tooltip();
        tooltip1.setText("With any service, it is very important to think about your privacy.\n We believe strongly " +
                "that your data is yours and we don’t want to know anything about it. \n" +
                "This is one of the fundamental beliefs " +
                "No-look-pass was built upon." +
                "That’s why our privacy policy is simple: " +
                "\n your data is your data. We don’t use it, we don’t share it, \n " +
                "and we don’t sell it. You’re our customer, not our product." +
                " \n Thank you for trusting us with your most important information. \n We won’t let you down.");
        checkbox.setTooltip(tooltip1);
    }

    /**
     *
     * @param event
     */
    ////code for registration new user
        @FXML
        private void addNewUser(ActionEvent event)
        {
         boolean comparePassword = password.getText().equalsIgnoreCase(confirmpassword.getText());
         boolean emailFormat = email.getText().contains("@")&&email.getText().contains(".");

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
                    alert.setHeaderText("Missing some information!");
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.getDialogPane().setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))));
                    alert.showAndWait();

                }
                else if (emailFormat!=true){

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Email wrong format!");
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.getDialogPane().setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))));
                    alert.showAndWait();
                }
////////////

                else if(comparePassword!=true) {

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Passwords do not match!");
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.getDialogPane().setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))));
                    alert.showAndWait();

                    //alert.getDialogPane().setStyle("-fx-background-color: #FFFFF");
                 // alert.setStyle("-fx-background-color: #FFFFFF;");
                }

                 else
                     if (checkbox.isSelected()) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Registration successful");
                    alert.initStyle(StageStyle.UNDECORATED);
                    alert.getDialogPane().setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))));
                    alert.showAndWait();
                    Stage stage1 = (Stage) this.registernow.getScene().getWindow();
                    stage1.close();
                }

                else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Please accept terms of use \n and privacy policy");
                    alert.initStyle(StageStyle.UNDECORATED);


                    alert.getDialogPane().setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))));

                    //alert1.showAndWait();
                    Optional<ButtonType> result1 = alert.showAndWait();

                }

            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

    /**
     * close the window
     * @param event
     */
    @FXML
     private void closeRegistrationForm(ActionEvent event)
    {
        Stage stage = (Stage) this.xclose.getScene().getWindow();
        stage.close();

    }


    /**
     * link to sign in
     * @param event
     */
    @FXML
    private void signInFromRegisterForm(ActionEvent event)
    {
        Stage stage = (Stage) this.signinhyperlink.getScene().getWindow();
        stage.close();

    }



}
