package Utility;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    /**
     *
     * @param BoxTitle
     * @param AlertMessage
     * @throws Exception
     */
        public static void displayAlertBox(String BoxTitle, String AlertMessage) throws Exception{
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(BoxTitle);
            window.setHeight(250);
            window.setWidth(250);

            Button bt = new Button("Close");
            bt.setOnAction(e -> {
                window.close();
                e.consume();

            });

            Label lb = new Label();
            lb.setText(AlertMessage);

            VBox layout = new VBox();
            layout.getChildren().addAll(lb,bt);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();

        }

    }

