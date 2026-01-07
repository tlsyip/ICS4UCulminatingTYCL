import javafx.scene.image.Image;

/**
* Represents a standard playing card with a value and a suit
*/

public class Card {
    // The suit of the card
    private String suit;
    // The value of the card
    private String value;
    // The rank of the card
    private int rank;
    private int status;
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
        status = -1;
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
    
    /**
     * Returns the front image of the card
     * @return Image of the front of the card
     */
    public Image getFrontImg() {
    	return frontImg;
    }

    /**
     * Returns the back image of the card
     * @return Image of the back of the card
     */
    public Image getBackImg() {
    	return backImg;
    }
    
    /** Returns a string representation of the card (the suit followed by the value)
    * @return String representation of the card
    */
    public String toString () {
        return suit + value;
    }
    
    /**
     * Returns the suit of the card
     * @return String of the suit
     */
    public String getSuit(){
        return suit; 
    }
    
    /**
     * Returns the rank of the card
     * @return int value of the rank
     */
    public int getRank(){
        return rank;
    }

    /**
     * Returns the status of the card (0 if the card is bigger, 1 if smaller and 2 if tied)
     * @return int of the status
     */
    public int getStatus() {
        return status;
    }
    
    /**
     * Sets the status of the card
     * @param int s - status
     */
    public void setStatus(int s) {
    	status = s;
    }
    
    /**
     * Compares this card to another card
     * @param Card c2 - card being compared to
     */
   
    public void compare(Card c2){
        if(this.rank>c2.getRank()){
            System.out.println("Your card is bigger.");
            status = 0;
        }
        else if(this.rank<c2.getRank()){
            System.out.println("Your card is smaller.");
            status = 1;
        }
        else if(this.rank==c2.getRank()){
            System.out.println("Your cards are equal.");
            status = 2;
        }
    }
}