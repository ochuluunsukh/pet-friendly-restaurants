package edu.miu.cs.cs544.oderdene.rating.jms;

import edu.miu.cs.cs544.oderdene.rating.service.RateReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Receiver {
    @Autowired
    private RateReviewService reviewService;

    @JmsListener(destination = "${springjms.ratingQueue}")
    public void receiveRating(Map<String, ?> map) {
        Integer restaurantId = (Integer) map.get("restaurantId");
        Integer rating = (Integer) map.get("rating");

        reviewService.updateRateReview(restaurantId, rating);

        System.out.println("Received rating: Restaurant ID= " + restaurantId + ", Rating= " + rating);
    }
}
