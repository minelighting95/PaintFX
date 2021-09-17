package PaintFX;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/* ******************************************************************** */
/*                                                                      */
/*   Title: PaintFX.java                                                */
/*   CS 250 Project                                                     */
/*   Programmer: Alex Appel						*/
/*   Date: 9-11-2021                                                    */
/*									*/
/*   Purpose:                                                           */
/*   Create a Microsoft Paint-like JavaFX program.                      */
/*                                                                      */
/*   Version: 1.1.0                                                     */
/*   Notes: See PaintFX_Release_Notes.txt                               */
/*                                                                      */
/* ******************************************************************** */

public class PaintFX extends Application {

    volatile double w = 800;                                                    // Create default variables
    volatile double h = 600;
    volatile String fileName;
    volatile double x1;
    volatile double y1;
    volatile double x2;
    volatile double y2;
    volatile int hold = 0;
    volatile int hold1 = 0;
    
    public static void main(String[] args) {                                    // Main
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{                     // Create Stage
        
        primaryStage.setTitle("PaintFX");                                       // Set Title and Logo
        primaryStage.getIcons().add(new Image(PaintFX.class.getResourceAsStream("logosmall.png")));
        BorderPane border = new BorderPane();                                   // Create new stack
        
        Menu menu = new Menu("File");                                           // Create menu item "File"
        Menu menu1 = new Menu("Help");                                          // Create menu item "Help"
        Menu menu2 = new Menu("Draw");                                          // Create menu item "Draw"
        Menu menu3 = new Menu("Edit");                                          // Create menu item "Edit"
        MenuBar menuBar = new MenuBar();                                        // Create new menu bar
        menuBar.getMenus().add(menu);                                           // Add File to menu bar
        menuBar.getMenus().add(menu3);                                          // Add Edit to menu bar
        menuBar.getMenus().add(menu2);                                          // Add Draw to menu bar
        menuBar.getMenus().add(menu1);                                          // Add Help to menu bar
        
        VBox vBox = new VBox(menuBar);                                          // Create vBox for menubar
        MenuItem menuOpen = new MenuItem("Open...");                            // Create "Open" item
        MenuItem menuSave = new MenuItem("Save");                               // Create "Save" item
        MenuItem menuSaveAs = new MenuItem("Save As...");                       // Create "Save As..." item
        MenuItem menuExit = new MenuItem("Exit");                               // Create "Exit" item
        
        MenuItem menuSnap = new MenuItem("Snap to Fit");                        // Create "Snap to Fit" item
        MenuItem menuTools = new MenuItem("Toggle Tools");                       // Create "Toggle Tools" item
        MenuItem menuClear = new MenuItem("Clear Canvas");                      // Create "Clear Canvas" item
        
        MenuItem menuHelp = new MenuItem("Help");                               // Create "Help" item
        MenuItem menuAbout = new MenuItem("About");                             // Create "About" item
        
        menu.getItems().addAll(menuOpen, menuSave, menuSaveAs, menuExit);       // Add items to "File"
        menu3.getItems().addAll(menuSnap);                                      // Add items to "Edit"
        menu2.getItems().addAll(menuTools, menuClear);                                      // Add items to "Draw"
        menu1.getItems().addAll(menuHelp, menuAbout);                           // Add items to "Help"
        menu.setGraphic(new ImageView("icon.png"));                             // Set menu graphic
        
        ScrollPane sp = new ScrollPane();                                       // Create ScrollPance
        sp.setFitToWidth(true);                                                 // Fit Pane Window
        sp.setFitToHeight(true);
        
        FileChooser fileChooser = new FileChooser();                            // Create file chooser
        Canvas canvas = new Canvas(w, h);                                       // Create Canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();                     // Allow graphics on canvas
        StackPane coolCrab = new StackPane();                                   // Create Node for image and canvas
        ImageView cool = new ImageView();                                       // Create new image view
        
        ColorPicker colorPicker = new ColorPicker();                            // Create a color picker
        HBox hColor = new HBox(colorPicker);
        GridPane gP = new GridPane();                                           // Create sidebar grid
        gP.setVgap(10);                                                         // Set vertical padding to 10
        ToggleButton lineBtn = new ToggleButton("Line");                        // Create Line toggle button
        HBox hLine = new HBox(lineBtn);
        Image lineImg = new Image("line.png");
        ImageView lineView = new ImageView(lineImg);
        lineBtn.setGraphic(lineView);
        
        Label widLabel = new Label("Enter Width:");                             // Create width label
        Label shapeLabel = new Label("Choose Shape:");                          // Create height label

        TextField widthText = new TextField();                                  // Create a width text field
        widthText.setText("1");                                                 // Set default value to 1
        
        gP.add(shapeLabel, 0, 0);                                               // Assemble sidebar tool grid
        gP.add(hLine, 0, 1);
        gP.add(hColor, 0, 2);
        gP.add(widLabel, 0, 3);
        gP.add(widthText, 0, 4);
       
        border.setTop(vBox);                                                    // Set the menu bar at the top of the screen
               
        coolCrab.getChildren().addAll(canvas);                                  // Add canvas to initial screen
        border.setCenter(coolCrab);                                             // Place stack in the center of the screen
        border.setMargin(coolCrab, new Insets(20, 25, 25, 25));                 // Center Stack T R B L

        Scene scene = new Scene(border, w+100, h+100);                          // Create scene
        primaryStage.setScene(scene);                                           // Set scene as primary scene
        primaryStage.show();                                                    // Show scene
        
        primaryStage.setWidth(w+200);                                           // Set window to slightly larger than image
        primaryStage.setHeight(h+200);
        
        menuOpen.setOnAction(e -> {                                             // If open pressed
            try{                                                                // Attempt...
                fileChooser.setTitle("Open Picture...");                        // Named Open Picture
                fileChooser.getExtensionFilters().addAll(                       // Add image filters
                    new FileChooser.ExtensionFilter("All Supported Files", "*.jpg", "*.png", "*.bmp", "*.gif")
                    ,new FileChooser.ExtensionFilter("JPG Images", "*.jpg")
                    ,new FileChooser.ExtensionFilter("PNG Images", "*.png")
                    ,new FileChooser.ExtensionFilter("BitMap Images", "*.bmp")
                    ,new FileChooser.ExtensionFilter("GIF File", "*.gif")
                );
                File selectedFile = fileChooser.showOpenDialog(primaryStage);   // Grab user selected file
                fileName = selectedFile.toURI().toString().substring(6);        // Save file name as string
                
                primaryStage.setTitle("PaintFX - " + selectedFile.getName());   // Update application title to show current image
            
                Image crab = new Image(selectedFile.toURI().toString());        // Open user image
                cool.setImage(crab);                                            // Set Image
                cool.setPreserveRatio(true);                                    // Preserve Image Ratio
                
                border.setTop(vBox);                                            // Set the menu bar at the top of the screen

                w = crab.getWidth();                                            // Record image dimensions
                h = crab.getHeight();                                           
                
                canvas.setWidth(w);                                             // Set canvas to the current dimensions
                canvas.setHeight(h);
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());      // Clear Canvas
                
                coolCrab.getChildren().clear();                                 // Remove previous image
                coolCrab.getChildren().addAll(cool, canvas);                    // Add new image
                sp.setContent(coolCrab);                                        // Place stack on ScrollPane
                border.setCenter(sp);                                           // Place ScrollPane in center

                if(primaryStage.isMaximized()){}                                // If window is minimized
                else{
                    primaryStage.setWidth(w+200);                               // Set window to slightly larger than image
                    primaryStage.setHeight(h+200);
                }
            }
            catch(Exception f){}                                                // If invalid image or closed explorer
        });
        
        menuTools.setOnAction(e -> {                                            // If toggle tools button pushed
            if(hold1 == 0){                                                     // If hidden, show
                border.setRight(gP);
                hold1 = 1;
            }
            else{                                                               // If shown, hide
                border.setRight(null);
                hold1 = 0;
            }
        });
        
        lineBtn.setOnAction(new EventHandler<ActionEvent>() {                   // When Line button pressed

            @Override
            public void handle(ActionEvent a) {
            
                hold = 0;
            
                canvas.setOnMouseClicked((event) ->{                            // When mouse clicked
                    if(hold == 0){                                              // 1st time
                        x1 = event.getX();                                      // Record Coordinates
                        y1 = event.getY();
                        hold = 1;
                    }
                    else if(hold == 1){                                         // 2nd time
                        x2 = event.getX();                                      // Record Coordinates
                        y2 = event.getY();
                        hold = 2;
                        gc.setStroke(colorPicker.getValue());                   // Set Line Color to selected value
                        int lineWidth = Integer.parseInt(widthText.getText());  // Convert width text to integer
                        if(lineWidth <= 0){                                     // If invalid value,
                            lineWidth = 1;                                      // Set to width = 1
                            widthText.setText("1");
                        }
                        gc.setLineWidth(lineWidth);                             // Set line to desired width
                        gc.strokeLine(x1, y1, x2, y2);                          // Create line between user selected points
                        lineBtn.setSelected(false);                             // Untoggle button
                    }
                });
            }
        });
        
        menuClear.setOnAction(e -> {                                            // If Clear Canvas button is pushed
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());          // Clear Canvas
        });
        
