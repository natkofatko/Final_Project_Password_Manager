package GenerateNewpassword;


import NewEntry.NewEntryController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;




public class newPasswordController implements Initializable {



        private boolean hasDigits;
        private boolean hasLowercase;
        private boolean hasUppercase;
        private boolean hasSpecial;

        List<CharacterRule> rules;

        @FXML
        private JFXTextField newpassword, PasswordLength;

        @FXML
        private JFXCheckBox digits, specialcharacters, uppercase, lowercase;


        @FXML
        private Button generatepassword, ok, cancel;



    @FXML
    private void closeWindow(ActionEvent event)
    {
        Stage stage = (Stage) this.PasswordLength.getScene().getWindow();
        stage.close();

    }
    //This method copy newly generated password from GenerateNewPassword form and paste it to the addNewEntryForm
    @FXML
    private void ClickOK(ActionEvent event) throws Exception
    {
        String newgeneratedPassword = newpassword.getText();
        Stage stage = (Stage) this.PasswordLength.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/NewEntry/NewEntryFXML.fxml"));
        Parent root = (Parent) loader.load();
        NewEntryController controller22 = loader.getController();
        controller22.initPassword(newgeneratedPassword);
        Stage user = new Stage();
        user.setScene(new Scene(root));
        user.show();



    }

    @FXML
    private void clearFields(ActionEvent event)
    {

                uppercase.setSelected(false);
                specialcharacters.setSelected(false);
                digits.setSelected(false);
                lowercase.setSelected(false);

                PasswordLength.setText("");
                newpassword.setText("");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }



        @FXML
        private void onChange(ActionEvent event) throws Exception{

            String length = PasswordLength.getText();
            if(length.equals("0")){
                PasswordLength.setText("text-field-error");
            }
            else if(Integer.parseInt(length) > 14 || Integer.parseInt(length) < 4  ){
                PasswordLength.setText("text-field-error");
               System.out.print("Invalid Length");
                // new AlertBox().display("Invalid Length ", "Length should be less than 14!!");
            }
            else{

                boolean choiceValid = getChoices();

                if(choiceValid){
                    addRules();

                    String GPassword = generatePassword(length);
                    newpassword.setText(GPassword);
                }
            }
        }




    private String generatePassword(String length) {

            PasswordGenerator generator = new PasswordGenerator();
            String password = generator.generatePassword(Integer.parseInt(length), rules);
            return password;
        }

        private void addRules() {

            rules = new LinkedList<CharacterRule>();

            if(hasDigits)
                rules.add(new CharacterRule(EnglishCharacterData.Digit, 1 ));
            if(hasLowercase)
                rules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1 ));
            if(hasUppercase)
                rules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1));
            if(hasSpecial)
                rules.add(new CharacterRule(EnglishCharacterData.Special, 1));

        }

        private boolean getChoices() throws Exception{
            // TODO Auto-generated method stub

            if(!digits.isSelected() && !specialcharacters.isSelected() && !uppercase.isSelected() && !lowercase.isSelected() ){
                PasswordLength.setText("Select at least one check box");


                // new AlertBox().display("no choice Selected!"," you must select atleast one choice!!!!!!!!!!");
//                digits.getStyleClass().add("check-box-error");
//                uppercase.getStyleClass().add("check-box-error");
//                specialcharacter.getStyleClass().add("check-box-error");
//                lowercase.getStyleClass().add("check-box-error");

                return false;
            }else{
                if(digits.isSelected())
                    hasDigits = true;
                else
                    hasDigits = false;

                if(specialcharacters.isSelected())
                    hasSpecial = true;
                else
                    hasSpecial = false;

                if(uppercase.isSelected())
                    hasUppercase = true;
                else
                    hasUppercase = false;

                if(lowercase.isSelected())
                    hasLowercase = true;
                else
                    hasLowercase = false;

                return true;
            }



        }



    }



