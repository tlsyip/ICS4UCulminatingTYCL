import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.image.Image;
/**
* Represents a standard 52-deck and provides operations for drawing
* cards, dealing cards to a hand, shuffling, and displaying the 
* deck's contents.
*/
public class Deck {
    private ArrayList<Card> deck;

    /**
    * Constructs a standard 52-card deck using all suits and names.
    */
    Deck () {
        deck = new ArrayList <Card>();

        String suit = "suit";
		// create all 12 cards for each suit
		for (int i = 0; i < 4; i++) {
			
			if (i == 0) {
				suit = "clubs";
			}
			
			if (i == 1) {
				suit = "spades";
			}
			
			if (i == 2) {
				suit = "diamonds";
			}
			
			if (i == 3) {
				suit = "hearts";
			}
			
			// create all the number cards and add their images
			String[] names = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
			for (String name : names) {
				
				String img = "/images/" + name + "_of_" + suit + ".png";
				String backImg = "/images/blank.png";
				System.out.println(img);
				
				int rank;
                if (name.equals("ace")) {
                    rank = 14;
                }
                else if (name.equals("jack")) {
                    rank = 11;
                }
                else if (name.equals("queen")) {
                    rank = 12;
                }
                else if (name.equals("king")) {
                    rank = 13;
                }
                else {
                    rank = Integer.parseInt(name);
                }
				//load images onto the cards from the image source
				Image image3 = new Image(getClass().getResourceAsStream(img));
				Image backOfCard = new Image(getClass().getResourceAsStream(backImg));
				
				//Create a new card object using the loaded images
				Card c = new Card(suit, name, rank, image3, backOfCard);
				//add card to the deck
				deck.add(c);
			}
		}
		
    }

    /**
    * Draws and remove the top card from the deck.
    * @return Card - the card drawn from the deck. 
    * @throws Exception if the deck is empty. 
    */
    public Card drawDeck() throws Exception {
        if (deck.size() == 0) {
            throw new Exception("No cards remaining in deck.");
        }
        return deck.remove(0);
    }

    /**
    * deals with cards from the deck to the specified hand.
    * @param n the number of cards to deal. 
    * @param h the hand to receive the cards. 
    */
    public void deal (int n, Hand h) {
        for (int i = 0; i<n; i++) {
            try {
                h.addCard(drawDeck());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
    *randomly shuffles the deck.
    */
    public void shuffleDeck () {
        Collections.shuffle(deck);
    }
    /**
     * creates and return a fresh deck
     * This method allocates a new deck and return it back.
     * @return a newly created deck
     */
    public Deck resetDeck() {
    	Deck d = new Deck(); 
    	return d;
    }

    /**
    * returns a string representation of the deck.
    * @return String representation of the deck  
    */
    public String toString() {
        return deck.toString();
    }
}