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
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * JavaFx GUIDriver for game "War".
 * This class will handle display of main menu, instructions, and game play scenes.
 * It will also handle UI state such as flip card and next round.
 * After the game ends, it will display whether the player lose or win. 
 */
public class WarGUIDriver extends Application{
    private static Hand hand1 = new Hand("hand1");
    private static Hand hand2 = new Hand("hand2");
    private static Hand tempHand = new Hand("tempHand");
    private static Deck deck = new Deck();
    private static Pane handPane;
    private static Pane oppPane;

    private static Card playerCurrCard;
    private static Card opponentCurrCard;

    private final static double CARD_WIDTH  = 80;
    private final static double CARD_HEIGHT = 120;
    private final static double CARD_OVERLAP = 40;
    private static boolean flipCard = false;
    private static boolean nextRound = true;

    private static Label playerDeckSize;
    private static Label opponentDeckSize;
    
    /*
     * Starting screen of the game
     * @param Stage stage - to display graphics
     * @throws Exception ifJavaFx cannot initialize application
     */
    public void start (Stage stage) throws Exception {
    	//creates font
        Font smallFont = Font.font("Times New Roman", 20);
        Font bigFont = Font.font("Times New Roman", 50);
    	
        playerDeckSize = new Label("");
        opponentDeckSize = new Label("");
        
        Label title = new Label ("~~~~War~~~~");
        
        Button btnGameStart = new Button ("Start Game");
        Button btnInstructions = new Button ("Instructions");
        
        // create the object to hold the buttons
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
        
        // button to start the game
        // set the size of the button
        btnGameStart.setPrefSize(155, 30); 
        // set the font of the button
        btnGameStart.setFont(smallFont);
        // enters the game
        btnGameStart.setOnAction(e -> stage.setScene(scene1));
        // set the appearance of the button
        btnGameStart.setStyle(
        		"-fx-background-color: darkolivegreen; " +
        	    "-fx-border-color: black; " +
        	    "-fx-border-width: 2;" +
        	    "-fx-text-fill:moccasin;"
        );
        
        // set the size of the button
        btnInstructions.setPrefSize(155, 30); 
        // set the font of the button
        btnInstructions.setFont(smallFont);
        // enters the instructions
        btnInstructions.setOnAction(e -> stage.setScene(scene2));
        // set the appearance of the button
        btnInstructions.setStyle(
        		"-fx-background-color: darkolivegreen; " +
        	    "-fx-border-color: black; " +
        	    "-fx-border-width: 2;" +
        	    "-fx-text-fill:moccasin;"
        );
        
        // set the font of the title
        title.setFont(bigFont);
  
        // creates brown background
        layout.setStyle("-fx-background-color:moccasin");
        scene.setFill(Color.MOCCASIN);

        stage.setScene(scene);
        stage.show();
    }
    
    /*
     * Recreation of the starting screen of the game
     * @param Stage stage - to display graphics
     * @throws Exception ifJavaFx cannot initialize application
     */
    public static Scene startScene (Stage stage) {
    	//creates font
        Font smallFont = Font.font("Times New Roman", 20);
        Font bigFont = Font.font("Times New Roman", 50);
    	
        playerDeckSize = new Label("");
        opponentDeckSize = new Label("");
        
        Label title = new Label ("~~~~War~~~~");
        
        Button btnGameStart = new Button ("Start Game");
        Button btnInstructions = new Button ("Instructions");
        
        // create the object to hold the buttons
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
        
        // button to start the game
        // set the size of the button
        btnGameStart.setPrefSize(155, 30); 
        // set the font of the button
        btnGameStart.setFont(smallFont);
        // enters the game
        btnGameStart.setOnAction(e -> stage.setScene(scene1));
        // set the appearance of the button
        btnGameStart.setStyle(
        		"-fx-background-color: darkolivegreen; " +
        	    "-fx-border-color: black; " +
        	    "-fx-border-width: 2;" +
        	    "-fx-text-fill:moccasin;"
        );
        
        // set the size of the button
        btnInstructions.setPrefSize(155, 30); 
        // set the font of the button
        btnInstructions.setFont(smallFont);
        // enters the instructions
        btnInstructions.setOnAction(e -> stage.setScene(scene2));
        // set the appearance of the button
        btnInstructions.setStyle(
        		"-fx-background-color: darkolivegreen; " +
        	    "-fx-border-color: black; " +
        	    "-fx-border-width: 2;" +
        	    "-fx-text-fill:moccasin;"
        );
        
        // set the font of the title
        title.setFont(bigFont);
  
        // creates brown background
        layout.setStyle("-fx-background-color:moccasin");
        scene.setFill(Color.MOCCASIN);

        return scene;
    }
    
