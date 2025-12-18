import javafx.scene.image.Image;

/**
* Represents a standard playing card with a value and a suit
*/

public class Card {
    // The suit of the card
    private String suit;
    // The value of the card
    private String value;
    private int rank;
    
    private Image frontImg;
    private Image backImg;

    /**
    * Constructs a card with a specified suit and value
    * @param String s - the suit of the card
    * @param String v - the value of the card
    */
    Card (String s, String v, int r, Image frontIMG, Image backIMG) {
        suit = s;
        value = v;
        rank = r;
        frontImg = frontIMG;
        backImg = backIMG;
    }

    /**
    * Returns the value of the card
    * @return String value of the card
    */
    public String getValue () {
        return value;
    }

    public Image getFrontImg() {
    	return frontImg;
    }

    public Image getBackImg() {
    	return backImg;
    }
    
    /** Returns a string representation of the card (the suit followed by the value)
    * @return String representation of the card
    */
    public String toString () {
        return suit + value;
    }
    
    public String getSuit(){
        return suit; 
    }

    public int getRank(){
        return rank;
    }
    
    public void compare(Card c2){
        if(this.rank>c2.getRank()){
            System.out.println("Big");
        }
        else if(this.rank<c2.getRank()){
            System.out.println("Small");
        }
        else if(this.rank==c2.getRank()){
            System.out.println("Equal");
        }
    }
}