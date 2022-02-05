package com.example.progettoispw;

import com.example.progettoispw.recipemodel.Ingredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShoppingListController implements Initializable {
    @FXML
    private Button gotohomeButton;
    @FXML
    private TextField nameField;
    @FXML
    private Button saveListButton;
    @FXML
    private TextField amountField;
    @FXML
    private TableView<Ingredient> listTable;
    @FXML
    private  TableColumn<Ingredient,String> nameCol;
    @FXML
    private  TableColumn<Ingredient,String> amountCol;

    private String str="textInvalid";
    private final BackControllerA bca;
    private static Logger logger=Logger.getLogger(ShoppingListController.class.getName());
    ObservableList<Ingredient> observableList = FXCollections.observableArrayList();

    public ShoppingListController(){
        bca=new BackControllerA();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        listTable.setItems(observableList);
    }

    @FXML
    public void gotoHome() throws IOException, ClassNotFoundException {
        Stage window = (Stage) gotohomeButton.getScene().getWindow();
        if (bca.getSpecialization().equalsIgnoreCase("User") || bca.getSpecialization().equalsIgnoreCase("Premium")) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
            window.setScene(GeneralScene.getHome(root));
        }else{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeChef.fxml")));
            window.setScene(new Scene(root, 850, 594));
        }
    }

    @FXML
    public void addIngredient(){
        String ingName = nameField.getText();
        String ingAm = amountField.getText();
        if(ingName.isEmpty() && ingAm.isEmpty() || !ingAm.matches("[0-9]+") || !ingName.matches("[a-zA-Z]+")) {
            nameField.getStyleClass().add(str);
            amountField.getStyleClass().add(str);
        }
        else{
            listTable.getItems().add(new Ingredient(ingName,ingAm));
            nameField.setText("");
            amountField.setText("");
            nameField.getStyleClass().remove(str);
            amountField.getStyleClass().removeAll(str);
        }
    }

    @FXML
    public void removeIngredient(){
        ObservableList<Ingredient> singleIngredient;
        observableList = listTable.getItems();
        singleIngredient = listTable.getSelectionModel().getSelectedItems();
        singleIngredient.forEach(observableList::remove);

    }

    public void handleSave(){
        Stage secondaryStage = new Stage();
        FileChooser fc = new FileChooser();
        fc.setTitle("Save shopping list");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        if(observableList.isEmpty()){
            secondaryStage.initOwner(this.saveListButton.getScene().getWindow());
            Alert emptyTableAlert = new Alert(Alert.AlertType.ERROR, "EMPTY TABLE",ButtonType.OK);
            emptyTableAlert.setContentText("You have nothing to save");
            emptyTableAlert.initModality(Modality.APPLICATION_MODAL);
            emptyTableAlert.initOwner(this.saveListButton.getScene().getWindow());
            emptyTableAlert.showAndWait();
            if(emptyTableAlert.getResult() == ButtonType.OK){
                emptyTableAlert.close();
            }
        }
        else{
            File file = fc.showSaveDialog(secondaryStage);
            if(file != null){
                saveFile(observableList,file);
            }
        }
    }

    public void saveFile(ObservableList<Ingredient> observableList,File file){
        try {
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));
            outWriter.write("----------------  ShoppingList  ----------------"+'\n');
            for(Ingredient i : observableList){
                outWriter.write(i.getName()+" "+i.getAmount()+"g;");
                outWriter.newLine();
            }
            logger.log(Level.INFO, String.valueOf(observableList));
            outWriter.close();
        }
        catch (IOException e) {
            Alert ioAlert = new Alert(Alert.AlertType.ERROR,"OPS!",ButtonType.OK);
            ioAlert.setContentText("Sorry. An error has occurred");
            ioAlert.showAndWait();
            if(ioAlert.getResult() == ButtonType.OK){
                ioAlert.close();
            }
        }
    }
}
