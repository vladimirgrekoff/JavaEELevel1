package com.grekoff.hibernate.h2.data;


import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private int cost;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Product() {
    }

    public Product(String title) {
        this.title = title;
    }

    public Product(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

    public Product(Long id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public void print(){
        System.out.println("Product Id = " + id + "; Title = " + title + "; Cost = " + cost);
    }

    @Override
    public String toString() {
        return "Product{" + "id = " + id + "; Title = '" + title + "'"  + "; Cost = " + cost + "}";
    }
}
