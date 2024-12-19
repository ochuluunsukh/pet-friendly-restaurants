package edu.miu.cs.cs544.oderdene.restaurant.service;

import edu.miu.cs.cs544.oderdene.restaurant.entity.Customer;
import edu.miu.cs.cs544.oderdene.restaurant.entity.Favorite;
import edu.miu.cs.cs544.oderdene.restaurant.entity.Restaurant;
import edu.miu.cs.cs544.oderdene.restaurant.exception.DuplicateResourceException;
import edu.miu.cs.cs544.oderdene.restaurant.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.oderdene.restaurant.repository.CustomerRepository;
import edu.miu.cs.cs544.oderdene.restaurant.repository.FavoriteRepository;
import edu.miu.cs.cs544.oderdene.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;


    public List<Favorite> getFavoritesByCustomerId(Integer customerId) {
        return favoriteRepository.findByCustomerId(customerId);
    }

    public Favorite addFavorite(Integer customerId, Integer restaurantId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        Optional<Favorite> existingFavorite = favoriteRepository.findByCustomerIdAndRestaurantId(customerId, restaurantId);

        if (existingFavorite.isPresent()) {
            throw new DuplicateResourceException("This restaurant is already in the customer's favorites.");
        }

        Favorite favorite = new Favorite();
        favorite.setCustomer(customer);
        favorite.setRestaurant(restaurant);
        return favoriteRepository.save(favorite);
    }

    public void removeFavorite(Integer customerId, Integer restaurantId) {
        Favorite favorite = favoriteRepository.findByCustomerIdAndRestaurantId(customerId, restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite not found"));
        favoriteRepository.delete(favorite);
    }
}
