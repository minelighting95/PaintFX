package PaintFX;

import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

/* ******************************************************************** */
/*                                                                      */
/*   Title: PaintFX.java                                                */
/*   CS 250 Project                                                     */
/*   Programmer: Alex Appel						*/
/*   Date: 9-18-2021                                                    */
/*									*/
/*   Purpose:                                                           */
/*   Create a Microsoft Paint-like JavaFX program.                      */
/*                                                                      */
/*   Version: 1.2.1                                                     */
/*   Notes: See PaintFX_Release_Notes.txt                               */
/*                                                                      */
/* ******************************************************************** */

public class PaintFX extends Application {

    volatile private static double w = 800;                                             // Create default variables
    volatile private static double h = 600;
    volatile private static String fileName;
    volatile private static String fileName1;
    volatile private static String fileName2;
    volatile private static int change;
    volatile private static int change1;
    volatile private static int change2;
    volatile private int hold = 0;
    volatile private int hold1 = 0;
    volatile private int hold2 = 0;
    volatile private static int selec = 0;
    volatile private static Image crab;
    volatile private static Image crab1;
    volatile private static Image crab2;
    volatile private static String name;
    volatile private static Color argb;
    volatile private static Color argb1;
    volatile private static ToggleButton lineBtn;
    volatile private static ToggleButton curveBtn;
    volatile private static ToggleButton rectBtn;
    volatile private static ToggleButton squBtn;
    volatile private static ToggleButton ovalBtn;
    volatile private static ToggleButton cirBtn;
    volatile private static ToggleButton textBtn;
    volatile private static ToggleButton dropBtn;
    volatile private static ToggleButton dropBtn1;
    
    public static Image getImage(){return crab;}                                // Create Get and Set Methods
    public static void setImage(Image c){crab = c;}
    public static Image getImage1(){return crab1;}
    public static void setImage1(Image c){crab1 = c;}
    public static Image getImage2(){return crab2;}
    public static void setImage2(Image c){crab2 = c;}
    
    public static int getChange(){return change;}
    public static void setChange(int c){change = c;}
    public static int getChange1(){return change1;}
    public static void setChange1(int c){change1 = c;}
    public static int getChange2(){return change2;}
    public static void setChange2(int c){change2 = c;}
    
    public static int getSelec(){return selec;}
    public static void setSelec(int c){selec = c;}
    
    public static String getFileName(){return fileName;}
    public static void setFileName(String c){fileName = c;}
    public static String getFileName1(){return fileName1;}
    public static void setFileName1(String c){fileName1 = c;}
    public static String getFileName2(){return fileName2;}
    public static void setFileName2(String c){fileName2 = c;}
    
    public static String getName(){return name;}
    public static void setName(String c){name = c;}
    public static Color getColor(){return argb;}
    public static void setColor(Color c){argb = c;}
    public static Color getColor1(){return argb1;}
    public static void setColor1(Color c){argb1 = c;}
    
    public static void setVariables(double wtemp, double htemp){
        w = wtemp;
        h = htemp;
    }
    
    public static void main(String[] args) {                                    // Main
        launch(args);
    }

