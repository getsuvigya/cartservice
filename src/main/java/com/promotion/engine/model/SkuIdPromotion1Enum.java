package com.promotion.engine.model;

public enum  SkuIdPromotion1Enum {
    SKU_ID_PROM_A('A',3,130),
    SKU_ID_PROM_B('B',2,45);

    SkuIdPromotion1Enum(Character skuId, int quantity, double fixedPrice){
        this.skuId = skuId;
        this.fixedPrice = fixedPrice;
        this.quantity  =quantity;
    }
    private Character skuId;
    private int quantity;
    private double fixedPrice;

    public Character getSkuId() {
        return skuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getFixedPrice() {
        return fixedPrice;
    }
}
