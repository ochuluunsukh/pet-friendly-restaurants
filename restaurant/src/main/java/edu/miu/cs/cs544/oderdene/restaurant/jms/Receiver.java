package edu.miu.cs.cs544.oderdene.restaurant.jms;

import edu.miu.cs.cs544.oderdene.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Receiver {

    @Autowired
    private RestaurantService restaurantService;

    @JmsListener(destination = "${springjms.ratingQueue1}")
    public void receiveRating(Map<String, ?> map) {
        Integer restaurantId = (Integer) map.get("restaurantId");
        Integer rating = (Integer) map.get("rating");

        restaurantService.updateRating(restaurantId, rating);

        System.out.println("Received ratingQueue1: RestaurantID=" + restaurantId + " Rating=" + rating);
    }
}
