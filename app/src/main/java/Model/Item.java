package Model;

import android.media.Image;

public class Item {

    private String name;
    private String description;
    private String price;
    private Image image;


    public Item(String name, String description, String price){
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

    public void setPrice(String price) {
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

    public String getPrice() {
        return price;
    }
}
