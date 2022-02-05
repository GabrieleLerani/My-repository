package com.example.progettoispw;

import com.example.progettoispw.recipemodel.Ingredient;
import com.example.progettoispw.recipemodel.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SearchDAO extends SubjectSearchDAO{
    private ArrayList<Recipe> recipes;
    private ArrayList<Recipe> states;
    private ArrayList<ArrayList<Ingredient>> ingredients;
    private ArrayList<String> names;
    private ArrayList<String> ar;
    private int state=0;
    private int internalstate=0;
    private String r="Ricetta";
    private Conn con;
    private Connection conn;
    private static SearchDAO instance=null;

    private SearchDAO(){
        recipes=new ArrayList<>();
        names=new ArrayList<>();
        states=new ArrayList<>();
        ar=new ArrayList<>();
        ingredients= new ArrayList<>();
        con=Conn.getInstance();
        conn=con.connect();
    }

    public static SearchDAO getInstance(){
        if (SearchDAO.instance == null)
            SearchDAO.instance = new SearchDAO();
        return instance;
    }

    public ArrayList<Recipe> searchRec(String name, String cl, String ap, String username) throws MyException {
        int num;

        try {
            num=instance.checkCL(cl,username);
            if(ap ==null){
                ap =instance.getAP(username);
            }
            recipes.clear();
            names.clear();
            ingredients.clear();

            ResultSet rs=SimpleQueries.getRecipeFromNameCLAPAll(name, num, ap, conn);
            if(!rs.first()){
                throw new MyException("Ricetta non trovata");
            }
            rs.first();
            this.createRecipe(rs);
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        state++;
        return recipes;
    }

    public ArrayList<Recipe> searchRecipe(String diff, String general, String cl, String ap, String username) throws MyException {
        int num;

        try {
            num=instance.checkCL(cl,username);
            if(ap ==null){
                ap =instance.getAP(username);
            }
            recipes.clear();
            names.clear();
            ar.clear();
            ingredients.clear();

            ResultSet rs=null;
            if(diff.equalsIgnoreCase("time")) {
                rs = SimpleQueries.getRecipeFromTimeCLAPAll(Integer.parseInt(general), num, ap, conn);
            }else if(diff.equalsIgnoreCase("ingr")){
                rs=SimpleQueries.getRecipeFromIngrCLAPAll(general, num, ap, conn);
            }else if(diff.equalsIgnoreCase("type")){
                rs=SimpleQueries.getRecipeFromTypeCLAPAll(general, num, ap, conn);
            }
            if(!rs.first()){
                throw new MyException("Ricetta non trovata");
            }
            rs.first();
            this.createRecipe(rs);
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        state++;
        return recipes;
    }

    private int checkCL(String cl, String username){
        int i = 0;
        if(cl ==null){
            i=instance.getCL(username);
        }else if(cl.equalsIgnoreCase("beginner")){
            i=1;
        }else if(cl.equalsIgnoreCase("intermediate")){
            i=2;
        }else if(cl.equalsIgnoreCase("advanced")){
            i=3;
        }
        return i;
    }

    private String checkCL(int i){
        String level = "";
        if(i==1){
            level="Beginner";
        }else if(i==2){
            level="Intermediate";
        }else if(i==3){
            level="Advanced";
        }
        return level;
    }

    private int getCL(String username) {
        int i=0;

        try {
            ResultSet rs = SimpleQueries.selectUserFromName(username, conn);
            if (!rs.first()) {
                i = 0;
            } else {
                i = rs.getInt("CookingLevel");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    private void createRecipe(ResultSet rs) throws MyException {
        String type;
        String cT;
        String desc;
        String ric;
        String nome;
        byte[] image;
        Blob blob;
        String allergies;
        int cookingLevel;
        String ck;
        int num=0;
        int z=0;
        ResultSet pq;
        try {
            do {
                // lettura delle colonne "by ricetta"
                ric = rs.getString(r);
                nome = rs.getString("Nome");
                allergies = rs.getString("Allergies");
                type = rs.getString("Type");
                desc = rs.getString("Description");
                cT = rs.getString("Tempo");
                cookingLevel = rs.getInt("Livello");
                ck = this.checkCL(cookingLevel);

                do {
                    ingredients.add(new ArrayList<>());
                    if (rs.getString(r).equalsIgnoreCase(ric) && rs.getString("Nome").equalsIgnoreCase(nome)) {
                        ingredients.get(z).add(new Ingredient(rs.getString("Ingrediente"), rs.getString("Ammontare")));
                    } else {
                        break;
                    }
                } while (rs.next());
                rs.previous();

                if (num == 0 || !names.get(num - 1).equals(nome) || !ar.get(num - 1).equals(ric)) { //controllo che l'ultimo nome non sia lo stesso che si userà per ricavare l'immagine
                    pq = SimpleQueries.getImageFromChef(ric, nome, conn);
                    if (!pq.first()) {
                        throw new MyException("Immagine non trovata");
                    }
                    blob = pq.getBlob("IMG");

                    image = blob.getBytes(1, (int) blob.length());
                    recipes.add(new Recipe(ric, image, type, ck, desc, cT, ingredients.get(z)));
                    recipes.get(num).setChef(nome);
                    this.all(allergies, num);
                    num++;
                    z++;

                    pq.close();
                    names.add(nome);
                    ar.add(ric);
                }
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void all(String allergies, int num){
        StringTokenizer st = new StringTokenizer(allergies);
        while (st.hasMoreTokens()) {
            recipes.get(num).addAll(st.nextToken());
        }
    }

    private String getAP(String username){
        String ap = null;

        try {
            ResultSet rs=SimpleQueries.selectUserFromName(username, conn);
            rs.first();
            ap=rs.getString("AlimentarPreferences");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ap;
    }

    @Override
    protected boolean isThereAnythingToNotify(){
        boolean b= internalstate<state;
        internalstate=state;
        if(b) {
            this.doSomething();
        }
        return b;
    }

    protected void doSomething(){
        states=recipes;
    }

    public ArrayList<Recipe> getState(){
        return states;
    }
}
