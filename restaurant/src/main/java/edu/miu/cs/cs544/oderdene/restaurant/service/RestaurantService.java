package edu.miu.cs.cs544.oderdene.restaurant.service;

import edu.miu.cs.cs544.oderdene.restaurant.repository.RestaurantSearchCriteriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import edu.miu.cs.cs544.oderdene.restaurant.entity.Restaurant;
import edu.miu.cs.cs544.oderdene.restaurant.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.oderdene.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantSearchCriteriaRepository restaurantSearchCriteriaRepository;

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

    // it used for MOM
    public void updateRating(Integer id, Integer rating, Integer numberOfRatings) {
        Restaurant res = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        res.setRating(rating);
        res.setNumberOfRatings(numberOfRatings);
        restaurantRepository.save(res);
    }

    public void deleteRestaurant(Integer id) {
        restaurantRepository.deleteById(id);
    }

    public List<Restaurant> searchRestaurants(String name, String street, String city, String state, String zipCode, Boolean petMenuAvailable, Boolean hasPetPlayArea) {
        return restaurantSearchCriteriaRepository.searchRestaurants(name, street, city, state, zipCode, petMenuAvailable, hasPetPlayArea);
    }

    public List<Restaurant> getRestaurantsByName(String name) {
        return restaurantRepository.findByName(name);
    }
}
