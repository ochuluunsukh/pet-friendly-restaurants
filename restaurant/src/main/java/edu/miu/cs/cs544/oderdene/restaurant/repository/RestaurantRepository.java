package edu.miu.cs.cs544.oderdene.restaurant.repository;

import edu.miu.cs.cs544.oderdene.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findByName(@Param("name") String name);

    @Query("SELECT r FROM Restaurant r WHERE " +
            "(:name IS NULL OR LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:street IS NULL OR LOWER(r.address.street) LIKE LOWER(CONCAT('%', :street, '%'))) AND " +
            "(:city IS NULL OR LOWER(r.address.city) LIKE LOWER(CONCAT('%', :city, '%'))) AND " +
            "(:state IS NULL OR LOWER(r.address.state) LIKE LOWER(CONCAT('%', :state, '%'))) AND " +
            "(:zipCode IS NULL OR LOWER(r.address.zipCode) LIKE LOWER(CONCAT('%', :zipCode, '%'))) AND " +
            "(:petMenuAvailable IS NULL OR r.isPetMenuAvailable = :petMenuAvailable) AND " +
            "(:hasPetPlayArea IS NULL OR r.hasPetPlayArea = :hasPetPlayArea)")
    List<Restaurant> searchRestaurants(
            @Param("name") String name,
            @Param("street") String street,
            @Param("city") String city,
            @Param("state") String state,
            @Param("zipCode") String zipCode,
            @Param("petMenuAvailable") Boolean petMenuAvailable,
            @Param("hasPetPlayArea") Boolean hasPetPlayArea);

}
