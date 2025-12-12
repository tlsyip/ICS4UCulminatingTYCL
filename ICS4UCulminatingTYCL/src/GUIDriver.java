import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class GUIDriver extends Application{
        private static Scanner input = new Scanner(System.in);
        private static boolean playerTurnStatus = true;
        private static boolean opponentTurnStatus = false;
        private static boolean alreadyDrawn = false;
        private static boolean doublesRemoved = false;
        private Pane handPane;
        private static Deck deck = new Deck();
        private static Hand hand1 = new Hand();
        private static Hand hand2 = new Hand();
        private final double CARD_WIDTH  = 80;
        private final double CARD_HEIGHT = 120;
        private final double CARD_OVERLAP = 50;

    public void start (Stage stage) throws Exception {     
        // create fonts
        Font TimesNewRomanFont = Font.font("Times New Roman", 50);
        Font VerdanaFont = Font.font("Verdana", 15);
        
        Label title = new Label ("~~~~Old Maid~~~~");
        Button btnGameStart = new Button ("Start Game");
        Button btnInstructions = new Button ("Instructions");
  
        btnGameStart.setPrefSize(155, 20); 
        btnInstructions.setPrefSize(155, 20); 

        // set text to font
        title.setFont(TimesNewRomanFont);
        btnGameStart.setFont(VerdanaFont);
        btnInstructions.setFont(VerdanaFont);

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
        // create fonts
        Font TimesNewRomanFont = Font.font("Times New Roman", 50);
        Font VerdanaFont = Font.font("Verdana", 12);
        
        Label title = new Label ("~~~~Instructions~~~~");
        Label instructions = new Label ("The first player draws one card from their opponent and discards any\nresulting pair. That player then offers their hand to the next player.\nPlay continues this way until only one unpaired card remains and \nwhoever holds it is the Old Maid. Have fun!");
        Button btnGameStart = new Button ("Start Game");
        
        //set text to fonts
        title.setFont(TimesNewRomanFont);
        instructions.setFont(VerdanaFont);
        btnGameStart.setFont(VerdanaFont);
        
        // Create a layout object
        VBox layout = new VBox(10);
        layout.getChildren().addAll(title, instructions, btnGameStart);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 500);

        btnGameStart.setOnAction(e -> stage.setScene(gameScene(stage)));

        return scene;
    }


    public Scene gameScene (Stage stage){
    	// create font
        Font VerdanaFont = Font.font("Verdana", 12);
    	
        Label optionsMenu = new Label ("~~~Options Menu~~~");
        Label errorMessage = new Label ("");
        Button btnDrawOpponent = new Button ("Draw from opponent");
        Button btnRemoveDoubles = new Button ("Remove doubles");
        Button btnEndTurn = new Button ("End turn");
        Button btnQuitGame = new Button ("Quit game");
        
        optionsMenu.setFont(VerdanaFont);
        errorMessage.setFont(VerdanaFont);
        btnDrawOpponent.setFont(VerdanaFont);
        btnRemoveDoubles.setFont(VerdanaFont);
        btnEndTurn.setFont(VerdanaFont);
        btnQuitGame.setFont(VerdanaFont);
  
        btnDrawOpponent.setPrefSize(155, 20); 
        btnRemoveDoubles.setPrefSize(155, 20); 
        btnEndTurn.setPrefSize(155, 20); 
        btnQuitGame.setPrefSize(155, 20); 

        // Create a layout object
        VBox layout = new VBox(5);
        layout.getChildren().addAll(optionsMenu, btnDrawOpponent, btnRemoveDoubles, btnEndTurn, btnQuitGame, errorMessage);
        layout.setAlignment(Pos.BOTTOM_RIGHT);
        layout.setPadding(new Insets(15));


        Scene scene = new Scene(layout, 500, 500);
        

        handPane = new Pane();
        handPane.setPrefSize(600,200);

        deck.shuffleDeck();

        if (Math.random() <= 0.5) {
            deck.deal(25, hand1);
            deck.deal(26, hand2);
        }
        else {
            deck.deal(26, hand1);
            deck.deal(25, hand2);
        }
        try {
            Card oldMaidCard = deck.drawDeck();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        hand1.removeDoubles();
        hand2.removeDoubles();
        renderHand(hand1);
        layout.getChildren().add(handPane);
        
        
        btnDrawOpponent.setOnAction(e -> {
            if (!alreadyDrawn) {
            	checkGameOver(stage);
                errorMessage.setText("");
                hand1.drawOpponent(hand2);
                alreadyDrawn = true;
                hand1.displayHand();
                hand2.displayHand();
                renderHand(hand1);
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
                hand1.displayHand();
                renderHand(hand1);
            }
            else {
                errorMessage.setText("All your doubles are removed.");
            }
        });
        btnEndTurn.setOnAction(e -> {
            errorMessage.setText("");
            playerTurnStatus = false;
            opponentTurnStatus = true;
            opponentTurn(stage);
            renderHand(hand1);
        });
        btnQuitGame.setOnAction(e -> System.exit(0));
        return scene;
    }
    
    private Pane createCardNode(Card card, double width, double height){

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
//        System.out.println("DEBUG suit from getSuit(): [" + suit + "], card: " + card.toString());
        if (suit.contains("♥") || suit.contains("♦")) {
            txt.setFill(Color.RED);
        } else {
            txt.setFill(Color.BLACK);
        }
        container.getChildren().addAll(rect, txt);
        return container;

    }  

    public static void endScenePlayerWin (Stage stage) {
        Label winMessage = new Label ("You got rid of all your cards! Your opponent is the Old Maid!");
        // Create a layout object
        VBox layout = new VBox(10);
        layout.getChildren().addAll(winMessage);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 500, 500);
        stage.setScene(scene);
        stage.show();
    }
    
    private void renderHand(Hand hand){
        handPane.getChildren().clear();

        double startX= 30;
        double startY=30;

        for(int i=0;i<hand.getSize(); i++){
            Card card= hand.getCard(i);

            Pane cardNode = createCardNode(card, CARD_WIDTH,CARD_HEIGHT);
            cardNode.setLayoutX(startX +i*CARD_OVERLAP);
            cardNode.setLayoutY(startY);
            
            handPane.getChildren().add(cardNode);
        }
    }

    public static void endScenePlayerLose (Stage stage) {
        Label loseMessage = new Label ("Your opponent got rid of all their cards. You are the Old Maid!");
        // Create a layout object
        VBox layout = new VBox(10);
        layout.getChildren().addAll(loseMessage);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void opponentTurn(Stage stage) {
        hand2.drawOpponent(hand1);
        // checks if the user is out of cards
        checkGameOver(stage);
        hand2.removeDoubles();
        // checks if the opponent is out of cards
        checkGameOver(stage);
        System.out.println("Opponent turn finished.\nYour turn!");
        opponentTurnStatus = false;
        playerTurnStatus = true;
        alreadyDrawn = false;
        doublesRemoved = false;
    }

    private static void checkGameOver(Stage stage){
        int p1 = hand1.getSize();
        int p2 = hand2.getSize();

        // checks if user has won
        if(p1==0 && p2!=0){
            System.out.println("You got rid of all your cards! Your opponent is the Old Maid!");
            endScenePlayerWin(stage);
        }
        // checks if user has lost
        else if(p1!=0 && p2==0){
            System.out.println("Your opponent got rid of all their cards. You are the Old Maid!");
            endScenePlayerLose(stage);
        }
    }

    public static void main(String [] args) {
        launch(args);
    }
}
