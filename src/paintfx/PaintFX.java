package PaintFX;

import java.io.File;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

/* ******************************************************************** */
/*                                                                      */
/*   Title: PaintFX.java                                                */
/*   CS 250 Project                                                     */
/*   Programmer: Alex Appel						*/
/*   Date: 9-4-2021                                                     */
/*									*/
/*   Purpose:                                                           */
/*   Create a JavaFX program to open a user chosen image, have a menu   */
/*   bar, and close nicely.                                             */
/*                                                                      */
/*   Version: 1.0.1                                                     */
/*   Notes: See PaintFX_Release_Notes.txt                               */
/*                                                                      */
/* ******************************************************************** */

public class PaintFX extends Application {

    volatile double w = 800;                                                    // Create default dimensions
    volatile double h = 600;
    
    public static void main(String[] args) {                                    // Main
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{                     // Create Stage
        
        int hold = 1;                                                           // Create Hold Variable
        
        primaryStage.setTitle("PaintFX");                                       // Set Title and Logo
        primaryStage.getIcons().add(new Image(PaintFX.class.getResourceAsStream("logosmall.png")));
        StackPane stack = new StackPane();                                      // Create new stack
        stack.setAlignment(Pos.CENTER);                                         // Center Stack
        
        Menu menu = new Menu("File");                                           // Create menu item "File"
        MenuBar menuBar = new MenuBar();                                        // Create new menu bar
        menuBar.getMenus().add(menu);                                           // Add File to menu bar
        VBox vBox = new VBox(menuBar);                                          // Create vBox for menubar
        MenuItem menuOpen = new MenuItem("Open...");                            // Create "Open" item
        MenuItem menuExit = new MenuItem("Exit");                               // Create "Exit" item
        menu.getItems().addAll(menuOpen, menuExit);                             // Add items to "File"
        menu.setGraphic(new ImageView("icon.png"));                             // Set menu graphic
        
        while(hold == 1){                                                       // While Holding
            try{                                                                // Attempt...
                FileChooser fileChooser = new FileChooser();                    // Create file chooser
                fileChooser.setTitle("Open Picture");                           // Named Open Picture
                fileChooser.getExtensionFilters().addAll(                       // Add image filters
                    new FileChooser.ExtensionFilter("All Supported Files", "*.jpg", "*.png", "*.bmp", "*.gif")
                    ,new FileChooser.ExtensionFilter("JPG Images", "*.jpg")
                    ,new FileChooser.ExtensionFilter("PNG Images", "*.png")
                    ,new FileChooser.ExtensionFilter("BitMap Images", "*.bmp")
                    ,new FileChooser.ExtensionFilter("GIF File", "*.gif")
                );
            
                File selectedFile = fileChooser.showOpenDialog(primaryStage);   // Grab user selected file
            
                Image crab = new Image(selectedFile.toURI().toString());        // Open user image
                ImageView cool = new ImageView();                               // Create new image view
                cool.setImage(crab);                                            // Set Image
                cool.setPreserveRatio(true);                                    // Preserve Image Ratio
        
                stack.getChildren().addAll(cool, vBox);                         // Create stack with image and menu
                
                w = (crab.getWidth()+50);                                       // Create window dimensions slightly
                h = (crab.getHeight()+50);                                      // larger than image
                
                Rectangle2D screenBounds = Screen.getPrimary().getBounds();     // Read monitor dimensions, and if image is larger,
                if((screenBounds.getWidth() < w) || (screenBounds.getHeight() < h)){
                    cool.setFitWidth(screenBounds.getWidth()-50);                  // Set image dimensions to monitor size
                    cool.setFitHeight(screenBounds.getHeight()-50);
                    w = screenBounds.getWidth();                                // Set Screen dimensions to image
                    h = screenBounds.getHeight();
                }

                Scene scene = new Scene(stack, w+50, h+50);                     // Create scene
                primaryStage.setScene(scene);                                   // Set scene as primary scene
                primaryStage.show();                                            // Show scene
                
                hold = 0;                                                       // Remove hold
            }
            catch(Exception e){                                                 // If fail,
                System.out.println("File Error or Operation Cancelled");        // Print to console error message
                System.exit(0);                                                 // Quit program
            }
        }
        
        menuOpen.setOnAction(e -> {                                             // If open pressed
            try{                                                                // Attempt...
                FileChooser fileChooser = new FileChooser();                    // Create menu item "File"
                fileChooser.setTitle("Open Picture");                           // Named Open Picture
                fileChooser.getExtensionFilters().addAll(                       // Add image filters
                    new FileChooser.ExtensionFilter("All Supported Files", "*.jpg", "*.png", "*.bmp", "*.gif")
                    ,new FileChooser.ExtensionFilter("JPG Images", "*.jpg")
                    ,new FileChooser.ExtensionFilter("PNG Images", "*.png")
                    ,new FileChooser.ExtensionFilter("BitMap Images", "*.bmp")
                    ,new FileChooser.ExtensionFilter("GIF File", "*.gif")
                );
                File selectedFile = fileChooser.showOpenDialog(primaryStage);   // Grab user selected file
            
                Image crab1 = new Image(selectedFile.toURI().toString());       // Open user image
                ImageView coolCrab = new ImageView();                           // Create new image view
                coolCrab.setImage(crab1);                                       // Set Image
                coolCrab.setPreserveRatio(true);                                // Preserve Image Ratio
                
                stack.getChildren().clear();                                    // Remove previous image
                stack.getChildren().addAll(coolCrab, vBox);                     // Display new image
                
                w = (crab1.getWidth()+50);                                      // Create window dimensions slightly
                h = (crab1.getHeight()+50);                                     // larger than image
                
                Rectangle2D screenBounds = Screen.getPrimary().getBounds();     // Read monitor dimensions, and if image is larger,
                if((screenBounds.getWidth() < w) || (screenBounds.getHeight() < h)){
                    coolCrab.setFitWidth(screenBounds.getWidth()-50);              // Set image dimensions to monitor size
                    coolCrab.setFitHeight(screenBounds.getHeight()-50);
                    w = screenBounds.getWidth();                                // Set Screen dimensions to image
                    h = screenBounds.getHeight();
                }
                
                if(primaryStage.isMaximized()){}                                // If window is minimized
                else{
                    primaryStage.setWidth(w+50);                                // Set window to slightly larger than image
                    primaryStage.setHeight(h+50);
                }

            }
            catch(Exception f){}                                                // If invalid image or closed explorer
        });
        
        menuExit.setOnAction(e -> {                                             // If exit button is pushed
            System.exit(0);                                                     // Close Window
        }); 
    }
}