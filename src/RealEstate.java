public class RealEstate {
    private String name;
    private String type; // "Start", "Property", "Tax", "Reward"
    private double purchasePrice;
    private double rentPrice;
    private Player owner;

    public RealEstate(String name, String type, double purchasePrice, double rentPrice) {
        this.name = name;
        this.type = type;
        this.purchasePrice = purchasePrice;
        this.rentPrice = rentPrice;
        owner = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }
}

