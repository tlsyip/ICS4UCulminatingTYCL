import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * JavaFx GUIDriver that acts as the main menu for card games "War" and "Old Maid".
 * This class gave selection to two games: Old Maid and War. 
 */
public class MainGUIDriver extends Application{
	/**
	 * JavaFx starting point. Creates and displays the main menu and buttons to each game. 
	 * @param stage the primary JavaFx stage
	 * @throws Exception if JavaFx fails to initialize the application
	 */
    public void start (Stage stage) throws Exception{
        Label title = new Label ("~~~~Card Games~~~~");
        Button btnGameType1= new Button ("Old Maid");
        Button btnGameType2= new Button ("War");
        btnGameType1.setStyle(
        		"-fx-background-color: lightsteelblue; " +
        	    "-fx-border-color: black; " +
        	    "-fx-border-width: 2;" +
        	    "-fx-text-fill: white;" 
        );
        btnGameType2.setStyle(
        		"-fx-background-color: moccasin; " +
        	    "-fx-border-color: black; " +
        	    "-fx-border-width: 2;" +
        	    "-fx-text-fill: darkolivegreen;"
        );
        
        Font smallFont = Font.font("Times New Roman", 25);
        Font bigFont = Font.font("Times New Roman", 50);
  
        btnGameType1.setPrefSize(155, 20); 
        btnGameType2.setPrefSize(155, 20); 
        
        title.setFont(bigFont);
        btnGameType1.setFont(smallFont);
        btnGameType2.setFont(smallFont);
        
        HBox buttonHolder = new HBox(20);
        buttonHolder.getChildren().addAll(btnGameType1,btnGameType2);
        buttonHolder.setAlignment(Pos.CENTER);

        // Create a layout object
        VBox layout = new VBox(30);
        layout.getChildren().addAll(title, buttonHolder);
        layout.setAlignment(Pos.CENTER);

        layout.setStyle("-fx-background-color:lavenderblush");
        

        Scene scene = new Scene(layout, 500, 500);   
        scene.setFill(Color.LAVENDERBLUSH);

        btnGameType1.setOnAction(e ->{
            stage.setScene(OldMaidGUIDriver.startScene(stage));
        });

        btnGameType2.setOnAction(e -> {
            stage.setScene(WarGUIDriver.startScene(stage));
        });

        stage.setScene(scene);
        stage.show();
    }
    /**
     * Create the main scene 
     * This methods allow other drivers to switch back to the main menu.
     * @param stage the primary JavaFx stage 
     * @return a represents the application's main menu. 
     */
    public static Scene startScene (Stage stage) {
        Label title = new Label ("~~~~Card Games~~~~");
        Button btnGameType1= new Button ("Old Maid");
        Button btnGameType2= new Button("War");
        btnGameType1.setStyle(
        		"-fx-background-color: lightsteelblue; " +
        	    "-fx-border-color: black; " +
        	    "-fx-border-width: 2;"
        );
        btnGameType2.setStyle(
        		"-fx-background-color: moccasin; " +
        	    "-fx-border-color: black; " +
        	    "-fx-border-width: 2;"
        );
        
  
        Font smallFont = Font.font("Times New Roman", 25);
        Font bigFont = Font.font("Times New Roman", 50);
  
        btnGameType1.setPrefSize(155, 20); 
        btnGameType2.setPrefSize(155, 20); 
        
        title.setFont(bigFont);
        btnGameType1.setFont(smallFont);
        btnGameType2.setFont(smallFont);
       

        HBox buttonHolder = new HBox(20);
        buttonHolder.getChildren().addAll(btnGameType1,btnGameType2);
        buttonHolder.setAlignment(Pos.CENTER);

        // Create a layout object
        VBox layout = new VBox(30);
        layout.getChildren().addAll(title, buttonHolder);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color:lavenderblush");
        

        Scene scene = new Scene(layout, 500, 500);   
        scene.setFill(Color.LAVENDERBLUSH);
       

        btnGameType1.setOnAction(e ->{
            stage.setScene(OldMaidGUIDriver.startScene(stage));
        });

        btnGameType2.setOnAction(e -> {
            stage.setScene(WarGUIDriver.startScene(stage));
        });

        return scene;
    }
    /**
     * Launches the JavaFx application.
     * @param args command-line arguments.
     */
     public static void main(String [] args) {
        launch(args);
    }
}


