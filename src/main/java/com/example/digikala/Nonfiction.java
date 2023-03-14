package com.example.digikala;

public class Nonfiction extends Book{
    private String topic;

    public Nonfiction(String name, int price, int amount, String comment, String brand, int yearOfPublish, String publisher, int pages, String language, String topic) {
        super(name, price, amount, comment, brand, yearOfPublish, publisher, pages, language);
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
