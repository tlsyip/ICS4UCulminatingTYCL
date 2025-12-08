import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.geometry.Pos;

public class GUIDriver extends Application{
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
        Label optionsMenu = new Label ("~~~Options Menu~~~");
        Button btnDrawOpponent = new Button ("Draw from opponent");
        Button btnRemoveDoubles = new Button ("Remove doubles");
        Button btnQuitGame = new Button ("Quit game");
  
        btnDrawOpponent.setPrefSize(155, 20); 
        btnRemoveDoubles.setPrefSize(155, 20); 
        btnQuitGame.setPrefSize(155, 20); 

        // Create a layout object
        VBox layout = new VBox(5);

        layout.getChildren().addAll(optionsMenu, btnDrawOpponent, btnRemoveDoubles, btnQuitGame);
        layout.setAlignment(Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(layout, 500, 500);

        btnQuitGame.setOnAction(e -> System.exit(0));

        return scene;
    }

    public static void main(String [] args) {
        launch(args);
    }
}
