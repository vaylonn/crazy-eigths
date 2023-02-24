//This game has been created by @vaylonn in 2023

import java.util.*;

public class CrazyEights {
    // Constants for card suits and ranks
    private static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    // The deck of cards
    private List<Card> deck;

    // The stock pile
    public Stack<Card> stock;

    // The starter pile
    public Stack<Card> starter;

    // The players
    private List<Player> players;

    // The current player
    private int currentPlayer;

    // The number of players
    private int numPlayers;

    Scanner input = new Scanner(System.in);

    public CrazyEights() {
        // Print the welcome message
        System.out.println("-----");
        System.out.println("\nWelcome to the Crazy Eights card game!\n");
        
        // Prompt the user for the number of players
        while (true) {
            System.out.print("Enter the number of players (2-7): \n");
            numPlayers = input.nextInt();
            if (numPlayers >= 2 && numPlayers <= 7) {
                break;
            }
            System.out.println("Invalid number of players. Please enter a number between 2 and 7.");
        }

        // Initialize the deck of cards
        deck = new ArrayList<>();
        for (String suit : SUITS) {
            for (String rank : RANKS) {
                deck.add(new Card(suit, rank));
            }
        }

        // Shuffle the deck
        Collections.shuffle(deck);

        // Deal 5 cards to each player
        players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player("Player " + i));
            for (int j = 0; j < 5; j++) {
                players.get(i-1).addCard(deck.remove(0));
            }
        }

        // Initialize the stock pile
        stock = new Stack<>();
        for (Card card : deck) {
            stock.push(card);
        }

        // Initialize the starter pile
        starter = new Stack<>();
        starter.push(stock.pop());

         // Set the current player to a random player
        currentPlayer = new Random().nextInt(numPlayers);
        
        // Get the current player
        Player current = players.get(currentPlayer);
       
        System.out.println("\n" + current.getName() + " is the first player to start !\n");
        
    }
    

    
    public void play() {
        while (true) {
            // Get the current player
            Player current = players.get(currentPlayer);
            
            // Print the current state of the game
            System.out.println("\n\n\n\nStock: " + stock.size());
            System.out.println("Pile: " + starter.peek() + "\n");
            
            // Print the deck of the current player
            System.out.println(current.getName() + "'s deck: " +current.getCards()+"\n");
                
            // Ask the current player for their next move
            System.out.println("It's " + current.getName() + "'s turn. Please enter the card you want to play\nor press 'enter' to draw a card\n");
            
            Card card = current.play(starter.peek(), stock, current);

            while (true) {
                // Check if the current move is valid
                if (!isValidMove(card, current)) {
                    System.out.println("Invalid move. Please enter a valid card:\n");
                    card = current.play(starter.peek(), stock, current);
                } else {
                    break;
                }
                
            }

            // If the card is an 8, prompt the user to choose a suit
            if (card.getRank().equals("8")) {
                System.out.println("Eight played. Please choose a suit:\n(write it without mistakes and the first letter in capital: Clubs)\n");
                String suit = input.next();
                card.setSuit(suit);
            }

            // Remove the card from the player's deck and add it to the starter pile
            current.getCards().remove(card);
            starter.push(card);

            // Check if the current player has won
            if (current.getCards().isEmpty()) {
                System.out.println("\n" + current.getName() + " wins the game !");
                break;
            }

            // Check if the game is over
            if (stock.isEmpty() && current.getCards().isEmpty()) {
                System.out.println("\nThe game is over, no player can play a card.");
                break;
            }

            // Set the next player
            currentPlayer = (currentPlayer + 1) % numPlayers;
        }
    }

    // Method to check if the current move is valid
    public boolean isValidMove(Card card, Player current) {
        if (card == null) {
            System.out.println("Invalid move. Card can't be null. Please enter a valid card:");
            return false;
        } 
        // Check if the card has the same rank or suit, or is an 8
        if (card.getSuit().equals(starter.peek().getSuit()) || card.getRank().equals(starter.peek().getRank()) || card.getRank().equals("8")) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        CrazyEights game = new CrazyEights();
        game.play();
    }

}
