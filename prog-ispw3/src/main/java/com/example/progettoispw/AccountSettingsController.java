package com.example.progettoispw;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class AccountSettingsController {
    @FXML
    private Button goBackButton;
    @FXML
    private Button changeUsernameButton;
    @FXML
    private Button changePassButton;
    @FXML
    private Button confirm1;
    @FXML
    private Button confirm2;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField emailchange;
    @FXML
    private TextField spchange;
    @FXML
    private Label error;

    private LogBean login;
    private AccountSettingsControllerA asca;
    private FileController fl;
    private BackControllerA bca;

    public AccountSettingsController(){
        fl=new FileController();
        asca=new AccountSettingsControllerA();
        bca=new BackControllerA();
    }

    public void initialize() throws IOException, ClassNotFoundException {
        login=fl.getLog();
        usernameField.setText(login.getUser());
        passwordField.setText(login.getPass());
        emailchange.setText(login.getEmail());
        spchange.setText(login.getSpec());
    }

    @FXML
    public void goBack() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Settings.fxml")));
        Stage window=(Stage) goBackButton.getScene().getWindow();
        window.setScene(new Scene(root, 850, 594));
    }

    @FXML
    public void changeU() throws IOException, ClassNotFoundException {
        if(!bca.getSpecialization().equalsIgnoreCase("Chef")) {
            usernameLabel.setOpacity(1);
            usernameField.setEditable(true);
            usernameField.setText("");
        }else{
            error.setText("Name not editable for chef");
        }
    }

    @FXML
    public void changePass(){
        passwordLabel.setOpacity(1);
        passwordField.setEditable(true);
        passwordField.setText("");
    }

    @FXML
    public void appearConfirmUButton(){
        confirm1.setDisable(false);
    }

    @FXML
    public void appearConfirmPassButton(){
        confirm2.setDisable(false);
    }

    @FXML
    public void setConfirm1() throws IOException, ClassNotFoundException {
        String username=usernameField.getText();
        asca.confirmUser(username);
        login=fl.getLog();
        usernameField.setText(login.getUser());
    }

    @FXML
    public void setConfirm2() throws IOException, ClassNotFoundException {
        String pass=passwordField.getText();
        if(asca.confirmPass(pass)==1){
            error.setText("Password non adeguata");
        }else {
            login =fl.getLog();
            passwordField.setText(login.getPass());
        }
    }

    @FXML
    public void logout() throws IOException, ClassNotFoundException {
        asca.deselect();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        Stage window = (Stage) goBackButton.getScene().getWindow();
        window.setScene(new Scene(root, 850, 594));
    }
}
