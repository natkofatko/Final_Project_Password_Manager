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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.dbStatus;

import  database.dbConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class NewEntryController implements Initializable{

    @FXML private JFXButton addentry, clear, cancel, generatenewpassword;
    @FXML private JFXTextField username, urladdress;
    @FXML private JFXPasswordField password;
    @FXML private JFXComboBox<groupOption> groups;
    private dbConnection conn;

    dbStatus dataBase= new dbStatus();

    /**
     *
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        if (dataBase.isDatabaseConnected()) {
            System.out.print("Database connected succesfully");
        } else {
            System.out.print("Problem with daatabase connection");
        }
        this.groups.setItems(FXCollections.observableArrayList(groupOption.values()));
    }

    /**
     *
     * @param pass
     */
    // Set new password
    public void initPassword(String pass)
    {
        password.setText(pass);
    }

    /**
     *
     * @param event
     * @throws Exception
     */
    //Open generateNewPasswordWindow
    @FXML
    private void generatePassword(ActionEvent event) throws Exception
    {
        Stage stage1 = (Stage) this.username.getScene().getWindow();
        stage1.close();
        Stage userStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = (Pane) loader.load(getClass().getResource("/GenerateNewpassword/newPassword.fxml"));
        Scene scene= new Scene(root);
        userStage.initStyle(StageStyle.UNDECORATED);
        userStage.setScene(scene);
        userStage.setResizable(false);

        userStage.show();
    }

    /**
     *
     * @param event
     */
//Clear all the text fields
    @FXML
    private void clearFields(ActionEvent event)
    {
        username.setText("");
        urladdress.setText("");
        password.setText("");


    }

    /**
     *
     * @param event
     * @throws SQLException
     * @throws Exception
     */
    @FXML
    private void addNewEntry(ActionEvent event) throws SQLException,Exception {
        String SqlInsert = "INSERT INTO USERDATA(username,address,password) VALUES(?,?,?)";

        try {
            Connection connection = dbConnection.getCOnnection();
            PreparedStatement stmp = connection.prepareStatement(SqlInsert);

            stmp.setString(1, this.username.getText());
            // stmp.setString(2,this.groups.getEditor().getText());
            stmp.setString(2, this.urladdress.getText());
            stmp.setString(3, this.password.getText());

            //ResultSet rs = stmp.executeQuery();
          stmp.execute();
            connection.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("New entry added successfuly");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.getDialogPane().setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))));
            alert.showAndWait();
            Stage stage1 = (Stage) this.username.getScene().getWindow();
            stage1.close();








        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    private void Exit(ActionEvent event)
    {

       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to close?");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getDialogPane().setBackground((new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))));
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
