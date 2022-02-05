package com.example.progettoispw;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomePageController {
    @FXML
    private AnchorPane paneHome;
    @FXML
    private AnchorPane paneSearchRecipe;
    @FXML
    private AnchorPane paneWeeklyPlan;
    @FXML
    private AnchorPane paneSaved;
    @FXML
    private AnchorPane panePremiumUser;
    @FXML
    private AnchorPane paneCalc;

    @FXML
    private Parent general;

    @FXML
    private VBox recipeBox;
    @FXML
    private VBox savedBox;
    @FXML
    private Button homeButton;
    @FXML
    private Button planButton;
    @FXML
    private Button settingButton;
    @FXML
    private Button savedButton;
    @FXML
    private Button recipeButton;
    @FXML
    private Button recipesHistoryButton;
    @FXML
    private Button shoppingListButton;
    @FXML
    private Button alimentarPreferencesButton;
    @FXML
    private Button confirmSearchButton;
    @FXML
    private Button premiumButton;
    @FXML
    private Button tutorialButton;
    @FXML
    private Label nameUser;
    @FXML
    private CheckBox recipe;
    @FXML
    private CheckBox time;
    @FXML
    private CheckBox ingredient;
    @FXML
    private CheckBox type;
    @FXML
    private TextField searchField;
    @FXML
    private Label labelerr;
    @FXML
    private Label errwp;

    @FXML private Button monBreak;
    @FXML private Button tueBreak;
    @FXML private Button wedBreak;
    @FXML private Button thuBreak;
    @FXML private Button friBreak;
    @FXML private Button satBreak;
    @FXML private Button sunBreak;
    @FXML private Button monLunch;
    @FXML private Button tueLunch;
    @FXML private Button wedLunch;
    @FXML private Button thuLunch;
    @FXML private Button friLunch;
    @FXML private Button satLunch;
    @FXML private Button sunLunch;
    @FXML private Button monDinner;
    @FXML private Button tueDinner;
    @FXML private Button wedDinner;
    @FXML private Button thuDinner;
    @FXML private Button friDinner;
    @FXML private Button satDinner;
    @FXML private Button sunDinner;

    private final SavedControllerA sca;
    private final SearchRecipeA sra;
    private final ArrayList<Button> buttons=new ArrayList<>();
    private ArrayList<AnchorPane> aps;
    private LogBean login;
    private WeeklyPlanControllerA wpca;
    private FileController fl;
    private List<RecipeBean> recipes;
    private String template="Recipetemplate.fxml";
    private AlimentarPreferencesControllerA apca;
    private CookingLevelControllerA clca;

    public HomePageController() throws IOException, ClassNotFoundException {
        sra=new SearchRecipeA();
        sca=new SavedControllerA();
        aps=new ArrayList<>();
        recipes=new ArrayList<>();
        fl=new FileController();
        login=fl.getLog();
        apca=new AlimentarPreferencesControllerA();
        clca=new CookingLevelControllerA();
    }

    public void initialize() throws IOException, ClassNotFoundException {
        assert login != null;
        nameUser.setText("Hi "+login.getUser());
        apca.getPref();
        apca.getAll();
        clca.getCL(login.getUser());
        wpca=new WeeklyPlanControllerA();
    }

    @FXML
    public void showRecipesHistory() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("History.fxml")));
        Stage window=(Stage) recipesHistoryButton.getScene().getWindow();
        window.setScene(new Scene(root, 850, 594));
    }
    
    @FXML
    public void showAlimentarPreferences() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AlimentarPreferences.fxml")));
        Stage window=(Stage) alimentarPreferencesButton.getScene().getWindow();
        window.setScene(new Scene(root, 850, 594));
    }

    @FXML
    public void showShoppingList() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ShoppingList.fxml")));
        Stage window=(Stage) shoppingListButton.getScene().getWindow();
        window.setScene(new Scene(root, 850, 594));
    }

    @FXML
    public void gotoSettings() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Settings.fxml")));
        Stage window=(Stage) settingButton.getScene().getWindow();
        window.setScene(new Scene(root, 850, 594));
    }

    @FXML
    public void gotoTutorial() throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TutorialSettings.fxml")));
        Stage window=(Stage) tutorialButton.getScene().getWindow();
        window.setScene(new Scene(root, 850, 594));

    }

    @FXML
    public void handleSideBar(ActionEvent event) throws IOException, ClassNotFoundException {
        errwp.setOpacity(0);
        Object source = event.getSource();
        if(source.equals(homeButton)) {
            if (checkPaneState(paneHome)) {

                manageButtonEffect(0);

                enablePane(paneSaved, false);
                enablePane(panePremiumUser,false);
                enablePane(paneSearchRecipe, false);
                enablePane(paneWeeklyPlan, false);
                enablePane(paneCalc, false);
                enablePane(paneHome, true);

                general=paneHome.getParent();
                GeneralScene.refreshHome(general);
            }
        }else if(source.equals(savedButton)) {

            if (checkPaneState(paneSaved)) {
                manageButtonEffect(1);

                enablePane(paneSearchRecipe, false);
                enablePane(paneWeeklyPlan, false);
                enablePane(paneHome, false);
                enablePane(panePremiumUser,false);
                enablePane(paneCalc, false);
                enablePane(paneSaved, true);

                general=paneHome.getParent();
                GeneralScene.refreshHome(general);

                recipes = sca.saved();
                aps.clear();
                buttons.clear();
                savedBox.getChildren().clear();
                savedBox.setSpacing(20);
                if(recipes!=null && recipes.size()>0) {
                    this.createButton(recipes);
                }
            }
        }else if(source.equals(planButton)) {
            this.weekPlan();
            general=paneHome.getParent();
            GeneralScene.refreshHome(general);
        }else if(source.equals(recipeButton) && checkPaneState(paneSearchRecipe)) {
            manageButtonEffect(3);

            enablePane(paneSearchRecipe,true);
            enablePane(paneSaved,false);
            enablePane(paneWeeklyPlan,false);
            enablePane(panePremiumUser,false);
            enablePane(paneCalc, false);
            enablePane(paneHome,false);

            general=paneHome.getParent();
            GeneralScene.refreshHome(general);
        }
    }

    @FXML
    public void calculate() throws IOException, ClassNotFoundException {
        if(wpca.calc()) {
            manageButtonEffect(2);
            enablePane(paneSaved, false);
            enablePane(paneHome, false);
            enablePane(paneSearchRecipe, false);
            enablePane(panePremiumUser, false);
            enablePane(paneCalc, false);
            enablePane(paneWeeklyPlan, true);

            general=paneHome.getParent();
            GeneralScene.refreshHome(general);
        }else{
            errwp.setOpacity(1);
        }
    }

    @FXML
    public void gotoPremiumPayment() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Payment.fxml")));
        Stage window=(Stage) premiumButton.getScene().getWindow();
        window.setScene(new Scene(root, 850, 594));
    }


    private void enablePane(AnchorPane pane,boolean able) {
        EnablePane.enablePane(pane, able);
    }

    private boolean checkPaneState(AnchorPane pane){
        return pane.getOpacity() != 1;
    }

    private void manageButtonEffect(int index) {
        final Button[] sideBarButtons = {homeButton, savedButton, planButton, recipeButton};
        for (int elem = 0; elem < 4; elem++) {
            if (elem == index) {
                sideBarButtons[elem].getStyleClass().remove("button, focus");
                sideBarButtons[elem].getStyleClass().add("buttonAfterClick");
            } else {
                sideBarButtons[elem].getStyleClass().clear();
                sideBarButtons[elem].getStyleClass().add("button");
            }
        }
    }

    @FXML
    public void addDynamicElement() throws IOException, ClassNotFoundException {
        RecipeBean rb;
        ArrayList<Button> bt = new ArrayList<>();
        ArrayList<Label> lb = new ArrayList<>();
        ArrayList<ImageView> iw = new ArrayList<>();
        List<RecipeBean> rbs = new ArrayList<>();
        labelerr.setOpacity(0);
        recipeBox.getChildren().clear();
        try {
            if (recipe.isSelected()) {
                String rec = searchField.getText();
                rb = new RecipeBean(rec);
                rbs = sra.searchRecipe(rb);
                time.setSelected(false);
                ingredient.setSelected(false);
                type.setSelected(false);
            } else if (time.isSelected()) {
                String tm = searchField.getText();
                rbs = sra.searchRecipeGen(tm, "time");
                ingredient.setSelected(false);
                type.setSelected(false);
            } else if (ingredient.isSelected()) {
                String ingr = searchField.getText();
                rbs = sra.searchRecipeGen(ingr, "ingr");
                type.setSelected(false);
            } else if (type.isSelected()) {
                String t = searchField.getText();
                rbs = sra.searchRecipeGen(t, "type");
            }
            aps = new ArrayList<>();
            for (int i = 0; i < rbs.size(); i++) {
                bt.add(new Button(rbs.get(i).getName()));
                bt.get(i).setMaxSize(150, 30);
                bt.get(i).getStyleClass().add("button1");
                bt.get(i).setFont(Font.font("Century Gothic", 14));
                bt.get(i).setCursor(Cursor.HAND);

                int finalI = i;
                bt.get(i).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        IndexTrace.tempset(finalI);
                        IndexTrace.setFive(0);
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(template)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Stage window = (Stage) paneHome.getScene().getWindow();
                        window.setScene(GeneralScene.getRecipe(root));
                    }
                });

                lb.add(new Label("Chef: " + rbs.get(i).getChef()));
                lb.get(i).setMaxSize(100, 20);
                iw.add(new ImageView());
                iw.get(i).setImage(new Image(new ByteArrayInputStream(rbs.get(i).getImage())));
                iw.get(i).setFitHeight(75);
                iw.get(i).setFitWidth(100);
                aps.add(new AnchorPane());
                if ((i % 2) == 0) {
                    aps.get(i).setStyle("-fx-background-color: #FFFFFF");
                } else {
                    aps.get(i).setStyle("-fx-background-color: #16b229");
                }
                aps.get(i).getChildren().addAll(bt.get(i), lb.get(i), iw.get(i));
                aps.get(i).setMaxSize(564, 75);
                aps.get(i).setLeftAnchor(bt.get(i), 10.0);
                aps.get(i).setTopAnchor(bt.get(i), 10.0);
                aps.get(i).setLeftAnchor(lb.get(i), 15.0);
                aps.get(i).setTopAnchor(lb.get(i), 45.0);
                aps.get(i).setRightAnchor(iw.get(i), 10.0);
                aps.get(i).setTopAnchor(iw.get(i), 0.0);
                recipeBox.getChildren().add(aps.get(i));
            }
            if(rbs.size()==0){
                labelerr.setText("Ricetta o immagine relativa non trovata");
                labelerr.setOpacity(1);
            }
        } catch (MyException e) {
            labelerr.setText("Ricetta o immagine relativa non trovata");
            labelerr.setOpacity(1);
        }
    }

    @FXML
    public void handlePlan(ActionEvent e) throws IOException {
        IndexTrace.setFive(3);
        Object source=e.getSource();
        String sou=source.toString();

        String[] days={"mon", "tue", "wed", "thu", "fri", "sat", "sun"};

        int index=0;
        for(int i=0; i<days.length; i++){
            if(sou.contains(days[i])){
                index=i+1;
                break;
            }
        }

        IndexTrace.dayset(index);
        if(source.toString().contains("Break")){
            IndexTrace.timeset(1);
        }else if(source.toString().contains("Lunch")){
            IndexTrace.timeset(2);
        }else if(source.toString().contains("Dinner")){
            IndexTrace.timeset(3);
        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(template)));
        Stage window=(Stage) premiumButton.getScene().getWindow();
        window.setScene(GeneralScene.getRecipe(root));
    }

    private void createButton(List<RecipeBean> recipes){
        for(int i = 0; i< recipes.size(); i++) {
            buttons.add(new Button(recipes.get(i).getName()));
            buttons.get(i).setPrefSize(300, 50);
            buttons.get(i).setFont(Font.font("Centhury Gothic", 20));
            buttons.get(i).getStyleClass().add("button2");
            buttons.get(i).setCursor(Cursor.HAND);


            int finalI = i;
            buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    IndexTrace.preset(finalI);
                    IndexTrace.setFive(2);
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(template)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage window=(Stage) paneHome.getScene().getWindow();
                    window.setScene(GeneralScene.getRecipe(root));
                }
            });

            aps.add(new AnchorPane());
            aps.get(i).getChildren().addAll(buttons.get(i));
            aps.get(i).setLeftAnchor(buttons.get(i), 10.0);
            aps.get(i).setTopAnchor(buttons.get(i), 10.0);

            savedBox.getChildren().add(buttons.get(i));
        }
    }

    private void weekPlan() throws IOException, ClassNotFoundException {
        if (paneWeeklyPlan.getOpacity() != 1){
            if(wpca.getPremiumUser() && !wpca.getFiles()){
                manageButtonEffect(2);
                enablePane(paneSaved,false);
                enablePane(paneHome,false);
                enablePane(paneSearchRecipe,false);
                enablePane(panePremiumUser,false);
                enablePane(paneCalc, true);
                enablePane(paneWeeklyPlan, false);
            }else if(wpca.getPremiumUser() && wpca.getFiles()){
                manageButtonEffect(2);
                enablePane(paneSaved,false);
                enablePane(paneHome,false);
                enablePane(paneSearchRecipe,false);
                enablePane(paneCalc, false);
                enablePane(panePremiumUser,false);
                enablePane(paneWeeklyPlan,true);
            }
            else{
                manageButtonEffect(2);
                enablePane(paneSaved,false);
                enablePane(paneHome,false);
                enablePane(paneSearchRecipe,false);
                enablePane(paneCalc, false);
                enablePane(panePremiumUser,true);
                enablePane(paneWeeklyPlan,false);
            }
        }
    }
}
