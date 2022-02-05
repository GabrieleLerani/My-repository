package com.example.progettoispw;

import com.example.progettoispw.recipemodel.Recipe;

import java.io.Serializable;

public abstract class Meal implements Serializable {
    protected Recipe main;
    protected Recipe side;
    protected Recipe dess;

    protected Meal(){}

    public abstract Recipe getRecipe();
}
