package Model;

public class Promotion {
    private String name;
    private String startDate;
    private String endDate;
    private Double amount;
    private String itemID;


    public Promotion(String name, String startDate, String endDate, Double amount, String itemId){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.itemID = itemId;
    }


    public Promotion(){}

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getName() {

        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Double getAmount() {
        return amount;
    }

    public String getItemID() {
        return itemID;
    }
}
