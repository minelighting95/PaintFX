package PaintFX;

import java.awt.Toolkit;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Event Handler to Open a File in PaintFX
 * 
 * @author Alex Appel
 */
public class openHandler implements EventHandler<ActionEvent> {

    private Stage primaryStage;
    private ImageView cool;
    private ImageView cool1;
    private ImageView cool2;
    private BorderPane border;
    private Canvas canvas;
    private Canvas canvas1;
    private Canvas canvas2;
    private double w;
    private double h;
    private VBox vBox;
    private GraphicsContext gc;
    private GraphicsContext gc1;
    private GraphicsContext gc2;
    private StackPane coolCrab;
    private ScrollPane sp;
    private TabPane tabPane;
    
    private void open(){
        try{                                                                    // Attempt...
            FileChooser fileChooser = new FileChooser();                        // Create file chooser
            fileChooser.setTitle("Open Picture...");                            // Named Open Picture
            fileChooser.getExtensionFilters().addAll(                           // Add image filters
                new FileChooser.ExtensionFilter("All Supported Files", "*.jpg", "*.png", "*.bmp", "*.gif")
                ,new FileChooser.ExtensionFilter("JPG Images", "*.jpg")
                ,new FileChooser.ExtensionFilter("PNG Images", "*.png")
                ,new FileChooser.ExtensionFilter("BitMap Images", "*.bmp")
                ,new FileChooser.ExtensionFilter("GIF File", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(primaryStage);       // Grab user selected file
            PaintFX.setName(selectedFile.getName());
            
            Image crab = new Image(selectedFile.toURI().toString());            // Open user image
            
            if(PaintFX.getSelec() == 0){
                PaintFX.setFileName(selectedFile.toURI().toString().substring(6));// Save file name as string
                PaintFX.setChange(0);
                tabPane.getTabs().get(0).setText(selectedFile.getName());       // Set Tab Name
                cool.setFitHeight(0);                                           // Set Fit Dimensions
                cool.setFitWidth(0);
                cool.setImage(crab);                                            // Set Image
                PaintFX.setImage(crab);
                cool.setPreserveRatio(true);                                    // Preserve Image Ratio
            }
            else if(PaintFX.getSelec() == 1){
                PaintFX.setFileName1(selectedFile.toURI().toString().substring(6));// Save file name as string
                PaintFX.setChange1(0);
                tabPane.getTabs().get(1).setText(selectedFile.getName());       // Set Tab Name
                cool1.setFitHeight(0);                                          // Set Fit Dimensions
                cool1.setFitWidth(0);
                cool1.setImage(crab);                                           // Set Image
                PaintFX.setImage1(crab);
                cool1.setPreserveRatio(true);                                   // Preserve Image Ratio
            }
            else if(PaintFX.getSelec() == 2){
                PaintFX.setFileName2(selectedFile.toURI().toString().substring(6));// Save file name as string
                PaintFX.setChange2(0);
                tabPane.getTabs().get(2).setText(selectedFile.getName());       // Set Tab Name
                cool2.setFitHeight(0);                                          // Set Fit Dimensions
                cool2.setFitWidth(0);
                cool2.setImage(crab);                                           // Set Image
                PaintFX.setImage2(crab);
                cool2.setPreserveRatio(true);                                   // Preserve Image Ratio
            }
            else{
               System.out.println("Open Error 0");
            }
            
                
            border.setTop(vBox);                                                // Set the menu bar at the top of the screen

            w = crab.getWidth();                                                // Record image dimensions
            h = crab.getHeight();     
            
            if(PaintFX.getSelec() == 0){
                canvas.setWidth(w);                                             // Set canvas to the current dimensions
                canvas.setHeight(h);
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());      // Clear Canvas
            }
            else if(PaintFX.getSelec() == 1){
                canvas1.setWidth(w);                                            // Set canvas to the current dimensions
                canvas1.setHeight(h);
                gc1.clearRect(0, 0, canvas1.getWidth(), canvas1.getHeight());   // Clear Canvas
            }
            else if(PaintFX.getSelec() == 2){
                canvas2.setWidth(w);                                            // Set canvas to the current dimensions
                canvas2.setHeight(h);
                gc2.clearRect(0, 0, canvas2.getWidth(), canvas2.getHeight());   // Clear Canvas
            }
            else{
                System.out.println("Open Error 1");
            }
            
            coolCrab.setScaleX(1);                                              // Remove Zoom
            coolCrab.setScaleY(1);
            PaintFX.setScaleX(1);                                               // Remove Scale
            PaintFX.setScaleY(1);
            coolCrab.getChildren().clear();                                     // Remove previous image
            
            if(PaintFX.getSelec() == 0){
                PaintFX.canvasClear();                                          // Clear Undo/Redo Stacks
                PaintFX.redoClear();
                PaintFX.coolClear();
                PaintFX.recoolClear();
                PaintFX.canvasPush(canvas);                                     // Add new canvas and image
                PaintFX.coolPush(cool);
                coolCrab.getChildren().addAll(cool, canvas);                    // Add new image
            }
            else if(PaintFX.getSelec() == 1){
                PaintFX.canvas1Clear();                                         // Clear Undo/Redo Stacks
                PaintFX.redo1Clear();
                PaintFX.recool1Clear();
                PaintFX.cool1Clear();
                PaintFX.canvas1Push(canvas1);                                   // Add new canvas and image
                PaintFX.cool1Push(cool1);
                coolCrab.getChildren().addAll(cool1, canvas1);                  // Add new image
            }
            else if(PaintFX.getSelec() == 2){
                PaintFX.canvas2Clear();                                         // Clear Undo/Redo Stacks
                PaintFX.redo2Clear();
                PaintFX.recool2Clear();
                PaintFX.cool2Clear();
                PaintFX.canvas2Push(canvas2);                                   // Add new canvas and image
                PaintFX.cool2Push(cool2);
                coolCrab.getChildren().addAll(cool2, canvas2);                  // Add new image
            }
            else{
                System.out.println("Open Error 2");
            }
            
            sp.setContent(coolCrab);                                            // Place stack on ScrollPane

            PaintFX.setVariables(w, h);                                         // Set variables
            PaintFX.logItem("Open", 2);
        }
        catch(Exception f){}                                                    // If invalid image or closed explorer
    }
    
    /**
     * Event Handler to open a new Image
     * @param primaryStage Main Program Stage
     * @param cool Tab 1 Image
     * @param cool1 Tab 2 Image
     * @param cool2 Tab 3 Image
     * @param border Main Program BorderPane
     * @param canvas Tab 1 Canvas
     * @param canvas1 Tab 2 Canvas
     * @param canvas2 Tab 3 Canvas
     * @param vBox Main Program VBox
     * @param gc Tab 1 GraphicsContext
     * @param gc1 Tab 2 GraphicsContext
     * @param gc2 Tab 3 GraphicsContext
     * @param coolCrab Main Program StackPane
     * @param sp Main Program ScrollPane
     * @param tabPane Main Program TabPane
     */
    public openHandler(Stage primaryStage, ImageView cool, ImageView cool1, ImageView cool2, BorderPane border, Canvas canvas, Canvas canvas1, 
            Canvas canvas2, VBox vBox, GraphicsContext gc, GraphicsContext gc1, GraphicsContext gc2, StackPane coolCrab, ScrollPane sp, TabPane tabPane){
        
        this.primaryStage = primaryStage;
        this.cool = cool;
        this.cool1 = cool1;
        this.cool2 = cool2;
        this.border = border;
        this.canvas = canvas;
        this.canvas1 = canvas1;
        this.canvas2 = canvas2;
        this.vBox = vBox;
        this.gc = gc;
        this.gc1 = gc1;
        this.gc2 = gc2;
        this.coolCrab = coolCrab;
        this.sp = sp;
        this.tabPane = tabPane;
      
    }
    
    @Override
    public void handle(ActionEvent e) {
        
        if((PaintFX.getSelec() == 0) && (PaintFX.getChange() == 1)){            // Unsaved content
            Stage saveWarnWindow = new Stage();                                 // Create new stage
            saveWarnWindow.setTitle("Warning!");                                // Set Title and Logo
            saveWarnWindow.getIcons().add(new Image(PaintFX.class.getResourceAsStream("logosmall.png")));
                
            GridPane saveWarn = new GridPane();                                 // Create grid
            saveWarn.setAlignment(Pos.CENTER);                                  // Center the grid
            saveWarn.setHgap(10);                                               // Set the gaps between grid elements to 10
            saveWarn.setVgap(10);
            saveWarn.setPadding(new Insets(25, 25, 25, 25));                    // Set border padding to 25
                    
            Text warn = new Text("Would you like to save your changes?");                               
            warn.setFont(new Font("Arial", 18));                                // Set Font and Size
            warn.setTextAlignment(TextAlignment.CENTER);                        // Align text to center
            saveWarn.add(warn, 1, 0);                                           // Add label to right top box
                    
            Scene saveWarnScene = new Scene(saveWarn, 500, 100);                // Create a 500 by 100 window
            saveWarnWindow.setScene(saveWarnScene);                             // Place the scene on the main stage
            saveWarnWindow.show();                                              // Show main stage
            
            Button yesBtn = new Button("Save");                                 // Create a button saying "Save"
            HBox yBtn = new HBox(yesBtn);                                       // Create a horizontal box for button
            yBtn.setAlignment(Pos.CENTER);                                      // Align horizontal box to bottom center
            saveWarn.add(yBtn, 0, 1);                                           // Place the horizontal box
                    
            Button noBtn = new Button("Don't Save");                            // Create a button saying "Don't Save"
            HBox nBtn = new HBox(noBtn);                                        // Create a horizontal box for button
            nBtn.setAlignment(Pos.CENTER);                                      // Align horizontal box to bottom center
            saveWarn.add(nBtn, 1, 1);                                           // Place the horizontal box on the bottom right grid box
                    
            Button canBtn = new Button("Cancel");                               // Create a button saying "Cancel"
            HBox cBtn = new HBox(canBtn);                                       // Create a horizontal box for button
            cBtn.setAlignment(Pos.CENTER);                                      // Align horizontal box to bottom center
            saveWarn.add(cBtn, 2, 1);                                           // Place the horizontal box on the bottom right grid box
            Toolkit.getDefaultToolkit().beep();

            yesBtn.setOnAction(new EventHandler<ActionEvent>() {                // When Save button pressed

                @Override
                public void handle(ActionEvent a) {
                    saveHandler.save(coolCrab, tabPane);
                    saveWarnWindow.close();                                     // Save and Exit window
                    open();
                    }
            });
            noBtn.setOnAction(new EventHandler<ActionEvent>() {                 // When Don't Save button pressed

                @Override
                public void handle(ActionEvent a) {
                    saveWarnWindow.close();                                     // Exit window
                    open();
                }
            });
            canBtn.setOnAction(new EventHandler<ActionEvent>() {                // When close button pressed

                @Override
                public void handle(ActionEvent a) {
                    saveWarnWindow.close();                                     // Exit window
                    }
            });
        }
        else if((PaintFX.getSelec() == 1) && (PaintFX.getChange1() == 1)){
            Stage saveWarnWindow = new Stage();                                 // Create new stage
            saveWarnWindow.setTitle("Warning!");                                // Set Title and Logo
            saveWarnWindow.getIcons().add(new Image(PaintFX.class.getResourceAsStream("logosmall.png")));
                
            GridPane saveWarn = new GridPane();                                 // Create grid
            saveWarn.setAlignment(Pos.CENTER);                                  // Center the grid
            saveWarn.setHgap(10);                                               // Set the gaps between grid elements to 10
            saveWarn.setVgap(10);
            saveWarn.setPadding(new Insets(25, 25, 25, 25));                    // Set border padding to 25
                    
            Text warn = new Text("Would you like to save your changes?");                               
            warn.setFont(new Font("Arial", 18));                                // Set Font and Size
            warn.setTextAlignment(TextAlignment.CENTER);                        // Align text to center
            saveWarn.add(warn, 1, 0);                                           // Add label
                    
            Scene saveWarnScene = new Scene(saveWarn, 500, 100);                // Create a 500 by 100 window
            saveWarnWindow.setScene(saveWarnScene);                             // Place the scene on the main stage
            saveWarnWindow.show();                                              // Show main stage
            
            Button yesBtn = new Button("Save");                                 // Create a button saying "Save"
            HBox yBtn = new HBox(yesBtn);                                       // Create a horizontal box for button
            yBtn.setAlignment(Pos.CENTER);                                      // Align horizontal box to bottom center
            saveWarn.add(yBtn, 0, 1);                                           // Place the horizontal box
                    
            Button noBtn = new Button("Don't Save");                            // Create a button saying "Don't Save"
            HBox nBtn = new HBox(noBtn);                                        // Create a horizontal box for button
            nBtn.setAlignment(Pos.CENTER);                                      // Align horizontal box to bottom center
            saveWarn.add(nBtn, 1, 1);                                           // Place the horizontal box
                    
            Button canBtn = new Button("Cancel");                               // Create a button saying "Cancel"
            HBox cBtn = new HBox(canBtn);                                       // Create a horizontal box for button
            cBtn.setAlignment(Pos.CENTER);                                      // Align horizontal box to bottom center
            saveWarn.add(cBtn, 2, 1);                                           // Place the horizontal box
            Toolkit.getDefaultToolkit().beep();

            yesBtn.setOnAction(new EventHandler<ActionEvent>() {                // When close button pressed

                @Override
                public void handle(ActionEvent a) {
                    saveHandler.save(coolCrab, tabPane);
                    saveWarnWindow.close();                                     // Save Exit window
                    open();
                    }
            });
            noBtn.setOnAction(new EventHandler<ActionEvent>() {                 // When Don't Save button pressed

                @Override
                public void handle(ActionEvent a) {
                    saveWarnWindow.close();                                     // Exit window
                    open();
                }
            });
            canBtn.setOnAction(new EventHandler<ActionEvent>() {                // When cancel button pressed

                @Override
                public void handle(ActionEvent a) {
                    saveWarnWindow.close();                                     // Exit window
                    }
            });
        }
        else if((PaintFX.getSelec() == 2) && (PaintFX.getChange2() == 1)){
            Stage saveWarnWindow = new Stage();                                 // Create new stage
            saveWarnWindow.setTitle("Warning!");                                // Set Title and Logo
            saveWarnWindow.getIcons().add(new Image(PaintFX.class.getResourceAsStream("logosmall.png")));
                
            GridPane saveWarn = new GridPane();                                 // Create grid
            saveWarn.setAlignment(Pos.CENTER);                                  // Center the grid
            saveWarn.setHgap(10);                                               // Set the gaps between grid elements to 10
            saveWarn.setVgap(10);
            saveWarn.setPadding(new Insets(25, 25, 25, 25));                    // Set border padding to 25
                    
            Text warn = new Text("Would you like to save your changes?");                               
            warn.setFont(new Font("Arial", 18));                                // Set Font and Size
            warn.setTextAlignment(TextAlignment.CENTER);                        // Align text to center
            saveWarn.add(warn, 1, 0);                                           // Add label to right top box
                    
            Scene saveWarnScene = new Scene(saveWarn, 500, 100);                // Create a 500 by 100 window
            saveWarnWindow.setScene(saveWarnScene);                             // Place the scene on the main stage
            saveWarnWindow.show();                                              // Show main stage
            
            Button yesBtn = new Button("Save");                                 // Create a button saying "Save"
            HBox yBtn = new HBox(yesBtn);                                       // Create a horizontal box for button
            yBtn.setAlignment(Pos.CENTER);                                      // Align horizontal box to bottom center
            saveWarn.add(yBtn, 0, 1);                                           // Place the horizontal box
                    
            Button noBtn = new Button("Don't Save");                            // Create a button saying "Don't Save"
            HBox nBtn = new HBox(noBtn);                                        // Create a horizontal box for button
            nBtn.setAlignment(Pos.CENTER);                                      // Align horizontal box to bottom center
            saveWarn.add(nBtn, 1, 1);                                           // Place the horizontal box
                    
            Button canBtn = new Button("Cancel");                               // Create a button saying "Cancel"
            HBox cBtn = new HBox(canBtn);                                       // Create a horizontal box for button
            cBtn.setAlignment(Pos.CENTER);                                      // Align horizontal box to bottom center
            saveWarn.add(cBtn, 2, 1);                                           // Place the horizontal box
            Toolkit.getDefaultToolkit().beep();

            yesBtn.setOnAction(new EventHandler<ActionEvent>() {                // When close button pressed

                @Override
                public void handle(ActionEvent a) {
                    saveHandler.save(coolCrab, tabPane);
                    saveWarnWindow.close();                                     // Save and Exit window
                    open();
                    }
            });
            noBtn.setOnAction(new EventHandler<ActionEvent>() {                 // When close button pressed

                @Override
                public void handle(ActionEvent a) {
                    saveWarnWindow.close();                                     // Exit window
                    open();
                }
            });
            canBtn.setOnAction(new EventHandler<ActionEvent>() {                // When close button pressed

                @Override
                public void handle(ActionEvent a) {
                    saveWarnWindow.close();                                     // Exit window
                    }
            });
        }
        else open();                                                            // Open
        
    }
    
}
