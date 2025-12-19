import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Arrays; 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class WarGUIDriver extends Application{
    private static Scanner input = new Scanner(System.in);
    private static Hand hand1 = new Hand("hand1");
    private static Hand hand2 = new Hand("hand2");
    private static Deck deck = new Deck();
    private static Pane handPane;
    private static Pane oppPane;
    private static Card playerCurrCard;
    private static Card opponentCurrCard;

    private final static double CARD_WIDTH  = 80;
    private final static double CARD_HEIGHT = 120;
    private final static double CARD_OVERLAP = 40;

    public void start (Stage stage) throws Exception {
        
        Label title = new Label ("~~~~War~~~~");
        Button btnGameStart = new Button ("Start Game");
        Button btnInstructions = new Button ("Instructions");
  
        btnGameStart.setPrefSize(155, 20); 
        btnInstructions.setPrefSize(155, 20); 

        HBox buttonHolder = new HBox(20);
        buttonHolder.getChildren().addAll(btnGameStart, btnInstructions);
        buttonHolder.setAlignment(Pos.CENTER);

        // Create a layout object
        VBox layout = new VBox(30);
        layout.getChildren().addAll(title, buttonHolder);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 500);
        Scene scene1 = gameScene(stage);  
        Scene scene2 = instructionsScene(stage);

        btnGameStart.setOnAction(e -> stage.setScene(scene1));
        btnInstructions.setOnAction(e -> stage.setScene(scene2));

        stage.setScene(scene);
        stage.show();


    }

    public Scene instructionsScene (Stage stage){
        Label title = new Label ("~~~~Instructions~~~~");
        Label instructions = new Label (" shuffle a standard deck, deal all cards face-down evenly between\ntwo players, then simultaneously flip your top card; the higher card \nwins both and adds them to their pile, while ties trigger a 'War' where \nplayers lay three cards down and flip a fourth (higher card wins all)\n until one player has all the cards, using Ace as high, 2 as low. Have fun!");

        Button btnGameStart = new Button ("Start Game");
        
        // Create a layout object
        VBox layout = new VBox(10);
        layout.getChildren().addAll(title, instructions, btnGameStart);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 500);

        btnGameStart.setOnAction(e -> stage.setScene(gameScene(stage)));
        

        return scene;
    }
    public Scene gameScene (Stage stage){
        Label optionsMenu = new Label ("~~~Options Menu~~~");
        Label errorMessage = new Label ("");
        Button btnFlipCard = new Button ("Flip card");      
        Button btnNextRound = new Button ("Next Round");

  
     
        btnFlipCard.setPrefSize(155, 20);        
        btnNextRound.setPrefSize(155, 20); 
    
     
        // Create a layout object
        VBox layout = new VBox(5);
        layout.getChildren().addAll(optionsMenu,btnFlipCard, btnNextRound, errorMessage);
        layout.setAlignment(Pos.BOTTOM_RIGHT);
        layout.setPadding(new Insets(15));


        Scene scene = new Scene(layout, 500, 500);   

        handPane = new Pane();
        handPane.setPrefSize(600,200);
        oppPane = new Pane();
        oppPane.setPrefSize(600,200);

        deck.shuffleDeck();
       
        deck.deal(26, hand1);
        deck.deal(26, hand2);

        renderHand(hand1, handPane);
        renderHand(hand2, oppPane);
        layout.getChildren().addAll(oppPane, handPane);

        btnFlipCard.setOnAction(e -> {
            flipCard();
        });
        btnNextRound.setOnAction(e -> {
            nextRound();
            renderHand(hand1, handPane);
            renderHand(hand2, oppPane);
            checkGameOver(stage);
        });
        return scene;

      
     }

     private static void renderHand(Hand hand, Pane pane){
        pane.getChildren().clear();

        double startX = 210;
        double startY = 30;

        for(int i=0;i<hand.getSize(); i++){
            Card card= hand.getCard(i);

            Pane cardNode = createCardNode(card, CARD_WIDTH,CARD_HEIGHT, "back");
            cardNode.setLayoutX(startX);
            cardNode.setLayoutY(startY);
            
            pane.getChildren().add(cardNode);
        }
    }

     private static Pane createCardNode(Card card, double width, double height, String side){

        Pane container = new Pane();

        Rectangle rect= new Rectangle(width,height);
        rect.setArcWidth(10);
        rect.setArcHeight(10);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);

        Text txt= new Text(card.toString());
        txt.setFont(Font.font("Arial", 18));
        txt.setX(10);
        txt.setY(25);

        String suit= card.getSuit().trim();
         System.out.println("DEBUG suit from getSuit(): [" + suit + "], card: " + card.toString());
        if (suit.contains("heart") || suit.contains("diamond")) {
            txt.setFill(Color.RED);
        } else {
            txt.setFill(Color.BLACK);
        }
      //  container.getChildren().addAll(rect, txt); previous code
        if (side.equals("back")) {
            ImageView cardImage = new ImageView(card.getBackImg());
            container.getChildren().add(cardImage);
        }
        else {
            ImageView cardImage = new ImageView(card.getFrontImg());
            container.getChildren().add(cardImage);
        }
        return container;

    }  

    public static void flipCard(){
        handPane.getChildren().clear();
        oppPane.getChildren().clear();

        double startX = 210;
        double startY = 30;
        double oppStartX = 210;
        double oppStartY = 30;

        playerCurrCard = hand1.getCard(0);
        opponentCurrCard = hand2.getCard(0);

        Pane cardNode = createCardNode(playerCurrCard, CARD_WIDTH,CARD_HEIGHT, "front");
        Pane oppCardNode = createCardNode(opponentCurrCard, CARD_WIDTH,CARD_HEIGHT, "front");
        cardNode.setLayoutX(startX);
        cardNode.setLayoutY(startY);
        oppCardNode.setLayoutX(oppStartX);
        oppCardNode.setLayoutY(oppStartY);
        handPane.getChildren().add(cardNode);
        oppPane.getChildren().add(oppCardNode);
        playerCurrCard.compare(opponentCurrCard);
        if (playerCurrCard.getStatus()==2) {
            System.out.println("seen");
        battle();
        }
    }

    public void nextRound() {
        if (playerCurrCard.getStatus()==0) {
            hand1.addCard(opponentCurrCard);
            hand1.addCard(playerCurrCard);
            hand1.removeCard(0);
            hand2.removeCard(0);
        }
        else if (playerCurrCard.getStatus()==1) {
            hand2.addCard(opponentCurrCard);
            hand2.addCard(playerCurrCard);
            hand2.removeCard(0);
            hand1.removeCard(0);
        }
    }

    private static void checkGameOver(Stage stage){
        int p1 = hand1.getSize();
        int p2 = hand2.getSize();    
        // checks if opponent has lost
        if(p1==0){
            System.out.println("You Lost");
        }
        // checks if user has lost
        else if(p2==0){
            System.out.println("You Win");
        }
    }

    private static void battle() {
        if (hand1.getSize() < 4 || hand2.getSize() < 4) {
            System.out.println("Not enough cards for war");
        return;
        }
        handPane.getChildren().clear();
        oppPane.getChildren().clear();
        double startX = 30;
        double startY = 30;
        double oppStartY = 30;
	    for(int i=0;i<3; i++){
            Card card = hand1.getCard(i);
            if (i%2!=0) {
                Pane cardNode = createBackCardNode(card, CARD_WIDTH,CARD_HEIGHT);
                cardNode.setLayoutX(startX +i*CARD_OVERLAP);
                cardNode.setLayoutY(startY);
                handPane.getChildren().add(cardNode);
            }
            else {
                Pane cardNode = createFrontCardNode(card, CARD_WIDTH,CARD_HEIGHT);
                cardNode.setLayoutX(startX +i*CARD_OVERLAP);
                cardNode.setLayoutY(startY);
                handPane.getChildren().add(cardNode);
                playerCurrCard = card;
            }  
	    }
	    for(int j=0;j<3; j++){
	            Card card = hand2.getCard(j);
            if (j%2!=0) {
                Pane cardNode = createBackCardNode(card, CARD_WIDTH,CARD_HEIGHT);
                cardNode.setLayoutX(startX +j*CARD_OVERLAP);
                cardNode.setLayoutY(oppStartY);
                oppPane.getChildren().add(cardNode);
            }
            else {
                Pane cardNode = createFrontCardNode(card, CARD_WIDTH,CARD_HEIGHT);
                cardNode.setLayoutX(startX +j*CARD_OVERLAP);
                cardNode.setLayoutY(oppStartY);
                oppPane.getChildren().add(cardNode);
                opponentCurrCard = card;
            }  
        }
        playerCurrCard.compare(opponentCurrCard);
        if (playerCurrCard.getStatus()==0) {
            hand1.addCard(opponentCurrCard);
            hand1.addCard(playerCurrCard);
            hand1.removeCard(0);
            hand2.removeCard(0);
        }
        else if (playerCurrCard.getStatus()==1) {
            hand2.addCard(opponentCurrCard);
            hand2.addCard(playerCurrCard);
            hand2.removeCard(0);
            hand1.removeCard(0);
        }
        else if (playerCurrCard.getStatus()==2) {
            battle();
        }
    }

    private static Pane createFrontCardNode(Card card, double width, double height){

        Pane container = new Pane();

        Rectangle rect= new Rectangle(width,height);
        rect.setArcWidth(10);
        rect.setArcHeight(10);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);

        Text txt= new Text(card.toString());
        txt.setFont(Font.font("Arial", 18));
        txt.setX(10);
        txt.setY(25);

        String suit= card.getSuit().trim();
         System.out.println("DEBUG suit from getSuit(): [" + suit + "], card: " + card.toString());
        if (suit.contains("heart") || suit.contains("diamond")) {
            txt.setFill(Color.RED);
        } else {
            txt.setFill(Color.BLACK);
        }
      //  container.getChildren().addAll(rect, txt); previous code
        ImageView cardImage = new ImageView(card.getFrontImg());
        container.getChildren().add(cardImage);
        return container;

    }  
    
    /**
     * 
     * @param card
     * @param width
     * @param height
     * @return Pane with back of card image
     */
    
    private static Pane createBackCardNode(Card card, double width, double height){

        Pane container = new Pane();

        Rectangle rect= new Rectangle(width,height);
        rect.setArcWidth(10);
        rect.setArcHeight(10);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);

        Text txt= new Text(card.toString());
        txt.setFont(Font.font("Arial", 18));
        txt.setX(10);
        txt.setY(25);

        String suit= card.getSuit().trim();
         System.out.println("DEBUG suit from getSuit(): [" + suit + "], card: " + card.toString());
        if (suit.contains("heart") || suit.contains("diamond")) {
            txt.setFill(Color.RED);
        } else {
            txt.setFill(Color.BLACK);
        }
      //  container.getChildren().addAll(rect, txt); previous code
        ImageView cardImage = new ImageView(card.getBackImg());
        container.getChildren().add(cardImage);
        return container;

    } 

    public static void main(String [] args) {
        launch(args);
    } 


}
