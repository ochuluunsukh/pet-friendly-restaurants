package edu.miu.cs.cs544.oderdene.rating.service;

import edu.miu.cs.cs544.oderdene.rating.entity.RateReview;
import edu.miu.cs.cs544.oderdene.rating.jms.Sender;
import edu.miu.cs.cs544.oderdene.rating.repository.RateReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RateReviewService {
    @Autowired
    private RateReviewRepository rateReviewRepository;

    @Autowired
    private Sender sender;

    public void updateRateReview(Integer id, Integer rating) {
        Optional<RateReview> ro = rateReviewRepository.findById(id);

        if (ro.isPresent()) {
            RateReview rate = ro.get();
            Integer total = rate.getTotalRating();
            Integer count = rate.getCount();

            Integer reviewRating = (total + rating) / (count + 1);

            rate.setTotalRating(total+rating);
            rate.setCount(count+1);
            rate.setRating(reviewRating);

            rateReviewRepository.save(rate);

            // send rate
            sender.sendRate(rate);
        } else {
            RateReview rate = new RateReview(id, rating, 1, rating);
            rateReviewRepository.save(rate);
            // send rate
            sender.sendRate(rate);
        }
    }
}
