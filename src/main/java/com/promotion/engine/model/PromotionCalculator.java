package com.promotion.engine.model;

import java.util.Map;

public interface PromotionCalculator {
    void calculatePromotion(Map<Character,Order> skuIdOrderMap);
}
