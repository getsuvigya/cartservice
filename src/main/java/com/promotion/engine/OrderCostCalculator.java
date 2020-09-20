package com.promotion.engine;

import com.promotion.engine.model.Order;
import com.promotion.engine.model.SkuIdPriceEnum;

import java.util.HashMap;
import java.util.Map;

public class OrderCostCalculator {
    private static Map<Character,SkuIdPriceEnum> skuIdPriceEnumMap = new HashMap<Character, SkuIdPriceEnum>();
    static{
        skuIdPriceEnumMap.put('A',SkuIdPriceEnum.SKU_ID_PRICE_A);
        skuIdPriceEnumMap.put('B',SkuIdPriceEnum.SKU_ID_PRICE_B);
        skuIdPriceEnumMap.put('C',SkuIdPriceEnum.SKU_ID_PRICE_C);
        skuIdPriceEnumMap.put('D',SkuIdPriceEnum.SKU_ID_PRICE_D);
    }

    public void calculateOrderCost(Order order){
        order.setCost(order.getQuantity()* skuIdPriceEnumMap.get(order.getSkuId()).getPrice());
    }
}
