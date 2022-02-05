package com.example.progettoispw;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ObserverRecipeDAO {
    private static Logger logger=Logger.getLogger(ObserverRecipeDAO.class.getName());
    protected SearchDAO sd;

    protected void notifySubjectStatus(String message){
        logger.log(Level.INFO, message);
    }

    public void setSubject(SearchDAO subject) {
        this.sd = subject;
    }

    public abstract void update();
}
