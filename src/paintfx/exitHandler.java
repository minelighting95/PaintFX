package PaintFX;

import java.awt.Toolkit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class exitHandler implements EventHandler<WindowEvent> {

    public StackPane coolCrab;
    public Stage primaryStage;
    public TabPane tabPane;
    
    public exitHandler(StackPane coolCrab, Stage primaryStage, TabPane tabPane){
        
        this.coolCrab = coolCrab;
        this.primaryStage = primaryStage;
        this.tabPane = tabPane;
        
    }
    
    public static void exit(StackPane coolCrab, Stage primaryStage, TabPane tabPane){
        if((PaintFX.getChange() == 1) || (PaintFX.getChange1() == 1) || (PaintFX.getChange2() == 1)){
                    Stage saveWarnWindow = new Stage();                         // Create new stage
                    saveWarnWindow.setTitle("Warning!");                        // Set Title and Logo
                    saveWarnWindow.getIcons().add(new Image(PaintFX.class.getResourceAsStream("logosmall.png")));
                    
                    GridPane saveWarn = new GridPane();                         // Create grid
                    saveWarn.setAlignment(Pos.CENTER);                          // Center the grid
                    saveWarn.setHgap(10);                                       // Set the gaps between grid elements to 10
                    saveWarn.setVgap(10);
                    saveWarn.setPadding(new Insets(25, 25, 25, 25));            // Set border padding to 25
                    
                    Text warn = new Text("Would you like to save all your changes?");                               
                    warn.setFont(new Font("Arial", 18));                        // Set Font and Size
                    warn.setTextAlignment(TextAlignment.CENTER);                // Align text to center
                    saveWarn.add(warn, 1, 0);                                   // Add label to right top box
                    
                    Scene saveWarnScene = new Scene(saveWarn, 530, 100);        // Create a 500 by 100 window
                    saveWarnWindow.setScene(saveWarnScene);                     // Place the scene on the main stage
                    saveWarnWindow.show();                                      // Show main stage
            
                    Button yesBtn = new Button("Save All");                         // Create a button saying "Save"
                    HBox yBtn = new HBox(yesBtn);                               // Create a horizontal box for button
                    yBtn.setAlignment(Pos.CENTER);                              // Align horizontal box to bottom center
                    saveWarn.add(yBtn, 0, 1);                                   // Place the horizontal box
                    
                    Button noBtn = new Button("Don't Save");                    // Create a button saying "Don't Save"
                    HBox nBtn = new HBox(noBtn);                                // Create a horizontal box for button
                    nBtn.setAlignment(Pos.CENTER);                              // Align horizontal box to bottom center
                    saveWarn.add(nBtn, 1, 1);                                   // Place the horizontal box
                    
                    Button canBtn = new Button("Cancel");                       // Create a button saying "Cancel"
                    HBox cBtn = new HBox(canBtn);                               // Create a horizontal box for button
                    cBtn.setAlignment(Pos.CENTER);                              // Align horizontal box to bottom center
                    saveWarn.add(cBtn, 2, 1);                                   // Place the horizontal box
                    Toolkit.getDefaultToolkit().beep();

                    yesBtn.setOnAction(new EventHandler<ActionEvent>() {        // When close button pressed

                        @Override
                        public void handle(ActionEvent a) {
                            saveHandler.finalSave(coolCrab, primaryStage, tabPane);
                            saveWarnWindow.close();                             // Exit window
                            System.exit(0);                                     // Exit
                        }
                    });
                    noBtn.setOnAction(new EventHandler<ActionEvent>() {        // When close button pressed

                        @Override
                        public void handle(ActionEvent a) {
                            saveWarnWindow.close();                             // Exit window
                            System.exit(0);                                     // Exit
                        }
                    });
                    canBtn.setOnAction(new EventHandler<ActionEvent>() {        // When close button pressed

                        @Override
                        public void handle(ActionEvent a) {
                            saveWarnWindow.close();                             // Exit window
                        }
                    });
                }
                else{
                    System.exit(0);
                }
    }
    
    @Override
    public void handle(WindowEvent e){
        
                e.consume();                                                    // Halt Operations
                exit(coolCrab, primaryStage, tabPane);
    }
    
}
