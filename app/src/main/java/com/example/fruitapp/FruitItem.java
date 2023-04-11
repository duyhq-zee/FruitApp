package com.example.fruitapp;

public class FruitItem {
    private String name;
    private String family;
    private int calories;
    private int sugar;
    private int carbohydrates;

    public FruitItem(String name, String family, int calories, int sugar, int carbohydrates) {
        this.name = name;
        this.family = family;
        this.calories = calories;
        this.sugar = sugar;
        this.carbohydrates = carbohydrates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }
}