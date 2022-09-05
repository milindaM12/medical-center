package lk.ijse.dep9.clinic.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lk.ijse.dep9.clinic.security.SecurityContextHolder;

import java.io.IOException;

public class AdminDashboardController {
    public Button btnProfileMgt;
    public Button btnViewrecords;
    public Button btnSettings;
    public Button btnLogOut;

    public void initialize(){
        System.out.println(SecurityContextHolder.getPrinciple());
    }

    public void btnProfileMgtOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ProfileManagementForm.fxml"))));
        stage.setTitle("Open Source MEDICARE: Profile Management");
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
        Stage owner = (Stage) btnSettings.getScene().getWindow();
        owner.hide();
        stage.setOnCloseRequest(windowEvent -> owner.show());
    }

    public void btnViewrecordsOnAction(ActionEvent actionEvent) {
    }

    public void btnSettingsOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SettingsForm.fxml"))));
        stage.setTitle("Open Source MEDICARE: Settings");
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
        Stage owner = (Stage) btnSettings.getScene().getWindow();
        owner.hide();
        stage.setOnCloseRequest(windowEvent -> owner.show());
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"))));
        stage.setTitle("Open Medicare");
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
        btnLogOut.getScene().getWindow().hide();
    }
}
