package com.promotion.engine.model;

public enum CombinationPromEnum {
    COMB_SKU_ID_PROM_CD("CD",30);
    CombinationPromEnum(String combinationString,double price){
        this.combinationString = combinationString;
        this.price = price;
    }
    private String combinationString;
    private double price;

    public String getCombinationString() {
        return combinationString;
    }

    public double getPrice() {
        return price;
    }
}
