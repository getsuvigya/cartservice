package com.promotion.engine.service;

import com.promotion.engine.model.Order;
import com.promotion.engine.model.Promotion1;
import com.promotion.engine.model.Promotion2;
import com.promotion.engine.model.PromotionCalculator;
import com.promotion.engine.OrderCostCalculator;
import com.promotion.engine.PromotionApplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class PromotionApplierTest {
    private static Map<Character,Integer> skuIdQuantityMap=new HashMap<Character, Integer>();
    private static Map<Character, Order> skuIdOrderMap = new HashMap<Character, Order>();
    private static List<PromotionCalculator> promotionCalculators = new LinkedList<PromotionCalculator>();
    @BeforeAll
    public static void createskuIdQuantityMapList(){
        Character[] skuIdArray = {'A','A','B','C','D','A','B','D'};
        List<Character> skuIdList = Arrays.asList(skuIdArray);
        new HashMap<Character, Integer>();
        for (Character c: skuIdList){
            if(skuIdQuantityMap.containsKey(c)){
                skuIdQuantityMap.put(c, skuIdQuantityMap.get(c)+1);
            }else{
                skuIdQuantityMap.put(c,1);
            }
        }
        OrderCostCalculator orderCostCalculator = new OrderCostCalculator();
        List<Order> orderList = new LinkedList<Order>();
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

        skuIdOrderMap = new HashMap<Character, Order>();
        for(Order order : orderList){
            skuIdOrderMap.put(order.getSkuId(), order);
        }

        Promotion1 promotion1 = new Promotion1();
        Promotion2 promotion2 = new Promotion2();
        promotionCalculators.add(promotion1);
        promotionCalculators.add(promotion2);
    }


@Test
    public void applyPromotion(){
        PromotionApplier promotionApplier = new PromotionApplier();
        promotionApplier.applyPromotion(skuIdOrderMap,promotionCalculators);
        Order order = new Order();
        order.setSkuId('A');
        order.setQuantity(3);
        order.setCost(130);
        assertEquals(order.getCost(),skuIdOrderMap.get('A').getCost());
    }
}
