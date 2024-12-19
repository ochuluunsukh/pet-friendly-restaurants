package edu.miu.cs.cs544.oderdene.restaurant.repository;

import edu.miu.cs.cs544.oderdene.restaurant.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByCustomerId(Integer customerId);
    Optional<Favorite> findByCustomerIdAndRestaurantId(Integer customerId, Integer restaurantId);
}
