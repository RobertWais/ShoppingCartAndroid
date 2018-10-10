package Model;

import android.media.Image;

public class Item {

    private String name;
    private String description;
    private Double price;
    private Image image;


    public Item(String name, String description, Double price){
        this.name = name;
        this.description = description;
        this.price = price;

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
        return price;
    }
}
