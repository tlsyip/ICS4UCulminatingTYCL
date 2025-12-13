import javafx.scene.image.Image;

/**
* Represents a standard playing card with a value and a suit
*/

public class Card {
    // The suit of the card
    private String suit;
    // The value of the card
    private String value;
    
    private Image img;

    /**
    * Constructs a card with a specified suit and value
    * @param String s - the suit of the card
    * @param String v - the value of the card
    */
    Card (String s, String v, Image image) {
        suit = s;
        value = v;
        img = image;
    }

    /**
    * Returns the value of the card
    * @return String value of the card
    */
    public String getValue () {
        return value;
    }

    public Image getImage() {
    	return img;
    }
    /**
    * Returns a string representation of the card (the suit followed by the value)
    * @return String representation of the card
    */
    public String toString () {
        return suit + value;
    }
    
    public String getSuit(){
        return suit; 
    }
}