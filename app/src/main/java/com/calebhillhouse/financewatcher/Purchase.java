package com.calebhillhouse.financewatcher;

/**
 * Created by caleb on 12/6/2016.
 */
public class Purchase {
    public String typeOfPurchase;
    public double amount;

    public Purchase(String typeOfPurchase, double amount){
        this.typeOfPurchase = typeOfPurchase;
        this.amount = amount;
    }
}