    /*
     * creates the instructions describing rules of War.
     * @param stage main JavaFx stage used for switching scenes
     * @return a Scene containing instructions and start button 
     */
    public static Scene instructionsScene (Stage stage){   
    	// create fonts
    	Font smallFont = Font.font("Times New Roman", 16);
    	Font bigFont = Font.font("Times New Roman", 50);
    	
        Label title = new Label ("~~~~Instructions~~~~");
        Label instructions = new Label ("Simultaneously flip your top card; the higher card wins both and the\nplayer who winns adds both cards to their pile. Ties trigger a 'War' where \nplayers lay three cards down (higher card wins all). Play goes on until one \nplayer has all the cards, using 2 as the lowest value card and Ace as the \nhighest. Have fun!");
        
        Button btnGameStart = new Button ("Start Game");
        
        // create a layout object
        VBox layout = new VBox(10);
        layout.getChildren().addAll(title, instructions, btnGameStart);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 500, 500);
        
        // button to enter the game
        // set the font of the button
        btnGameStart.setFont(smallFont);
        // enters the game
        btnGameStart.setOnAction(e -> stage.setScene(gameScene(stage)));
        // set the appearance of the button
        btnGameStart.setStyle(
        		"-fx-background-color: darkolivegreen; " +
        	    "-fx-border-color: black; " +
        	    "-fx-border-width: 2;" +
        	    "-fx-text-fill:moccasin;"
        );
        
        // sets the font of the labels
        title.setFont(bigFont);
        instructions.setFont(smallFont);

        // creates the brown background
        layout.setStyle("-fx-background-color:moccasin");
        scene.setFill(Color.MOCCASIN);
        
