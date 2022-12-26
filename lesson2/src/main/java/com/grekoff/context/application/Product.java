package com.grekoff.context.application;

public class Product {
    private Long id;
    private String title;
    private Float cost;


    public Product(Long id, String title, Float cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Product() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Float getCost() {
        return cost;
    }


    @Override
    public String toString() {
        String strProduct = getId() + " " +  getTitle() + " " +  getCost();
        return strProduct;
    }
}
