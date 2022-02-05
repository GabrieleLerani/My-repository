package com.example.progettoispw;

import com.example.progettoispw.recipemodel.Recipe;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class AddRecipeControllerA {
    private AddRecipeDAO dao;
    private Login login;
    private File file;
    private FileInterDAO filedao;

    public AddRecipeControllerA() throws IOException, ClassNotFoundException {
        dao=AddRecipeDAO.getInstance();
        filedao=FileInterDAO.getInstance();
        login=filedao.readLog();
    }

    public void addRecipe(RecipeBean rb) throws IOException, ClassNotFoundException, SQLException {
        Recipe recipe=Convert.convertBeanToEntity(rb);
        dao.insertRecipe(recipe, login.getUser());
        filedao.writeChef(recipe);
    }

    public File saveIm(){
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images file", "*.jpg", "*.png"));

            file = fc.showOpenDialog(null);
            fc.setInitialDirectory(file.getParentFile());
            return file;
        }catch (NullPointerException e){
            return null;
        }
    }

    public void sendIm(String name) throws FileNotFoundException {
        File act=new File(String.valueOf(file));
        InputStream fin = new java.io.FileInputStream(act);
        dao.sendImage(name, login.getUser(), act, fin);
    }

    public IngredientBean createBean(String name, String amount){
        return new IngredientBean(name, amount);
    }
}
