package com.example.progettoispw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlimentarPreferencesControllerA {
    private AlimentarDAO dao;
    private Login login;
    private FileInterDAO filedao;

    public AlimentarPreferencesControllerA() throws IOException, ClassNotFoundException {
        dao=AlimentarDAO.getInstance();
        filedao=FileInterDAO.getInstance();
        login=filedao.readLog();
    }

    public void setPref(String pref, List<String> all) throws IOException, ClassNotFoundException {
        if(pref.equals("I have no particular preferences")){
            pref="None";
        }
        dao.insertAP(login.getUser(), pref, all);
        if(!pref.equalsIgnoreCase("")) {
            login.setAP(pref);
        }
        if(!all.isEmpty()) {
            login.deleteAll();
            for (int i = 0; i < all.size(); i++) {
                login.addAll(all.get(i));
            }
        }
        filedao.writeLog(login);
    }

    public String getPref() throws IOException, ClassNotFoundException {
        String choise=dao.getPref(login.getUser());
        login.setAP(choise);
        filedao.writeLog(login);
        return choise;
    }

    public List<String> getAll() throws IOException, ClassNotFoundException {
        List<String> list=dao.getAll(login.getUser());
        for(String s : list){
            login.addAll(s);
        }
        filedao.writeLog(login);
        return list;
    }

    public void clear(){
        dao.clear(login.getUser());
    }
}
