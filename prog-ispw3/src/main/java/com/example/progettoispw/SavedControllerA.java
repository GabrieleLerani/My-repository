package com.example.progettoispw;

import com.example.progettoispw.recipemodel.Recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SavedControllerA {
    private ArrayList<RecipeBean> recipes;
    private List<Recipe> rcp;

    public SavedControllerA(){
        recipes= new ArrayList<>();
        rcp=new ArrayList<>();
    }

    public List<RecipeBean> saved() throws IOException, ClassNotFoundException {
        FileInterDAO filedao=FileInterDAO.getInstance();
        rcp=filedao.readSaved();
        recipes.clear();
        if(rcp!=null) {
            for (int i = 0; i < rcp.size(); i++) {
                recipes.add(Convert.convertEntityToBean(rcp.get(i)));
            }
        }else{
            return null;
        }
        return recipes;
    }
}
