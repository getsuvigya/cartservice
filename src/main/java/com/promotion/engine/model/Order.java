package com.promotion.engine.model;

public class Order {
    private Character skuId;
    private int quantity;
    private double cost;

    public Character getSkuId() {
        return skuId;
    }

    public void setSkuId(Character skuId) {
        this.skuId = skuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
