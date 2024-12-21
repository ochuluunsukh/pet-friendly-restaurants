package edu.miu.cs.cs544.oderdene.rating.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class RateReview {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer restaurantId;
    private Integer rating;
    private Integer count;
    private Integer totalRating;

    protected RateReview() {}

    public RateReview(int restaurantId, int rating, int count, int totalRating) {
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.count = count;
        this.totalRating = totalRating;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(Integer totalRating) {
        this.totalRating = totalRating;
    }

    @Override
    public String toString() {
        return "RateReview{" +
                "restaurantId=" + restaurantId +
                ", rating=" + rating +
                ", count=" + count +
                ", totalRating=" + totalRating +
                '}';
    }
}