        menuSave.setOnAction(e -> {                                             // If Save button is pushed
            Bounds bnd = cool.getBoundsInParent();                              // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File(fileName);                             // Grab original user file
                String file = saveFile.toURI().toString();                      // Grab file extension
                String fileExt = file.substring(file.length()-3);
                if(fileExt.equals("png")){                                      // If the image was a PNG
                    sP.setFill(Color.TRANSPARENT);                              // Make backgroud transparent
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot and save image
                    ImageIO.write(SwingFXUtils.fromFXImage(wim, null), fileExt, saveFile);
                }
                else{                                                           // If the image was JPG or BMP
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot
                    BufferedImage bufARGB = SwingFXUtils.fromFXImage(wim, null);// Convert from ARGB image to RGB image to fix colors
                    BufferedImage bufRGB = new BufferedImage(bufARGB.getWidth(), bufARGB.getHeight(), BufferedImage.OPAQUE);
                    Graphics2D graphics = bufRGB.createGraphics();              // Create graphics from picture
                    graphics.drawImage(bufARGB, 0, 0, null);                    // Draw image to RGB image
                    ImageIO.write(bufRGB, fileExt, saveFile);                   // Save RGB image
                }
            } catch (Exception s) {
            }
        }); 
        
        menuSaveAs.setOnAction(e -> {                                           // If Save As button is pushed
            Bounds bnd = cool.getBoundsInParent();                              // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            FileChooser saveChooser = new FileChooser();                        // Create file chooser
            saveChooser.setTitle("Save Picture As...");                         // Named Save Picture As
            saveChooser.setInitialFileName("untilted");                         // Default the name to untitled
            saveChooser.getExtensionFilters().addAll(                           // Add image filters
                    new FileChooser.ExtensionFilter("PNG Images", "*.png")
                    ,new FileChooser.ExtensionFilter("JPG Images", "*.jpg")
                    ,new FileChooser.ExtensionFilter("BitMap Images", "*.bmp")
                );
            
            try {
                File saveFile = saveChooser.showSaveDialog(primaryStage);       // Grab user selected file and path
                String file = saveFile.toURI().toString();                      // Grab file extension
                String fileExt = file.substring(file.length()-3);
                if(fileExt.equals("png")){                                      // If the image is a PNG
                    sP.setFill(Color.TRANSPARENT);                              // Make backgroud transparent
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot and save image
                    ImageIO.write(SwingFXUtils.fromFXImage(wim, null), fileExt, saveFile);
                }
                else{                                                           // If the image was JPG or BMP
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot
                    BufferedImage bufARGB = SwingFXUtils.fromFXImage(wim, null);// Convert from ARGB image to RGB image to fix colors
                    BufferedImage bufRGB = new BufferedImage(bufARGB.getWidth(), bufARGB.getHeight(), BufferedImage.OPAQUE);
                    Graphics2D graphics = bufRGB.createGraphics();              // Create graphics from picture
                    graphics.drawImage(bufARGB, 0, 0, null);                    // Draw image to RGB image
                    ImageIO.write(bufRGB, fileExt, saveFile);                   // Save RGB image
                }
            } catch (Exception s) {
            }
        }); 
        
        menuExit.setOnAction(e -> {                                             // If exit button is pushed
            System.exit(0);                                                     // Close Window
        });
        
        menuSnap.setOnAction(e -> {                                             // If Snap button is pushed
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();         // Read monitor dimensions, and if image is larger,
                if((screenBounds.getWidth() < w) || (screenBounds.getHeight() < h)){
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    cool.setFitWidth(screenBounds.getWidth()-200);              // Set image dimensions to monitor size
                    cool.setFitHeight(screenBounds.getHeight()-200);
                    w = cool.getFitWidth();                                     // Set Screen dimensions to image
                    h = cool.getFitHeight();
                }
                else{}
                
                canvas.setWidth(w);                                             // Set Canvas to new Size
                canvas.setHeight(h);
                
                if(primaryStage.isMaximized()){}                                // If window is minimized
                else{
                    primaryStage.setWidth(w+200);                               // Set window to slightly larger than image
                    primaryStage.setHeight(h+200);
                }
        });
        
        menuHelp.setOnAction(e -> {                                             // If Help button is pushed
            Stage aboutWindow = new Stage();                                    // Create new window
            aboutWindow.setTitle("Help");                                       // Set Title and Logo
            aboutWindow.getIcons().add(new Image(PaintFX.class.getResourceAsStream("logosmall.png")));
            
            GridPane help = new GridPane();                                     // Create grid
            help.setAlignment(Pos.CENTER);                                      // Center the grid
            help.setHgap(10);                                                   // Set the gaps between grid elements to 10
            help.setVgap(10);
            help.setPadding(new Insets(25, 25, 25, 25));                        // Set border padding to 25 
            
            ScrollPane spHelp = new ScrollPane();                               // Create new scroll pane
            
            Label helpVar = new Label("Help");                                  // Create a new label "Help"
            helpVar.setFont(new Font("Arial", 36));                             // Set Font and Size
            help.add(helpVar, 0, 0);                                            // Add label to top
            
            Text info = new Text(                                               // Help File
                "- Open an Image: To open an image, select File->Open..., and select your image from the File Explorer Window. Supported formats are: PNG, JPG, BMP, and GIF.\n" +
                "\n" +
                "- Save an Image: To save the current image with changes to the original location, choose File->Save. Note: This will override the original image. Supported Save formats are: PNG, JPG, and BMP.\n" +
                "\n" +
                "- Save an Image as: To save the current image as a different format and/or change the name, choose File->Save as... Note: This will need to be done each time the new image needs to be saved. Supported Save formats are: PNG, JPG, and BMP.\n" +
                "\n" +
                "- Exit the Program: To exit the program, click the red X in the top right corner, or press File->Exit.\n" +
                "\n" +
                "- To clear the current canvas: Press Draw->Clear Canvas.\n" +
                "\n" +
                "- To snap a large image to fit the window: Press Edit->Snap to Fit. This will clear the current canvas.\n" +
                "\n" +
                "- To Show Drawing Tools: Press Draw->Toggle Tools.\n" +
                "\n" +
                "- In order to draw a line, once tools are visible, Choose a Color, Enter a Line Width, and Select Line from the shapes. This will allow to you draw one line. In order to draw another identical line, press Solid Line again.");                               // Create a new label "X:"
            info.setFont(new Font("Arial", 18));                                // Set Font and Size
            info.setTextAlignment(TextAlignment.LEFT);                          // Align left
            spHelp.setContent(info);                                            // Display Text in scroll pane
            help.add(spHelp, 0, 1);                                             // Add text to middle
            
            Scene aboutScene = new Scene(help, 640, 480);                       // Create a 640 x 480 window
            aboutWindow.setScene(aboutScene);                                   // Place the scene on the about stage
            aboutWindow.show();
            
            Button btn = new Button("Close");                                   // Create a button saying "Close"
            HBox hbBtn = new HBox(10);                                          // Create a horizontal box for button
            hbBtn.setAlignment(Pos.CENTER);                                     // Align horizontal box to bottom center
            hbBtn.getChildren().add(btn);                                       // Place the button in the horizontal box
            help.add(hbBtn, 0, 2);                                              // Place the horizontal box on the bottom grid box

            btn.setOnAction(new EventHandler<ActionEvent>() {                   // When Close button pressed

                @Override
                public void handle(ActionEvent a) {
                    aboutWindow.close();                                        // Close Help Window
                    }
            });

        });
        
        menuAbout.setOnAction(e -> {                                            // If about button is pushed
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
            
            Text info = new Text("PaintFX v1.1.0\n"                             // Create about text
                    + "Sept. 11, 2021\n\n"
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

        });
    }
}