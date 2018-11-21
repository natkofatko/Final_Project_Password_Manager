package WatchTawer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class watchtawerController implements Initializable{


@FXML private Button checkforsecurity, clear, close;
@FXML private TextField urladdress, security;
private  boolean result;

private Watchtower watch = new Watchtower();

    public void initialize(URL url, ResourceBundle rb)
    {
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) this.urladdress.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clearFields(ActionEvent event) {
        urladdress.setText("");
        security.setText("");
    }

    @FXML
    private void checkForSecurity(ActionEvent event) throws Exception
    {
      result  = watch.checkUnsecure(urladdress.getText());
      if(result==true)
      {
          security.setText("Website is secure");
      }
      else {
          security.setText("Website is not secure");
      }

    }




}
