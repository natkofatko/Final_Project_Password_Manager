package UserWindow;

import NewEntry.NewEntryController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample.dbStatus;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

import database.dbConnection;


public class LoginController implements Initializable {
    @FXML
    private TextField search, displaypassword;
    @FXML
    private JFXButton addnewentry, deleteentry, editentry, generatepassword, scorethestrength, refresh;
    @FXML
    private JFXButton logout, exit, analyzepassword;
    @FXML
    TableView<UserData> usertable;
    @FXML
    TableColumn<UserData, String> usernamecollumn;
    @FXML
    TableColumn<UserData, String> addresscollumn;
    @FXML
    TableColumn<UserData, String> groupcollumn;
    @FXML
    TableColumn<UserData, String> passwordcollumn;
    private dbConnection db;
    private ObservableList<UserData> data;
    private UserData user;


    public void initialize(URL url, ResourceBundle rs) {

    }

    /*
    Edid entry by clicking twice on selected field
     */
    @FXML
    private void editEntry(MouseEvent event) {

        usertable.setEditable(true);
        usernamecollumn.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordcollumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addresscollumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }


    /*
    this method will open new Entry window, load selected entry and allow you to make some changes to it
     */
    @FXML
    private void editEntryAllFields(ActionEvent event) {

    }

    /*
    This method removes selected entry from the UI just temporary
     */
    @FXML
    private void deleteEntry(ActionEvent event) {
        //change selectionModel to allow multiply selection
        usertable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<UserData> selectedRows = usertable.getSelectionModel().getSelectedItems();
        ArrayList<UserData> rows = new ArrayList<>(selectedRows);
        rows.forEach(row -> usertable.getItems().remove(row));


//
    }


    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) this.search.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void generateNewPassword(ActionEvent event) {
        String strongPassword = "";
        int passwordLength = (int) (Math.random() * 5 + 12); //returns a minimum of 12

        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String integers = "0123456789";
        String specialCharacters = "!" + "\"" + "#$%&" + "\'" + "()*,+-./:\\;<=>?@[\\]^_`{|}~";

        for (int i = 0; i < passwordLength; i++) {
            int a = (int) (Math.random() * 3); //generates a number between 0 and 2
            if (a == 0) {
                int index = (int) (Math.random() * 26);
                int upperOrLower = (int) (Math.random() * 2);
                if (upperOrLower == 0) {
                    strongPassword += lowerCase.charAt(index);
                } else {
                    strongPassword += upperCase.charAt(index);
                }
            } else if (a == 1) {
                int index2 = (int) (Math.random() * integers.length());
                strongPassword += integers.charAt(index2);
            } else {
                int index3 = (int) (Math.random() * specialCharacters.length());
                strongPassword += specialCharacters.charAt(index3);
            }

        }
        displaypassword.setText(strongPassword);


    }


    @FXML
    private void addNewEntry(ActionEvent event) throws Exception {

        Stage userStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = (Pane) loader.load(getClass().getResource("/NewEntry/NewEntryFXML.fxml"));
        Scene scene = new Scene(root);
        userStage.initStyle(StageStyle.UNDECORATED);
        userStage.setScene(scene);
        userStage.setResizable(false);

        userStage.show();

    }

    /*
    Load all the information from database and display them in table
     */
    @FXML
    private void loadData(ActionEvent event) {
        try {
            Connection conn = dbConnection.getCOnnection();
            this.data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT* FROM USERDATA");
            while (rs.next()) {
                this.data.add(new UserData(rs.getString(1), rs.getString(3), rs.getString(4)));

            }
            this.usernamecollumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("username"));
            this.passwordcollumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("password"));
            this.addresscollumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("address"));

            this.usertable.setItems(null);
            this.usertable.setItems(this.data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*

     */
    @FXML
    private void logout(ActionEvent event) throws Exception {
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
            Scene scene = new Scene(root);
            userStage.initStyle(StageStyle.UNDECORATED);
            userStage.setScene(scene);
            userStage.setResizable(false);

            userStage.show();


        }
        if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }
@FXML
    public ObservableList<UserData> getEntries2 (ActionEvent event) throws Exception{
        // TODO Auto-generated method stub

        try{
            //System.out.println(account.getEntries());
            ObservableList<UserData> userEntries = FXCollections.observableArrayList(getEntries());
            return userEntries;
        }
        catch(Exception e){

        }
        return null;
    }

    public ArrayList<UserData> getEntries() throws Exception {
        Connection conn = dbConnection.getCOnnection();
        ArrayList<UserData> entryList = new ArrayList<>();
        UserData selectedUser = usertable.getSelectionModel().getSelectedItem();
        String sql = "Select * from USERDATA where username =?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, selectedUser.getPassword());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("password"));
        }
    return entryList;

    }



    @FXML
    private void onAnalyzeBTClick(MouseEvent event) throws Exception {
        System.out.println("lol");
        ObservableList<UserData> selectedItems = usertable.getSelectionModel().getSelectedItems();
        System.out.println(selectedItems.toString());

    }

    /*
    This method will delete all user information from  the database
     */
    public void deleteEverything(ActionEvent event) throws Exception {
        Connection conn = dbConnection.getCOnnection();
        UserData selectedUser = usertable.getSelectionModel().getSelectedItem();
        //ResultSet rs = conn.createStatement().executeQuery("SELECT* FROM USERDATA");
        if ( selectedUser!= null) {

            PreparedStatement statement = conn.prepareStatement("DELETE FROM USERDATA WHERE username = ?");
            statement.setString(1, selectedUser.getUsername());
            statement.executeUpdate();
            // Update the table
            loadData(event);
        }
    }
@FXML
    public void ScoreTheStrength(ActionEvent event) throws Exception {
    ResultSet rs;

    Connection conn = dbConnection.getCOnnection();
    UserData selecteduser = usertable.getSelectionModel().getSelectedItem();
    // UserData selectedPassword = usertable.getSelectionModel().getSelectedItem();
    if (selecteduser != null) {
        String sql = ("SELECT * FROM USERDATA");

        PreparedStatement st = conn.prepareStatement(sql);
st.setString(1,selecteduser.getUsername());

        rs = st.executeQuery();

       // while (rs.next()) {
            // String name = rs.getString("username");
        String ss = rs.getString(1);
            ArrayList<UserData> list = new ArrayList<>();
            //list.add(ss);

        conn.close();
        // ObservableList<String> ids = FXCollections.observableArrayList(rs.getString("password"));
        //System.out.println(ids);
    }
}

/*;
Search for given username or password
 */
    @FXML
    private void SearchTable(MouseEvent event)
    {
        this.usernamecollumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("username"));
        usernamecollumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        passwordcollumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        addresscollumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<UserData> filteredData = new FilteredList<>(data, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (person.getPassword().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (person.getAddress().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<UserData> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(usertable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        usertable.setItems(sortedData);
        //sortedData.get(2);
    }

}
