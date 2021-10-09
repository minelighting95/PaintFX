package PaintFX;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.control.Tooltip;
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
/*   Version: 1.4.0                                                     */
/*   Notes: See PaintFX_Release_Notes.txt                               */
/*                                                                      */
/* ******************************************************************** */

/**
 * Main Control Class for PaintFX
 * @author Alex Appel
 */


public class PaintFX extends Application {

    volatile private static double w = 1000;                                             // Create default variables
    volatile private static double h = 800;
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
    volatile private static Canvas canvas;
    volatile private static Canvas canvas1;
    volatile private static Canvas canvas2;
    volatile private static ImageView cool;
    volatile private static ImageView cool1;
    volatile private static ImageView cool2;
    volatile private static TabPane tabPane;
    volatile private static StackPane coolCrab;
    volatile private static ToggleButton lineBtn;
    volatile private static ToggleButton curveBtn;
    volatile private static ToggleButton rectBtn;
    volatile private static ToggleButton squBtn;
    volatile private static ToggleButton ovalBtn;
    volatile private static ToggleButton cirBtn;
    volatile private static ToggleButton textBtn;
    volatile private static ToggleButton dropBtn;
    volatile private static ToggleButton dropBtn1;
    volatile private static ToggleButton eraserBtn;
    volatile private static ToggleButton rRectBtn;
    volatile private static ToggleButton polyBtn;
    volatile private static Stack<Canvas> canvasStack;
    volatile private static Stack<Canvas> canvas1Stack;
    volatile private static Stack<Canvas> canvas2Stack;
    volatile private static Stack<Canvas> redoStack;
    volatile private static Stack<Canvas> redo1Stack;
    volatile private static Stack<Canvas> redo2Stack;
    volatile private static Stack<ImageView> coolStack;
    volatile private static Stack<ImageView> cool1Stack;
    volatile private static Stack<ImageView> cool2Stack;
    volatile private static Stack<ImageView> recoolStack;
    volatile private static Stack<ImageView> recool1Stack;
    volatile private static Stack<ImageView> recool2Stack;
    volatile private static int saveTime = 15;
    volatile private static int interval = saveTime;
    volatile private static Timer timer;
    volatile private static boolean save;
    volatile private static String saveName;
    
    public static Image getImage(){return crab;}                                // Create Get and Set Methods
    public static void setImage(Image c){crab = c;}
    public static Image getImage1(){return crab1;}
    public static void setImage1(Image c){crab1 = c;}
    public static Image getImage2(){return crab2;}
    public static void setImage2(Image c){crab2 = c;}
    
    public static double getW(){return w;}
    public static double getH(){return h;}
    
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
    
    public static Canvas canvasPeek(){return canvasStack.peek();}
    public static void canvasPush(Canvas g){canvasStack.push(g);}
    public static void canvasPop(){canvasStack.pop();}
    public static void canvasClear(){canvasStack.clear();}
    
    public static Canvas canvas1Peek(){return canvas1Stack.peek();}
    public static void canvas1Push(Canvas g){canvas1Stack.push(g);}
    public static void canvas1Pop(){canvas1Stack.pop();}
    public static void canvas1Clear(){canvas1Stack.clear();}
    
    public static Canvas canvas2Peek(){return canvas2Stack.peek();}
    public static void canvas2Push(Canvas g){canvas2Stack.push(g);}
    public static void canvas2Pop(){canvas2Stack.pop();}
    public static void canvas2Clear(){canvas2Stack.clear();}
    
    public static Canvas redoPeek(){return redoStack.peek();}
    public static void redoPush(Canvas g){redoStack.push(g);}
    public static void redoPop(){redoStack.pop();}
    public static void redoClear(){redoStack.clear();}
    
    public static Canvas redo1Peek(){return redo1Stack.peek();}
    public static void redo1Push(Canvas g){redo1Stack.push(g);}
    public static void redo1Pop(){redo1Stack.pop();}
    public static void redo1Clear(){redo1Stack.clear();}
    
    public static Canvas redo2Peek(){return redo2Stack.peek();}
    public static void redo2Push(Canvas g){redo2Stack.push(g);}
    public static void redo2Pop(){redo2Stack.pop();}
    public static void redo2Clear(){redo2Stack.clear();}
    
    public static ImageView coolPeek(){return coolStack.peek();}
    public static void coolPush(ImageView g){coolStack.push(g);}
    public static void coolPop(){coolStack.pop();}
    public static void coolClear(){coolStack.clear();}
    
    public static ImageView cool1Peek(){return cool1Stack.peek();}
    public static void cool1Push(ImageView g){cool1Stack.push(g);}
    public static void cool1Pop(){cool1Stack.pop();}
    public static void cool1Clear(){cool1Stack.clear();}
    
