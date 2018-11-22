package com.example.jayeshdk.receivedatabase;

public class Data {
    private String name;
    private int calories;
    private Double fat;

    public Data(String name,int calories,double fat)
    {
        this.setName(name);
        this.setCalories(calories);
        this.setFat(fat);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }
}

