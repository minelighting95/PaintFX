package PaintFX;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Event Handler to Control Auto Save Window
 * 
 * @author Alex Appel
 */
public class autoHandler implements EventHandler<ActionEvent> {

    private static CheckBox ver;
    
    public void handle(ActionEvent e){
        
            Stage autoWindow = new Stage();                                     // Create new stage
            autoWindow.setTitle("Auto Save Settings");                          // Set Title and Logo
            autoWindow.getIcons().add(new Image(PaintFX.class.getResourceAsStream("logosmall.png")));
            
            GridPane auto = new GridPane();                                     // Create grid
            auto.setAlignment(Pos.CENTER);                                      // Center the grid
            auto.setHgap(10);                                                   // Set the gaps between grid elements to 10
            auto.setVgap(10);
            auto.setPadding(new Insets(25, 25, 25, 25));                        // Set border padding to 25
            
            Label ansTell = new Label("Auto Save Time (s): ");                  // Create new label "Auto Save Time (s): "
            auto.add(ansTell, 1, 1);                                            // Add text
            
            Label visTell = new Label("Timer Visible: ");                       // Create new label "Timer Visible: "
            auto.add(visTell, 1, 2);                                            // Add text
            
            TextField saver = new TextField();                                  // Create new text field (Time)
            auto.add(saver, 2, 1);                                              // Add field
            saver.setMaxWidth(50);                                              // Set width to 50
            saver.setText(PaintFX.getSaveName());                               // Set Text to Current Auto Save Time
            
            ver = new CheckBox();                                               // Create visible checkbox
            auto.add(ver, 3, 2);                                                // Add to window
            ver.setSelected(PaintFX.getSave());                                 // If save is visible, set
            
            Scene autoScene = new Scene(auto, 300, 170);                        // Create a 300 by 170 window
            autoWindow.setScene(autoScene);                                     // Place the scene on the main stage
            autoWindow.show();                                                  // Show main stage
            
            Button Sbtn = new Button("Set");                                    // Create a button saying "Set"
            HBox hSet = new HBox(10);                                           // Create a horizontal box for button
            hSet.setAlignment(Pos.CENTER);                                      // Align horizontal box to bottom center
            hSet.getChildren().add(Sbtn);                                       // Place the button in the horizontal box
            auto.add(hSet, 3, 1);                                               // Place the horizontal box on the bottom right grid box
            
            Button btn = new Button("Close");                                   // Create a button saying "Close"
            HBox hbBtn = new HBox(10);                                          // Create a horizontal box for button
            hbBtn.setAlignment(Pos.CENTER);                                     // Align horizontal box to bottom center
            hbBtn.getChildren().add(btn);                                       // Place the button in the horizontal box
            auto.add(hbBtn, 2, 3);                                              // Place the horizontal box on the bottom right grid box

            Sbtn.setOnAction(new EventHandler<ActionEvent>() {                  // When set button pressed

                @Override
                public void handle(ActionEvent a) {
                    String xVal = saver.getText();                              // Pulls typed time text
                    int x = Integer.parseInt(xVal);                             // Convert time text to Int
                    if(x <= 0){                                                 // If below min, set to min
                        x = 10;
                        saver.setText("10");
                    }
                    try{                                                        // Write time to file
                        File time = new File("save.tfx");                       // Make file
                        if (time.delete()) {} else {System.out.println("Del");} // File Conditions
                        if (time.createNewFile()) {} else {System.out.println("Create");}
                        FileWriter myWriter = new FileWriter(time);             // Create file writer
                        myWriter.write(saver.getText());                        // Write File
                        myWriter.close();
                        PaintFX.setSaveTime(x);                                 // Set Save Time
                    } catch (IOException e) {
                        System.out.println("Auto File Error");
                        e.printStackTrace();
                    }
                }
            });
            
            btn.setOnAction(new EventHandler<ActionEvent>() {                   // When close button pressed

                @Override
                public void handle(ActionEvent a) {
                    autoWindow.close();                                         // Exit window
                }
            });
            
            ver.setOnAction(new EventHandler<ActionEvent>() {                   // When check box changed

                @Override
                public void handle(ActionEvent a) {
                    if(ver.isSelected()){PaintFX.setSave(true);}                // Change save timer visibility
                    else{
                        PaintFX.setSave(false);
                    }
                }
            });
    }
}