        return scene;
    }
    
    /*
     * Creates the game scene and make a new game state. 
     * Controls the flip, next round and quit buttons
     * @param Stage stage - to display graphics
     * @return a Scene containing the main game play
     */
    public static Scene gameScene (Stage stage){   
    	// create fonts
    	Font smallFont = Font.font("Times New Roman", 15);
    	Font bigFont = Font.font("Times New Roman", 20);
    	
        Label optionsMenu = new Label ("~~~Options Menu~~~");
        Label errorMessage = new Label ("");
        
        Button btnFlipCard = new Button ("Flip card");      
        Button btnNextRound = new Button ("Next Round");
        Button btnQuitGame= new Button("Quit Game");
        
        // create a layout object
        VBox layout = new VBox(1);
        layout.getChildren().addAll(optionsMenu,btnFlipCard, btnNextRound, btnQuitGame, opponentDeckSize, playerDeckSize, errorMessage);
        layout.setAlignment(Pos.BOTTOM_RIGHT);
        layout.setPadding(new Insets(15));
        
        Scene scene = new Scene(layout, 500, 500); 
        
        // creates the two panes to display both hands
        handPane = new Pane();
        oppPane = new Pane();
        handPane.setPrefSize(600,200);
        oppPane.setPrefSize(600,200);
        
        // button to flip the card
        // sets the size of the button
        btnFlipCard.setPrefSize(155, 20);
        // sets the font of the button
        btnFlipCard.setFont(smallFont);
        // flips the card
        btnFlipCard.setOnAction(e -> {
            errorMessage.setText("");
            if (!flipCard) {
                flipCard(stage);
                flipCard = true;
                nextRound = false;
            }
            else {
            	// displays an error message if the user has already flipped the cards
                errorMessage.setText("Card already flipped.");
            }
        });
        // sets the appearance of the button
        btnFlipCard.setStyle(
        		"-fx-background-color: darkolivegreen; " +
        	    "-fx-border-color: black; " +
        	    "-fx-border-width: 2;" +
        	    "-fx-text-fill:moccasin;"
        );
        
        // button to enter the next round
        // sets the size of the button
        btnNextRound.setPrefSize(155, 20);
        // sets the font of the button
        btnNextRound.setFont(smallFont);
        // enters the next round
        btnNextRound.setOnAction(e -> {
            if (!nextRound) {
                errorMessage.setText("");
                nextRound();
                nextRound = true;
                renderHand(hand1, handPane);
                renderHand(hand2, oppPane);
                checkGameOver(stage);
                flipCard = false;
            }
            else {
            	// displays an error message if the user has not flipped the card but is attempting to enter the next round
                errorMessage.setText("Card not flipped yet.");
            }
        });
        // sets the appearance of the button
        btnNextRound.setStyle(
        		"-fx-background-color: darkolivegreen; " +
        	    "-fx-border-color: black; " +
        	    "-fx-border-width: 2;" +
        	    "-fx-text-fill:moccasin;"
        );
        
        // button to quit the game
        // sets the size of the button
        btnQuitGame.setPrefSize(155, 20); 
        // sets the font of the button
        btnQuitGame.setFont(smallFont);
        // returns to the main menu
        btnQuitGame.setOnAction(e -> {
        	System.out.println("Returning to Main Menu...");
        	stage.setScene(MainGUIDriver.startScene(stage));
        });
        // sets the appearance of the button
        btnQuitGame.setStyle(
        		"-fx-background-color: darkolivegreen; " +
        	    "-fx-border-color: black; " +
        	    "-fx-border-width: 2;" +
        	    "-fx-text-fill:moccasin;"
        );
            
        // sets the fonts of the labels
        errorMessage.setFont(smallFont);
        optionsMenu.setFont(bigFont); 
        playerDeckSize.setFont(smallFont);
        opponentDeckSize.setFont(smallFont);
        //sets the error message to be red
        errorMessage.setTextFill(Color.RED);
        
        // create a brown background
        layout.setStyle("-fx-background-color:moccasin");       
        scene.setFill(Color.MOCCASIN);

        // creates a new game
        resetGame();
  
        // renders both hands
        renderHand(hand1, handPane);
        renderHand(hand2, oppPane);
        
        layout.getChildren().addAll(oppPane, handPane);
        return scene;
      
     }
    
     /**
     * Create visual pictures of cards 
     * @param Card card - the created
     * @param double width - width of the card
     * @param double height - height of the card
     * @return Pane with back of card image
     */
     private static Pane createCardNode(Card card, double width, double height, String side){
   	   // creates a new pane to display the images
       Pane container = new Pane();
       
       // gets the image of the back of the card
       if (side.equals("back")) {
           ImageView cardImage = new ImageView(card.getBackImg());
           container.getChildren().add(cardImage);
       }
       // gets the image of the front of the card
       else {
           ImageView cardImage = new ImageView(card.getFrontImg());
           container.getChildren().add(cardImage);
       }
       
       return container;
     } 
     
     /**
      * Create the image of the front of the card
      * @param Card card - the card created
      * @param double width - width of the card
      * @param double height - height of the card
      * @return Pane with front of card images
      */
     private static Pane createFrontCardNode(Card card, double width, double height){
    	 // creates a new pane to display the images
         Pane container = new Pane();
         // gets the image of the front of the card
         ImageView cardImage = new ImageView(card.getFrontImg());
         container.getChildren().add(cardImage);
         
         return container;
     }  
     
     /**
      * Create the image of the back of the card
      * @param Card card - the created
      * @param double width - width of the card
      * @param double height - height of the card
      * @return Pane with back of card image
      */
     private static Pane createBackCardNode(Card card, double width, double height){
    	 // creates a new pane to display the images
         Pane container = new Pane();
         // gets the image of the back of the card
         ImageView cardImage = new ImageView(card.getBackImg());
         container.getChildren().add(cardImage);
         
         return container;
     } 
    
     /**
     * Renders a hand onto target using card back images
     * @param Hand hand - to render
     * @param Pane pane - to draw into
     */
     private static void renderHand(Hand hand, Pane pane){
    	 // clears the pane
    	 pane.getChildren().clear();
        
    	 double startX = 210;
    	 double startY = 15;
    	 double oppStartY = 10;

    	 // renders the users hand
    	 if (hand.getName().equals("hand1")) {
	        for(int i=0;i<hand.getSize(); i++){
	            Card card= hand.getCard(i);
	
	            Pane cardNode = createCardNode(card, CARD_WIDTH,CARD_HEIGHT, "back");
	            cardNode.setLayoutX(startX);
	            cardNode.setLayoutY(startY);
	            
	            pane.getChildren().add(cardNode);
	        }
    	 }
    	 // renders the opponent's hand
    	 if (hand.getName().equals("hand2")) {
            for(int i=0;i<hand.getSize(); i++){
                Card card= hand.getCard(i);

                Pane cardNode = createCardNode(card, CARD_WIDTH,CARD_HEIGHT, "back");
                cardNode.setLayoutX(startX);
                cardNode.setLayoutY(oppStartY);
                
                pane.getChildren().add(cardNode);
            }
    	 }
     }
     
     /**
      * The end scene telling the user that they won
      * @param Stage stage - to display the graphics
      */
     public static void endScenePlayerWin (Stage stage) {
    	 // create font
    	 Font smallFont = Font.font("Times New Roman", 16);
    	 
         Label winMessage = new Label ("You Win! You have all the cards.");
         
         Button btnMainMenu = new Button ("Return to Main Menu");
         
         
         // create a layout object
         VBox layout = new VBox(10);
         layout.setAlignment(Pos.CENTER);
         layout.getChildren().addAll(winMessage, btnMainMenu);
        
         Scene scene = new Scene(layout, 500, 500);
         
         // button to return to the main menu
         btnMainMenu.setPrefSize(200, 20);
         btnMainMenu.setFont(smallFont);
         btnMainMenu.setOnAction(e ->  {
         	stage.setScene(MainGUIDriver.startScene(stage));
         });
         btnMainMenu.setStyle(
     	 		"-fx-background-color: lavenderblush; " +
      	 	    "-fx-border-color: black; " +
      	 	    "-fx-border-width: 2;" +
      	 	    "-fx-text-fill:black;"
          );
         
         // set font of the label
         winMessage.setFont(smallFont);
         
         // creates green background
         layout.setStyle("-fx-background-color:palegreen");
         scene.setFill(Color.PALEGREEN);
         
         // displays the graphics
         stage.setScene(scene);
         stage.show();
     }
     
     /**
      * The end scene telling the user that they lost
      * @param Stage stage - to display graphics
      */
     public static void endScenePlayerLose (Stage stage) {
    	 //create font
    	 Font smallFont = Font.font("Times New Roman", 16);
    	 
         Label loseMessage = new Label ("You Lost! Your opponent has all the cards.");
         
         Button btnMainMenu = new Button ("Return to Main Menu");
         
         // create a layout object
         VBox layout = new VBox(10);
         layout.setAlignment(Pos.CENTER);
         layout.getChildren().addAll(loseMessage, btnMainMenu);
        
         Scene scene = new Scene(layout, 500, 500);
         
         // button to return to the main menu
         btnMainMenu.setPrefSize(200, 20);
         btnMainMenu.setFont(smallFont);
         btnMainMenu.setOnAction(e ->  {
         	stage.setScene(MainGUIDriver.startScene(stage));
         });
         btnMainMenu.setStyle(
     	 		"-fx-background-color: lavenderblush; " +
      	 	    "-fx-border-color: black; " +
      	 	    "-fx-border-width: 2;" +
      	 	    "-fx-text-fill:black;"
          );
         
         // set font of the label
         loseMessage.setFont(smallFont);
         
         // creates red background
         layout.setStyle("-fx-background-color:lightcoral");
         scene.setFill(Color.LIGHTCORAL);
         
         // displays the graphics
         stage.setScene(scene);
         stage.show();
     }
     
      /**
      * Flip the top card for players and compare them, if tied then "war" is activated 
      * @param Stage stage - to display the graphics
      */
      public static void flipCard(Stage stage){
        // gets the current card from the top of the hand
        playerCurrCard = hand1.getCard(0);
        opponentCurrCard = hand2.getCard(0);
    	  
    	// clears the pane
        handPane.getChildren().clear();
        oppPane.getChildren().clear();
        
        Pane cardNode = createCardNode(playerCurrCard, CARD_WIDTH,CARD_HEIGHT, "front");
        Pane oppCardNode = createCardNode(opponentCurrCard, CARD_WIDTH,CARD_HEIGHT, "front");

        double startX = 210;
        double startY = 15;
        double oppStartX = 210;
        double oppStartY = 10;

        cardNode.setLayoutX(startX);
        cardNode.setLayoutY(startY);
        oppCardNode.setLayoutX(oppStartX);
        oppCardNode.setLayoutY(oppStartY);
        
        handPane.getChildren().add(cardNode);
        oppPane.getChildren().add(oppCardNode);
        
        System.out.println("Flipped card.");
        // update the card counter
        updateCounter();
        
        // compares the two cards
        playerCurrCard.compare(opponentCurrCard);
        
        // checks if the game over
        checkGameOver(stage);
        
        // if the the cards are tied, enter a battle
        if (playerCurrCard.getStatus()==2) {
        	battle(stage);
        }
        
        // check if the game is over
        checkGameOver(stage);
    }
    
     /**
     * Allow the game to progress by awarding flipped cards to players who won the comparison, and then removes the flipped card
     */
     public static void nextRound() {
    	System.out.println("Started next round.");
    	// if the user won, add cards to the user's hand
        if (playerCurrCard.getStatus()==0) {
            hand1.addCard(opponentCurrCard);
            hand1.addCard(playerCurrCard);
            hand1.removeCard(0);
            hand2.removeCard(0);
            updateCounter();
        }
     // if the user won, add cards to the opponent's hand
        else if (playerCurrCard.getStatus()==1) {
            hand2.addCard(opponentCurrCard);
            hand2.addCard(playerCurrCard);
            hand2.removeCard(0);
            hand1.removeCard(0);
            updateCounter();
        }
        else {
        	updateCounter();
        }
     }
     
      /**
      * Checks if the game is over
      * @param Stage stage - to display graphics
      */
     private static void checkGameOver(Stage stage){
    	 // checks both hand sizes
         int p1 = hand1.getSize();
         int p2 = hand2.getSize();    
        
         // checks if opponent has lost
         if(p1==0){
            System.out.println("You Lost.");
            endScenePlayerLose(stage);
         }
         
         // checks if user has lost
         else if(p2==0){
            System.out.println("You Win.");
            endScenePlayerWin(stage);
        }
     }
    
    /*
     * Resolves a tied scenario by drawing three cards from each player where two are displayed face up and one face down 
     * @param Stage stage - to display graphics
     */
    private static void battle(Stage stage) {
    	// checks if the user's hand size is too small for war
        if (hand1.getSize() < 3) {
            checkGameOver(stage);
            System.out.println("You win, your opponent does not have enough cards for war.");
            endScenePlayerWin(stage);
            
        }
        // checks if the opponent's hand size is too small for war
        if(hand2.getSize() < 3){
            checkGameOver(stage);
            System.out.println("You lose, your opponent does not have enough cards for war.");
            endScenePlayerLose(stage);
        }
        
        // clears the pane
        handPane.getChildren().clear();
        oppPane.getChildren().clear();
        
        double startX = 30;
        double startY = 30;
        double oppStartY = 30;
        
        // creates the user's battle hand
	    for(int i=0;i<3; i++){
            Card card = hand1.removeCard(0);
            tempHand.addCard(card);
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
	    
	    // creates the opponent's battle hand
	    for(int j=0;j<3; j++){
	            Card card = hand2.removeCard(0);
	            tempHand.addCard(card);
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
	    
	    // compares the two cards
        playerCurrCard.compare(opponentCurrCard);
        if (playerCurrCard.getStatus()==0) {
        	for (int i = 0; i < tempHand.getSize(); i++) {
        		hand1.addCard(tempHand.getCard(i));
        	}
        }
        else if (playerCurrCard.getStatus()==1) {
        	for (int j = 0; j < tempHand.getSize(); j++) {
        		hand2.addCard(tempHand.getCard(j));
        	}
        }
        else if (playerCurrCard.getStatus()==2) {
            battle(stage);
        }
        
        // checks if either hand is out of cards
        checkGameOver(stage);
        
        // changes the status to indicate a completed battle
        playerCurrCard.setStatus(3);
        
        // clears the temporary hand
        while (tempHand.getSize()>0) {
        	tempHand.removeCard(0);
        }
    }
    
     /**
     *  Update the counters for player and opponent card count.
     */
     public static void updateCounter(){
    	 // player card count
         playerDeckSize.setText("Your # of Cards: " + Integer.toString(hand1.getSize()));
         // opponent card count
         opponentDeckSize.setText("Opponent # of Cards: " + Integer.toString(hand2.getSize()));
     }
    
    
     /**
     * Resets the game to be played again
     */
     private static void resetGame() {
    	 flipCard = false;
    	 nextRound = true;
    	
    	 deck = new Deck();
    	 hand1 = new Hand("hand1");
    	 hand2 = new Hand("hand2");
    	 tempHand = new Hand("tempHand");
    	 
    	 // shuffles the deck before dealing cards
    	 deck.shuffleDeck();
         
    	 // deals the cards evenly to both hands
         deck.deal(1, hand1);
         deck.deal(51, hand2);
         
         // updates the counter
         updateCounter();
     }
    
    public static void main(String [] args) {
        launch(args);
    } 


}
