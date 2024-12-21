package edu.miu.cs.cs544.oderdene.restaurant.repository;

import edu.miu.cs.cs544.oderdene.restaurant.entity.Customer;
import edu.miu.cs.cs544.oderdene.restaurant.entity.Restaurant;
import edu.miu.cs.cs544.oderdene.restaurant.entity.Review;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByCustomerId(Integer customerId);
    List<Review> findByRestaurantId(Integer restaurantId);

    boolean existsByCustomerAndRestaurant(Customer customer, Restaurant restaurant);

    Optional<Review> findByCustomerAndRestaurant(Customer customer, Restaurant restaurant);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Review r WHERE r.id = :reviewId AND r.customer.id = :customerId")
    Optional<Review> findByIdAndCustomerId(@Param("reviewId") Integer reviewId, @Param("customerId") Integer customerId);
}
