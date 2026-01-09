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

/**
 * JavaFx GUIDriver for "Old Maid".
 * This class will handle display of main menu, instructions, and game play scenes.
 * It will also handle user interactions through buttons and game controls, update the cards visually, and managing scene transitions.
 */
public class OldMaidGUIDriver extends Application{
        private static boolean alreadyDrawn = false;
        private static boolean doublesRemoved = false;
        private static Pane handPane;
        private static Pane finalPane;
        private static Pane oppPane;
        private static Deck deck = new Deck();
        private static Hand hand1 = new Hand("hand1");
        private static Hand hand2 = new Hand("hand2");
        private static Hand oldMaidCard = new Hand("oldMaidCard");
        private final static double CARD_WIDTH  = 80;
        private final static double CARD_HEIGHT = 120;
        private final static double CARD_OVERLAP = 40;
        

        /**
         * Starting screen of the game
         * @param Stage stage - to display graphics
         */
    public void start (Stage stage) throws Exception {
    	// resets the deck
    	deck.resetDeck();
    	
    	// create fonts
    	Font smallFont = Font.font("Times New Roman", 20);
        Font bigFont = Font.font("Times New Roman", 50);
         
        Label title = new Label ("~~~~Old Maid~~~~");
     	 
        Button btnGameStart = new Button ("Start Game");
        Button btnInstructions = new Button ("Instructions");
        
 		// create an object to hold the buttons
 	    HBox buttonHolder = new HBox(20);
 	    buttonHolder.getChildren().addAll(btnGameStart, btnInstructions);
 	    buttonHolder.setAlignment(Pos.CENTER);
 	
 	    // create a layout object
 	    VBox layout = new VBox(30);
 		layout.getChildren().addAll(title, buttonHolder);
 		layout.setAlignment(Pos.CENTER);
         
 		Scene scene = new Scene(layout, 500, 500);
 		Scene scene1 = gameScene(stage);
 		Scene scene2 = instructionsScene(stage);
         
        // button to enter the game
        // sets the size of the button
        btnGameStart.setPrefSize(155, 20); 
        // sets the font of the button
        btnGameStart.setFont(smallFont);
        // enters the game
        btnGameStart.setOnAction(e -> stage.setScene(scene1));
        // sets the appearance of the button
        btnGameStart.setStyle(
	 		"-fx-background-color: white; " +
 	 	    "-fx-border-color: black; " +
 	 	    "-fx-border-width: 2;" +
 	 	    "-fx-text-fill:steelblue;"
        );
         
        // button to enter the instructions
        // sets the size of the button
        btnInstructions.setPrefSize(155, 20); 
        // sets the font of the button
        btnInstructions.setFont(smallFont);
        // enters the instructions
        btnInstructions.setOnAction(e -> stage.setScene(scene2));
        // sets the appearance of the button
        btnInstructions.setStyle(
 	 		"-fx-background-color: white; " +
 	 	    "-fx-border-color: black; " +
 	 	    "-fx-border-width: 2;" +
 	 	    "-fx-text-fill:steelblue;"
         );
 	           
        // sets the font of the title
        title.setFont(bigFont);

 		// creates blue background
 		layout.setStyle("-fx-background-color:lightsteelblue;");
 		scene.setFill(Color.LIGHTSTEELBLUE);

        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Recreation of the starting screen of the game
     * @param Stage stage - to display graphics
     * @return Scene for user to interact with
     */
    public static Scene startScene (Stage stage) {
    	// create fonts
        Font smallFont = Font.font("Times New Roman", 20);
        Font bigFont = Font.font("Times New Roman", 50);
        
        Label title = new Label ("~~~~Old Maid~~~~");
    	 
        Button btnGameStart = new Button ("Start Game");
        Button btnInstructions = new Button ("Instructions");
        
		// create an object to hold the buttons
	    HBox buttonHolder = new HBox(20);
	    buttonHolder.getChildren().addAll(btnGameStart, btnInstructions);
	    buttonHolder.setAlignment(Pos.CENTER);
	
	    // create a layout object
	    VBox layout = new VBox(30);
		layout.getChildren().addAll(title, buttonHolder);
		layout.setAlignment(Pos.CENTER);
        
		Scene scene = new Scene(layout, 500, 500);
		Scene scene1 = gameScene(stage);
		Scene scene2 = instructionsScene(stage);
		
        
        // button to enter the game
        // sets the size of the button
        btnGameStart.setPrefSize(155, 20); 
        // sets the font of the button
        btnGameStart.setFont(smallFont);
        // enters the game
        btnGameStart.setOnAction(e -> stage.setScene(scene1));
        // sets the appearance of the button
        btnGameStart.setStyle(
	 		"-fx-background-color: white; " +
	 	    "-fx-border-color: black; " +
	 	    "-fx-border-width: 2;" +
	 	    "-fx-text-fill:steelblue;"
        );
        
        // button to enter the instructions
        // sets the size of the button
        btnInstructions.setPrefSize(155, 20); 
        // sets the font of the button
        btnInstructions.setFont(smallFont);
        // enters the instructions
        btnInstructions.setOnAction(e -> stage.setScene(scene2));
        // sets the appearance of the button
        btnInstructions.setStyle(
	 		"-fx-background-color: white; " +
	 	    "-fx-border-color: black; " +
	 	    "-fx-border-width: 2;" +
	 	    "-fx-text-fill:steelblue;"
         );
	           
        // sets the font of the title
	    title.setFont(bigFont);

		// creates blue background
		layout.setStyle("-fx-background-color:lightsteelblue;");
		scene.setFill(Color.LIGHTSTEELBLUE);		
		
		return scene;
    }

    /**
     * Instructions scene that displays how to play the game
     * @param Stage stage - to display graphics
     * @return Scene for user to interact with
     */
    public static Scene instructionsScene (Stage stage){
    	// create fonts
        Font smallFont = Font.font("Times New Roman", 16);
        Font bigFont = Font.font("Times New Roman", 50);
        
        Label title = new Label ("~~~~Instructions~~~~");
        Label instructions = new Label ("The first player draws one card from their opponent and discards any\nresulting pair. That player then offers their hand to the next player.\nPlay continues this way until only one unpaired card remains and \nwhoever holds it is the Old Maid. Have fun!");
        
        Button btnGameStart = new Button ("Start Game");
        
        // create a layout object
        VBox layout = new VBox(10);
        layout.getChildren().addAll(title, instructions, btnGameStart);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 500, 500);
        
        // button to enter the game
        // sets the font of the button
        btnGameStart.setFont(smallFont);
        // enters the game
        btnGameStart.setOnAction(e -> stage.setScene(gameScene(stage)));
        // sets the appearance of the button
        btnGameStart.setStyle(
        		"-fx-background-color: white; " +
        	    "-fx-border-color: black; " +
        	    "-fx-border-width: 2;" +
        	    "-fx-text-fill:steelblue;"
        );
        
        // sets the font of the labels
        title.setFont(bigFont);
        instructions.setFont(smallFont);
        
        //creates blue background
        layout.setStyle("-fx-background-color:lightsteelblue;");
        scene.setFill(Color.LIGHTSTEELBLUE);

        return scene;
    }

