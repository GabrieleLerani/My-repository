package com.example.progettoispw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogBean implements Serializable {
    private String user=null;
    private String pass=null;
    private String spec=null;
    private String email=null;
    private String cl=null;
    private int check=-1;
    private String ap=null;
    private ArrayList<String> all=new ArrayList<>();
    private boolean prem=false;

    private static Logger logger=Logger.getLogger(LogBean.class.getName());

    public LogBean(){
    }

    public LogBean(String user, String pass){
        this.user=user;
        this.pass=pass;
    }

    public LogBean(String user, String pass, String spec, String email){
        if(!user.equalsIgnoreCase("") && !pass.equalsIgnoreCase("") && !spec.equalsIgnoreCase("") && !email.equalsIgnoreCase("")) {
            this.user = user;
            this.pass = pass;
            this.spec = spec;
            this.email = email;
        }else{
            logger.log(Level.INFO, "Parametri non validi");
        }
    }

    public String getUser(){
        return user;
    }

    public void setUser(String user){
        if(!user.equalsIgnoreCase("")) {
            this.user = user;
        }else{
            this.user=null;
        }
    }

    public String getPass(){
        return pass;
    }

    public void setPass(String pass){
        if(!pass.equalsIgnoreCase("")) {
            this.pass = pass;
        }else{
            this.pass=null;
        }
    }

    public String getCL() { return cl; }

    public void setCL(String cl){
        if(cl.equalsIgnoreCase("")) {
            this.cl = null;
        }else{
            this.cl = cl;
        }
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        if(!email.equalsIgnoreCase("")) {
            this.email = email;
        }else{
            this.email=null;
        }
    }

    public String getSpec() { return spec; }

    public void setSpec(String spec) {
        if(!spec.equalsIgnoreCase("")) {
            this.spec = spec;
        }else{
            this.spec=null;
        }
    }

    public int getCheck(){ return check; }

    public void setCheck(){ this.check =1; }

    public void desetCheck() { check =0; }

    public void setAP(String ap){
        if(!ap.equalsIgnoreCase("")) {
            this.ap = ap;
        }else {
            this.ap = null;
        }
    }

    public String getAP(){ return ap; }

    public void addAll(String all){
        if(!all.equalsIgnoreCase("")) {
            this.all.add(all);
        }else{
            this.all=null;
        }
    }

    public List<String> getAll(){ return all; }

    public void setPremium(){ prem=true; }

    public void desetPremium(){ prem=false; }

    public boolean getPremium(){ return prem; }
}
