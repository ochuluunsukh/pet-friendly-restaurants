package com.lesson.cs544.odkoo.backendformanagements.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Embedded
    private Address address;
    private String phone;
    private String website;
    private Boolean is_pet_friendly;

    @ManyToMany
    private List<Category> category;

    protected Restaurant() {}

    public Restaurant(String name, Address address, String phone, String website, Boolean is_pet_friendly) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.is_pet_friendly = is_pet_friendly;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Boolean getIs_pet_friendly() {
        return is_pet_friendly;
    }

    public void setIs_pet_friendly(Boolean is_pet_friendly) {
        this.is_pet_friendly = is_pet_friendly;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", is_pet_friendly=" + is_pet_friendly +
                '}';
    }
}
