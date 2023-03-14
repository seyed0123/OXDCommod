package com.example.digikala;

public class FictionBook extends Book{
    private String genre;
    private String authorsCredentials;
    private String author;

    public FictionBook(String name, int price, int amount, String comment, String brand, int yearOfPublish, String publisher, int pages, String language, String genre, String authorsCredentials, String author) {
        super(name, price, amount, comment, brand, yearOfPublish, publisher, pages, language);
        this.genre = genre;
        this.authorsCredentials = authorsCredentials;
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthorsCredentials() {
        return authorsCredentials;
    }

    public void setAuthorsCredentials(String authorsCredentials) {
        this.authorsCredentials = authorsCredentials;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return super.toString()+
                "FictionBook{" +
                "genre='" + genre + '\'' +
                ", authorsCredentials='" + authorsCredentials + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
