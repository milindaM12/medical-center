package lk.ijse.dep9.clinic.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.dep9.clinic.security.SecurityContextHolder;
import lk.ijse.dep9.clinic.security.User;
import lk.ijse.dep9.clinic.security.UserRole;

import java.io.IOException;
import java.sql.*;

public class LoginFormController {
    public TextField txtUsername;
    public PasswordField txtPassword;
    public Button btnLogin;

    public void initialize(){
        btnLogin.setDefaultButton(true);
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws ClassNotFoundException, IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        /*validation logic*/
        if (username.isBlank()){
            new Alert(Alert.AlertType.ERROR, "Username can't be empty").show();
            txtUsername.requestFocus();
            txtUsername.selectAll();
            return;
        }else if (password.isBlank()){
            new Alert(Alert.AlertType.ERROR, "Password can't be empty").show();
            txtPassword.requestFocus();
            txtPassword.selectAll();
            return;
        }else if (!username.matches("^[A-Za-z0-9]+$")){
            new Alert(Alert.AlertType.ERROR,"Invalid login credentials").show();
            txtUsername.requestFocus();
            txtUsername.selectAll();
            return;
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_clinic", "root", "Padme@1995")){
            String sql = "SELECT role FROM User WHERE username=? AND password=?"; /*positional parameters - SQL */
            /*sql = String.format(sql, username, password);

            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery(sql);*/

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,username);
            stm.setString(2,password);
            ResultSet rst = stm.executeQuery();


            if (rst.next()){
                String role = rst.getString("role");
                SecurityContextHolder.setPrincipal(new User(username, UserRole.valueOf(role)));
                Scene scene = null;
                switch (role){
                    case "Admin":
                        scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/AdminDashboard.fxml")));
                        break;
                    case "Doctor":
                        scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/DoctorDashboardForm.fxml")));
                        break;
                    default:
                        scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/ReceiptionistDashboardForm.fxml")));
                }
                Stage stage = new Stage();
                stage.setTitle("Medical Center");
                stage.setScene(scene);
                stage.show();
                stage.centerOnScreen();

                btnLogin.getScene().getWindow().hide();

            }else{
                new Alert(Alert.AlertType.ERROR,"Invalid login credentials").show();
                txtUsername.requestFocus();
                txtUsername.selectAll();
            }
        }catch (SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to connect with the database, try again").show();
        }
    }
}