    public static ImageView cool2Peek(){return cool2Stack.peek();}
    public static void cool2Push(ImageView g){cool2Stack.push(g);}
    public static void cool2Pop(){cool2Stack.pop();}
    public static void cool2Clear(){cool2Stack.clear();}
    
    public static ImageView recoolPeek(){return recoolStack.peek();}
    public static void recoolPush(ImageView g){recoolStack.push(g);}
    public static void recoolPop(){recoolStack.pop();}
    public static void recoolClear(){recoolStack.clear();}
    
    public static ImageView recool1Peek(){return recool1Stack.peek();}
    public static void recool1Push(ImageView g){recool1Stack.push(g);}
    public static void recool1Pop(){recool1Stack.pop();}
    public static void recool1Clear(){recool1Stack.clear();}
    
    public static ImageView recool2Peek(){return recool2Stack.peek();}
    public static void recool2Push(ImageView g){recool2Stack.push(g);}
    public static void recool2Pop(){recool2Stack.pop();}
    public static void recool2Clear(){recool2Stack.clear();}
    
    public static void setSave(boolean s){save = s;}
    public static boolean getSave(){return save;}
    
    public static String getSaveName(){return saveName;}
    
    public static void setSaveTime(int s){
        saveTime = s;
        interval = s;
    }
    
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
        eraserBtn.setSelected(false);
        rRectBtn.setSelected(false);
        polyBtn.setSelected(false);
    }
    
    public static void undo(){
        if(selec == 0){
            if(canvasStack.peek().equals(canvas)){}
            else{
                redoPush(canvasPeek());
                canvasPop();
                if(change == 0){
                    tabPane.getTabs().get(0).setText(tabPane.getTabs().get(0).getText() + "*");
                    PaintFX.setChange(1);
                }
                coolCrab.getChildren().remove(1);
                coolCrab.getChildren().add(1, canvasPeek());
            }
            if(coolStack.peek().equals(cool)){}
            else{
                recoolPush(coolPeek());
                coolPop();
                if(change == 0){
                    tabPane.getTabs().get(0).setText(tabPane.getTabs().get(0).getText() + "*");
                    PaintFX.setChange(1);
                }
                coolCrab.getChildren().remove(0);
                coolCrab.getChildren().add(0, coolPeek());
            }
        }
        else if(selec == 1){
            if(canvas1Stack.peek().equals(canvas1)){}
            else{
                redo1Push(canvas1Peek());
                canvas1Pop();
                if(change1 == 0){
                    tabPane.getTabs().get(1).setText(tabPane.getTabs().get(1).getText() + "*");
                    PaintFX.setChange1(1);
                }
                coolCrab.getChildren().remove(1);
                coolCrab.getChildren().add(1, canvas1Peek());
            }
            if(cool1Stack.peek().equals(cool1)){}
            else{
                recool1Push(cool1Peek());
                cool1Pop();
                if(change1 == 0){
                    tabPane.getTabs().get(1).setText(tabPane.getTabs().get(1).getText() + "*");
                    PaintFX.setChange1(1);
                }
                coolCrab.getChildren().remove(0);
                coolCrab.getChildren().add(0, cool1Peek());
            }
        }
        else if(selec == 2){
            if(canvas2Stack.peek().equals(canvas2)){}
            else{
                redo2Push(canvas2Peek());
                canvas2Pop();
                if(change2 == 0){
                    tabPane.getTabs().get(2).setText(tabPane.getTabs().get(2).getText() + "*");
                    PaintFX.setChange2(1);
                }
                coolCrab.getChildren().remove(1);
                coolCrab.getChildren().add(1, canvas2Peek());
            }
            if(cool2Stack.peek().equals(cool2)){}
            else{
                recool2Push(cool2Peek());
                cool2Pop();
                if(change2 == 0){
                    tabPane.getTabs().get(2).setText(tabPane.getTabs().get(2).getText() + "*");
                    PaintFX.setChange2(1);
                }
                coolCrab.getChildren().remove(0);
                coolCrab.getChildren().add(0, cool2Peek());
            }
        }
    }
    
    public static void redo(){
        if(selec == 0){
            if(redoStack.empty()){}
            else{
                canvasPush(redoPeek());
                redoPop();
                if(change == 0){
                    tabPane.getTabs().get(0).setText(tabPane.getTabs().get(0).getText() + "*");
                    PaintFX.setChange(1);
                }
                coolCrab.getChildren().remove(1);
                coolCrab.getChildren().add(1, canvasPeek());
            }
            if(recoolStack.empty()){}
            else{
                coolPush(recoolPeek());
                recoolPop();
                if(change == 0){
                    tabPane.getTabs().get(0).setText(tabPane.getTabs().get(0).getText() + "*");
                    PaintFX.setChange(1);
                }
                coolCrab.getChildren().remove(0);
                coolCrab.getChildren().add(0, coolPeek());
            }
        }
        else if(selec == 1){
            if(redo1Stack.empty()){}
            else{
                canvas1Push(redo1Peek());
                redo1Pop();
                if(change1 == 0){
                    tabPane.getTabs().get(1).setText(tabPane.getTabs().get(1).getText() + "*");
                    PaintFX.setChange1(1);
                }
                coolCrab.getChildren().remove(1);
                coolCrab.getChildren().add(1, canvas1Peek());
            }
            if(recool1Stack.empty()){}
            else{
                cool1Push(recool1Peek());
                recool1Pop();
                if(change1 == 0){
                    tabPane.getTabs().get(1).setText(tabPane.getTabs().get(1).getText() + "*");
                    PaintFX.setChange1(1);
                }
                coolCrab.getChildren().remove(0);
                coolCrab.getChildren().add(0, cool1Peek());
            }
        }
        else if(selec == 2){
            if(redo2Stack.empty()){}
            else{
                canvas2Push(redo2Peek());
                redo2Pop();
                if(change2 == 0){
                    tabPane.getTabs().get(2).setText(tabPane.getTabs().get(2).getText() + "*");
                    PaintFX.setChange2(1);
                }
                coolCrab.getChildren().remove(1);
                coolCrab.getChildren().add(1, canvas2Peek());
            }
            if(recool2Stack.empty()){}
            else{
                cool2Push(recool2Peek());
                recool2Pop();
                if(change2 == 0){
                    tabPane.getTabs().get(2).setText(tabPane.getTabs().get(2).getText() + "*");
                    PaintFX.setChange2(1);
                }
                coolCrab.getChildren().remove(0);
                coolCrab.getChildren().add(0, cool2Peek());
            }
        }
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
        MenuItem menuAuto = new MenuItem("Auto Save Settings");                 // Create "Save" item
        MenuItem menuSaveAs = new MenuItem("Save As...");                       // Create "Save As..." item
        MenuItem menuExit = new MenuItem("Exit");                               // Create "Exit" item
                                                                                // Create Key Commands
        menuOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        menuSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        menuSaveAs.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
        menuAuto.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN));
        menuExit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        
        MenuItem menuUndo = new MenuItem("Undo");                        // Create "Snap to Fit" item
        MenuItem menuRedo = new MenuItem("Redo");                        // Create "Snap to Fit" item
        MenuItem menuSnap = new MenuItem("Snap to Fit");                        // Create "Snap to Fit" item
        MenuItem menuCanvas = new MenuItem("Resize Canvas");                    // Create "Resize Canvas" item
        MenuItem menuZoomIn = new MenuItem("Zoom In");                          // Create "Zoom In" item
        MenuItem menuZoomOut = new MenuItem("Zoom Out");                        // Create "Zoom Out" item
        MenuItem menuTools = new MenuItem("Toggle Tools");                      // Create "Toggle Tools" item
        MenuItem menuMove = new MenuItem("Cut and Move");                      // Create "Toggle Tools" item
        MenuItem menuClear = new MenuItem("Clear Canvas");                      // Create "Clear Canvas" item
                                                                                // Create Key Commands
        menuUndo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        menuRedo.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN));
        menuSnap.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
        menuCanvas.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
        menuZoomIn.setAccelerator(new KeyCodeCombination(KeyCode.ADD, KeyCombination.CONTROL_DOWN));
        menuZoomOut.setAccelerator(new KeyCodeCombination(KeyCode.SUBTRACT, KeyCombination.CONTROL_DOWN));
        menuTools.setAccelerator(new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN));
        menuMove.setAccelerator(new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN));
        menuClear.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        
        MenuItem menuHelp = new MenuItem("Help");                               // Create "Help" item
        MenuItem menuAbout = new MenuItem("About");                             // Create "About" item
                                                                                // Create Key Commands
        menuHelp.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
        menuAbout.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN));
        
        menu.getItems().addAll(menuOpen, menuSave, menuSaveAs, menuAuto, menuExit);       // Add items to "File"
        menu3.getItems().addAll(menuUndo, menuRedo, menuSnap,
                menuCanvas, menuZoomIn, menuZoomOut);                           // Add items to "Edit"
        menu2.getItems().addAll(menuTools, menuMove, menuClear);                          // Add items to "Draw"
        menu1.getItems().addAll(menuHelp, menuAbout);                           // Add items to "Help"
        menu.setGraphic(new ImageView("icon.png"));                             // Set menu graphic
        
        canvasStack = new Stack<Canvas>();
        canvas1Stack = new Stack<Canvas>();
        canvas2Stack = new Stack<Canvas>();
        
        redoStack = new Stack<Canvas>();
        redo1Stack = new Stack<Canvas>();
        redo2Stack = new Stack<Canvas>();
        
        coolStack = new Stack<ImageView>();
        cool1Stack = new Stack<ImageView>();
        cool2Stack = new Stack<ImageView>();
        
        recoolStack = new Stack<ImageView>();
        recool1Stack = new Stack<ImageView>();
        recool2Stack = new Stack<ImageView>();
        
        FileChooser fileChooser = new FileChooser();                            // Create file chooser
        canvas = new Canvas(w, h);                                       // Create Canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();                     // Allow graphics on canvas
        canvasPush(canvas);
        coolCrab = new StackPane();                                   // Create Node for image and canvas
        cool = new ImageView();                                       // Create new image view
        ScrollPane sp = new ScrollPane();                                       // Create ScrollPance
        sp.setFitToWidth(true);                                                 // Fit Pane Window
        sp.setFitToHeight(true);
        
        canvas1 = new Canvas(w, h);                                      // Create Canvas
        GraphicsContext gc1 = canvas1.getGraphicsContext2D();                   // Allow graphics on canvas
        canvas1Push(canvas1);
        cool1 = new ImageView();                                      // Create new image view
        
        canvas2 = new Canvas(w, h);                                      // Create Canvas
        GraphicsContext gc2 = canvas2.getGraphicsContext2D();                   // Allow graphics on canvas
        canvas2Push(canvas2);
        cool2 = new ImageView();                                      // Create new image view
        
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
        
        coolPush(cool);
        cool1Push(cool1);
        cool2Push(cool2);
        
        tabPane = new TabPane();                                        // Create Tab Pane
        Tab tab1 = new Tab("Image 1", sp);                                      // Create 3 Tabs
        Tab tab2 = new Tab("Image 2");
        Tab tab3 = new Tab("Image 3");
        tab1.setClosable(false);                                                // Make tabs Closeable
        tab2.setClosable(false);
        tab3.setClosable(false);
        tabPane.getTabs().addAll(tab1, tab2, tab3);                             // Add Tabs to pane
        VBox tabBox = new VBox(tabPane);
        
        ColorPicker colorPicker = new ColorPicker(Color.BLACK);                 // Create a color picker
        Tooltip.install(colorPicker, new Tooltip(
                "Select Edge Color"));
        HBox hColor = new HBox(colorPicker);
        ColorPicker fillPick = new ColorPicker();                               // Create a color picker
        Tooltip.install(fillPick, new Tooltip(
                "Select Fill Color"));
        HBox hFill = new HBox(fillPick);
        
        GridPane gP = new GridPane();                                           // Create sidebar grid
        gP.setVgap(10);                                                         // Set vertical padding to 10
        GridPane gPLeft = new GridPane();                                       // Create canvas sidebar grid
        gPLeft.setVgap(10);                                                     // Set vertical padding to 10
        
        ToggleGroup toggleGroup = new ToggleGroup();                            // Create ToggleGroup
        
        CheckBox fillBox = new CheckBox("Fill Shape");                          // Create CheckBox
        Tooltip.install(fillBox, new Tooltip(
                "Select to add Fill to Shape"));
        CheckBox eraseBox = new CheckBox("Transparent Erase");                          // Create CheckBox
        Tooltip.install(eraseBox, new Tooltip(
                "Select to Switch from White Erase to Transparent Erase"));
        
        lineBtn = new ToggleButton("Line");                        // Create Line toggle button
        HBox hLine = new HBox(lineBtn);
        Image lineImg = new Image("line.png");
        ImageView lineView = new ImageView(lineImg);
        lineBtn.setGraphic(lineView);
        Tooltip.install(lineBtn, new Tooltip(
                "Draws a Straight Line between Two Points"));
        
        curveBtn = new ToggleButton("Curve");                      // Create Curve Toggle Button
        HBox hCurve = new HBox(curveBtn);
        Image curveImg = new Image("curve.png");
        ImageView curveView = new ImageView(curveImg);
        curveBtn.setGraphic(curveView);
        Tooltip.install(curveBtn, new Tooltip(
                "Draws a Curve following the Mouse"));
        
        rectBtn = new ToggleButton("Rectangle");                   // Create Rectangle Toggle Button
        HBox hRect = new HBox(rectBtn);
        Image rectImg = new Image("rectangle.png");
        ImageView rectView = new ImageView(rectImg);
        rectBtn.setGraphic(rectView);
        Tooltip.install(rectBtn, new Tooltip(
                "Draws a Rectangle between Two Points"));
        
        squBtn = new ToggleButton("Square");                       // Create Square Toggle Button
        HBox hSqu = new HBox(squBtn);
        Image squImg = new Image("square.png");
        ImageView squView = new ImageView(squImg);
        squBtn.setGraphic(squView);
        Tooltip.install(squBtn, new Tooltip(
                "Draws a Square between Two Points"));
        
        ovalBtn = new ToggleButton("Ellipse");                     // Create Ellipse Toggle Button
        HBox hOval = new HBox(ovalBtn);
        Image ovalImg = new Image("oval.png");
        ImageView ovalView = new ImageView(ovalImg);
        ovalBtn.setGraphic(ovalView);
        Tooltip.install(ovalBtn, new Tooltip(
                "Draws an Ellipse between Two Points"));
        
        cirBtn = new ToggleButton("Circle");                       // Create Circle Toggle Button
        HBox hCir = new HBox(cirBtn);
        Image cirImg = new Image("circle.png");
        ImageView cirView = new ImageView(cirImg);
        cirBtn.setGraphic(cirView);
        Tooltip.install(cirBtn, new Tooltip(
                "Draws a Circle between Two Points"));
        
        textBtn = new ToggleButton("Text");                        // Create Text Toggle Button
        HBox hText = new HBox(textBtn);
        Tooltip.install(textBtn, new Tooltip(
                "Places above Text where the Mouse Button is Released"));
        
        dropBtn = new ToggleButton("Color Dropper");               // Create Main Dropper toggle button
        HBox hDrop = new HBox(dropBtn);
        Image dropImg = new Image("dropper.png");
        ImageView dropView = new ImageView(dropImg);
        dropBtn.setGraphic(dropView);
        Tooltip.install(dropBtn, new Tooltip(
                "Selects Edge Color from Selected Color on the Canvas"));
        
        dropBtn1 = new ToggleButton("Color Dropper");              // Create Fill Dropper toggle button
        HBox hDrop1 = new HBox(dropBtn1);
        Image dropImg1 = new Image("dropper1.png");
        ImageView dropView1 = new ImageView(dropImg1);
        dropBtn1.setGraphic(dropView1);
        Tooltip.install(dropBtn1, new Tooltip(
                "Selects Fill Color from Selected Color on the Canvas"));
        
        eraserBtn = new ToggleButton("Eraser");              // Create Fill Dropper toggle button
        HBox hEraser = new HBox(eraserBtn);
        Image eraserImg = new Image("eraser.png");
        ImageView eraserView = new ImageView(eraserImg);
        eraserBtn.setGraphic(eraserView);
        Tooltip.install(eraserBtn, new Tooltip(
                "Erases Content on Canvas with either White or Transparency"));
        
        polyBtn = new ToggleButton("Polygon");                                  // Create Polygon Toggle Button
        HBox hPoly = new HBox(polyBtn);
        Image polyImg = new Image("polygon.png");
        ImageView polyView = new ImageView(polyImg);
        polyBtn.setGraphic(polyView);
        Tooltip.install(polyBtn, new Tooltip(
                "Draws a Polygon of Entered Sides between Two Points"));
        
        rRectBtn = new ToggleButton("Rounded Rectangle");              // Create Fill Dropper toggle button
        HBox hRRect = new HBox(rRectBtn);
        Image rRectImg = new Image("rRectangle.png");
        ImageView rRectView = new ImageView(rRectImg);
        rRectBtn.setGraphic(rRectView);
        Tooltip.install(rRectBtn, new Tooltip(
                "Draws a Rounded Rectangle between Two Points"));
        
        Button canvasBtn = new Button("Set Size");                              // Create Resize Button
        Tooltip.install(canvasBtn, new Tooltip(
                "Set Canvas to Specified Size"));
        HBox hCanvas = new HBox(canvasBtn);
        
        lineBtn.setToggleGroup(toggleGroup);                                    // Add Toggle Buttons to Group
        curveBtn.setToggleGroup(toggleGroup);
        rectBtn.setToggleGroup(toggleGroup);
        squBtn.setToggleGroup(toggleGroup);
        ovalBtn.setToggleGroup(toggleGroup);
        cirBtn.setToggleGroup(toggleGroup);
        textBtn.setToggleGroup(toggleGroup);
        eraserBtn.setToggleGroup(toggleGroup);
        rRectBtn.setToggleGroup(toggleGroup);
        polyBtn.setToggleGroup(toggleGroup);
        
        Label widLabel = new Label("Enter Width or Font Size:");                             // Create width label
        Label sideLabel = new Label("Enter Number of Sides:");                             // Create width label
        Label shapeLabel = new Label("Choose Shape:");                          // Create height label
        Label colorLabel = new Label("Choose Color:");                          // Create color label
        Label textLabel = new Label("Enter Text:");                             // Create text label
        Label canWidth = new Label("Enter Canvas Width:");                      // Create can width label
        Label canHeight = new Label("Enter Canvas Height:");                    // Create can height label

        TextField widthText = new TextField();                                  // Create a width text field
        Tooltip.install(widthText, new Tooltip(
                "Enter Width or Font Size"));
        widthText.setText("1");                                                 // Set default value to 1
        TextField pointText = new TextField();                                  // Create a width text field
        Tooltip.install(pointText, new Tooltip(
                "Enter Number of Points"));
        pointText.setText("3");                                                 // Set default value to 1
        TextField enteredText = new TextField();                                // Create a user text field
        Tooltip.install(enteredText, new Tooltip(
                "Text to be Placed Onscreen"));
        TextField canW = new TextField();                                       // Create can width text field
        Tooltip.install(canW, new Tooltip(
                "Enter Canvas Width"));
        TextField canH = new TextField();                                       // Create can height text field
        Tooltip.install(canH, new Tooltip(
                "Enter Canvas Height"));
        
        gP.add(colorLabel, 0, 0);                                               // Assemble sidebar tool grid
        gP.add(hColor, 0, 1);
        gP.add(hDrop, 0, 2);
        gP.add(widLabel, 0, 3);
        gP.add(widthText, 0, 4);
        gP.add(shapeLabel, 0, 5);
        gP.add(hEraser, 0, 6);
        gP.add(eraseBox, 0, 7);
        gP.add(hLine, 0, 8);
        gP.add(hCurve, 0, 9);
        gP.add(hRect, 0, 10);
        gP.add(hRRect, 0, 11);
        gP.add(hSqu, 0, 12);
        gP.add(hOval, 0, 13);
        gP.add(hCir, 0, 14);
        gP.add(hPoly, 0, 15);
        gP.add(sideLabel, 0, 16);
        gP.add(pointText, 0, 17);
        gP.add(fillBox, 0, 18);
        gP.add(textLabel, 0, 21);
        gP.add(enteredText, 0, 22);
        gP.add(hText, 0, 23);
        
        gPLeft.add(canWidth, 0, 0);                                             // Assemble sidebar canvas grid
        gPLeft.add(canW, 0, 1);
        gPLeft.add(canHeight, 0, 2);
        gPLeft.add(canH, 0, 3);
        gPLeft.add(hCanvas, 0, 4);
       
        border.setTop(vBox);                                                    // Set the menu bar at the top of the screen
               
        coolCrab.getChildren().addAll(cool, canvas);                            // Add canvas and image to initial screen
        
        sp.setContent(coolCrab);                                                // Place in scrollpane
        
        border.setCenter(tabBox);                                               // Place stack in the center of the screen

        Scene scene = new Scene(border, w+100, h+100);                          // Create scene
        primaryStage.setScene(scene);                                           // Set scene as primary scene
        primaryStage.show();                                                    // Show scene
        
        primaryStage.setWidth(w+200);                                           // Set window to slightly larger than image
        primaryStage.setHeight(h+200);
        
        try {
            File time = new File("save.tfx");
            Scanner myReader = new Scanner(time);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                setSaveTime(Integer.parseInt(data));
                saveName = data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            try{
                File time = new File("save.tfx");
                FileWriter myWriter = new FileWriter(time);
                myWriter.write("15");
                saveName = "15";
                myWriter.close();
            } catch (IOException a) {
                System.out.println("File Error");
                e.printStackTrace();
            }
        }
        
        timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if(interval > 0)
                        {
                            if(save){
                                primaryStage.setTitle("PaintFX - " + interval + "s");
                            }
                            else{
                                primaryStage.setTitle("PaintFX");
                            }                                       // Set Title and Logo
                            interval--;
                        }
                        else{
                            saveHandler.autoSave(coolCrab, primaryStage);
                            interval = saveTime;
                        }
                    }
                });
            }
        }, 0, 1000);
                                                                                // Create Tab Switch
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{
            
            ov.setContent(null);                                                // Switch Tab Contents
            coolCrab.getChildren().clear();
            nv.setContent(sp);

            if(nv == tab2){
                setButtonSelects();
                setVariables(canvas1Peek().getWidth(), canvas1Peek().getHeight());
                setName(tab2.getText());
                setSelec(1);
                coolCrab.getChildren().addAll(cool1Peek(), canvas1Peek());
            }
            else if(nv == tab3){
                setButtonSelects();
                setVariables(canvas2Peek().getWidth(), canvas2Peek().getHeight());
                setName(tab3.getText());
                setSelec(2);
                coolCrab.getChildren().addAll(cool2Peek(), canvas2Peek());
            }
            else if(nv == tab1){
                setButtonSelects();
                setVariables(canvasPeek().getWidth(), canvasPeek().getHeight());
                setName(tab1.getText());
                setSelec(0);
                coolCrab.getChildren().addAll(coolPeek(), canvasPeek());
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
        
        menuUndo.setOnAction(e -> {undo();});                                           // If resize canvas button pushed
        
        menuRedo.setOnAction(e -> {redo();});                                           // If resize canvas button pushed
        
        menuZoomIn.setOnAction(e -> {                                           // If Zoom In triggered
            if(selec == 0){
                coolPeek().setFitWidth(w + 10);                                       // Adjust dimensions
                coolPeek().setFitHeight(h + 10);
                w = coolPeek().getFitWidth();                                         // Set screen dimensions to image
                h = coolPeek().getFitHeight();
                canvas.setWidth(w);                                             // Set Canvas to new Size
                canvas.setHeight(h);
            }
            else if(selec == 1){
                cool1Peek().setFitWidth(w + 10);                                      // Set adjust dimensions
                cool1Peek().setFitHeight(h + 10);
                w = cool1Peek().getFitWidth();                                        // Set Screen dimensions to image
                h = cool1Peek().getFitHeight();
                canvas1.setWidth(w);                                            // Set Canvas to new Size
                canvas1.setHeight(h);
            }
            else if(selec == 2){
                cool2Peek().setFitWidth(w + 10);                                      // Ajust dimensions
                cool2Peek().setFitHeight(h + 10);
                w = cool2Peek().getFitWidth();                                        // Set Screen dimensions to image
                h = cool2Peek().getFitHeight();
                canvas2.setWidth(w);                                            // Set Canvas to new Size
                canvas2.setHeight(h);
            }
            else{}
        });
        
        menuZoomOut.setOnAction(e -> {                                           // If resize canvas button pushed
            if(selec == 0){
                coolPeek().setFitWidth(w - 10);                                       // Adjust dimensions
                coolPeek().setFitHeight(h - 10);
                w = coolPeek().getFitWidth();                                         // Set screen dimensions to image
                h = coolPeek().getFitHeight();
                canvas.setWidth(w);                                             // Set Canvas to new Size
                canvas.setHeight(h);
            }
            else if(selec == 1){
                cool1Peek().setFitWidth(w - 10);                                      // Set adjust dimensions
                cool1Peek().setFitHeight(h - 10);
                w = cool1Peek().getFitWidth();                                        // Set Screen dimensions to image
                h = cool1Peek().getFitHeight();
                canvas1.setWidth(w);                                            // Set Canvas to new Size
                canvas1.setHeight(h);
            }
            else if(selec == 2){
                cool2Peek().setFitWidth(w - 10);                                      // Ajust dimensions
                cool2Peek().setFitHeight(h - 10);
                w = cool2Peek().getFitWidth();                                        // Set Screen dimensions to image
                h = cool2Peek().getFitHeight();
                canvas2.setWidth(w);                                            // Set Canvas to new Size
                canvas2.setHeight(h);
            }
            else{}
        });
        
        canvasBtn.setOnAction(e -> {                                            // If canvas toggle button pushed
            if(selec == 0){
                coolPeek().setFitWidth(Integer.parseInt(canW.getText()));             // Set image dimensions entered size
                coolPeek().setFitHeight(Integer.parseInt(canH.getText()));
                w = coolPeek().getFitWidth();                                         // Set Screen dimensions to image
                h = coolPeek().getFitHeight();
                if(change == 0){tab1.setText(tab1.getText() + "*");}            // Update save
                change = 1;
            
                canvas.setWidth(w);                                             // Set Canvas to new Size
                canvas.setHeight(h);
            }
            else if(selec == 1){
                cool1Peek().setFitWidth(Integer.parseInt(canW.getText()));            // Set image dimensions entered size
                cool1Peek().setFitHeight(Integer.parseInt(canH.getText()));
                w = cool1Peek().getFitWidth();                                        // Set Screen dimensions to image
                h = cool1Peek().getFitHeight();
                if(change1 == 0){tab2.setText(tab2.getText() + "*");}           // Update save
                change1 = 1;
            
                canvas1.setWidth(w);                                            // Set Canvas to new Size
                canvas1.setHeight(h);
            }
            else if(selec == 2){
                cool2Peek().setFitWidth(Integer.parseInt(canW.getText()));            // Set image dimensions entered size
                cool2Peek().setFitHeight(Integer.parseInt(canH.getText()));
                w = cool2Peek().getFitWidth();                                        // Set Screen dimensions to image
                h = cool2Peek().getFitHeight();
                if(change2 == 0){tab3.setText(tab3.getText() + "*");}           // Update save
                change2 = 1;
            
                canvas2.setWidth(w);                                            // Set Canvas to new Size
                canvas2.setHeight(h);
            }
        });
        
        fillBox.setOnAction(e -> {                                              // If fill box checked
            if(hold2 == 0){                                                     // If hidden, show
                gP.add(hFill, 0, 19);
                gP.add(hDrop1, 0, 20);
                hold2 = 1;
            }
            else{                                                               // If shown, hide
                gP.getChildren().remove(hFill);
                gP.getChildren().remove(hDrop1);
                hold2 = 0;
            }
        });
                                                                                // Create Handers for Color Droppers
        dropBtn.setOnAction(new dropBtnHandler(coolCrab));
        dropBtn1.setOnAction(new dropBtn1Handler(coolCrab));
        
                                                                                // Create Event Handlers for tools
        lineBtn.setOnAction(new lineHandler(coolCrab, colorPicker, widthText, lineBtn, primaryStage, tabPane, dropBtn));
        curveBtn.setOnAction(new curveHandler(coolCrab, colorPicker, widthText, curveBtn, primaryStage, tabPane, dropBtn));
        rectBtn.setOnAction(new rectangleHandler(coolCrab, colorPicker, fillPick, widthText, rectBtn, primaryStage, tabPane, fillBox, dropBtn, dropBtn1));
        squBtn.setOnAction(new squareHandler(coolCrab, colorPicker, fillPick, widthText, squBtn, primaryStage, tabPane, fillBox, dropBtn, dropBtn1));
        ovalBtn.setOnAction(new ovalHandler(coolCrab, colorPicker, fillPick, widthText, ovalBtn, primaryStage, tabPane, fillBox, dropBtn, dropBtn1));
        cirBtn.setOnAction(new circleHandler(coolCrab, colorPicker, fillPick, widthText, cirBtn, primaryStage, tabPane, fillBox, dropBtn, dropBtn1));
        textBtn.setOnAction(new textHandler(coolCrab, colorPicker, widthText, textBtn, primaryStage, tabPane, dropBtn, enteredText));
        eraserBtn.setOnAction(new eraserHandler(coolCrab, widthText, eraserBtn, primaryStage, tabPane, eraseBox));
        rRectBtn.setOnAction(new rRectangleHandler(coolCrab, colorPicker, fillPick, widthText, rRectBtn, primaryStage, tabPane, fillBox, dropBtn, dropBtn1));
        polyBtn.setOnAction(new polygonHandler(coolCrab, colorPicker, fillPick, widthText, pointText, polyBtn, primaryStage, tabPane, fillBox, dropBtn, dropBtn1));
        menuMove.setOnAction(new moveHandler(coolCrab, primaryStage, tabPane, eraseBox));
        
        menuClear.setOnAction(e -> {                                            // If Clear Canvas button is pushed
            if(selec == 0){
                canvasPush(canvas);
                coolCrab.getChildren().remove(1);
                coolCrab.getChildren().add(1, canvasPeek());
            }
            else if (selec == 1){
                canvas1Push(canvas1);
                coolCrab.getChildren().remove(1);
                coolCrab.getChildren().add(1, canvas1Peek());
            }
            else if (selec == 2){
                canvas2Push(canvas2);
                coolCrab.getChildren().remove(1);
                coolCrab.getChildren().add(1, canvas2Peek());
            }
            else{
                System.out.println("Canvas Clear Error");
            }
        });
                                                                                // Create Menu Action Handlers
        menuSave.setOnAction(new saveHandler(coolCrab, primaryStage, tabPane)); 
        menuSaveAs.setOnAction(new saveAsHandler(coolCrab, primaryStage, tabPane)); 
        primaryStage.setOnCloseRequest(new exitHandler(coolCrab, primaryStage, tabPane));
        
        menuExit.setOnAction(e -> {                                             // If exit button is pushed
            exitHandler.exit(coolCrab, primaryStage, tabPane);
        });
        
        menuSnap.setOnAction(e -> {                                             // If Snap button is pushed
            if(selec == 0){
                Rectangle2D screenBounds = Screen.getPrimary().getBounds();     // Read monitor dimensions, and if image is larger,
                if((screenBounds.getWidth() < w) || (screenBounds.getHeight() < h)){
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    coolPeek().setFitWidth(screenBounds.getWidth()-200);              // Set image dimensions to monitor size
                    coolPeek().setFitHeight(screenBounds.getHeight()-200);
                    w = coolPeek().getFitWidth();                                     // Set Screen dimensions to image
                    h = coolPeek().getFitHeight();
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
                    cool1Peek().setFitWidth(screenBounds.getWidth()-200);             // Set image dimensions to monitor size
                    cool1Peek().setFitHeight(screenBounds.getHeight()-200);
                    w = cool1Peek().getFitWidth();                                    // Set Screen dimensions to image
                    h = cool1Peek().getFitHeight();
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
                    cool2Peek().setFitWidth(screenBounds.getWidth()-200);             // Set image dimensions to monitor size
                    cool2Peek().setFitHeight(screenBounds.getHeight()-200);
                    w = cool2Peek().getFitWidth();                                    // Set Screen dimensions to image
                    h = cool2Peek().getFitHeight();
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
        menuAuto.setOnAction(new autoHandler());                              // If about button is pushed
    }
}