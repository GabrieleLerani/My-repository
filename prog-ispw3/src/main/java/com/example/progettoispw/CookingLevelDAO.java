package com.example.progettoispw;

import java.sql.*;

public class CookingLevelDAO {
    private static CookingLevelDAO instance=null;
    private Conn con;
    private Connection conn;

    private CookingLevelDAO(){
        con=Conn.getInstance();
        conn=con.connect();
    }

    public static CookingLevelDAO getInstance(){
        if (CookingLevelDAO.instance == null)
            CookingLevelDAO.instance = new CookingLevelDAO();
        return instance;
    }

    public void insertCL(String cl, String username){
        int num=0;
        try {
            if (cl.equalsIgnoreCase("beginner")) {
                num = 1;
            } else if (cl.equalsIgnoreCase("intermediate")) {
                num = 2;
            }else if(cl.equalsIgnoreCase("advanced")){
                num = 3;
            }

            SimpleQueries.insertCookingLevel(num, username, conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCL(String username){
        String lv=null;
        try {
            ResultSet rs = SimpleQueries.getSpecFromName(username, conn);
            if(!rs.first()){
                return null;
            }
            rs.first();
            int cl=rs.getInt("CookingLevel");
            if(cl==1){
                lv="Beginner";
            }else if(cl==2){
                lv="Intermediate";
            }else if(cl==3){
                lv="Advanced";
            }
            return lv;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
