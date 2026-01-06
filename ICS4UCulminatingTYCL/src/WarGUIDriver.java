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


public class WarGUIDriver extends Application{
    private static Hand hand1 = new Hand("hand1");
    private static Hand hand2 = new Hand("hand2");
    private static Hand tempHand = new Hand("tempHand");
    private static Deck deck = new Deck();
    private static Pane handPane;
    private static Pane oppPane;
    private static Pane finalPane;

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
     * Creates and shows main menu 
     * @param stage main JavaFx stage 
     * @throws Exception ifJavaFx cannot initialize application
     */
    public void start (Stage stage) throws Exception {
        playerDeckSize = new Label("");
        opponentDeckSize = new Label("");
        
        Label title = new Label ("~~~~War~~~~");
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
        layout.setStyle("-fx-background-color:moccasin");

        Scene scene = new Scene(layout, 500, 500);
        scene.setFill(Color.MOCCASIN);
        Scene scene1 = gameScene(stage);  
        Scene scene2 = instructionsScene(stage);

        btnGameStart.setOnAction(e -> stage.setScene(scene1));
        btnInstructions.setOnAction(e -> stage.setScene(scene2));

        stage.setScene(scene);
        stage.show();
    }
    /*
     * Creates the main menu for application.
     * @param stage main JavaFx stage used for switching scenes 
     * @return a Scene containing the main menu 
     */

    public static Scene startScene (Stage stage) {
        playerDeckSize = new Label("");
        opponentDeckSize = new Label("");
        
        Label title = new Label ("~~~~War~~~~");
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
        layout.setStyle("-fx-background-color:moccasin");

        Scene scene = new Scene(layout, 500, 500);
        scene.setFill(Color.MOCCASIN);
        Scene scene1 = gameScene(stage);  
        Scene scene2 = instructionsScene(stage);

        btnGameStart.setOnAction(e -> stage.setScene(scene1));
        btnInstructions.setOnAction(e -> stage.setScene(scene2));

        return scene;
    }
    /*
     * creates the instructions describing rules of War.
     * @param stage main JavaFx stage used for switching scenes
     * @return a Scene containing instructions and start button 
     */

