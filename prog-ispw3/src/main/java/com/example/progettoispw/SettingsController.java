package com.example.progettoispw;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SettingsController {

    @FXML
    private Button homeButton;

    @FXML
    private Button cookingLevelButton;


    @FXML
    private Button accountButton;

    @FXML
    private Button tutorialButton;

    private BackControllerA bca;

    public SettingsController(){
        bca=new BackControllerA();
    }

    @FXML
    public void gotoCookingLevel() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CookingLevel.fxml")));
        Stage window=(Stage) cookingLevelButton.getScene().getWindow();
        window.setScene(new Scene(root, 850, 594));
    }

    @FXML
    public void gotoAccount() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AccountSettings.fxml")));
        Stage window=(Stage) accountButton.getScene().getWindow();
        window.setScene(new Scene(root, 850, 594));
    }

    @FXML
    public void gotoTutorial() throws IOException, ClassNotFoundException {
        if(bca.getSpecialization().equalsIgnoreCase("User") || bca.getSpecialization().equalsIgnoreCase("Premium")) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TutorialSettings.fxml")));
            Stage window = (Stage) homeButton.getScene().getWindow();
            window.setScene(GeneralScene.getHome(root));
        }else{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TutorialChef.fxml")));
            Stage window = (Stage) homeButton.getScene().getWindow();
            window.setScene(new Scene(root, 850, 594));
        }
    }

    @FXML
    public void gotoHome() throws IOException, ClassNotFoundException {
        if(bca.getSpecialization().equalsIgnoreCase("User") || bca.getSpecialization().equalsIgnoreCase("Premium")) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
            Stage window = (Stage) homeButton.getScene().getWindow();
            window.setScene(GeneralScene.getHome(root));
        }else{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeChef.fxml")));
            Stage window = (Stage) homeButton.getScene().getWindow();
            window.setScene(new Scene(root, 850, 594));
        }
    }
}
