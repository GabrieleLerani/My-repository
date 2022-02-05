package com.example.progettoispw;

import com.example.progettoispw.recipemodel.Recipe;

import java.io.File;
import java.io.InputStream;
import java.sql.*;

public class AddRecipeDAO {
    private int num;
    private Conn con;
    private Connection conn;
    private static AddRecipeDAO instance=null;

    private AddRecipeDAO(){
        con=Conn.getInstance();
        conn=con.connect();
    }

    public static AddRecipeDAO getInstance(){
        if (AddRecipeDAO.instance == null)
            AddRecipeDAO.instance = new AddRecipeDAO();
        return instance;
    }

    public void insertRecipe(Recipe rb, String username) throws SQLException {
        StringBuilder str = new StringBuilder();
        if(rb.getCookingLevel().equals("Beginner")){
            num=1;
        }else if(rb.getCookingLevel().equals("Intermediate")){
            num=2;
        }else if(rb.getCookingLevel().equals("Advanced")){
            num=3;
        }

        for(int i=0; i<rb.getAll().size(); i++){
            if(i==0){
                str = new StringBuilder(rb.getAll().get(i));
            }else {
                str = new StringBuilder(str + " " + rb.getAll().get(i));
            }
        }
        String all=String.valueOf(str);

        ResultSet rs=SimpleQueries.getRecipeFromName(username, rb.getName(), conn);
        if(rs.first()){
            throw new SQLIntegrityConstraintViolationException("Ricetta già esistente");
        }

        for(int i=0; i<IndexTrace.get()+1; i++){
            SimpleQueries.insertRecipeFromIngredient(username, rb, rb.getIngredient().get(i).getName(), num, rb.getIngredient().get(i).getAmount(), all, conn);
        }
    }

    public void sendImage(String name, String chef, File file, InputStream fin){
        try {
            SimpleQueries.insertImages(name, chef, file, fin, conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
