package com.example.digikala;

abstract class Book extends Product{

    private int yearOfPublish;
    private String publisher;
    private int pages;
    private String language;

    public Book(String name, int price, int amount, String comment, String brand, int yearOfPublish, String publisher, int pages, String language) {
        super(name, price, amount, comment, brand);
        this.yearOfPublish = yearOfPublish;
        this.publisher = publisher;
        this.pages = pages;
        this.language = language;
    }

    public int getYearOfPublish() {
        return yearOfPublish;
    }

    public void setYearOfPublish(int yearOfPublish) {
        this.yearOfPublish = yearOfPublish;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Book{" +
                "yearOfPublish=" + yearOfPublish +
                ", publisher='" + publisher + '\'' +
                ", pages=" + pages +
                ", language='" + language + '\'' +
                '}';
    }
}
