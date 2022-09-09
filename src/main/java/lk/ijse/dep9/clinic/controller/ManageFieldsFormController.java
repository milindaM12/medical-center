package lk.ijse.dep9.clinic.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lk.ijse.dep9.clinic.entity.Field;

import java.sql.*;

public class ManageFieldsFormController {
    public Button btnSave;
    public Button btnNew;
    public Button btnDelete;
    public ListView<Field> lstField;
    public TextField txtField;
    //public ListView<Field> lstField;

    public void initialize() throws SQLException {
        Platform.runLater(txtField::requestFocus);
        loadAllFieldsFromDB();
    }

    private void loadAllFieldsFromDB() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_clinic", "root", "Padme@1995")){
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Field");
            lstField.getItems().clear();
            while (rst.next()){
                int id = rst.getInt("id");
                String description = rst.getString("description");
                //lstFields.getItems().add(description);
                Field field = new Field(id, description);
                lstField.getItems().add(field);
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, "Failed to load fields").show();
            e.printStackTrace();
        }
    }
    public void btnSaveOnAction(ActionEvent actionEvent) {
        String field = txtField.getText();

        //Data Validation
        if(field.isBlank()){
            new Alert(Alert.AlertType.ERROR, "Field description can't be empty").show();
            txtField.requestFocus();
            txtField.selectAll();
            return;
        }else if(!field.matches("[A-Za-z]+")){
            new Alert(Alert.AlertType.ERROR,"Invalid field description").show();
            txtField.requestFocus();
            txtField.selectAll();
            return;
        }

        //Business validation
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_clinic", "root", "Padme@1995")){
            PreparedStatement stm1 = connection.
                    prepareStatement("SELECT  * FROM Field WHERE description=?");
            stm1.setString(1,field);
            ResultSet rst = stm1.executeQuery();
            if (rst.next()){
                new Alert(Alert.AlertType.ERROR, "Field already exists").show();
                txtField.requestFocus();
                txtField.selectAll();
            }else{
                PreparedStatement stm2 = connection.prepareStatement("INSERT INTO Field (description) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                stm2.setString(1,field);
                stm2.executeUpdate();
                ResultSet generatedKeys = stm2.getGeneratedKeys();
                generatedKeys.next();
                int id = generatedKeys.getInt(1);

                lstField.getItems().add(new Field(id,field));

                //loadAllFieldsFromDB();
                txtField.clear();
                txtField.requestFocus();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the field").show();
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }
}
