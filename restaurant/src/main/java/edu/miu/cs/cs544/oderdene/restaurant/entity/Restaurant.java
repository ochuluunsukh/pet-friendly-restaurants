package edu.miu.cs.cs544.oderdene.restaurant.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "Restaurant.findByName", query = "SELECT r FROM Restaurant r WHERE r.name like '%:name%'")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private double rating;
    private Integer numberOfRatings;

    private boolean isPetMenuAvailable;
    private boolean hasPetPlayArea;

    @Column(length = 500)
    private String petPolicyDetails;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Embedded
    private Address address;

    protected Restaurant() {}
    public Restaurant(String name, double rating, int numberOfRatings, boolean isPetMenuAvailable, boolean hasPetPlayArea, String petPolicyDetails, Address address) {
        this.name = name;
        this.rating = rating;
        this.numberOfRatings = numberOfRatings;
        this.isPetMenuAvailable = isPetMenuAvailable;
        this.hasPetPlayArea = hasPetPlayArea;
        this.petPolicyDetails = petPolicyDetails;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }
    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }
    public boolean isPetMenuAvailable() {
        return isPetMenuAvailable;
    }
    public void setPetMenuAvailable(boolean petMenuAvailable) {
        isPetMenuAvailable = petMenuAvailable;
    }
    public boolean isHasPetPlayArea() {
        return hasPetPlayArea;
    }
    public void setHasPetPlayArea(boolean hasPetPlayArea) {
        this.hasPetPlayArea = hasPetPlayArea;
    }
    public String getPetPolicyDetails() {
        return petPolicyDetails;
    }
    public void setPetPolicyDetails(String petPolicyDetails) {
        this.petPolicyDetails = petPolicyDetails;
    }
//    public List<Review> getReviews() {
//        return reviews;
//    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", isPetMenuAvailable=" + isPetMenuAvailable +
                ", hasPetPlayArea=" + hasPetPlayArea +
                ", petPolicyDetails='" + petPolicyDetails + '\'' +
                ", reviews=" + reviews +
                ", address=" + address +
                '}';
    }
}
