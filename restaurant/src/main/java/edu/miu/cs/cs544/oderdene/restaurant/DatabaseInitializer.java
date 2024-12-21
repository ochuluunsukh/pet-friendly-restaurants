package edu.miu.cs.cs544.oderdene.restaurant;

import edu.miu.cs.cs544.oderdene.restaurant.entity.*;
import edu.miu.cs.cs544.oderdene.restaurant.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Add Customers
        Customer customer = new Customer("odkoo", "Od-Erdene", "Chuluunsukh", "ochuluunsukh@miu.edu", passwordEncoder.encode("123"), "ROLE_ADMIN");
        Customer customer1 = new Customer("john", "John", "Doe", "john.doe@example.com", passwordEncoder.encode("123"), "ROLE_USER");
        Customer customer2 = new Customer("jane", "Jane", "Smith", "jane.smith@example.com", passwordEncoder.encode("123"), "ROLE_USER");
        Customer customer3 = new Customer("jack", "Jack", "Westenberg", "jack.westenberg@example.com", passwordEncoder.encode("123"), "ROLE_USER");
        customerRepository.saveAll(List.of(customer, customer1, customer2, customer3));

        // Add Restaurants
        Address address1 = new Address("USA", "123 Main St", "Seattle", "WA", "98101");
        Address address2 = new Address("USA", "456 Elm St", "Portland", "OR", "97201");
        Restaurant restaurant1 = new Restaurant("Pet Grill", 5, 1, true, true, "", address1);
        Restaurant restaurant2 = new Restaurant("Cat Cafe", 4, 1, true, false, "", address2);
        restaurantRepository.saveAll(List.of(restaurant1, restaurant2));

        // Add Reviews
        Review review1 = new Review(customer1, restaurant1, "Great food and dog-friendly!", Rating.FIVE, "Dog-friendly patio");
        Review review2 = new Review(customer2, restaurant2, "Loved the cat-friendly vibe!", Rating.FOUR, "Perfect for cat lovers");
        reviewRepository.saveAll(List.of(review1));

        // Add Favorites
        Favorite favorite1 = new Favorite(customer1, restaurant1);
        Favorite favorite2 = new Favorite(customer2, restaurant2);
        favoriteRepository.saveAll(List.of(favorite1, favorite2));

        System.out.println("Database initialized with sample data.");
    }
}
