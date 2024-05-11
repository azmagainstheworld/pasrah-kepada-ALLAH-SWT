package com.gotpb.tubespbokelompok7;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;

public class RegisterController {
    @FXML
    private TextField fieldUsername;
    @FXML
    private TextField fieldEmail;
    @FXML
    private TextField fieldPassword;

    // Informasi koneksi database
    private static final String URL = "jdbc:mysql://localhost:3306/gotpb";
    private static final String USER = "username_mysql";
    private static final String PASSWORD = "password_mysql";

    // Atribut akun
    private String username;
    private String email;
    private String password;

    // Konstruktor
    public RegisterController(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Method untuk mengecek apakah akun sudah terdaftar
    public boolean isAkunTerdaftar() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT COUNT(*) FROM nama_tabel WHERE username = ? OR email = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Jika count > 0, berarti akun sudah terdaftar
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method untuk menambahkan akun ke dalam database
    public void buatAkun() {
        if (isAkunTerdaftar()) {
            System.out.println("Akun dengan username atau email yang sama sudah terdaftar.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO nama_tabel (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.executeUpdate();
            System.out.println("Akun berhasil didaftarkan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Contoh penggunaan
        RegisterController akun = new RegisterController("user123", "user123@example.com", "password123");
        akun.buatAkun();
    }
}





