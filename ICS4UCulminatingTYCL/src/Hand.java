import java.util.ArrayList;
import java.util.Collections;
/**
* Represents a player's hand of playing cards
* Provides options for adding, displaying and shuffling cards; removing duplicate 
* cards; getting the size of a player's hand; and drawing from an opponent
*/
public class Hand {
    // The list of cards in a player's hand
    private ArrayList<Card> cards;

    /**
    * Construct an empty hand
    */
    Hand() {
        cards = new ArrayList<Card>();
    }

    /**
    * Adds a card to the hand
    * @param Card c - the card being added
    */
    public void addCard(Card c){
        cards.add(c);
    }

    /** 
    * Displays the current hand
    */
    public void displayHand() {
        System.out.println("\nYour current hand: " + cards);
    }

    /** 
    * Randomly shuffles the current hand
    */
    public void shuffleHand() {
        Collections.shuffle(cards);
    }

    /** 
    * Returns the number of cards in the current hand
    * @return int - the size of the hand
    */
    public int getSize() {
        return cards.size();
    }

    /** 
    * Removes any pairs of matching cards in the current hand with the same value
    */
    public void removeDoubles() {
        for (int i = 0; i < cards.size(); i++) {
            String target = cards.get(i).getValue();            
                for (int j = i+1; j < cards.size(); j++) {
                    if (cards.get(j).getValue().equals(target)) {
                        cards.remove(j);
                        cards.remove(i);
                        i--;
                        j=cards.size();
                    }
                }
            
        }
    }

    /**
    * Helper method to remove a card at a given index
    * @param int index - index of the card being removed
    * @return removing the card
    */
    private Card removeCard(int index) {
        return cards.remove(index);
    }

    /**
    * Draws a card from another hand and adds it to this one
    * @param Hand h - the opponent's hand to draw from
    */
    public void drawOpponent(Hand h) {
        if(h.getSize()==0){
            System.out.println("No cards to draw from this hand.");
        }
        // chooses a random card to remove
        int randomCard = (int)(Math.random()*h.getSize());
        Card stolen = h.removeCard(randomCard);
        addCard(stolen);
    }


    
}