import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Monopoly {
    private Board board = new Board();
    private ArrayList<Player> players = new ArrayList<>(6);
    private double salary = 0; // Salary for passing "Start"
    private int maxRounds = 0;
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    public void setMaxRounds(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    //REAL ESTATE

    //automatic
    public void addRealEstate(String name, String type, double purchasePrice, double rentPrice) {
        board.insert(new RealEstate(name, type, purchasePrice, rentPrice));
    }
    //manual
    public void createRealEstate() {
        System.out.println("Enter the name of the real estate: ");
        String name = scanner.nextLine();

        System.out.println("Enter the type of the real estate (Property, Tax, Reward): ");
        String type = scanner.nextLine();

        System.out.println("Enter the purchase price of the real estate: ");
        double purchasePrice = scanner.nextDouble();

        System.out.println("Enter the rent price of the real estate: ");
        double rentPrice = scanner.nextDouble();

        //assigns and add the new one
        if (type.equals("Tax") || type.equals("Property") || type.equals("Reward")){
            RealEstate newRealEstate = new RealEstate(name, type, purchasePrice, rentPrice);
            board.insert(newRealEstate);
            //feedback
            System.out.println("Real estate " + name + " added to the board.");
        } else {
            System.out.println("Type not found, property not created");
        }

    }
    //update
    public void updateRealEstate() {
        System.out.println("Enter the name of the RealEstate to update: ");
        String name = scanner.nextLine();

        Node node = board.getPropertyByName(name);
        if (node != null) {
            RealEstate realEstate = node.getProperties();

            System.out.println("Updating real estate: " + realEstate.getName());
            System.out.println("Enter new name (leave blank to keep): ");
            String newName = scanner.nextLine();
            if (!newName.isBlank()) {
                realEstate.setName(newName);
            }

            System.out.println("Enter new type (leave blank to keep): ");
            String newType = scanner.nextLine();
            if (!newType.isBlank()) {
                realEstate.setType(newType);
            }

            System.out.println("Enter new purchase price (or 0 to keep): ");
            double newPurchasePrice = scanner.nextDouble();
            if (newPurchasePrice > 0) {
                realEstate.setPurchasePrice(newPurchasePrice);
            }

            System.out.println("Enter new rent price (or 0 to keep): ");
            double newRentPrice = scanner.nextDouble();
            if (newRentPrice > 0) {
                realEstate.setRentPrice(newRentPrice);
            }

            scanner.nextLine();
            System.out.println("Real estate updated successfully.");
        } else {
            System.out.println("Real estate not found!");
        }
    }
    // remove real estate
    public void removeRealEstate() {
        System.out.println("Enter the name of the real estate to remove: ");
        String name = scanner.nextLine();

        boolean removed = board.removeByName(name);
        if (removed) {
            System.out.println("Real estate " + name + " removed from the board.");

        } else {
            System.out.println("Real estate not found!");
        }
    }
    // list properties
    public void listProperties() {
        board.list();
        System.out.println("Number of properties: "+board.getSize());
    }


    //PLAYERS

    //automatic
    public void addPlayer(String name) {
        players.add(new Player(name));
    }
    //manual
    public void createPlayer() {
        System.out.println("Enter the name of the player: ");
        String name = scanner.nextLine();

        scanner.nextLine();
        Player newPlayer = new Player(name);
        players.add(newPlayer);
        System.out.println("Player " + name + " added to the game.");
    }
    //list players
    public void listPlayers() {
        if (players.isEmpty()) {
            System.out.println("\nNo players are registered.");
        } else {
            System.out.println("List of players:");
            for (Player player : players) {
                System.out.println("Name: " + player.getName() + ", Balance: $" + player.getBalance());
            }
            System.out.println("\nNumber of players: "+players.size());
        }
    }
    // update player
    public void updatePlayer() {
        System.out.println("Enter the name of the player to update: ");
        String name = scanner.nextLine();

        Player playerToUpdate = null;
        for (Player player : players) {
            if (player.getName().equals(name)) {
                playerToUpdate = player;
                break;
            }
        }

        if (playerToUpdate != null) {
            System.out.println("Updating player: " + playerToUpdate.getName());
            System.out.println("Enter new name (leave blank to keep): ");
            String newName = scanner.nextLine();
            if (!newName.trim().isEmpty()) {
                playerToUpdate.setName(newName);
            }

            System.out.println("Enter new balance (or -1 to keep): ");
            double newBalance = scanner.nextDouble();
            if (newBalance != -1) {
                playerToUpdate.setBalance(newBalance);
            }

            scanner.nextLine(); // Consome o caractere de nova linha
            System.out.println("Player updated successfully.");
        } else {
            System.out.println("Player not found!");
        }
    }
    // remove player
    public void removePlayer() {
        System.out.println("Enter the name of the player to remove: ");
        String name = scanner.nextLine();

        Player playerToRemove = null;
        for (Player player : players) {
            if (player.getName().equals(name)) {
                playerToRemove = player;
                break;
            }
        }

        if (playerToRemove != null) {
            players.remove(playerToRemove);
            System.out.println("Player " + name + " removed from the game.");
        } else {
            System.out.println("Player not found!");
        }
    }


    //INITIAL CONFIGS

    //define initial balance
    public void initialBalancePlayers() {
        System.out.println("\nEnter the initial balance of all players: ");
        double initialBalance = scanner.nextDouble();

        if (initialBalance > 0) {
            for (Player p : players) {
                p.setBalance(initialBalance);
            }
        } else {
            System.out.println("Invalid value");
        }
    }
    //define max rounds
    public void maxRounds() {
        System.out.println("Set the maximum number of game rounds: ");
        int maxRounds = scanner.nextInt();

        if (maxRounds > 0) {
            //setMaxRounds(maxRounds);
            this.maxRounds = maxRounds;
        } else {
            System.out.println("Invalid value");
        }
    }
    //define salary
    public void setSalary(){
        System.out.println("Set the salary of all players: ");
        double salary = scanner.nextDouble();

        if (salary > 0){
            this.salary = salary;
        } else {
            System.out.println("Invalid value");
        }
    }


    //GAME

    public void movePlayer(Player player) {
        // Roll the dice (1 a 6)
        int dice = random.nextInt(6) + 1;
        System.out.println(player.getName() + " rolled a " + dice);

        if (player.getPosition() + dice > board.getSize()){
            player.setBalance(player.getBalance() + salary);
            System.out.println("Salary paid");
        }

        // Move to new position
        int newPosition = (player.getPosition() + dice) % board.getSize();
        player.setPosition(newPosition);


        // Locate the current player position
        Node currentNode = board.getPropertyByPosition(newPosition);
        RealEstate currentSpace = currentNode.getProperties();
        System.out.println(player.getName() + " landed on: " + currentSpace.getName());

        // Verify the type of property
        switch (currentSpace.getType()) {
            case "Property":
                if (currentSpace.getOwner() == null) {
                    // info
                    System.out.println("\nThis property is available for purchase:");
                    System.out.println("- Name: " + currentSpace.getName());
                    System.out.println("- Purchase Price: $" + currentSpace.getPurchasePrice());
                    System.out.println("- Rent Price: $" + currentSpace.getRentPrice());
                    System.out.println("\nYour current balance: $" + player.getBalance());

                    // ask
                    System.out.print("Do you want to buy this property? (yes/no): ");
                    String choice = scanner.nextLine().trim().toLowerCase();

                    if (choice.equals("yes")) {
                        if (player.getBalance() >= currentSpace.getPurchasePrice()) {
                            player.getProperties().add(currentSpace);
                            currentSpace.setOwner(player);
                            player.setBalance(player.getBalance() - currentSpace.getPurchasePrice());
                            System.out.println("Congratulations! You bought " + currentSpace.getName() + " for $" + currentSpace.getPurchasePrice());
                        } else {
                            System.out.println("You don't have enough balance to buy this property.");
                        }
                    } else {
                        System.out.println("You chose not to buy the property.");
                    }
                } else if (currentSpace.getOwner() != player) {
                    // Pay rent
                    player.setBalance(player.getBalance() - currentSpace.getRentPrice());
                    currentSpace.getOwner().setBalance(currentSpace.getOwner().getBalance() + currentSpace.getRentPrice());
                    System.out.println(player.getName() + " paid rent of $" + currentSpace.getRentPrice() + " to " + currentSpace.getOwner().getName());
                }
                break;

            case "Tax":
                double tax = player.getBalance() * 0.05;
                player.setBalance(player.getBalance() - tax);
                System.out.println(player.getName() + " paid tax of $" + tax);
                break;

            case "Reward":
                double reward = salary * 0.1;
                player.setBalance(player.getBalance() + reward);
                System.out.println(player.getName() + " received a reward of $" + reward);
                break;
        }
    }

    public void startGame() {
        if (players.size() < 2) {
            System.out.println("Cannot start the game. At least 2 players are required.");
            return;
        }

        if (board.getSize() < 10) {
            System.out.println("Cannot start the game. The board must have at least 10 real estate.");
            return;
        }

        System.out.println("\n -- Game is starting! --");

        for (int round = 1; round <= maxRounds; round++) {
            System.out.println("\n--- Round " + round + " ---");

            for (Player player : players) {
                movePlayer(player);
            }
        }

        // End the game and shows the winner
        endGame();
    }
    // end the game
    public void endGame() {
        Player winner = null;
        for (Player player : players) {
            if (winner == null || player.getBalance() > winner.getBalance()) {
                winner = player;
            }
        }

        System.out.println("\n--- Game Over ---");
        System.out.println("Winner: " + winner.getName() + " with a balance of $" + winner.getBalance());
    }
    //CONFIG GAME
    public void configGame(){
        System.out.println("\n-- Configure Match --\n");

        int option;
        do {
            System.out.println("- Property registration (maximum: 40) -\n");
            System.out.println("1. Add a new property");
            System.out.println("2. List all properties");
            System.out.println("3. Update property information");
            System.out.println("4. Remove a property");
            System.out.println("5. Exit");
            System.out.print("\nChoose an option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createRealEstate();
                    break;
                case 2:
                    listProperties();
                    break;
                case 3:
                    updateRealEstate();
                    break;
                case 4:
                    removeRealEstate();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);

        int option1;
        do {
            System.out.println("\n- Player registration (maximum: 6) -\n");
            System.out.println("1. Register a new player");
            System.out.println("2. List all players");
            System.out.println("3. Update player information");
            System.out.println("4. Remove a player");
            System.out.println("5. Exit");
            System.out.print("\nChoose an option: ");

            option1 = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (option1) {
                case 1:
                    createPlayer();
                    break;
                case 2:
                    listPlayers();
                    break;
                case 3:
                    updatePlayer();
                    break;
                case 4:
                    removePlayer();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option1 != 5);

        initialBalancePlayers();
        maxRounds();
        setSalary();
    }
}