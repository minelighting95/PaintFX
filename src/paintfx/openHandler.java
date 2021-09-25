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

public class openHandler implements EventHandler<ActionEvent> {

    public Stage primaryStage;
    public FileChooser fileChooser;
    public ImageView cool;
    public ImageView cool1;
    public ImageView cool2;
    public BorderPane border;
    public Canvas canvas;
    public Canvas canvas1;
    public Canvas canvas2;
    public String fileName;
    public double w;
    public double h;
    public VBox vBox;
    public GraphicsContext gc;
    public GraphicsContext gc1;
    public GraphicsContext gc2;
    public StackPane coolCrab;
    public ScrollPane sp;
    public TabPane tabPane;
    
    public void open(){
        try{                                                                    // Attempt...
            fileChooser.setTitle("Open Picture...");                            // Named Open Picture
            fileChooser.getExtensionFilters().addAll(                           // Add image filters
                new FileChooser.ExtensionFilter("All Supported Files", "*.jpg", "*.png", "*.bmp", "*.gif")
                ,new FileChooser.ExtensionFilter("JPG Images", "*.jpg")
                ,new FileChooser.ExtensionFilter("PNG Images", "*.png")
                ,new FileChooser.ExtensionFilter("BitMap Images", "*.bmp")
                ,new FileChooser.ExtensionFilter("GIF File", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(primaryStage);       // Grab user selected file
            fileName = selectedFile.toURI().toString().substring(6);            // Save file name as string
            PaintFX.setName(selectedFile.getName());
            
            Image crab = new Image(selectedFile.toURI().toString());            // Open user image
            
            if(PaintFX.getSelec() == 0){
                PaintFX.setChange(0);
                tabPane.getTabs().get(0).setText(selectedFile.getName());       // Set Tab Name
                cool.setFitHeight(0);
                cool.setFitWidth(0);
                cool.setImage(crab);                                            // Set Image
                PaintFX.setImage(crab);
                cool.setPreserveRatio(true);                                    // Preserve Image Ratio
            }
            else if(PaintFX.getSelec() == 1){
                PaintFX.setChange1(0);
                tabPane.getTabs().get(1).setText(selectedFile.getName());       // Set Tab Name
                cool1.setFitHeight(0);
                cool1.setFitWidth(0);
                cool1.setImage(crab);                                           // Set Image
                PaintFX.setImage1(crab);
                cool1.setPreserveRatio(true);                                   // Preserve Image Ratio
            }
            else if(PaintFX.getSelec() == 2){
                PaintFX.setChange2(0);
                tabPane.getTabs().get(2).setText(selectedFile.getName());       // Set Tab Name
                cool2.setFitHeight(0);
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
            
                
            coolCrab.getChildren().clear();                                     // Remove previous image
            
            if(PaintFX.getSelec() == 0){
                coolCrab.getChildren().addAll(cool, canvas);                    // Add new image
            }
            else if(PaintFX.getSelec() == 1){
                coolCrab.getChildren().addAll(cool1, canvas1);                  // Add new image
            }
            else if(PaintFX.getSelec() == 2){
                coolCrab.getChildren().addAll(cool2, canvas2);                  // Add new image
            }
            else{
                System.out.println("Open Error 2");
            }
            
            sp.setContent(coolCrab);                                            // Place stack on ScrollPane

            if(primaryStage.isMaximized()){}                                    // If window is minimized
            else{
                primaryStage.setWidth(w+200);                                   // Set window to slightly larger than image
                primaryStage.setHeight(h+200);
            }
            PaintFX.setVariables(w, h, fileName);
        }
        catch(Exception f){}                                                    // If invalid image or closed explorer
    }
    
    public openHandler(Stage primaryStage, FileChooser fileChooser, ImageView cool, ImageView cool1, ImageView cool2, BorderPane border, Canvas canvas,
            Canvas canvas1, Canvas canvas2, VBox vBox, GraphicsContext gc, GraphicsContext gc1, GraphicsContext gc2, StackPane coolCrab,
            ScrollPane sp, TabPane tabPane){
        
        this.primaryStage = primaryStage;
        this.fileChooser = fileChooser;
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
                    saveHandler.finalSave(cool, cool1, cool2, coolCrab, canvas, canvas1, canvas2, primaryStage, tabPane);
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
                    saveHandler.finalSave(cool, cool1, cool2, coolCrab, canvas, canvas1, canvas2, primaryStage, tabPane);
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
                    saveHandler.finalSave(cool, cool1, cool2, coolCrab, canvas, canvas1, canvas2, primaryStage, tabPane);
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