    /**
     * The game scene where the user plays the game
     * Controls the draw opponent, remove doubles, end turn and quit game buttons
     * @param Stage stage - to display graphics
     * @return Scene containing main game play
     */
    public static Scene gameScene (Stage stage){
    	// create fonts
        Font smallFont = Font.font("Times New Roman", 15);
        Font bigFont = Font.font("Times New Roman", 20);
        
        Label optionsMenu = new Label ("~~~Options Menu~~~");
        Label errorMessage = new Label ("");
        
        Button btnDrawOpponent = new Button ("Draw from opponent");
        Button btnRemoveDoubles = new Button ("Remove doubles");
        Button btnEndTurn = new Button ("End turn");
        Button btnQuitGame = new Button ("Quit game");
        
        // create a layout object
        VBox layout = new VBox(1);
        layout.getChildren().addAll(optionsMenu, btnDrawOpponent, btnRemoveDoubles, btnEndTurn, btnQuitGame, errorMessage);
        layout.setAlignment(Pos.BOTTOM_RIGHT);
        layout.setPadding(new Insets(15));
        
        Scene scene = new Scene(layout, 500, 500);
        
        // creates the two panes to display both hands
        handPane = new Pane();
        oppPane = new Pane();
        handPane.setPrefSize(600,200);
        oppPane.setPrefSize(600,200);
        
        // button to draw from the opponent
        // sets the size of the button
        btnDrawOpponent.setPrefSize(155, 20);
        // sets the font of the button
        btnDrawOpponent.setFont(smallFont); 
        btnDrawOpponent.setOnAction(e -> {
        // checks if the user has already drawn for their opponent
        if (!alreadyDrawn) {
        	errorMessage.setText("");
        	// checks if the opponent has won before the draw
        	checkGameOver(stage);
        	// draws from the opponent
            hand1.drawOpponent(hand2);
            alreadyDrawn = true;
            // checks if the opponent has cards remaining
            checkGameOver(stage);
            // renders both hands
            renderHand(hand1, handPane);
            renderHand(hand2, oppPane);
            System.out.println("Drawed from opponent.");
         } 
         else {
            	// displays an error message if the user has already drawn from their opponent
                errorMessage.setText("You already picked from your opponent.");
         }
        });
        // sets the appearance of the button
        btnDrawOpponent.setStyle(
         		"-fx-background-color: white; " +
         	    "-fx-border-color: black; " +
         	    "-fx-border-width: 2;" +
         	    "-fx-text-fill:steelblue;"
         );
        
        // button to remove the doubles in the user's hand
        btnRemoveDoubles.setPrefSize(155, 20); 
        btnRemoveDoubles.setFont(smallFont); 
        btnRemoveDoubles.setOnAction(e -> {
        	// checks if the user has already removed the doubles
            if (!doublesRemoved) {
                errorMessage.setText("");
                // removes the doubles from the hand
                hand1.removeDoubles();
                doublesRemoved = true;
                // checks if the user has now won the game
                checkGameOver(stage);
                // renders both hands
                renderHand(hand1, handPane);
                renderHand(hand2, oppPane);
                System.out.println("Removed doubles.");
            }
            // displays an error message if the user has already removed the doubles
			  else {
                errorMessage.setText("All your doubles are removed.");
            }
        });
        // sets the appearance of the button
        btnRemoveDoubles.setStyle(
         		"-fx-background-color: white; " +
         	    "-fx-border-color: black; " +
         	    "-fx-border-width: 2;" +
         	    "-fx-text-fill:steelblue;"
        );
        
        // button to end the turn
        // sets the size of the button
        btnEndTurn.setPrefSize(155, 20); 
        // sets the font of the button
        btnEndTurn.setFont(smallFont);  
        btnEndTurn.setOnAction(e -> {
        	checkGameOver(stage);
        	System.out.println("Turn Ended");
            errorMessage.setText("");
            errorMessage.setText("Opponent Turn...");
            opponentTurn(stage);
            renderHand(hand1, handPane);
            renderHand(hand2, oppPane);
            errorMessage.setText("Opponent Turn Finished.");
        });
        // sets the appearance of the button
        btnEndTurn.setStyle(
          		"-fx-background-color: white; " +
          	    "-fx-border-color: black; " +
          	    "-fx-border-width: 2;" +
          	    "-fx-text-fill:steelblue;"
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
          		"-fx-background-color: white; " +
          	    "-fx-border-color: black; " +
          	    "-fx-border-width: 2;" +
          	    "-fx-text-fill:steelblue;"
         );
        
        // sets the font of the labels
        errorMessage.setFont(smallFont); 
        optionsMenu.setFont(bigFont); 
        // sets the error message to be red
        errorMessage.setTextFill(Color.RED);
        
        // creates blue background
        layout.setStyle("-fx-background-color:lightsteelblue;");
        scene.setFill(Color.LIGHTSTEELBLUE);

        // creates a new game
        resetGame();
        
        // renders both hands
        renderHand(hand1, handPane);
        renderHand(hand2, oppPane);
        
        layout.getChildren().addAll(oppPane, handPane);
        return scene;
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

        double startX = 30;
        double startY = 30;
        double oppStartY = 10;
        
        // renders the user's hand
        if (hand.getName().equals("hand1")) {
	        for(int i=0;i<hand.getSize(); i++){
	            Card card= hand.getCard(i);
	
	            Pane cardNode = createFrontCardNode(card, CARD_WIDTH,CARD_HEIGHT);
	            cardNode.setLayoutX(startX +i*CARD_OVERLAP);
	            cardNode.setLayoutY(startY);
	            // adds each card to the pane
	            pane.getChildren().add(cardNode);
	        }
        }
        // renders the opponent's hand
        else if (hand.getName().equals("hand2")) {
	        for(int i=0;i<hand.getSize(); i++){
	            Card card= hand.getCard(i);
	
	            Pane cardNode = createBackCardNode(card, CARD_WIDTH,CARD_HEIGHT);
	            cardNode.setLayoutX(startX +i*CARD_OVERLAP);
	            cardNode.setLayoutY(oppStartY);
	            // adds each card to the pane
	            pane.getChildren().add(cardNode);
	        }
        }
        // renders the Old Maid Card for the end scenes
        else if (hand.getName().equals("oldMaidCard")) {
        	pane.getChildren().clear();
	        Card card= hand.getCard(0);
	
            Pane cardNode = createFrontCardNode(card, CARD_WIDTH,CARD_HEIGHT);
            cardNode.setLayoutX(210);
            cardNode.setLayoutY(30);
            
            pane.getChildren().add(cardNode);
        }
    }
    
