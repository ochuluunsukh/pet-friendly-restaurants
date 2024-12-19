package edu.miu.cs.cs544.oderdene.restaurant.controller;

import edu.miu.cs.cs544.oderdene.restaurant.entity.Favorite;
import edu.miu.cs.cs544.oderdene.restaurant.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    // GET all favorites for a customer
    @GetMapping("/{customerId}")
    public ResponseEntity<List<Favorite>> getFavorites(@PathVariable Integer customerId) {
        return new ResponseEntity<>(favoriteService.getFavoritesByCustomerId(customerId), HttpStatus.OK);
    }

    // POST - Add a favorite restaurant for a customer
    @PostMapping
    public ResponseEntity<Favorite> addFavorite(@RequestParam Integer customerId, @RequestParam Integer restaurantId) {
        return new ResponseEntity<>(favoriteService.addFavorite(customerId, restaurantId), HttpStatus.CREATED);
    }

    // DELETE - Remove a favorite restaurant for a customer
    @DeleteMapping
    public ResponseEntity<Void> removeFavorite(@RequestParam Integer customerId, @RequestParam Integer restaurantId) {
        favoriteService.removeFavorite(customerId, restaurantId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
