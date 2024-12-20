package edu.miu.cs.cs544.oderdene.rating.jms;

import edu.miu.cs.cs544.oderdene.rating.entity.RateReview;
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

    @Value(value = "${springjms.ratingQueue1}")
    private String ratingQueue1;

    public void sendRate(RateReview rate) {
        Map map = new HashMap();
        map.put("restaurantId", rate.getRestaurantId());
        map.put("rating", rate.getRating());
        jmsTemplate.convertAndSend(ratingQueue1, map);
        System.out.println("Message sent to queue: ");
    }
}
