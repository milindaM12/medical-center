package lk.ijse.dep9.clinic.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SettingsFormController {
    public Button btnHospitalfee;
    public Button btnDiscount;
    public Button btnManageFields;
    public Button btnPassword;
    public Button btnAbout;
    public Button btnSave;
    public Button btnNew;
    public Button btnDelete;
    public AnchorPane pneContainer;

    public void initialize(){

    }

    public void btnHospitalfeeOnAction(ActionEvent actionEvent) {
    }

    public void btnDiscountOnAction(ActionEvent actionEvent) {
    }

    public void btnManageFieldsOnAction(ActionEvent actionEvent) throws IOException {
        pneContainer.getChildren().clear();
        AnchorPane manageFieldsForm = FXMLLoader.load(getClass().getResource("/view/ManageFieldsForm.fxml"));
        pneContainer.getChildren().add(manageFieldsForm);
    }

    public void btnPassword(ActionEvent actionEvent) {
    }

    public void btnAboutOnAction(ActionEvent actionEvent) {

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }
}
