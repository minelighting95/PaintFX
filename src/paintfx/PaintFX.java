package PaintFX;

import java.io.File;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
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
/*   Version: 1.0.0                                                     */
/*   Notes: See PaintFX_Release_Notes.txt                               */
/*                                                                      */
/* ******************************************************************** */

public class PaintFX extends Application {

    public static void main(String[] args) {                                    // Main
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{                   // Create Stage
        
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
                double w = crab.getWidth();                                     // Get dimensions of image
                double h = crab.getHeight();
            
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
            
                Image crab = new Image(selectedFile.toURI().toString());        // Open user image
                ImageView coolCrab = new ImageView();                           // Create new image view
                coolCrab.setImage(crab);                                        // Set Image
                coolCrab.setPreserveRatio(true);                                // Preserve Image Ratio

                stack.getChildren().clear();                                    // Remove previous image
                stack.getChildren().addAll(coolCrab, vBox);                     // Display new image
            }
            catch(Exception f){}                                                // If invalid image or closed explorer
        });
        
        menuExit.setOnAction(e -> {                                             // If exit button is pushed
            System.exit(0);                                                     // Close Window
        }); 
    }
}