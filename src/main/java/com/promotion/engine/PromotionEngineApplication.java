package com.promotion.engine;

import com.promotion.engine.model.Order;
import com.promotion.engine.model.Promotion1;
import com.promotion.engine.model.Promotion2;
import com.promotion.engine.model.PromotionCalculator;

import java.util.*;

public class PromotionEngineApplication {
    public static void main(String[] args) {
        Character[] skuIdArray = {'A','A','B','C','D','A','B','D'};
        List<Character> skuIdList = Arrays.asList(skuIdArray);
        List<PromotionCalculator> promotionCalculators = new LinkedList<PromotionCalculator>();
        Promotion1 promotion1 = new Promotion1();
        Promotion2 promotion2 = new Promotion2();
        promotionCalculators.add(promotion1);
        promotionCalculators.add(promotion2);
        System.out.println("Total car cost : "+calculateCartCost(skuIdList, promotionCalculators));
    }

    public static double calculateCartCost(List<Character> skuIdList, List<PromotionCalculator> promotionCalculators) {
        List<Order> orderList = new LinkedList<Order>();
        OrderCostCalculator orderCostCalculator = new OrderCostCalculator();
        Map<Character, Integer> skuIdQuantityMap = getSkuIdQuantityMap(skuIdList);
        Map<Character, Order> skuIdOrderMap = getSkuIdOrderMap(orderList, orderCostCalculator, skuIdQuantityMap);
        PromotionApplier promotionApplier = new PromotionApplier();
        promotionApplier.applyPromotion(skuIdOrderMap, promotionCalculators);

        double cartCost = 0;
        for(Order order : orderList){
            cartCost = cartCost + order.getCost();
        }
        return cartCost;
    }

    private static Map<Character, Order> getSkuIdOrderMap(List<Order> orderList, OrderCostCalculator orderCostCalculator, Map<Character, Integer> skuIdQuantityMap) {
        Set<Map.Entry<Character,Integer>> entries = skuIdQuantityMap.entrySet();
        Iterator<Map.Entry<Character,Integer>> entryIterator = entries.iterator();
        while (entryIterator.hasNext()){
            Map.Entry<Character,Integer> characterIntegerEntry = entryIterator.next();
            Order order = new Order();
            order.setSkuId(characterIntegerEntry.getKey());
            order.setQuantity(characterIntegerEntry.getValue());
            orderCostCalculator.calculateOrderCost(order);
            orderList.add(order);
        }

        Map<Character,Order> skuIdOrderMap = new HashMap<Character, Order>();
        for(Order order : orderList){
            skuIdOrderMap.put(order.getSkuId(), order);
        }
        return skuIdOrderMap;
    }

    private static Map<Character, Integer> getSkuIdQuantityMap(List<Character> skuIdList) {
        Map<Character,Integer> skuIdQuantityMap = new HashMap<Character, Integer>();
        for (Character c: skuIdList){
            if(skuIdQuantityMap.containsKey(c)){
                skuIdQuantityMap.put(c, skuIdQuantityMap.get(c)+1);
            }else{
                skuIdQuantityMap.put(c,1);
            }
        }
        return skuIdQuantityMap;
    }
}
