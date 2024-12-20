package edu.miu.cs.cs544.oderdene.restaurant.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Sender {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value(value = "${springjms.ratingQueue}")
    private String ratingQueue;

    public void sendRating(Integer restaurantId, Integer rating) {
        Map map = new HashMap();
        map.put("restaurantId", restaurantId );
        map.put("rating", rating);
        jmsTemplate.convertAndSend(ratingQueue, map);
        System.out.println("Message sent to ratingQueue: ");
    }
}
