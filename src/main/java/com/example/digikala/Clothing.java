package com.example.digikala;

abstract class Clothing extends Product{
    private String color;
    private String material;
    private String size;
    private String style;

    public Clothing(String name, int price, int amount, String comment, String brand, String color, String material, String size, String style) {
        super(name, price, amount, comment, brand);
        this.color = color;
        this.material = material;
        this.size = size;
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Clothing{" +
                "color='" + color + '\'' +
                ", material='" + material + '\'' +
                ", size='" + size + '\'' +
                ", style='" + style + '\'' +
                '}';
    }
}
