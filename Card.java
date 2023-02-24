//This game has been created by @vaylonn in 2023

public class Card {
    // private fields to store the suit and rank of the card
    private String suit;
    private String rank;

    // constructor method to initialize a card with a suit and rank
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // getter method for the suit field
    public String getSuit() {
        return suit;
    }

    // setter method for the suit field
    public void setSuit(String suit) {
        this.suit = suit;
    }

    // getter method for the rank field
    public String getRank() {
        return rank;
    }

    // setter method for the rank field
    public void setRank(String rank) {
        this.rank = rank;
    }

    // override of the toString method to provide a string representation of the card
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
