package com.example.digikala;

import java.util.UUID;

public class SubCategory extends Category{
    private String subTitle;

    public SubCategory(String name, int price, int amount, String description, String brand, UUID sellerID, String title, String subTitle) {
        super(name, price, amount, description, brand, sellerID, title);
        this.subTitle = subTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @Override
    public String toString() {
        return super.toString()+
                "  SubCategory{" +
                "subTitle='" + subTitle + '\'' +
                '}';
    }
}
