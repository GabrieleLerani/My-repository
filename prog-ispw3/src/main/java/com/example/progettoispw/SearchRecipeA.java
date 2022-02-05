package com.example.progettoispw;

import com.example.progettoispw.recipemodel.Recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchRecipeA {
    private SearchDAO dao;
    private ArrayList<Recipe> recipes;
    private List<RecipeBean> rbs;
    private Login login;
    private FileInterDAO filedao;

    public SearchRecipeA() throws IOException, ClassNotFoundException {
        dao=SearchDAO.getInstance();
        filedao=FileInterDAO.getInstance();
        login=filedao.readLog();
    }

    public List<RecipeBean> searchRecipe(RecipeBean rb) throws MyException, IOException, ClassNotFoundException {
        rbs=new ArrayList<>();
        SearchRecipeA sra=new SearchRecipeA();
        String recipe=rb.getName();
        recipes=dao.searchRec(recipe, login.getCL(), login.getAP(), login.getUser());

        rbs=sra.checkAlle(recipes);
        return rbs;
    }

    public List<RecipeBean> searchRecipeGen(String general, String diff) throws IOException, ClassNotFoundException, MyException {
        rbs=new ArrayList<>();
        SearchRecipeA sra=new SearchRecipeA();
        recipes = dao.searchRecipe(diff, general, login.getCL(), login.getAP(), login.getUser());

        rbs=sra.checkAlle(recipes);
        return rbs;
    }

    public List<RecipeBean> checkAlle(List<Recipe> recipe){
        List<RecipeBean> rb=new ArrayList<>();
        int h=0;

        // controllo delle allergie
        for(int i=0; i<recipe.size(); i++) {
            for (int j = 0; j < login.getAll().size(); j++) {
                for (int k = 0; k < recipe.get(i).getAll().size(); k++) {
                    if (login.getAll().get(j).equalsIgnoreCase(recipe.get(i).getAll().get(k))) {
                        h = 1;
                        break;
                    }
                }
                if (h == 1) {
                    break;
                }
            }
            if (h == 0) {
                rb.add(new RecipeBean(recipe.get(i).getName(), recipe.get(i).getChef(), recipe.get(i).getImage()));
            }
            h=0;
        }
        return rb;
    }
}
