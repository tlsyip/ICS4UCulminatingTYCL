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
     * 
     */
    public void start (Stage stage) throws Exception {
    	deck = deck.resetDeck();
        Label title = new Label ("~~~~Old Maid~~~~");
        Button btnGameStart = new Button ("Start Game");
        Button btnInstructions = new Button ("Instructions");
        
        Font smallFont = Font.font("Times New Roman", 25);
        Font bigFont = Font.font("Times New Roman", 50);
  
        btnGameStart.setPrefSize(155, 30); 
        btnInstructions.setPrefSize(155, 30); 
        title.setFont(bigFont);
        btnGameStart.setFont(smallFont);
        btnInstructions.setFont(smallFont);

        HBox buttonHolder = new HBox(20);
        buttonHolder.getChildren().addAll(btnGameStart, btnInstructions);
        buttonHolder.setAlignment(Pos.CENTER);

        // Create a layout object
        VBox layout = new VBox(30);
        layout.getChildren().addAll(title, buttonHolder);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color:lightsteelblue");

        Scene scene = new Scene(layout, 500, 500);
        scene.setFill(Color.LIGHTSTEELBLUE);
        Scene scene1 = gameScene(stage);
        Scene scene2 = instructionsScene(stage);

        btnGameStart.setOnAction(e -> stage.setScene(scene1));
        btnInstructions.setOnAction(e -> stage.setScene(scene2));

        stage.setScene(scene);
        stage.show();
    }
    
    public static Scene startScene (Stage stage) {
    	 Label title = new Label ("~~~~Old Maid~~~~");
         Button btnGameStart = new Button ("Start Game");
         Button btnInstructions = new Button ("Instructions");
         
         Font smallFont = Font.font("Times New Roman", 25);
         Font bigFont = Font.font("Times New Roman", 50);
   
         btnGameStart.setPrefSize(155, 20); 
         btnInstructions.setPrefSize(155, 20); 
         
         title.setFont(bigFont);
         btnGameStart.setFont(smallFont);
         btnInstructions.setFont(smallFont);

         HBox buttonHolder = new HBox(20);
         buttonHolder.getChildren().addAll(btnGameStart, btnInstructions);
         buttonHolder.setAlignment(Pos.CENTER);

         // Create a layout object
         VBox layout = new VBox(30);
         layout.getChildren().addAll(title, buttonHolder);
         layout.setAlignment(Pos.CENTER);
         layout.setStyle("-fx-background-color:lightsteelblue");

         Scene scene = new Scene(layout, 500, 500);
         scene.setFill(Color.LIGHTSTEELBLUE);
         Scene scene1 = gameScene(stage);
         Scene scene2 = instructionsScene(stage);

         btnGameStart.setOnAction(e -> stage.setScene(scene1));
         btnInstructions.setOnAction(e -> stage.setScene(scene2));

         return scene;
    }

    
    public static Scene instructionsScene (Stage stage){
        Label title = new Label ("~~~~Instructions~~~~");
        Label instructions = new Label ("The first player draws one card from their opponent and discards any\nresulting pair. That player then offers their hand to the next player.\nPlay continues this way until only one unpaired card remains and \nwhoever holds it is the Old Maid. Have fun!");
        Button btnGameStart = new Button ("Start Game");
        
        Font smallFont = Font.font("Times New Roman", 16);
        Font bigFont = Font.font("Times New Roman", 50);
        
        title.setFont(bigFont);
        btnGameStart.setFont(smallFont);
        instructions.setFont(smallFont);
        
        // Create a layout object
        VBox layout = new VBox(10);
        layout.getChildren().addAll(title, instructions, btnGameStart);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color:lightsteelblue");

        Scene scene = new Scene(layout, 500, 500);
        scene.setFill(Color.LIGHTSTEELBLUE);

        btnGameStart.setOnAction(e -> stage.setScene(gameScene(stage)));

        return scene;
    }


    public static Scene gameScene (Stage stage){
    	
//        gameSetup();
        Label optionsMenu = new Label ("~~~Options Menu~~~");
        Label errorMessage = new Label ("");
        Button btnDrawOpponent = new Button ("Draw from opponent");
        Button btnRemoveDoubles = new Button ("Remove doubles");
        Button btnEndTurn = new Button ("End turn");
        Button btnQuitGame = new Button ("Quit game");
        
        Font smallFont = Font.font("Times New Roman", 15);
        Font bigFont = Font.font("Times New Roman", 20);
  
        btnDrawOpponent.setPrefSize(155, 20); 
        btnRemoveDoubles.setPrefSize(155, 20); 
        btnEndTurn.setPrefSize(155, 20); 
        btnQuitGame.setPrefSize(155, 20); 
        
        btnDrawOpponent.setFont(smallFont); 
        btnRemoveDoubles.setFont(smallFont); 
        btnEndTurn.setFont(smallFont);  
        btnQuitGame.setFont(smallFont); 
        errorMessage.setFont(smallFont); 
        optionsMenu.setFont(bigFont); 
        errorMessage.setTextFill(Color.RED);

        // Create a layout object
        VBox layout = new VBox(1);
        layout.getChildren().addAll(optionsMenu, btnDrawOpponent, btnRemoveDoubles, btnEndTurn, btnQuitGame, errorMessage);
        layout.setAlignment(Pos.BOTTOM_RIGHT);
        layout.setPadding(new Insets(15));
        layout.setStyle("-fx-background-color:lightsteelblue");


        Scene scene = new Scene(layout, 500, 500);
        scene.setFill(Color.LIGHTSTEELBLUE);
        

        handPane = new Pane();
        oppPane = new Pane();
        handPane.setPrefSize(600,200);
        oppPane.setPrefSize(600,200);

        resetGame();
        renderHand(hand1, handPane);
        renderHand(hand2, oppPane);
        layout.getChildren().addAll(oppPane, handPane);
        
        
        btnDrawOpponent.setOnAction(e -> {
            if (!alreadyDrawn) {
            	errorMessage.setText("");
            	checkGameOver(stage);
                hand1.drawOpponent(hand2);
                checkGameOver(stage);
                alreadyDrawn = true;
              //  hand1.displayHand();
                renderHand(hand1, handPane);
                renderHand(hand2, oppPane);
                System.out.println("Drawed from opponent.");
            } 
            else {
                errorMessage.setText("You already picked from your opponent.");
            }
        });
        btnRemoveDoubles.setOnAction(e -> {
            if (!doublesRemoved) {
                errorMessage.setText("");
                hand1.removeDoubles();
                doublesRemoved = true;
            //    hand1.displayHand();
                checkGameOver(stage);
                renderHand(hand1, handPane);
                renderHand(hand2, oppPane);
                System.out.println("Removed doubles.");
            }
            else {
                errorMessage.setText("All your doubles are removed.");
            }
        });
        btnEndTurn.setOnAction(e -> {
        	System.out.println("Turn Ended");
            errorMessage.setText("");
            errorMessage.setText("Opponent Turn...");
            opponentTurn(stage);
            renderHand(hand1, handPane);
            renderHand(hand2, oppPane);
            errorMessage.setText("Opponent Turn Finished.");
        });
        btnQuitGame.setOnAction(e -> {
        	System.out.println("Returning to Main Menu...");
        	stage.setScene(MainGUIDriver.startScene(stage));
        });
        return scene;
    }
    
    /**
     * 
     * @param card
     * @param width
     * @param height
     * @return Pane with front of card images
     */
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

    public static void endScenePlayerWin (Stage stage) {
        Label winMessage = new Label ("You got rid of all your cards! Your opponent is the Old Maid!");
        Label lblOldMaidCard = new Label ("The Old Maid Card was: ");
        Button btnMainMenu = new Button ("Return to Main Menu");
        btnMainMenu.setPrefSize(200, 20);
        // Create a layout object
        VBox layout = new VBox(10);
        finalPane = new Pane();
        layout.setAlignment(Pos.CENTER);
        renderHand(oldMaidCard, finalPane);
        btnMainMenu.setOnAction(e ->  {
        	stage.setScene(MainGUIDriver.startScene(stage));
        	oldMaidCard.removeCard(0);
        });
        layout.getChildren().addAll(winMessage, lblOldMaidCard, finalPane, btnMainMenu);
        layout.setStyle("-fx-background-color:palegreen");
        Scene scene = new Scene(layout, 500, 500);
        scene.setFill(Color.PALEGREEN);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void endScenePlayerLose (Stage stage) {
        Label loseMessage = new Label ("Your opponent got rid of all their cards. You are the Old Maid!");
        Label lblOldMaidCard = new Label ("The Old Maid Card was: ");
        Button btnMainMenu = new Button ("Return to Main Menu");
        btnMainMenu.setPrefSize(200, 20);
     // Create a layout object
        VBox layout = new VBox(10);
        finalPane = new Pane();
        layout.setAlignment(Pos.CENTER);
        renderHand(oldMaidCard, finalPane); 
        btnMainMenu.setOnAction(e ->  {
        	stage.setScene(MainGUIDriver.startScene(stage));
        	oldMaidCard.removeCard(0);
        });
        layout.getChildren().addAll(loseMessage, lblOldMaidCard, finalPane, btnMainMenu);
        layout.setStyle("-fx-background-color:lightcoral");
        Scene scene = new Scene(layout, 500, 500);
        scene.setFill(Color.LIGHTCORAL);
        stage.setScene(scene);
        stage.show();
    }
    
    private static void renderHand(Hand hand, Pane pane){

        pane.getChildren().clear();

        double startX = 30;
        double startY = 30;
        double oppStartY = 10;

        if (hand.getName().equals("hand1")) {
	        for(int i=0;i<hand.getSize(); i++){
	            Card card= hand.getCard(i);
	
	            Pane cardNode = createFrontCardNode(card, CARD_WIDTH,CARD_HEIGHT);
	            cardNode.setLayoutX(startX +i*CARD_OVERLAP);
	            cardNode.setLayoutY(startY);
	            
	            pane.getChildren().add(cardNode);
	        }
        }
        
        else if (hand.getName().equals("hand2")) {
	        for(int i=0;i<hand.getSize(); i++){
	            Card card= hand.getCard(i);
	
	            Pane cardNode = createBackCardNode(card, CARD_WIDTH,CARD_HEIGHT);
	            cardNode.setLayoutX(startX +i*CARD_OVERLAP);
	            cardNode.setLayoutY(oppStartY);
	            
	            pane.getChildren().add(cardNode);
	        }
        }
        else if (hand.getName().equals("oldMaidCard")) {
        	pane.getChildren().clear();
	            Card card= hand.getCard(0);
	
	            Pane cardNode = createFrontCardNode(card, CARD_WIDTH,CARD_HEIGHT);
	            cardNode.setLayoutX(210);
	            cardNode.setLayoutY(30);
	            
	            pane.getChildren().add(cardNode);
        }
    }

    public static void opponentTurn(Stage stage) {
    	checkGameOver(stage);
        hand2.drawOpponent(hand1);
        // checks if the user is out of cards
        checkGameOver(stage);
        hand2.removeDoubles();
        // checks if the opponent is out of cards
        checkGameOver(stage);
        System.out.println("Opponent turn finished.\nYour turn!");
        alreadyDrawn = false;
        doublesRemoved = false;
    }

    private static void checkGameOver(Stage stage){
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
    
    private static void resetGame() {
		deck = new Deck();
		hand1 = new Hand("hand1");
		hand2 = new Hand("hand2");
		oldMaidCard = new Hand ("oldMaidCard");
		deck.shuffleDeck();
		
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
        

        hand1.removeDoubles();
        hand2.removeDoubles();
    }

    public static void main(String [] args) {
        launch(args);
    }
}
