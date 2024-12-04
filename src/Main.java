public class Main {
    public static void main(String[] args) {
        Monopoly game = new Monopoly();


        //config default
        game.addRealEstate("Go", "Start", 0, 0);

        // Add properties to the board
        game.addRealEstate("Forest House", "Property", 2000, 110);
        game.addRealEstate("Central Apartment", "Property", 3600, 180);
        game.addRealEstate("Flower Villa", "Property", 4600, 220);
        game.addRealEstate("Luxury Tax", "Tax", 0, 0);
        game.addRealEstate("Beach Lodge", "Property", 5000, 270);
        game.addRealEstate("Hill Mansion", "Property", 6000, 330);
        game.addRealEstate("Lake Residence", "Property", 4500, 709);
        game.addRealEstate("Community Chest", "Reward", 0, 0);
        game.addRealEstate("Diamond Penthouse", "Property", 7030, 370);
        game.addRealEstate("Baltic Avenue", "Property", 6000, 600);
        game.addRealEstate("Water Works", "Property", 1500, 150);

        //add default players
        game.addPlayer("Lucas");
        game.addPlayer("Mariana");

        //config the game
        game.configGame();

        //game
        game.startGame();


    }
}
