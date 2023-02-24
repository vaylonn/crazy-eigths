import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Player {
    //Player name
    private String name;
    //List of cards in hand
    private List<Card> cards;

    //constructor 
    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    //Get the name of player
    public String getName() {
        return name;
    }

    //Get the cards in hand
    public List<Card> getCards() {
        return cards;
    }

    //Add card to the hand
    public void addCard(Card card) {
        cards.add(card);
    }

    //Draw a card from stock pile
    public void drawCard(Stack<Card> stock) {
            addCard(stock.pop());
    }

    //play a card from hand
    public Card play(Card starter, Stack<Card> stock, Player current) {
        Scanner input = new Scanner(System.in);
        while (true) {
            String cardString = input.nextLine();
            if (cardString.isEmpty()) {
                if(stock.isEmpty()) {
                    //If stock pile is empty, player can't draw a card
                    System.out.println("The stock pile is empty, you can't draw a card.");
                }else {
                    System.out.println("You draw a card.");
                    System.out.println("\nStock: " + stock.size());
                    System.out.println("Pile: " + starter + "\n");
                    System.out.println("Your deck: " + current.getCards() + "\n");
                    drawCard(stock);
                }
                continue;
            }
            //iterate through cards
            for (Card card : cards) {
                if (card.toString().equalsIgnoreCase(cardString)) {
                    //remove card from hand and return
                    cards.remove(card);
                    return card;
                }
            }
            //card not found
            System.out.println("Invalid card. Please enter a card you have in your hand:\n");
        }
    }

    //Player score
    private int score;
    public int getScore() {
        return score;
    }

    //to string
    @Override
    public String toString() {
        return name + ": " + cards;
    }
}