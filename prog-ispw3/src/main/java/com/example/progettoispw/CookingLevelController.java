package com.example.progettoispw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.nio.file.ClosedFileSystemException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CookingLevelController {

    @FXML private Button confirmButton;
    @FXML private Button goBackButton;
    @FXML private Label selectedButtonLabel;
    @FXML private RadioButton r1;
    @FXML private RadioButton r2;
    @FXML private RadioButton r3;

    private CookingLevelControllerA clca;
    private FileController fl;
    private BackControllerA bca;

    public CookingLevelController(){
        fl=new FileController();
        clca=new CookingLevelControllerA();
        bca=new BackControllerA();
    }

    public void initialize() throws IOException, ClassNotFoundException {
        String cl;
        LogBean login=fl.getLog();
        if(bca.getSpecialization().equalsIgnoreCase("User") || bca.getSpecialization().equalsIgnoreCase("Premium")) {
            if (login != null && login.getCL() != null) {
                cl = login.getCL();
            }else{
                cl=clca.getCL(login.getUser());
            }
            if(cl.equalsIgnoreCase("beginner")) {
                r1.setSelected(true);
            }else if(cl.equalsIgnoreCase("intermediate")) {
                r2.setSelected(true);
            }else if(cl.equalsIgnoreCase("advanced")){
                r3.setSelected(true);
            }
        }else{
            clca.setCL("Advanced");
            r1.setSelected(false);
            r2.setSelected(false);
            r3.setSelected(true);
            r1.setDisable(true);
            r2.setDisable(true);
            r3.setDisable(true);
            confirmButton.setDisable(true);
        }
    }

    @FXML
    public void goBack() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Settings.fxml")));
        Stage window=(Stage) goBackButton.getScene().getWindow();
        window.setScene(new Scene(root, 850, 594));
    }

    @FXML
    public void handleCookingLevel(ActionEvent actionEvent){
        Logger logger=Logger.getLogger(AddRecipeController.class.getName());
        selectedButtonLabel.setText(((RadioButton)actionEvent.getSource()).getText());
        logger.log(Level.INFO, selectedButtonLabel.getText() + " selected");
    }

    @FXML
    public void confirmCL() throws IOException, ClassNotFoundException {
        String level = selectedButtonLabel.getText();
        //setta il cooking level dell'utente
        clca.setCL(level);
    }

}
