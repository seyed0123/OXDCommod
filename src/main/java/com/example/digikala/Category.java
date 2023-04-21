package com.example.digikala;

import java.util.UUID;

abstract class Category extends Product{
    private String title;

    public Category(String name, int price, int amount, String description, String brand, UUID sellerID, String title) {
        super(name, price, amount, description, brand, sellerID);
        this.title = title;
    }

    @Override
        public String toString() {
        return super.toString()+
                " Category{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
