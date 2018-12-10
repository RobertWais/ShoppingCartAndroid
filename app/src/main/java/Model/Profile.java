package Model;

public class Profile {

    private String shippingAddress;
    private String billingAddress;
    private String creditCard;

    public Profile(){}

    public Profile(String ship, String bill, String creditCard){
        this.shippingAddress = ship;
        this.billingAddress = bill;
        this.creditCard = creditCard;
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

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
