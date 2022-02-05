package com.example.progettoispw;

import com.example.progettoispw.recipemodel.Recipe;

import java.io.Serializable;

public class MealBreak extends Meal implements Serializable {
    public MealBreak(Recipe dess){
        this.dess=dess;
    }

    public Recipe getRecipe(){ return dess; }
}
