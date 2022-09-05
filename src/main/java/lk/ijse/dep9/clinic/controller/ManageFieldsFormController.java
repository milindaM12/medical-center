package lk.ijse.dep9.clinic.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ManageFieldsFormController {
    public Button btnSave;
    public Button btnNew;
    public Button btnDelete;
    public ListView<String> lstField;
    public TextField txtField;

    public void initialize(){
        Platform.runLater(txtField::requestFocus);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }
}
