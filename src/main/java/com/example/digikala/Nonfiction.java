package com.example.digikala;

import java.util.UUID;

public class Nonfiction extends Book{
    private String topic;

    public Nonfiction(String name, int price, int amount, String comment, String brand, UUID sellerID, int yearOfPublish, String publisher, int pages, String language, String topic) {
        super(name, price, amount, comment, brand, sellerID, yearOfPublish, publisher, pages, language);
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Nonfiction{" +
                "topic='" + topic + '\'' +
                '}';
    }
}
