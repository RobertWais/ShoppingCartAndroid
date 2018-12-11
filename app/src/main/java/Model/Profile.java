package Model;

public class Profile {

    private String shippingAddress;
    private String shippingState;
    private String shippingCode;
    private String billingAddress;
    private String billingState;
    private String billingCode;
    private String creditCard;
    private String ccv;

    public Profile(){}

    public Profile(String ship, String bill, String creditCard, String shipState, String shipCode, String billState, String billCode, String cc){
        this.shippingAddress = ship;
        this.shippingState = shipState;
        this.shippingCode = shipCode;
        this.billingAddress = bill;
        this.billingState = billState;
        this.billingCode = billCode;
        this.creditCard = creditCard;
        this.ccv = cc;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }
    public String getShippingState() {
        return shippingState;
    }
    public String getShippingCode() {
        return shippingCode;
    }

    public String getBillingAddress() {
        return billingAddress;
    }
    public String getBillingState() {
        return billingState;
    }
    public String getBillingCode() {
        return billingCode;
    }

    public String getCreditCard() {
        return creditCard;
    }
    public String getCcv() {
        return ccv;
    }
}
