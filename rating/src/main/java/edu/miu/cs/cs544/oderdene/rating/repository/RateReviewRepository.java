package edu.miu.cs.cs544.oderdene.rating.repository;

import edu.miu.cs.cs544.oderdene.rating.entity.RateReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateReviewRepository extends JpaRepository<RateReview, Integer> {
}
