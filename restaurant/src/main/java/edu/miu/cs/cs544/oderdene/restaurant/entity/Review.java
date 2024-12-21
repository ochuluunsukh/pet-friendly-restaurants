package edu.miu.cs.cs544.oderdene.restaurant.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
class RatingConverter implements AttributeConverter<Rating, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Rating rating) {
        if (rating == null) {
            return null;
        }
        return rating.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(Integer dbValue) {
        if (dbValue == null) {
            return null;
        }
        return Rating.fromValue(dbValue);
    }
}

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(length = 1000)
    private String text;

    @Convert(converter = RatingConverter.class)
    private Rating rating;

    @Column(length = 500)
    private String petSpecificNotes;

    @Version
    private Integer version;

    protected Review() {}

    public Review(Customer customer, Restaurant restaurant, String text, Rating rating, String petSpecificNotes) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.text = text;
        this.rating = rating;
        this.petSpecificNotes = petSpecificNotes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating.getValue();
    }

    public void setRating(Rating rating) {
        if (rating == null) {
            throw new IllegalArgumentException("Rating cannot be null.");
        }
        this.rating = rating;
    }

    public String getPetSpecificNotes() {
        return petSpecificNotes;
    }

    public void setPetSpecificNotes(String petSpecificNotes) {
        this.petSpecificNotes = petSpecificNotes;
    }

    @Override
    public String toString() {
        return "Review{" +
                "customer=" + customer +
                ", restaurant=" + restaurant +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                ", petSpecificNotes='" + petSpecificNotes + '\'' +
                '}';
    }
}
