package com.promotion.engine;

import com.promotion.engine.model.Order;
import com.promotion.engine.model.PromotionCalculator;

import java.util.List;
import java.util.Map;

public class PromotionApplier {
    public void applyPromotion(Map<Character, Order> skuIdOrderMap, List<PromotionCalculator> promotionCalculators){
        for(PromotionCalculator promotionCalculator:promotionCalculators){
            promotionCalculator.calculatePromotion(skuIdOrderMap);
        }
    }
}