    public static Scene instructionsScene (Stage stage){
        Label title = new Label ("~~~~Instructions~~~~");
        Label instructions = new Label ("Simultaneously flip your top card; the higher card wins both and the\nplayer who winns adds both cards to their pile. Ties trigger a 'War' where \nplayers lay three cards down (higher card wins all). Play goes on until one \nplayer has all the cards, using 2 as the lowest value card and Ace as the \nhighest. Have fun!");
        Button btnGameStart = new Button ("Start Game");
        
        // Create a layout object
        VBox layout = new VBox(10);
        layout.getChildren().addAll(title, instructions, btnGameStart);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color:moccasin");
        
        Font smallFont = Font.font("Times New Roman", 16);
        Font bigFont = Font.font("Times New Roman", 50);
        
        title.setFont(bigFont);
        btnGameStart.setFont(smallFont);
        instructions.setFont(smallFont);

        Scene scene = new Scene(layout, 500, 500);
        scene.setFill(Color.MOCCASIN);
        

        btnGameStart.setOnAction(e -> stage.setScene(gameScene(stage)));
        

        return scene;
    }
    /*
     * creates the game scene and make a new game state. 
     * controls the flip, nextround, quit
     * deals with a new deck
     * @param stage main JavaFx stage used for switching scenes
     * @return a Scene containing the main gameplay
     */
    public static Scene gameScene (Stage stage){
        Label optionsMenu = new Label ("~~~Options Menu~~~");
        Label errorMessage = new Label ("");
        Button btnFlipCard = new Button ("Flip card");      
        Button btnNextRound = new Button ("Next Round");
        Button btnQuitGame= new Button("Quit Game");
        Font smallFont = Font.font("Times New Roman", 15);
        Font bigFont = Font.font("Times New Roman", 20);
            
        btnQuitGame.setFont(smallFont); 
        errorMessage.setFont(smallFont); 
        optionsMenu.setFont(bigFont); 
        btnFlipCard.setFont(smallFont);
        btnNextRound.setFont(smallFont);
        playerDeckSize.setFont(smallFont);
        opponentDeckSize.setFont(smallFont);
        errorMessage.setTextFill(Color.RED);
        
        btnFlipCard.setPrefSize(155, 20);        
        btnNextRound.setPrefSize(155, 20);
        btnQuitGame.setPrefSize(155, 20); 
    
     
        // Create a layout object
        VBox layout = new VBox(1);
        layout.getChildren().addAll(optionsMenu,btnFlipCard, btnNextRound, btnQuitGame, opponentDeckSize, playerDeckSize, errorMessage);
        layout.setAlignment(Pos.BOTTOM_RIGHT);
        layout.setPadding(new Insets(15));
        layout.setStyle("-fx-background-color:moccasin");
        

        Scene scene = new Scene(layout, 500, 500);   
        scene.setFill(Color.MOCCASIN);

        handPane = new Pane();
        handPane.setPrefSize(600,200);
        oppPane = new Pane();
        oppPane.setPrefSize(600,200);
        resetGame();
  
        renderHand(hand1, handPane);
        renderHand(hand2, oppPane);
        layout.getChildren().addAll(oppPane, handPane);

        btnFlipCard.setOnAction(e -> {
            errorMessage.setText("");
            if (!flipCard) {
                flipCard(stage);
                flipCard = true;
                nextRound = false;
            }
            else {
                errorMessage.setText("Card already flipped.");
            }
        });
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
                errorMessage.setText("Card not flipped yet.");
            }
        });
        btnQuitGame.setOnAction(e -> {
        	System.out.println("Returning to Main Menu...");
        	stage.setScene(MainGUIDriver.startScene(stage));
        });
    
        return scene;
      
     }
    /*
     * renders a hand onto target using car back images
     * @param hand the Hand to render
     * @param pane the Pane to draw into
     */

     private static void renderHand(Hand hand, Pane pane){
        pane.getChildren().clear();
        
        double startX = 210;
        double startY = 15;
        double oppStartY = 10;

        if (hand.getName().equals("hand1")) {
	        for(int i=0;i<hand.getSize(); i++){
	            Card card= hand.getCard(i);
	
	            Pane cardNode = createCardNode(card, CARD_WIDTH,CARD_HEIGHT, "back");
	            cardNode.setLayoutX(startX);
	            cardNode.setLayoutY(startY);
	            
	            pane.getChildren().add(cardNode);
	        }
        }
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

    public static void flipCard(Stage stage){
        handPane.getChildren().clear();
        oppPane.getChildren().clear();

        double startX = 210;
        double startY = 15;
        double oppStartX = 210;
        double oppStartY = 10;

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
        System.out.println("Flipped card.");
        updateCounter();
        playerCurrCard.compare(opponentCurrCard);
        checkGameOver(stage);
        if (playerCurrCard.getStatus()==2) {
        	battle(stage);
        }
        checkGameOver(stage);
    }

    public static void nextRound() {
    	System.out.println("Started next round.");
        if (playerCurrCard.getStatus()==0) {
            hand1.addCard(opponentCurrCard);
            hand1.addCard(playerCurrCard);
            hand1.removeCard(0);
            hand2.removeCard(0);
            updateCounter();
        }
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

    private static void checkGameOver(Stage stage){
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

    private static void battle(Stage stage) {
        if (hand1.getSize() <= 3) {
            checkGameOver(stage);
            System.out.println("You lose, your opponent does not have enough cards for war.");
            endScenePlayerLose(stage);
            
        }
        if(hand2.getSize() <= 3){
            checkGameOver(stage);
            System.out.println("You win, your opponent does not have enough cards for war.");
            endScenePlayerWin(stage);
        }
        handPane.getChildren().clear();
        oppPane.getChildren().clear();
        double startX = 30;
        double startY = 30;
        double oppStartY = 30;
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
        checkGameOver(stage);
        playerCurrCard.setStatus(3);
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

    public static void updateCounter(){
        playerDeckSize.setText("Your # of Cards: " + Integer.toString(hand1.getSize()));
        opponentDeckSize.setText("Opponent # of Cards: " + Integer.toString(hand2.getSize()));
    }

     public static void endScenePlayerWin (Stage stage) {
       Label winMessage = new Label ("You Win! You have won the war!");
        Button btnMainMenu = new Button ("Return to Main Menu");
        btnMainMenu.setPrefSize(200, 20);
        // Create a layout object
        VBox layout = new VBox(10);
        finalPane = new Pane();
        layout.setAlignment(Pos.CENTER);
        btnMainMenu.setOnAction(e ->  {
        	stage.setScene(MainGUIDriver.startScene(stage));
        });
        layout.getChildren().addAll(winMessage, finalPane, btnMainMenu);
        layout.setStyle("-fx-background-color:palegreen");
        Scene scene = new Scene(layout, 500, 500);
        scene.setFill(Color.PALEGREEN);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void endScenePlayerLose (Stage stage) {
        Label loseMessage = new Label ("You Lost! Your opponent has won the war!");
        Button btnMainMenu = new Button ("Return to Main Menu");
        btnMainMenu.setPrefSize(200, 20);
        // Create a layout object
        VBox layout = new VBox(10);
        finalPane = new Pane();
        layout.setAlignment(Pos.CENTER);
        btnMainMenu.setOnAction(e ->  {
        	stage.setScene(MainGUIDriver.startScene(stage));
        });
        layout.getChildren().addAll(loseMessage, finalPane, btnMainMenu);
        layout.setStyle("-fx-background-color:lightcoral");
        Scene scene = new Scene(layout, 500, 500);
        scene.setFill(Color.LIGHTCORAL);
        stage.setScene(scene);
        stage.show();
    }
    
    private static void resetGame() {
    	 flipCard = false;
    	 nextRound = true;
    	 deck = new Deck();
    	 hand1 = new Hand("hand1");
    	 hand2 = new Hand("hand2");
    	 tempHand = new Hand("tempHand");
    	 deck.shuffleDeck();
         
         deck.deal(26, hand1);
         deck.deal(26, hand2);
         updateCounter();
    }

    public static void main(String [] args) {
        launch(args);
    } 


}
