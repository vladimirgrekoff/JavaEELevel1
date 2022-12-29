package com.grekoff.lesson4.data;

public class Product {
    private Integer index;
    private Long id;
    private String title;
    private Float cost;


    public Product(Integer index, Long id, String title, Float cost) {
        this.index = index;
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Product() {
    }

    public void setIndex(Integer index) {
        this.index = index;
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

    public Integer getIndex() {
        return index;
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



}
