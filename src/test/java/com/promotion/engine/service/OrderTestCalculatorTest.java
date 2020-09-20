package com.promotion.engine.service;

import com.promotion.engine.OrderCostCalculator;
import com.promotion.engine.model.Order;
import com.promotion.engine.model.SkuIdPriceEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTestCalculatorTest {
    private static Map<Character,Integer> skuIdQuantityMap=new HashMap<Character, Integer>();
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


    }

    @Test
    public void testCalculateOrderCost(){
        OrderCostCalculator orderCostCalculator = new OrderCostCalculator();

        Order order = new Order();
        order.setSkuId('A');
        order.setQuantity(3);
        double totalCost = order.getQuantity() * SkuIdPriceEnum.SKU_ID_PRICE_A.getPrice();
        orderCostCalculator.calculateOrderCost(order);
        assertEquals(totalCost,order.getCost());
    }

}
