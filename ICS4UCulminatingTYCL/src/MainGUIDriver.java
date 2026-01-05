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

public class MainGUIDriver extends Application{
    private static Scanner input = new Scanner(System.in);

    public void start (Stage stage) throws Exception{
        Label title = new Label ("~~~~Card Games~~~~");
        Button btnGameType1= new Button ("Old maid");
        Button btnGameType2= new Button("War");
  
        btnGameType1.setPrefSize(100, 20); 
        btnGameType2.setPrefSize(100, 20); 
       

        HBox buttonHolder = new HBox(20);
        buttonHolder.getChildren().addAll(btnGameType1,btnGameType2);
        buttonHolder.setAlignment(Pos.CENTER);

        // Create a layout object
        VBox layout = new VBox(30);
        layout.getChildren().addAll(title, buttonHolder);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 500);

        btnGameType1.setOnAction(e ->{
            stage.setScene(OldMaidGUIDriver.startScene(stage));
        });

        btnGameType2.setOnAction(e -> {
            stage.setScene(WarGUIDriver.startScene(stage));
        });

        stage.setScene(scene);
        stage.show();
    }

     public static void main(String [] args) {
        launch(args);
    }
}


