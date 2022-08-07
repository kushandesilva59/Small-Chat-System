package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class LoginFormController {

    public TextField txtUserName;
    String userName;

    public void loginOnAction(ActionEvent event) {
        userName = txtUserName.getText();
    }
}
