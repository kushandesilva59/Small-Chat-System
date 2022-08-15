package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientThreeLoginFormController {

    public AnchorPane clientThreeLoginForm;
    public TextField txtUsername;

    static String username;

    public void loginOnAction(ActionEvent event) throws IOException {
       if(txtUsername.getText()!=null){
           username = txtUsername.getText();
           Stage stage = (Stage) clientThreeLoginForm.getScene().getWindow();
           stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/ClientThreeForm.fxml"))));
           stage.show();
       }
    }

    public static String getUsername(){
        return username;
    }
}
