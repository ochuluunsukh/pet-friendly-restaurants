package edu.miu.cs.cs544.oderdene.restaurant.repository;

import edu.miu.cs.cs544.oderdene.restaurant.entity.Restaurant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantSearchCriteriaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Restaurant> searchRestaurants(String name, String street, String city, String state, String zipCode, Boolean petMenuAvailable, Boolean hasPetPlayArea) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurant> cq = cb.createQuery(Restaurant.class);
        Root<Restaurant> restaurant = cq.from(Restaurant.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            predicates.add(cb.like(cb.lower(restaurant.get("name")), "%" + name.toLowerCase() + "%"));
        }
        if (street != null) {
            predicates.add(cb.like(cb.lower(restaurant.get("address").get("street")), "%" + street.toLowerCase() + "%"));
        }
        if (city != null) {
            predicates.add(cb.like(cb.lower(restaurant.get("address").get("city")), "%" + city.toLowerCase() + "%"));
        }
        if (state != null) {
            predicates.add(cb.like(cb.lower(restaurant.get("address").get("state")), "%" + state.toLowerCase() + "%"));
        }
        if (zipCode != null) {
            predicates.add(cb.like(cb.lower(restaurant.get("address").get("zipCode")), "%" + zipCode.toLowerCase() + "%"));
        }
        if (petMenuAvailable != null) {
            predicates.add(cb.equal(restaurant.get("isPetMenuAvailable"), petMenuAvailable));
        }
        if (hasPetPlayArea != null) {
            predicates.add(cb.equal(restaurant.get("hasPetPlayArea"), hasPetPlayArea));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(cq).getResultList();
    }
}
