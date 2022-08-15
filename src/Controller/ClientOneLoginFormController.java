package Controller;

import com.sun.javafx.scene.control.skin.ComboBoxPopupControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientOneLoginFormController {

    public TextField txtUserName;
    public AnchorPane client1LoginFormContext;
    static String userName;

    public void loginOnAction(ActionEvent event) throws IOException {
        userName = txtUserName.getText();
        Stage stage = (Stage) client1LoginFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/ClientOneForm.fxml"))));
        stage.show();
    }

    public static String getName(){
        return userName;
    }
}
