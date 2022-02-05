package com.example.progettoispw;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class TutorialController {

    @FXML
    private Button goBackButton;
    @FXML
    private Button nextImage;
    @FXML
    private Button backImage;
    @FXML
    private ImageView view;
    @FXML
    private HBox h1;
    @FXML
    private HBox h2;
    @FXML
    private HBox h3;
    @FXML
    private HBox h4;

    @FXML
    private Text t1;
    @FXML
    private Text t2;
    @FXML
    private Text t3;
    @FXML
    private Text t4;



    private File file;
    private final ArrayList<HBox> hb;
    private final ArrayList<Text> text;
    private final BackControllerA bca;
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4=null;

    public TutorialController() throws IOException, ClassNotFoundException {
        bca = new BackControllerA();

        if (bca.getSpecialization().equalsIgnoreCase("User") || bca.getSpecialization().equalsIgnoreCase("Premium")) {
            file = new File("images/ImmagineHome.png");
            image1 = new Image(file.toURI().toString());
            file = new File("images/ImmagineAP.png");
            image2 = new Image(file.toURI().toString());
            file = new File("images/ImmaginePlan.png");
            image3 = new Image(file.toURI().toString());
            file = new File("images/ImmagineSearch.png");
            image4 = new Image(file.toURI().toString());
        }else{
            file = new File("images/ImmagineHomeChef.png");
            image1 = new Image(file.toURI().toString());
            file = new File("images/AggiungiRicetta.png");
            image2 = new Image(file.toURI().toString());
            file = new File("images/ImmaginePlan.png");
            image3 = new Image(file.toURI().toString());
        }

        ArrayList<Image> allImages = new ArrayList<>();
        allImages.add(image1);
        allImages.add(image2);
        allImages.add(image3);
        allImages.add(image4);

        text = new ArrayList<>();
        hb = new ArrayList<>();
        view = new ImageView();
    }

    public void initialize(){
        h1.setOpacity(1);
        h1.setDisable(false);
        h2.setOpacity(0);
        h2.setDisable(true);
        h3.setOpacity(0);
        h3.setDisable(true);

        hb.add(h1);
        hb.add(h2);
        hb.add(h3);

        text.add(t1);
        text.add(t2);
        text.add(t3);

        if(image4!=null) {
            h4.setOpacity(0);
            h4.setDisable(true);
            hb.add(h4);
            text.add(t4);
        }
    }

    @FXML
    public void goBack() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Settings.fxml")));
        Stage window=(Stage) goBackButton.getScene().getWindow();
        window.setScene(new Scene(root, 850, 594));
    }

    private Integer i = 0;
    private Integer prevImageNumber = -1;

    @FXML
    public int changeNextImage() {
        if(i==3 && image4!=null || i==2 && image4==null){
            nextImage.setDisable(true);
            return 0;
        }

        backImage.setDisable(false);

        if(image4!=null) {
            i = (i + 1) % 4;
        }else{
            i = (i + 1) % 3;
        }

        text.get(i).setVisible(true);
        text.get(i-1).setVisible(false);

        hb.get(i).setOpacity(1);
        hb.get(i).setDisable(false);
        hb.get(i-1).setOpacity(0);
        hb.get(i-1).setDisable(true);

        if(i>0){
            prevImageNumber = i-1;
        }

        return 1;
    }

    @FXML
    public int changePreviousImage(){

        if(prevImageNumber == -1){
            backImage.setDisable(true);
            return 0;
        }

        nextImage.setDisable(false);

        text.get(prevImageNumber).setVisible(true);
        text.get(prevImageNumber+1).setVisible(false);
        hb.get(prevImageNumber).setOpacity(1);
        hb.get(prevImageNumber).setDisable(false);
        hb.get(prevImageNumber+1).setOpacity(0);
        hb.get(prevImageNumber+1).setDisable(true);

        if(image4!=null) {
            i = (i - 1) % 4;
        }else{
            i = (i - 1) % 3;
        }

        prevImageNumber = prevImageNumber - 1;

        return 1;
    }

}
