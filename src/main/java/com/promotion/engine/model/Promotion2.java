package com.promotion.engine.model;

import java.util.HashMap;
import java.util.Map;

public class Promotion2 implements PromotionCalculator {
    private static Map<Character,SkuIdPriceEnum> skuIdPriceEnumMap = new HashMap<Character, SkuIdPriceEnum>();
    static{
        skuIdPriceEnumMap.put('A',SkuIdPriceEnum.SKU_ID_PRICE_A);
        skuIdPriceEnumMap.put('B',SkuIdPriceEnum.SKU_ID_PRICE_B);
        skuIdPriceEnumMap.put('C',SkuIdPriceEnum.SKU_ID_PRICE_C);
        skuIdPriceEnumMap.put('D',SkuIdPriceEnum.SKU_ID_PRICE_D);
    }
    private static Map<String,CombinationPromEnum> combinationFixedriceEnumMap = new HashMap<String, CombinationPromEnum>();
    static{
        combinationFixedriceEnumMap.put("CD",CombinationPromEnum.COMB_SKU_ID_PROM_CD);
    }
    public void calculatePromotion(Map<Character, Order> skuIdOrderMap) {
        for(CombinationPromEnum combinationPromEnum : CombinationPromEnum.values()){
            String combinationSkuIds = combinationPromEnum.getCombinationString();

            if (skuIdOrderMap.containsKey(combinationSkuIds.charAt(0)) && skuIdOrderMap.containsKey(combinationSkuIds.charAt(1))) {
                Order order1 = skuIdOrderMap.get(combinationSkuIds.charAt(0));
                Order order2 = skuIdOrderMap.get(combinationSkuIds.charAt(1));
                calculateCostForOrder(order1,order2);
            }
        }
    }

    private void calculateCostForOrder(Order order1, Order order2){
        int quantity1 = order1.getQuantity();
        int quantity2 = order2.getQuantity();

        if(quantity1>0 && quantity2>0) {
            if (quantity1>=quantity2){
                quantity1 = quantity1-quantity2;
                order1.setCost(quantity1*skuIdPriceEnumMap.get(order1.getSkuId()).getPrice());
                String combinationString="";
                combinationString = combinationString+order1.getSkuId()+order2.getSkuId();
                double combinationPrice = quantity2 * (combinationFixedriceEnumMap.get(combinationString).getPrice());
                order2.setCost(combinationPrice);
            }else{
                quantity2 = quantity2-quantity1;
                order2.setCost(quantity2*skuIdPriceEnumMap.get(order2.getSkuId()).getPrice());
                String combinationString="";
                combinationString = combinationString+order1.getSkuId()+order2.getSkuId();
                double combinationPrice = quantity1 * (combinationFixedriceEnumMap.get(combinationString).getPrice());
                order1.setCost(combinationPrice);
            }
        }
    }

}
