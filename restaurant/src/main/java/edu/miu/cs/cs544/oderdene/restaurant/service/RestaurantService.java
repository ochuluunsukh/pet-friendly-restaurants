package edu.miu.cs.cs544.oderdene.restaurant.service;

import edu.miu.cs.cs544.oderdene.restaurant.entity.Restaurant;
import edu.miu.cs.cs544.oderdene.restaurant.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.oderdene.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Integer id, Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        restaurant.setName(updatedRestaurant.getName());
        restaurant.setRating(updatedRestaurant.getRating());
        restaurant.setPetPolicyDetails(updatedRestaurant.getPetPolicyDetails());
        restaurant.setPetMenuAvailable(updatedRestaurant.isPetMenuAvailable());
        restaurant.setHasPetPlayArea(updatedRestaurant.isHasPetPlayArea());
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Integer id) {
        restaurantRepository.deleteById(id);
    }

    public List<Restaurant> searchRestaurants(String name, String street, String city, String state, String zipCode, Boolean petMenuAvailable, Boolean hasPetPlayArea) {
        return restaurantRepository.searchRestaurants(name, street, city, state, zipCode, petMenuAvailable, hasPetPlayArea);
    }
}
