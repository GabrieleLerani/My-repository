package com.example.progettoispw;

import com.example.progettoispw.recipemodel.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecipeBean {
    private String chef=null;
    private String name;
    private String type=null;
    private String cL =null;
    private String desc=null;
    private String alP=null;
    private String cookT=null;
    private List<Ingredient> ingr;
    private byte[] im =null;
    private ArrayList<String> all;

    private static Logger logger=Logger.getLogger(RecipeBean.class.getName());

    public RecipeBean(String name){
        ingr=new ArrayList<>();
        this.name=name;
        all=new ArrayList<>();
    }

    public RecipeBean(String name, String chef, byte[] image){
        ingr=new ArrayList<>();
        if(!name.equalsIgnoreCase("")) {
            this.name = name;
            this.chef = chef;
            this.im = image;
        }else{
            logger.log(Level.INFO, "Parametri non validi");
        }
        all=new ArrayList<>();
    }

    public void setName(String name){
        if(!name.equalsIgnoreCase("")) {
            this.name = name;
        }else{
            this.name=null;
        }
    }

    public String getName(){
        return name;
    }

    public void setType(String type){
        if(!type.equalsIgnoreCase("")) {
            this.type = type;
        }else{
            this.type=null;
        }
    }

    public String getType(){
        return type;
    }

    public void setCookingLevel(String cl){
        if(!cl.equalsIgnoreCase("")) {
            this.cL = cl;
        }else{
            this.cL=null;
        }
    }

    public String getCookingLevel(){
        return cL;
    }

    public void setDescription(String ds){
        if(!ds.equalsIgnoreCase("")) {
            this.desc = ds;
        }else{
            this.desc=null;
        }
    }

    public String getDescription(){
        return desc;
    }

    public void setAP(String aP){
        if(!aP.equalsIgnoreCase("")) {
            this.alP = aP;
        }else{
            this.alP=null;
        }
    }

    public String getAP(){
        return alP;
    }

    public void setCT(String cT){
        if(!cT.equalsIgnoreCase("")) {
            this.cookT = cT;
        }else{
            this.cookT=null;
        }
    }

    public String getCT(){
        return cookT;
    }

    public void addIngredient(Ingredient ingr){
        if(ingr!=null) {
            this.ingr.add(ingr);
        }
    }

    public void addIngredient(List<Ingredient> ingr){
        if(!ingr.isEmpty()) {
            this.ingr.addAll(ingr);
        }
    }

    public List<Ingredient> getIngredient(){ return ingr; }

    public void setImage(byte[] image){
        if(image!=null) {
            this.im = image;
        }else{
            this.im=null;
        }
    }

    public byte[] getImage(){ return im; }

    public void setChef(String chef){
        if(!chef.equalsIgnoreCase("")) {
            this.chef = chef;
        }else{
            this.chef=null;
        }
    }

    public String getChef(){ return chef; }

    public void addAll(String all){
        if(!all.equalsIgnoreCase("")) {
            this.all.add(all);
        }
    }

    public void addAll(List<String> all){
        if(!all.isEmpty()) {
            this.all.addAll(all);
        }
    }

    public List<String> getAll(){ return all; }
}
