package com.example.digikala;

public class Textbooks extends Book{
    private String subject;
    private String coverType;
    private String edition;

    public Textbooks(String name, int price, int amount, String comment, String brand, int yearOfPublish, String publisher, int pages, String language, String subject, String coverType, String edition) {
        super(name, price, amount, comment, brand, yearOfPublish, publisher, pages, language);
        this.subject = subject;
        this.coverType = coverType;
        this.edition = edition;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Textbooks{" +
                "subject='" + subject + '\'' +
                ", coverType='" + coverType + '\'' +
                ", edition='" + edition + '\'' +
                '}';
    }
}
