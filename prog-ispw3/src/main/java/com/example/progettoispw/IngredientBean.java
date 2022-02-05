package com.example.progettoispw;

import java.util.logging.Level;
import java.util.logging.Logger;

public class IngredientBean {
    private String name;
    private String amount;

    private static Logger logger=Logger.getLogger(IngredientBean.class.getName());

    public IngredientBean(String name,String amount) {
        if (!name.equalsIgnoreCase("") && !amount.equalsIgnoreCase("")) {
            this.name = name;
            this.amount = amount;
        } else {
            logger.log(Level.INFO, "Parametri non validi");
        }
    }

    public String getName(){
        return name;
    }

    public String getAmount() { return amount;}

    public void setName(String name) {
        if(!name.equalsIgnoreCase("")) {
            this.name = name;
        }else{
            this.name=null;
        }
    }

    public void setAmount(String amount) {
        if(!amount.equalsIgnoreCase("")) {
            this.amount = amount;
        }else{
            this.amount=null;
        }
    }
}
