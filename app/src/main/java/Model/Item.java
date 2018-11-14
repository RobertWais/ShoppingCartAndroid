package Model;

import android.media.Image;

public class Item {

    private String name;
    private String description;
    private Double price;
    private int quantity;
    private Image image;
    private String key;


    public Item(){}


    public Item(String name, String description, Double price){
        this.name = name;
        this.description = description;
        this.price = price;
        this.key = "";
    }

    public Item(String name, String description, Double price, int quantity){
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.key = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImage(Image image) {this.image = image;}

    public void setQuantity(int num) {this.quantity = num;}

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price; }

    public int getQuantity() {
        return quantity;
    }

    public void setKey(String key){this.key = key;}
    public String getKey(){return this.key;}
}
