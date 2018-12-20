package PasswordAnalysisF;
import javafx.fxml.Initializable;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.EnglishSequenceData;
import org.passay.IllegalRegexRule;
import org.passay.IllegalSequenceRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.RuleResultDetail;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


public class PasswordAnalysis implements Initializable {

    private String Password;
    private PasswordValidator validator;
    private RuleResult result;
    private PasswordScore score;
    private double scoreVar;
    private boolean isValid;
    private HashMap<Label, Boolean> labelValidityMap;
    private HashMap<String, Label> errorMap;
    private boolean IsScreenLoaded = true;

    @FXML
    private TextField PasswordInput;

    @FXML
    private ProgressBar Progress;
    @FXML
    private Button close;

    @FXML
    private Label NumofRepeatedChar, IllegalSequenceLabel,NumofLowerC,NumofSymbols,NumofUpperC,NumberofNums,NumberofChars,PWLength,PWPercentage;


    /**
     *
     * @param event
     */
    @FXML
    private void onChange(KeyEvent event) {
        isValid = isPasswordValid();
        setProgressbarColor();

    }


    /**
     * clese the window
     * @param event
     */
    @FXML
    private void GoBack(ActionEvent event)
    {
     Stage stage = (Stage)this.close.getScene().getWindow();
     stage.close();

    }

    /**
     * check if password is valid
     * @return
     */
    public boolean isPasswordValid() {
        // System.out.println(PasswordInput.getText());
        scoreVar = 0;
        PWPercentage.setText("0%");
        if (IsScreenLoaded)
            Password = PasswordInput.getText();
        boolean isPWValid = validatePassword();
        // System.out.println("is password valid : " + isValid);
        checkEachValidation();
        if (Password.length() != 0 && IsScreenLoaded) {
            setLabelColors();
            // System.out.println(score.getPasswordscore());
            calculateScore();
            setProgressbarColor();
        }
        return isPWValid;
    }

    /**
     * set the collor of a progress bar to red green or orange
     */
    private void setProgressbarColor() {
        if (scoreVar < 30)

            Progress.setStyle("-fx-accent: red; ");
            //Progress.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

        else if (scoreVar <= 60)
            Progress.setStyle("-fx-accent: orange; ");
            // Progress.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        else {
            Progress.setStyle("-fx-accent: lightgreen; ");
            //Progress.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        }
    }

    /**
     * calculate the % of a password
     */
    private void calculateScore() {

        if (Password.length() == 0)
            scoreVar = 0;
        if (Password.length() < 7)
            scoreVar = Password.length() * 2;
        else
            scoreVar = Password.length() * 3;

        for (Label label : labelValidityMap.keySet()) {
            if (labelValidityMap.get(label) == true) {

                if (scoreVar > 10)
                    scoreVar -= 10;

            } else {
                scoreVar += 8;
            }
        }
        if (scoreVar > 100)
            scoreVar = 100;
        score.setPasswordscore(scoreVar * 0.01);
        PWPercentage.setText(Double.toString(scoreVar) + "%");
    }

    /**
     * set the lable color for green or red depending on meeting criteria
     */
    private void setLabelColors() {
        // TODO Auto-generated method stub
        for (Label label : labelValidityMap.keySet()) {
            if (labelValidityMap.get(label) == true) {

                label.setBackground((new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY))));

            } else {
                label.setBackground((new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY))));
            }
        }

    }

    /**
     *
     *
     */
    private void checkEachValidation() {
        // TODO Auto-generated method stub
        initializeErrorMap();
        if (IsScreenLoaded) {
            initializeLabels();
            //initializeColorsofLabels();
            //initializeProgressBarColor();
        }
        ArrayList<String> listofErrors = new ArrayList<String>();
        for (RuleResultDetail msg : result.getDetails()) {
            // System.out.println("Error is " + msg.getErrorCode());
            Label errorLabel = errorMap.get(msg.getErrorCode());
            // System.out.println("Error label is " + errorLabel);
            if (errorLabel != null) {
                labelValidityMap.put(errorLabel, true);
            }
        }

    }


    /**
     *
     * @param password
     */
    public void setPasswordFieldText(String password) {
        PasswordInput.setText(password);
    }

    /**
     *
     * @param isScreenLoaded
     */
    public void setIsScreenLoaded(boolean isScreenLoaded) {
        IsScreenLoaded = isScreenLoaded;
    }


    /**
     *
     * @return
     */
    public RuleResult getResult() {
        return result;
    }


    /**
     *
     */
    private void initializeLabels() {
        // TODO Auto-generated method stub

        labelValidityMap.put(NumberofChars, false);
        labelValidityMap.put(NumberofNums, false);
        labelValidityMap.put(NumofLowerC, false);
        labelValidityMap.put(NumofUpperC, false);
        labelValidityMap.put(NumofRepeatedChar, false);
        labelValidityMap.put(PWLength, false);
        labelValidityMap.put(IllegalSequenceLabel, false);
        labelValidityMap.put(NumofSymbols, false);
    }

    /**
     *
     */
    private void initializeErrorMap() {
        errorMap.put("TOO_SHORT", PWLength);
        errorMap.put("INSUFFICIENT_UPPERCASE", NumofUpperC);
        errorMap.put("INSUFFICIENT_LOWERCASE", NumofLowerC);
        errorMap.put("INSUFFICIENT_DIGIT", NumberofNums);
        errorMap.put("INSUFFICIENT_SPECIAL", NumofSymbols);
        errorMap.put("ILLEGAL_MATCH", NumofRepeatedChar);
        errorMap.put("INSUFFICIENT_ALPHABETICAL", NumberofChars);
        errorMap.put("ILLEGAL_NUMERICAL_SEQUENCE", IllegalSequenceLabel);
        errorMap.put("ILLEGAL_ALPHABETICAL_SEQUENCE", IllegalSequenceLabel);
        errorMap.put("ILLEGAL_QWERTY_SEQUENCE", IllegalSequenceLabel);
    }

    /**
     *
     * @return
     */
    private boolean validatePassword() {
        // TODO Auto-generated method stub
        result = validator.validate(new PasswordData(new String(Password)));
        if (result.isValid())
            return true;
        return false;
    }

    /**
     *
     */
    private void addRules() {
        validator = new PasswordValidator(Arrays.asList(
                // length between 8 and 20 characters
                new LengthRule(8, 20),
                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 2),

                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 2),

                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 2),

                // at least one symbol (special character)
                new CharacterRule(EnglishCharacterData.Special, 2),

                new CharacterRule(EnglishCharacterData.Alphabetical, 2),

                new IllegalRegexRule("(\\w)\\1+"),

                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 3, true),

                new IllegalSequenceRule(EnglishSequenceData.Numerical, 3, true),

                new IllegalSequenceRule(EnglishSequenceData.USQwerty, 3, true)));

    }


    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        if (Password != null)
            PasswordInput.setText(Password);
        initializeScreen();
        addTextLimiter(PasswordInput, 14);
        score = new PasswordScore();
        score.setPasswordscore(0);
        score.numberProperty().addListener((v, oldValue, newValue) -> {
        });
        Progress.progressProperty().bind(score.numberProperty());
    }

    /**
     *
     */
    public void initializeScreen() {
        // TODO Auto-generated method stub
        errorMap = new HashMap<>();
        labelValidityMap = new HashMap<>();
        addRules();
    }

    /**
     * set text restriction
     * @param tf
     * @param maxLength
     */
    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener((ov, oldValue, newValue) -> {
            if (tf.getText().length() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
        });
    }


    /**
     *
     * @return
     */
    public String getPassword() {
        return Password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        Password = password;
    }

}