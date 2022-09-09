package lk.ijse.dep9.clinic.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lk.ijse.dep9.clinic.security.UserRole;

public class ProfileFormController {
    public ComboBox<String> cmbUserRoles;
    public TextField txtFullName;
    public TextField txtUserName;
    public ComboBox<String> cmbGender;
    public ComboBox<String> cmbField;
    public TextField txtNic;
    public TextField txtContact;
    public TextField txtAddress;
    public TextField txtPassword;
    public Label lblSubtitle;
    public Button btnSave;
    public VBox container;
    public HBox hBoxfield;
    public Label lblTitle;

    public void initialize(){
        UserRole[] roles = UserRole.values();
        for (UserRole role : roles) {
            cmbUserRoles.getItems().add(role.toString());
        }

        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("Female");
        container.getChildren().remove(hBoxfield);
        Platform.runLater(cmbUserRoles::requestFocus);
    }

    public void initData(boolean newProfile){
        if (!newProfile){
            lblTitle.setText("Edit Profile");
            lblSubtitle.setText("Update existing profile details");
        }
    }

    public void cmbUserRoleOnAction(ActionEvent actionEvent) {
        if (cmbUserRoles.getSelectionModel().getSelectedItem().equals("Doctor")){
            container.getChildren().add(3, hBoxfield);
        }else{
            container.getChildren().remove(hBoxfield);
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

    }
}
