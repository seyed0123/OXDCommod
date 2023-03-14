package com.example.digikala;

public class fragrance extends Cosmetic{
    private String gender;
    private String smell;
    private String perfumer;

    public fragrance(String name, int price, int amount, String comment, String brand, String type, String brandCountry, boolean isOrganic, String gender, String smell, String perfumer) {
        super(name, price, amount, comment, brand, type, brandCountry, isOrganic);
        this.gender = gender;
        this.smell = smell;
        this.perfumer = perfumer;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSmell() {
        return smell;
    }

    public void setSmell(String smell) {
        this.smell = smell;
    }

    public String getPerfumer() {
        return perfumer;
    }

    public void setPerfumer(String perfumer) {
        this.perfumer = perfumer;
    }

    @Override
    public String toString() {
        return super.toString()+
                "fragrance{" +
                "gender='" + gender + '\'' +
                ", smell='" + smell + '\'' +
                ", perfumer='" + perfumer + '\'' +
                '}';
    }
}
