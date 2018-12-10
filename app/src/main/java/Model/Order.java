package Model;

import java.util.List;

public class Order {


    private List<Item> allItems;
    private Promotion discounts;
    private Double shippingCost;
    private Double total;
    private String shippingAddress;
    private String billingAddress;

    public Order(){}

    public Order(List<Item> items, Promotion promo, Double shippingCost, Double total, String shipping, String billing){
        this.allItems = items;
        this.discounts = promo;
        this.shippingCost = shippingCost;
        this.total = total;
        this.shippingAddress = shipping;
        this.billingAddress = billing;
    }

    public Order(List<Item> items, Double shippingCost, Double total, String shipping, String billing){
        this.allItems = items;
        this.discounts = null;
        this.shippingCost = shippingCost;
        this.total = total;
        this.shippingAddress = shipping;
        this.billingAddress = billing;
    }

    public List<Item> getAllItems() {
        return allItems;
    }

    public void setAllItems(List<Item> allItems) {
        this.allItems = allItems;
    }

    public Promotion getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Promotion discounts) {
        this.discounts = discounts;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
}