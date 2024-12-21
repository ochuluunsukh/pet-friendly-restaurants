package edu.miu.cs.cs544.oderdene.rating;

import edu.miu.cs.cs544.oderdene.rating.entity.RateReview;
import edu.miu.cs.cs544.oderdene.rating.repository.RateReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    @Autowired
    private RateReviewRepository rateReviewRepository;

    @Override
    public void run(String... args) throws Exception {
        // add rate review
        RateReview rateReview = new RateReview(1, 5, 1, 5);
        RateReview rateReview1 = new RateReview(1, 4, 1, 4);
        rateReviewRepository.saveAll(List.of(rateReview, rateReview1));
    }
}
