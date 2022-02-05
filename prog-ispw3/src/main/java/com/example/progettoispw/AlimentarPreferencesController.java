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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlimentarPreferencesController {
    @FXML private Button goBackButton;
    @FXML private Label selectedButtonLabel1;
    @FXML private Button confirmButton;
    @FXML private RadioButton rb1;
    @FXML private RadioButton rb2;
    @FXML private RadioButton rb3;
    @FXML private RadioButton rb4;
    @FXML private RadioButton rb5;
    @FXML private RadioButton rb6;
    @FXML private RadioButton fd1;
    @FXML private RadioButton fd2;
    @FXML private RadioButton fd3;
    @FXML private Button clear;
    @FXML private Label succeed;

    private String no="No allergies";
    private final String[] allergies ={"Dried fruit","Fish","Eggs","Milk","Meat",no};
    private AlimentarPreferencesControllerA apca;
    private final BackControllerA bca;
    private static Logger logger=Logger.getLogger(AlimentarPreferencesController.class.getName());

    public AlimentarPreferencesController() throws IOException, ClassNotFoundException {
        apca = new AlimentarPreferencesControllerA();
        bca = new BackControllerA();
    }

    public void initialize() throws IOException, ClassNotFoundException {
        String pref=apca.getPref();
        if(pref.equals("Vegetarian")){
            fd1.setSelected(true);
        }else if(pref.equals("Vegan")){
            fd2.setSelected(true);
        }else if(pref.equals("None")){
            fd3.setSelected(true);
        }

        List<String> all=apca.getAll();
        for(int i=0; i<all.size(); i++){
            switch (all.get(i)) {
                case "Dried fruit" -> rb1.setSelected(true);
                case "Fish" -> rb2.setSelected(true);
                case "Eggs" -> rb3.setSelected(true);
                case "Milk" -> rb4.setSelected(true);
                case "Meat" -> rb5.setSelected(true);
                case "No Allergies" -> rb6.setSelected(true);
                default -> selectedButtonLabel1.getText();
            }
        }
    }


    @FXML
    public void goBack() throws IOException, ClassNotFoundException {
        Stage window = (Stage) goBackButton.getScene().getWindow();
        if (bca.getSpecialization().equalsIgnoreCase("User") || bca.getSpecialization().equalsIgnoreCase("Premium")) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
            window.setScene(GeneralScene.getHome(root));
        }else{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeChef.fxml")));
            window.setScene(new Scene(root, 850, 594));
        }
    }


    public void handleAlimentarPref(ActionEvent actionEvent){
        RadioButton rb = (RadioButton) actionEvent.getSource();
        if(rb.isSelected()){
            selectedButtonLabel1.setText(((RadioButton)actionEvent.getSource()).getText());
            logger.log(Level.INFO, selectedButtonLabel1.getText()+" selected");
        }
        else {
            logger.log(Level.INFO, "Deselected");
        }
    }

    @FXML
    public void clear(){
        apca.clear();
        rb1.setSelected(false);
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
        rb5.setSelected(false);
        rb6.setSelected(false);
    }

    @FXML
    public void confirm() throws IOException, ClassNotFoundException {
        final RadioButton[] allRB = {rb1,rb2,rb3,rb4,rb5,rb6};
        String preferences = selectedButtonLabel1.getText();
        ArrayList<String> listOfAllergies = new ArrayList<>() ;
        for(int i=0;i<6;i++){
            if(allRB[i].isSelected()){
                listOfAllergies.add(allergies[i]);
                if(allRB[i].getText().equals(no)){
                    listOfAllergies.clear();
                    listOfAllergies.add(no);
                }
            }
        }
        apca.setPref(selectedButtonLabel1.getText(), listOfAllergies);
        succeed.setOpacity(1);
        logger.log(Level.INFO, preferences, listOfAllergies);
    }
}