    /**
     * The end scene telling the user that they won
     * @param Stage stage - to display the graphics
     */
    public static void endScenePlayerWin (Stage stage) {
    	
    	Font smallFont = Font.font("Times New Roman", 16);
        
        
        Label winMessage = new Label ("You got rid of all your cards! Your opponent is the Old Maid!");
        Label lblOldMaidCard = new Label ("The Old Maid Card was: ");
        
        winMessage.setFont(smallFont);
        lblOldMaidCard.setFont(smallFont);
        
        
        Button btnMainMenu = new Button ("Return to Main Menu");
        btnMainMenu.setFont(smallFont);
        
        btnMainMenu.setStyle(
    	 		"-fx-background-color: lavenderblush; " +
     	 	    "-fx-border-color: black; " +
     	 	    "-fx-border-width: 2;" +
     	 	    "-fx-text-fill:black;"
            );
        
        // Create a pane to display the Old Maid Card
        finalPane = new Pane();
        
        // create a layout object
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(winMessage, lblOldMaidCard, finalPane, btnMainMenu);
       
        Scene scene = new Scene(layout, 500, 500);
        
        // button to return to the main menu
        btnMainMenu.setPrefSize(200, 20);
        btnMainMenu.setOnAction(e ->  {
        	stage.setScene(MainGUIDriver.startScene(stage));
        	oldMaidCard.removeCard(0);
        });
        
        // renders the old maid card to show what it was
        renderHand(oldMaidCard, finalPane);
        
        // creates green background
        layout.setStyle("-fx-background-color:palegreen;");
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
    	
    	Font smallFont = Font.font("Times New Roman", 16);
    	
        Label loseMessage = new Label ("Your opponent got rid of all their cards. You are the Old Maid!");
        Label lblOldMaidCard = new Label ("The Old Maid Card was: ");
        
        loseMessage.setFont(smallFont);
        lblOldMaidCard.setFont(smallFont);
        
        Button btnMainMenu = new Button ("Return to Main Menu");
        
        btnMainMenu.setStyle(
    	 		"-fx-background-color: lavenderblush; " +
     	 	    "-fx-border-color: black; " +
     	 	    "-fx-border-width: 2;" +
     	 	    "-fx-text-fill:black;"
            );
        
        // create a pane to display the Old Maid Card
        finalPane = new Pane();
        
        // create a layout object
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(loseMessage, lblOldMaidCard, finalPane, btnMainMenu);    
        
        Scene scene = new Scene(layout, 500, 500);
        
        
        
        // button to return to the main menu
        btnMainMenu.setPrefSize(200, 20);
        btnMainMenu.setOnAction(e ->  {
        	stage.setScene(MainGUIDriver.startScene(stage));
        	oldMaidCard.removeCard(0);
        });
        
        // renders the old maid card to show what it was
        renderHand(oldMaidCard, finalPane); 
        
        // creates red background
        layout.setStyle("-fx-background-color:lightcoral;");
        scene.setFill(Color.LIGHTCORAL);
        
        // displays the graphics
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    /**
     * Automates the opponent's turn
     * @param Stage stage - to display graphics
     */
    public static void opponentTurn(Stage stage) {
    	checkGameOver(stage);
    	// draws a card from the user
        hand2.drawOpponent(hand1);
        // checks if the user is out of cards
        checkGameOver(stage);
        // removes doubles from the opponent's hand
        hand2.removeDoubles();
        // checks if the opponent is out of cards
        checkGameOver(stage);
        System.out.println("Opponent turn finished.\nYour turn!");
        //resets user turn limitations
        alreadyDrawn = false;
        doublesRemoved = false;
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
            System.out.println("You Win! You got rid of all your cards! Your opponent is the Old Maid!");
            endScenePlayerWin(stage);
        }
        // checks if user has lost
        else if(p2==0){
            System.out.println("You Lost! Your opponent got rid of all their cards. You are the Old Maid!");
            endScenePlayerLose(stage);
        }
    }
    
    /**
     * Resets the game to be played again
     */
    private static void resetGame() {
		deck = new Deck();
		hand1 = new Hand("hand1");
		hand2 = new Hand("hand2");
		oldMaidCard = new Hand ("oldMaidCard");
		
		// shuffles the deck before dealing cards
		deck.shuffleDeck();
		
		// chooses which hand to be the Old Maid
    	if (Math.random() <= 0.5) {
            deck.deal(25, hand1);
            deck.deal(26, hand2);
            deck.deal(1, oldMaidCard);
            
        }
        else {
            deck.deal(26, hand1);
            deck.deal(25, hand2);
            deck.deal(1, oldMaidCard);
           
        }
    	
    	// removes doubles from both hands
        hand1.removeDoubles();
        hand2.removeDoubles();
    }
    /**
     * Launches the JavaFx application.
     * @param args command-line arguments.
     */
    public static void main(String [] args) {
        launch(args);
    }
}
