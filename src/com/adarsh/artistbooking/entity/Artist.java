package com.adarsh.artistbooking.entity;

import com.adarsh.artistbooking.enums.Category;

public class Artist {
    private long id;
    private String name;
    private Category category;
    private int experience;
    private String city;
    private double expectedAmount;
    private double rating;

    public Artist (long id, String name, Category category, String  city, int experience, double expectedAmount
            ) {
        this.id = id;
        validateName(name);
            this.name = name;
        this.category = category;
        validateExperience(experience);
            this.experience = experience;
        validateName(city);
            this.city = city;
        validateExpectedAmount(expectedAmount);
            this.expectedAmount = expectedAmount;
        this.rating = 0.0;

    }

    // getters for artist
    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public int getExperience() {
        return experience;
    }

    public String getCity() {
        return city;
    }

    public double getRating() {
        return rating;
    }

    public double getExpectedAmount() {
        return expectedAmount;
    }

    // setters

    public void changeName(String name) {
        validateName(name);
           this.name = name;
    }
    public void changeCity(String city) {
        validateCity(city);
             this.city = city;
    }
    public void changeExperience(int experience) {

        this.experience = experience;
    }
    public void changeExpectedAmount(double expectedAmount) {
        validateExpectedAmount(expectedAmount);
            this.expectedAmount = expectedAmount;
    }
    public void changeCategory(Category category) {
        this.category = category;
    }
    public void validateName(String name){
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

    }
    public void validateCity(String city){

        if(city == null || city.trim().isEmpty())
        {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
    }
    private void validateExperience(int experience) {

        if (experience < 0) {
            throw new IllegalArgumentException(
                    "Experience cannot be negative");
        }

    }
    public void validateExpectedAmount(double expectedAmount){

            if (expectedAmount <= 0) {
                throw new IllegalArgumentException(
                        "Expected Amount cannot be negative or Zero");
            }

        }

}
