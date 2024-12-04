import java.util.ArrayList;

public class Player {
    private String name;
    private double balance;
    private int position;
    private ArrayList<RealEstate> properties;

    public Player(String name) {
        this.name = name;
        this.balance = 0;
        properties = new ArrayList<>();
        position = 0;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<RealEstate> getProperties() {
        return properties;
    }

}
