package PaintFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Event Handler to Control About Window
 * 
 * @author Alex Appel
 */
public class aboutHandler implements EventHandler<ActionEvent> {

    
    public void handle(ActionEvent e){
        
            Stage aboutWindow = new Stage();                                    // Create new stage
            aboutWindow.setTitle("About PaintFX");                              // Set Title and Logo
            aboutWindow.getIcons().add(new Image(PaintFX.class.getResourceAsStream("logosmall.png")));
            
            GridPane about = new GridPane();                                    // Create grid
            about.setAlignment(Pos.CENTER);                                     // Center the grid
            about.setHgap(10);                                                  // Set the gaps between grid elements to 10
            about.setVgap(10);
            about.setPadding(new Insets(25, 25, 25, 25));                       // Set border padding to 25

            Image logo = new Image("paintfxlogo.png");                          // Open logo
            ImageView logoIm = new ImageView();                                 // Create new image view
            logoIm.setImage(logo);                                              // Set Image
            logoIm.setPreserveRatio(true);                                      // Preserve Image Ratio
            logoIm.setFitWidth(400);                                            // Fit image to 400 x 300
            logoIm.setFitHeight(300);
            
            about.add(logoIm, 0, 1);                                            // Add Image to left box
            
            Text info = new Text("PaintFX v1.4.0\n"                             // Create about text
                    + "Oct. 8, 2021\n\n"
                    + "Programmed by Alex Appel\n"
                    + "CS 250 Paint Project\n");                               
            info.setFont(new Font("Arial", 18));                                // Set Font and Size
            info.setTextAlignment(TextAlignment.CENTER);                        // Align text to center
            about.add(info, 1, 1);                                              // Add label to right top box
            
            Scene aboutScene = new Scene(about, 640, 480);                      // Create a 640 by 480 window
            aboutWindow.setScene(aboutScene);                                   // Place the scene on the main stage
            aboutWindow.show();                                                 // Show main stage
            
            Button btn = new Button("Close");                                   // Create a button saying "Close"
            HBox hbBtn = new HBox(10);                                          // Create a horizontal box for button
            hbBtn.setAlignment(Pos.CENTER);                                     // Align horizontal box to bottom center
            hbBtn.getChildren().add(btn);                                       // Place the button in the horizontal box
            about.add(hbBtn, 1, 2);                                             // Place the horizontal box on the bottom right grid box

            btn.setOnAction(new EventHandler<ActionEvent>() {                   // When close button pressed

                @Override
                public void handle(ActionEvent a) {
                    aboutWindow.close();                                        // Exit window
                    }
            });
    }
    
}
