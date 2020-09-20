package com.promotion.engine.model;

import java.util.HashMap;
import java.util.Map;

public class Promotion1 implements PromotionCalculator {
    private static Map<Character,SkuIdPriceEnum> skuIdPriceEnumMap = new HashMap<Character, SkuIdPriceEnum>();
    static{
        skuIdPriceEnumMap.put('A',SkuIdPriceEnum.SKU_ID_PRICE_A);
        skuIdPriceEnumMap.put('B',SkuIdPriceEnum.SKU_ID_PRICE_B);
        skuIdPriceEnumMap.put('C',SkuIdPriceEnum.SKU_ID_PRICE_C);
        skuIdPriceEnumMap.put('D',SkuIdPriceEnum.SKU_ID_PRICE_D);
    }
    public void calculatePromotion(Map<Character, Order> skuIdOrderMap) {
        for(SkuIdPromotion1Enum skuIdPromotion1Enum : SkuIdPromotion1Enum.values()) {
            if (skuIdOrderMap.containsKey(skuIdPromotion1Enum.getSkuId())) {
                Order order = skuIdOrderMap.get(skuIdPromotion1Enum.getSkuId());
                Integer quantity = order.getQuantity();
                int totalSets = quantity / skuIdPromotion1Enum.getQuantity();
                int remaining = quantity % (skuIdPromotion1Enum.getQuantity());
                double totalCost = totalSets * skuIdPromotion1Enum.getFixedPrice() + remaining * skuIdPriceEnumMap.get(skuIdPromotion1Enum.getSkuId()).getPrice();
                order.setCost(totalCost);
            }
        }
    }
}
