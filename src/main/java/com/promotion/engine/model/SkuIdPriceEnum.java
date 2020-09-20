package com.promotion.engine.model;

public enum SkuIdPriceEnum {
    SKU_ID_PRICE_A('A',50),
    SKU_ID_PRICE_B('B',30),
    SKU_ID_PRICE_C('C',50),
    SKU_ID_PRICE_D('D',30);
    SkuIdPriceEnum(Character skuId,double price){
        this.price=price;
    }
    private double price;
    private Character skuId;

    public double getPrice() {
        return price;
    }

    public Character getSkuId() {
        return skuId;
    }
}
