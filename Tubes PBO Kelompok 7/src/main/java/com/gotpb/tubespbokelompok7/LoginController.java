package com.gotpb.tubespbokelompok7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

public class LoginController {



    @FXML private TextField fieldUsername;
    @FXML private TextField fieldEmail;
    @FXML private TextField fieldPassword;
    @FXML private Label LoginMessage;
    //@FXML private Button registerbutton;?
    @FXML private Button loginbutton;

    public int validasilogin() {
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.getConnection();

        String verify = "SELECT count(1) FROM acc1 WHERE email = '" + fieldUsername.getText() +"' AND password = '" + fieldPassword.getText() + "'";

        try {
            Statement statement = connectdb.createStatement();
            ResultSet queryres = statement.executeQuery(verify);

            while (queryres.next()) {
                if (queryres.getInt(1) == 1 ) {
                    return 1;


                }

            }
        } catch (Exception ep) {
            ep.printStackTrace();

        }

        return 0;

    }

    @FXML
    private void toRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("tampilan register.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /*@FXML
    public void switcher(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("tampilan dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
        */




    public void masukAkun(ActionEvent event) {

        if (fieldUsername.getText().isBlank() == false && fieldPassword.getText().isBlank() == false) {
            //LoginMessage.setText("udh.");

            int result = validasilogin();

            if (result == 1) {
                try {
                    LoginMessage.setText("udh.");
                    Parent root = FXMLLoader.load(getClass().getResource("tampilan register.fxml"));
                    Stage stage = (Stage) fieldUsername.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException ex) {
                    System.err.println("Error loading FXML file: " + ex.getMessage());
                }

            } else {
                LoginMessage.setText("Identitas tidak terdaftar.");

            }


        } else {
            LoginMessage.setText("Tolong ae diisi dlu.");
        }

    }






}