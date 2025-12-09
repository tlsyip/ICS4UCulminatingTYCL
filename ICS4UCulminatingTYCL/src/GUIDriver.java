import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.geometry.Pos;
import java.util.Scanner;

public class GUIDriver extends Application{
        private static Scanner input = new Scanner(System.in);
        private static boolean playerTurnStatus = true;
        private static boolean opponentTurnStatus = false;
        private static boolean alreadyDrawn = false;
        private static boolean doublesRemoved = false;
        private static Deck deck = new Deck();
        private static Hand hand1 = new Hand();
        private static Hand hand2 = new Hand();

    public void start (Stage stage) throws Exception {
        Label title = new Label ("~~~~Old Maid~~~~");
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
        Label instructions = new Label ("The first player draws one card from their opponent and discards any\nresulting pair. That player then offers their hand to the next player.\nPlay continues this way until only one unpaired card remains and \nwhoever holds it is the Old Maid. Have fun!");
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
        gameSetup();
        Label optionsMenu = new Label ("~~~Options Menu~~~");
        Label errorMessage = new Label ("");
        Button btnDrawOpponent = new Button ("Draw from opponent");
        Button btnRemoveDoubles = new Button ("Remove doubles");
        Button btnEndTurn = new Button ("End turn");
        Button btnQuitGame = new Button ("Quit game");
  
        btnDrawOpponent.setPrefSize(155, 20); 
        btnRemoveDoubles.setPrefSize(155, 20); 
        btnEndTurn.setPrefSize(155, 20); 
        btnQuitGame.setPrefSize(155, 20); 

        // Create a layout object
        VBox layout = new VBox(5);
        layout.getChildren().addAll(optionsMenu, btnDrawOpponent, btnRemoveDoubles, btnEndTurn, btnQuitGame, errorMessage);
        layout.setAlignment(Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(layout, 500, 500);
        hand1.displayHand();

        while (opponentTurnStatus) {
                opponentTurn(stage);
        }
        
        btnDrawOpponent.setOnAction(e -> {
            if (!alreadyDrawn) {
                errorMessage.setText("");
                hand1.drawOpponent(hand2);
                alreadyDrawn = true;
                hand1.displayHand();
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
        });
        btnQuitGame.setOnAction(e -> System.exit(0));
        return scene;
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

    public static void gameSetup() {
        System.out.print("Game Setup Complete");
        // shuffles deck before dealing it
        deck.shuffleDeck();

        // randomly chooses the Old Maid
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
        // automatically removes doubles before game starts
        hand1.removeDoubles();
        hand2.removeDoubles();
        hand1.shuffleHand();
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

        // checks if opponent has won
        if(p1==0 && p2==1){
            System.out.println("You got rid of all your cards! Your opponent is the Old Maid!");
            endScenePlayerWin(stage);
        }
        // checks if user has won
        else if(p1==1 && p2==0){
            System.out.println("Your opponent got rid of all their cards. You are the Old Maid!");
            endScenePlayerLose(stage);
        }
    }

    public static void main(String [] args) {
        launch(args);
    }
}