    public static void setButtonSelects(){
        lineBtn.setSelected(false);                                     // Untoggle button
        curveBtn.setSelected(false);                                    // Untoggle button
        rectBtn.setSelected(false);                                     // Untoggle button
        squBtn.setSelected(false);                                      // Untoggle button
        ovalBtn.setSelected(false);                                     // Untoggle button
        cirBtn.setSelected(false);                                      // Untoggle button
        textBtn.setSelected(false);                                     // Untoggle button
        dropBtn.setSelected(false);                                     // Untoggle button
        dropBtn1.setSelected(false);                                    // Untoggle button   
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{                     // Create Stage
        
        primaryStage.setTitle("PaintFX");                                       // Set Title and Logo
        primaryStage.getIcons().add(new Image(PaintFX.class.getResourceAsStream("logosmall.png")));
        BorderPane border = new BorderPane();                                   // Create new stack
        
        Menu menu = new Menu("_File");                                          // Create menu item "File"
        Menu menu1 = new Menu("_Help");                                         // Create menu item "Help"
        Menu menu2 = new Menu("_Draw");                                         // Create menu item "Draw"
        Menu menu3 = new Menu("_Edit");                                         // Create menu item "Edit"
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
                                                                                // Create Key Commands
        menuOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        menuSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        menuSaveAs.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
        menuExit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        
        MenuItem menuSnap = new MenuItem("Snap to Fit");                        // Create "Snap to Fit" item
        MenuItem menuCanvas = new MenuItem("Resize Canvas");                    // Create "Resize Canvas" item
        MenuItem menuZoomIn = new MenuItem("Zoom In");                          // Create "Zoom In" item
        MenuItem menuZoomOut = new MenuItem("Zoom Out");                        // Create "Zoom Out" item
        MenuItem menuTools = new MenuItem("Toggle Tools");                      // Create "Toggle Tools" item
        MenuItem menuClear = new MenuItem("Clear Canvas");                      // Create "Clear Canvas" item
                                                                                // Create Key Commands
        menuSnap.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
        menuCanvas.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
        menuZoomIn.setAccelerator(new KeyCodeCombination(KeyCode.ADD, KeyCombination.CONTROL_DOWN));
        menuZoomOut.setAccelerator(new KeyCodeCombination(KeyCode.SUBTRACT, KeyCombination.CONTROL_DOWN));
        menuTools.setAccelerator(new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN));
        menuClear.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        
        MenuItem menuHelp = new MenuItem("Help");                               // Create "Help" item
        MenuItem menuAbout = new MenuItem("About");                             // Create "About" item
                                                                                // Create Key Commands
        menuHelp.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
        menuAbout.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN));
        
        menu.getItems().addAll(menuOpen, menuSave, menuSaveAs, menuExit);       // Add items to "File"
        menu3.getItems().addAll(menuSnap, menuCanvas, menuZoomIn, menuZoomOut); // Add items to "Edit"
        menu2.getItems().addAll(menuTools, menuClear);                          // Add items to "Draw"
        menu1.getItems().addAll(menuHelp, menuAbout);                           // Add items to "Help"
        menu.setGraphic(new ImageView("icon.png"));                             // Set menu graphic
        
        FileChooser fileChooser = new FileChooser();                            // Create file chooser
        Canvas canvas = new Canvas(w, h);                                       // Create Canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();                     // Allow graphics on canvas
        StackPane coolCrab = new StackPane();                                   // Create Node for image and canvas
        ImageView cool = new ImageView();                                       // Create new image view
        ScrollPane sp = new ScrollPane();                                       // Create ScrollPance
        sp.setFitToWidth(true);                                                 // Fit Pane Window
        sp.setFitToHeight(true);
        
        Canvas canvas1 = new Canvas(w, h);                                      // Create Canvas
        GraphicsContext gc1 = canvas1.getGraphicsContext2D();                   // Allow graphics on canvas
        ImageView cool1 = new ImageView();                                      // Create new image view
        
        Canvas canvas2 = new Canvas(w, h);                                      // Create Canvas
        GraphicsContext gc2 = canvas2.getGraphicsContext2D();                   // Allow graphics on canvas
        ImageView cool2 = new ImageView();                                      // Create new image view
        
        Image defImg = new Image("default.png");                                // Open user image
        cool.setFitHeight(0);
        cool.setFitWidth(0);
        cool.setImage(defImg);
        cool.setPreserveRatio(true);                                    // Preserve Image Ratio
        cool1.setFitHeight(0);
        cool1.setFitWidth(0);
        cool1.setImage(defImg);
        cool1.setPreserveRatio(true);                                    // Preserve Image Ratio
        cool2.setFitHeight(0);
        cool2.setFitWidth(0);
        cool2.setImage(defImg);
        cool2.setPreserveRatio(true);                                    // Preserve Image Ratio
        
        TabPane tabPane = new TabPane();                                        // Create Tab Pane
        Tab tab1 = new Tab("Image 1", sp);                                      // Create 3 Tabs
        Tab tab2 = new Tab("Image 2");
        Tab tab3 = new Tab("Image 3");
        tab1.setClosable(false);                                                // Make tabs Closeable
        tab2.setClosable(false);
        tab3.setClosable(false);
        tabPane.getTabs().addAll(tab1, tab2, tab3);                             // Add Tabs to pane
        VBox tabBox = new VBox(tabPane);
        
        ColorPicker colorPicker = new ColorPicker(Color.BLACK);                 // Create a color picker
        HBox hColor = new HBox(colorPicker);
        ColorPicker fillPick = new ColorPicker();                               // Create a color picker
        HBox hFill = new HBox(fillPick);
        
        GridPane gP = new GridPane();                                           // Create sidebar grid
        gP.setVgap(10);                                                         // Set vertical padding to 10
        GridPane gPLeft = new GridPane();                                       // Create canvas sidebar grid
        gPLeft.setVgap(10);                                                     // Set vertical padding to 10
        
        ToggleGroup toggleGroup = new ToggleGroup();                            // Create ToggleGroup
        
        CheckBox fillBox = new CheckBox("Fill Shape");                          // Create CheckBox
        
        lineBtn = new ToggleButton("Line");                        // Create Line toggle button
        HBox hLine = new HBox(lineBtn);
        Image lineImg = new Image("line.png");
        ImageView lineView = new ImageView(lineImg);
        lineBtn.setGraphic(lineView);
        
        curveBtn = new ToggleButton("Curve");                      // Create Curve Toggle Button
        HBox hCurve = new HBox(curveBtn);
        Image curveImg = new Image("curve.png");
        ImageView curveView = new ImageView(curveImg);
        curveBtn.setGraphic(curveView);
        
        rectBtn = new ToggleButton("Rectangle");                   // Create Rectangle Toggle Button
        HBox hRect = new HBox(rectBtn);
        Image rectImg = new Image("rectangle.png");
        ImageView rectView = new ImageView(rectImg);
        rectBtn.setGraphic(rectView);
        
        squBtn = new ToggleButton("Square");                       // Create Square Toggle Button
        HBox hSqu = new HBox(squBtn);
        Image squImg = new Image("square.png");
        ImageView squView = new ImageView(squImg);
        squBtn.setGraphic(squView);
        
        ovalBtn = new ToggleButton("Ellipse");                     // Create Ellipse Toggle Button
        HBox hOval = new HBox(ovalBtn);
        Image ovalImg = new Image("oval.png");
        ImageView ovalView = new ImageView(ovalImg);
        ovalBtn.setGraphic(ovalView);
        
        cirBtn = new ToggleButton("Circle");                       // Create Circle Toggle Button
        HBox hCir = new HBox(cirBtn);
        Image cirImg = new Image("circle.png");
        ImageView cirView = new ImageView(cirImg);
        cirBtn.setGraphic(cirView);
        
        textBtn = new ToggleButton("Text");                        // Create Text Toggle Button
        HBox hText = new HBox(textBtn);
        
        dropBtn = new ToggleButton("Color Dropper");               // Create Main Dropper toggle button
        HBox hDrop = new HBox(dropBtn);
        Image dropImg = new Image("dropper.png");
        ImageView dropView = new ImageView(dropImg);
        dropBtn.setGraphic(dropView);
        
        dropBtn1 = new ToggleButton("Color Dropper");              // Create Fill Dropper toggle button
        HBox hDrop1 = new HBox(dropBtn1);
        Image dropImg1 = new Image("dropper1.png");
        ImageView dropView1 = new ImageView(dropImg1);
        dropBtn1.setGraphic(dropView1);
        
        Button canvasBtn = new Button("Set Size");                              // Create Resize Button
        HBox hCanvas = new HBox(canvasBtn);
        
        lineBtn.setToggleGroup(toggleGroup);                                    // Add Toggle Buttons to Group
        curveBtn.setToggleGroup(toggleGroup);
        rectBtn.setToggleGroup(toggleGroup);
        squBtn.setToggleGroup(toggleGroup);
        ovalBtn.setToggleGroup(toggleGroup);
        cirBtn.setToggleGroup(toggleGroup);
        textBtn.setToggleGroup(toggleGroup);
        
        Label widLabel = new Label("Enter Width:");                             // Create width label
        Label shapeLabel = new Label("Choose Shape:");                          // Create height label
        Label colorLabel = new Label("Choose Color:");                          // Create color label
        Label textLabel = new Label("Enter Text:");                             // Create text label
        Label canWidth = new Label("Enter Canvas Width:");                      // Create can width label
        Label canHeight = new Label("Enter Canvas Height:");                    // Create can height label

        TextField widthText = new TextField();                                  // Create a width text field
        widthText.setText("1");                                                 // Set default value to 1
        TextField enteredText = new TextField();                                // Create a user text field
        TextField canW = new TextField();                                       // Create can width text field
        TextField canH = new TextField();                                       // Create can height text field
        
        gP.add(colorLabel, 0, 0);                                               // Assemble sidebar tool grid
        gP.add(hColor, 0, 1);
        gP.add(hDrop, 0, 2);
        gP.add(widLabel, 0, 3);
        gP.add(widthText, 0, 4);
        gP.add(shapeLabel, 0, 5);                                               
        gP.add(hLine, 0, 6);
        gP.add(hCurve, 0, 7);
        gP.add(hRect, 0, 8);
        gP.add(hSqu, 0, 9);
        gP.add(hOval, 0, 10);
        gP.add(hCir, 0, 11);
        gP.add(fillBox, 0, 12);
        gP.add(textLabel, 0, 15);
        gP.add(enteredText, 0, 16);
        gP.add(hText, 0, 17);
        
        gPLeft.add(canWidth, 0, 0);                                             // Assemble sidebar canvas grid
        gPLeft.add(canW, 0, 1);
        gPLeft.add(canHeight, 0, 2);
        gPLeft.add(canH, 0, 3);
        gPLeft.add(hCanvas, 0, 4);
       
        border.setTop(vBox);                                                    // Set the menu bar at the top of the screen
               
        coolCrab.getChildren().addAll(cool, canvas);                            // Add canvas and image to initial screen
        
        sp.setContent(coolCrab);                                                // Plae in scrollpane
        
        border.setCenter(tabBox);                                               // Place stack in the center of the screen

        Scene scene = new Scene(border, w+100, h+100);                          // Create scene
        primaryStage.setScene(scene);                                           // Set scene as primary scene
        primaryStage.show();                                                    // Show scene
        
        primaryStage.setWidth(w+200);                                           // Set window to slightly larger than image
        primaryStage.setHeight(h+200);
                                                                                // Create Tab Switch
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{
            
            ov.setContent(null);                                                // Switch Tab Contents
            coolCrab.getChildren().clear();
            nv.setContent(sp);

            if(nv == tab2){
                setButtonSelects();
                setVariables(canvas1.getWidth(), canvas1.getHeight());
                setName(tab2.getText());
                setSelec(1);
                coolCrab.getChildren().addAll(cool1, canvas1);
            }
            else if(nv == tab3){
                setButtonSelects();
                setVariables(canvas2.getWidth(), canvas2.getHeight());
                setName(tab3.getText());
                setSelec(2);
                coolCrab.getChildren().addAll(cool2, canvas2);
            }
            else if(nv == tab1){
                setButtonSelects();
                setVariables(canvas.getWidth(), canvas.getHeight());
                setName(tab1.getText());
                setSelec(0);
                coolCrab.getChildren().addAll(cool, canvas);
            }
            else{
                System.out.println("Tab Error");
            }
        });
                                                                                // Open Dialog
        menuOpen.setOnAction(new openHandler(primaryStage, fileChooser, cool, cool1, cool2,
                border, canvas, canvas1, canvas2, vBox, gc, gc1, gc2, coolCrab, sp, tabPane));
        
        
        menuTools.setOnAction(e -> {                                            // If toggle tools button pushed
            if(hold1 == 0){                                                     // If hidden, show
                border.setRight(gP);
                hold1 = 1;
            }
            else{                                                               // If shown, hide
                setButtonSelects();
                curveHandler.setHold(1);
                rectangleHandler.setHold(2);
                squareHandler.setHold(2);
                ovalHandler.setHold(2);
                circleHandler.setHold(2);
                border.setRight(null);
                hold1 = 0;
            }
        });
        
        menuCanvas.setOnAction(e -> {                                           // If resize canvas button pushed
            if(hold == 0){                                                      // If hidden, show
                border.setLeft(gPLeft);
                canW.setText(String.valueOf(w));                                // Adjust canvas size
                canH.setText(String.valueOf(h));
                hold = 1;
            }
            else{                                                               // If shown, hide
                border.setLeft(null);
                hold = 0;
            }
        });
        
        menuZoomIn.setOnAction(e -> {                                           // If Zoom In triggered
            if(selec == 0){
                cool.setFitWidth(w + 10);                                       // Adjust dimensions
                cool.setFitHeight(h + 10);
                w = cool.getFitWidth();                                         // Set screen dimensions to image
                h = cool.getFitHeight();
                canvas.setWidth(w);                                             // Set Canvas to new Size
                canvas.setHeight(h);
            }
            else if(selec == 1){
                cool1.setFitWidth(w + 10);                                      // Set adjust dimensions
                cool1.setFitHeight(h + 10);
                w = cool1.getFitWidth();                                        // Set Screen dimensions to image
                h = cool1.getFitHeight();
                canvas1.setWidth(w);                                            // Set Canvas to new Size
                canvas1.setHeight(h);
            }
            else if(selec == 2){
                cool2.setFitWidth(w + 10);                                      // Ajust dimensions
                cool2.setFitHeight(h + 10);
                w = cool2.getFitWidth();                                        // Set Screen dimensions to image
                h = cool2.getFitHeight();
                canvas2.setWidth(w);                                            // Set Canvas to new Size
                canvas2.setHeight(h);
            }
            else{}
        });
        
        menuZoomOut.setOnAction(e -> {                                           // If resize canvas button pushed
            if(selec == 0){
                cool.setFitWidth(w - 10);                                       // Adjust dimensions
                cool.setFitHeight(h - 10);
                w = cool.getFitWidth();                                         // Set screen dimensions to image
                h = cool.getFitHeight();
                canvas.setWidth(w);                                             // Set Canvas to new Size
                canvas.setHeight(h);
            }
            else if(selec == 1){
                cool1.setFitWidth(w - 10);                                      // Set adjust dimensions
                cool1.setFitHeight(h - 10);
                w = cool1.getFitWidth();                                        // Set Screen dimensions to image
                h = cool1.getFitHeight();
                canvas1.setWidth(w);                                            // Set Canvas to new Size
                canvas1.setHeight(h);
            }
            else if(selec == 2){
                cool2.setFitWidth(w - 10);                                      // Ajust dimensions
                cool2.setFitHeight(h - 10);
                w = cool2.getFitWidth();                                        // Set Screen dimensions to image
                h = cool2.getFitHeight();
                canvas2.setWidth(w);                                            // Set Canvas to new Size
                canvas2.setHeight(h);
            }
            else{}
        });
        
        canvasBtn.setOnAction(e -> {                                            // If canvas toggle button pushed
            if(selec == 0){
                cool.setFitWidth(Integer.parseInt(canW.getText()));             // Set image dimensions entered size
                cool.setFitHeight(Integer.parseInt(canH.getText()));
                w = cool.getFitWidth();                                         // Set Screen dimensions to image
                h = cool.getFitHeight();
                if(change == 0){tab1.setText(tab1.getText() + "*");}            // Update save
                change = 1;
            
                canvas.setWidth(w);                                             // Set Canvas to new Size
                canvas.setHeight(h);
            }
            else if(selec == 1){
                cool1.setFitWidth(Integer.parseInt(canW.getText()));            // Set image dimensions entered size
                cool1.setFitHeight(Integer.parseInt(canH.getText()));
                w = cool1.getFitWidth();                                        // Set Screen dimensions to image
                h = cool1.getFitHeight();
                if(change1 == 0){tab2.setText(tab2.getText() + "*");}           // Update save
                change1 = 1;
            
                canvas1.setWidth(w);                                            // Set Canvas to new Size
                canvas1.setHeight(h);
            }
            else if(selec == 2){
                cool2.setFitWidth(Integer.parseInt(canW.getText()));            // Set image dimensions entered size
                cool2.setFitHeight(Integer.parseInt(canH.getText()));
                w = cool2.getFitWidth();                                        // Set Screen dimensions to image
                h = cool2.getFitHeight();
                if(change2 == 0){tab3.setText(tab3.getText() + "*");}           // Update save
                change2 = 1;
            
                canvas2.setWidth(w);                                            // Set Canvas to new Size
                canvas2.setHeight(h);
            }
        });
        
        fillBox.setOnAction(e -> {                                              // If fill box checked
            if(hold2 == 0){                                                     // If hidden, show
                gP.add(hFill, 0, 13);
                gP.add(hDrop1, 0, 14);
                hold2 = 1;
            }
            else{                                                               // If shown, hide
                gP.getChildren().remove(hFill);
                gP.getChildren().remove(hDrop1);
                hold2 = 0;
            }
        });
                                                                                // Create Handers for Color Droppers
        dropBtn.setOnAction(new dropBtnHandler(cool, cool1, cool2, coolCrab, canvas, canvas1, canvas2));
        dropBtn1.setOnAction(new dropBtn1Handler(cool, cool1, cool2, coolCrab, canvas, canvas1, canvas2));
        
                                                                                // Create Event Handlers for tools
        lineBtn.setOnAction(new lineHandler(canvas, canvas1, canvas2, gc, gc1, gc2, colorPicker, widthText, lineBtn, primaryStage, tabPane, dropBtn));
        curveBtn.setOnAction(new curveHandler(canvas, canvas1, canvas2, gc, gc1, gc2, colorPicker, widthText, curveBtn, primaryStage, tabPane, dropBtn));
        rectBtn.setOnAction(new rectangleHandler(canvas, canvas1, canvas2, gc, gc1, gc2, colorPicker, fillPick, widthText, rectBtn, primaryStage, tabPane, fillBox, dropBtn, dropBtn1));
        squBtn.setOnAction(new squareHandler(canvas, canvas1, canvas2, gc, gc1, gc2, colorPicker, fillPick, widthText, squBtn, primaryStage, tabPane, fillBox, dropBtn, dropBtn1));
        ovalBtn.setOnAction(new ovalHandler(canvas, canvas1, canvas2, gc, gc1, gc2, colorPicker, fillPick, widthText, ovalBtn, primaryStage, tabPane, fillBox, dropBtn, dropBtn1));
        cirBtn.setOnAction(new circleHandler(canvas, canvas1, canvas2, gc, gc1, gc2, colorPicker, fillPick, widthText, cirBtn, primaryStage, tabPane, fillBox, dropBtn, dropBtn1));
        textBtn.setOnAction(new textHandler(canvas, canvas1, canvas2, gc, gc1, gc2, colorPicker, widthText, textBtn, primaryStage, tabPane, dropBtn, enteredText));
        
        menuClear.setOnAction(e -> {                                            // If Clear Canvas button is pushed
            if(selec == 0){
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());      // Clear Canvas
            }
            else if (selec == 1){
                gc1.clearRect(0, 0, canvas1.getWidth(), canvas1.getHeight());   // Clear Canvas
            }
            else if (selec == 2){
                gc2.clearRect(0, 0, canvas2.getWidth(), canvas2.getHeight());   // Clear Canvas
            }
            else{
                System.out.println("Canvas Clear Error");
            }
        });
                                                                                // Create Menu Action Handlers
        menuSave.setOnAction(new saveHandler(cool, cool1, cool2 , coolCrab, canvas, canvas1, canvas2, primaryStage, tabPane)); 
        menuSaveAs.setOnAction(new saveAsHandler(cool, cool1, cool2, coolCrab, canvas, canvas1, canvas2, primaryStage, tabPane)); 
        primaryStage.setOnCloseRequest(new exitHandler(cool, cool1, cool2, coolCrab, canvas, canvas1, canvas2, primaryStage, tabPane));
        
        menuExit.setOnAction(e -> {                                             // If exit button is pushed
            exitHandler.exit(cool, cool1, cool2, coolCrab, canvas, canvas1, canvas2, primaryStage, tabPane);
        });
        
        menuSnap.setOnAction(e -> {                                             // If Snap button is pushed
            if(selec == 0){
                Rectangle2D screenBounds = Screen.getPrimary().getBounds();     // Read monitor dimensions, and if image is larger,
                if((screenBounds.getWidth() < w) || (screenBounds.getHeight() < h)){
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    cool.setFitWidth(screenBounds.getWidth()-200);              // Set image dimensions to monitor size
                    cool.setFitHeight(screenBounds.getHeight()-200);
                    w = cool.getFitWidth();                                     // Set Screen dimensions to image
                    h = cool.getFitHeight();
                    if(change == 0){tab1.setText(tab1.getText() + "*");}
                    change = 1;
                }
                else{}
                
                canvas.setWidth(w);                                             // Set Canvas to new Size
                canvas.setHeight(h);
                
                if(primaryStage.isMaximized()){}                                // If window is minimized
                else{
                    primaryStage.setWidth(w+200);                               // Set window to slightly larger than image
                    primaryStage.setHeight(h+200);
                }
            }
            else if(selec == 1){
                Rectangle2D screenBounds = Screen.getPrimary().getBounds();     // Read monitor dimensions, and if image is larger,
                if((screenBounds.getWidth() < w) || (screenBounds.getHeight() < h)){
                    gc1.clearRect(0, 0, canvas1.getWidth(), canvas1.getHeight());
                    cool1.setFitWidth(screenBounds.getWidth()-200);             // Set image dimensions to monitor size
                    cool1.setFitHeight(screenBounds.getHeight()-200);
                    w = cool1.getFitWidth();                                    // Set Screen dimensions to image
                    h = cool1.getFitHeight();
                    if(change1 == 0){tab2.setText(tab2.getText() + "*");}
                    change1 = 1;
                }
                else{}
                
                canvas1.setWidth(w);                                            // Set Canvas to new Size
                canvas1.setHeight(h);
                
                if(primaryStage.isMaximized()){}                                // If window is minimized
                else{
                    primaryStage.setWidth(w+200);                               // Set window to slightly larger than image
                    primaryStage.setHeight(h+200);
                }
            }
            else if(selec == 2){
                Rectangle2D screenBounds = Screen.getPrimary().getBounds();     // Read monitor dimensions, and if image is larger,
                if((screenBounds.getWidth() < w) || (screenBounds.getHeight() < h)){
                    gc2.clearRect(0, 0, canvas2.getWidth(), canvas2.getHeight());
                    cool2.setFitWidth(screenBounds.getWidth()-200);             // Set image dimensions to monitor size
                    cool2.setFitHeight(screenBounds.getHeight()-200);
                    w = cool2.getFitWidth();                                    // Set Screen dimensions to image
                    h = cool2.getFitHeight();
                    if(change2 == 0){tab3.setText(tab3.getText() + "*");}
                    change2 = 1;
                }
                else{}
                
                canvas2.setWidth(w);                                            // Set Canvas to new Size
                canvas2.setHeight(h);
                
                if(primaryStage.isMaximized()){}                                // If window is minimized
                else{
                    primaryStage.setWidth(w+200);                               // Set window to slightly larger than image
                    primaryStage.setHeight(h+200);
                }
            }
            else{
                System.out.println("Snap Error");
            }
        });
        
        menuHelp.setOnAction(new helpHandler());                                // If help button is pushed
        
        menuAbout.setOnAction(new aboutHandler());                              // If about button is pushed
    }
}