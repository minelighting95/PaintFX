package PaintFX;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
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
/*   Version: 1.5.0                                                     */
/*   Notes: See PaintFX_Release_Notes.txt                               */
/*                                                                      */
/* ******************************************************************** */

/**
 * Main Control Class for PaintFX
 * @author Alex Appel
 */

public class PaintFX extends Application {

    volatile private static double w = 1000;                                    // Create default variables
    volatile private static double h = 800;
    volatile private static double scaleX = 1;
    volatile private static double scaleY = 1;
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
    volatile private static int saveTime = 60;
    volatile private static int interval = saveTime;
    volatile private static boolean save;
    volatile private static String saveName;
    volatile private static String log;
    volatile private static int logType;
    volatile private static boolean logLocked = true;
                                                                                // Create Get and Set Methods

    /**
     * Returns Tab 1 Image
     * @return crab - Tab 1 Image
     */
    public static Image getImage(){return crab;}                                

    /**
     * Sets Tab 1 Image
     * @param c New Tab 1 Image
     */
    public static void setImage(Image c){crab = c;}

    /**
     * Returns Tab 2 Image
     * @return crab1 - Tab 2 Image
     */
    public static Image getImage1(){return crab1;}

    /**
     * Sets Tab 2 Image
     * @param c New Tab 2 Image
     */
    public static void setImage1(Image c){crab1 = c;}

    /**
     * Returns Tab 3 Image
     * @return crab2 - Tab 3 Image
     */
    public static Image getImage2(){return crab2;}

    /**
     * Sets Tab 3 Image
     * @param c New Tab 3 Image
     */
    public static void setImage2(Image c){crab2 = c;}
    
    /**
     * Returns Current Canvas Width
     * @return w - Current Canvas Width
     */
    public static double getW(){return w;}

    /**
     * Returns Current Canvas Height
     * @return h - Current Canvas Height
     */
    public static double getH(){return h;}
    
    /**
     * Returns Tab 1 Change Status
     * @return change - 1 if Tab 1 has changed, otherwise 0
     */
    public static int getChange(){return change;}

    /**
     * Sets Tab 1 Change Status
     * @param c New Tab 1 Change Status
     */
    public static void setChange(int c){change = c;}

    /**
     * Returns Tab 2 Change Status
     * @return change1 - 1 if Tab 2 has changed, otherwise 0
     */
    public static int getChange1(){return change1;}

    /**
     * Sets Tab 2 Change Status
     * @param c New Tab 2 Change Status
     */
    public static void setChange1(int c){change1 = c;}

    /**
     * Returns Tab 3 Change Status
     * @return change2 - 1 if Tab 3 has changed, otherwise 0
     */
    public static int getChange2(){return change2;}

    /**
     * Sets Tab 3 Change Status
     * @param c Tab 3 Change Status
     */
    public static void setChange2(int c){change2 = c;}
    
    /**
     * Returns Currently Selected Tab
     * @return selec - 0 for Tab 1, 1 for Tab 2, 2 for Tab 3
     */
    public static int getSelec(){return selec;}

    /**
     * Sets Current Tab
     * @param c 0 for Tab 1, 1 for Tab 2, 2 for Tab 3
     */
    public static void setSelec(int c){selec = c;}
    
    /**
     * Returns Tab 1 File Name
     * @return fileName - Tab 1 File Name
     */
    public static String getFileName(){return fileName;}

    /**
     * Sets Tab 1 File Name
     * @param c New Tab 1 File Name
     */
    public static void setFileName(String c){fileName = c;}

    /**
     * Returns Tab 2 File Name
     * @return fileName1 - Tab 2 File Name
     */
    public static String getFileName1(){return fileName1;}

    /**
     * Sets Tab 2 File Name
     * @param c New Tab 2 File Name
     */
    public static void setFileName1(String c){fileName1 = c;}

    /**
     * Returns Tab 3 File Name
     * @return fileName2 - Tab 3 File Name
     */
    public static String getFileName2(){return fileName2;}

    /**
     * Sets Tab 3 File Name
     * @param c New Tab 3 File Name
     */
    public static void setFileName2(String c){fileName2 = c;}
    
    /**
     * Returns Tab File Name
     * @return name - Tab File Name
     */
    public static String getName(){return name;}

    /**
     * Sets Tab File Name
     * @param c New Tab File Name
     */
    public static void setName(String c){name = c;}

    /**
     * Returns Shape Edge Color
     * @return argb - Shape Edge Color
     */
    public static Color getColor(){return argb;}

    /**
     * Sets Shape Edge Color
     * @param c New Shape Edge Color
     */
    public static void setColor(Color c){argb = c;}

    /**
     * Returns Shape Fill Color
     * @return argb1 - Shape Fill Color
     */
    public static Color getColor1(){return argb1;}

    /**
     * Sets Shape Fill Color
     * @param c New Shape Fill Color
     */
    public static void setColor1(Color c){argb1 = c;}
    
    /**
     * Returns Current Canvas in Tab 1 Canvas Stack
     * @return Canvas - Current Tab 1 Canvas
     */
    public static Canvas canvasPeek(){return canvasStack.peek();}

    /**
     * Pushes New Canvas to Top of Tab 1 Stack
     * @param g New Tab 1 Canvas
     */
    public static void canvasPush(Canvas g){canvasStack.push(g);}

    /**
     * Pops Top Canvas from Tab 1 Stack
     */
    public static void canvasPop(){canvasStack.pop();}

    /**
     * Clears Tab 1 Canvas Stack
     */
    public static void canvasClear(){canvasStack.clear();}
    
    /**
     * Returns Current Canvas in Tab 2 Stack
     * @return Canvas - Current Tab 2 Canvas
     */
    public static Canvas canvas1Peek(){return canvas1Stack.peek();}

    /**
     * Pushes New Canvas to Top of Tab 2 Stack
     * @param g New Tab 2 Canvas
     */
    public static void canvas1Push(Canvas g){canvas1Stack.push(g);}

    /**
     * Pops Top Canvas from Tab 2 tack
     */
    public static void canvas1Pop(){canvas1Stack.pop();}

    /**
     * Clears Tab 2 Canvas Stack
     */
    public static void canvas1Clear(){canvas1Stack.clear();}
    
    /**
     * Returns Current Canvas in Tab 3 Stack
     * @return Canvas - Current Tab 3 Canvas
     */
    public static Canvas canvas2Peek(){return canvas2Stack.peek();}

    /**
     * Pushes New Canvas to Top of Tab 3 Stack
     * @param g New Tab 3 Canvas
     */
    public static void canvas2Push(Canvas g){canvas2Stack.push(g);}

    /**
     * Pops Top Canvas from Tab 3 Stack
     */
    public static void canvas2Pop(){canvas2Stack.pop();}

    /**
     * Clears Tab 3 Canvas Stack
     */
    public static void canvas2Clear(){canvas2Stack.clear();}
    
    /**
     * Returns Current Canvas in Tab 1 Redo Stack
     * @return Canvas - Current Tab 1 Redo Canvas
     */
    public static Canvas redoPeek(){return redoStack.peek();}

    /**
     * Pushes New Canvas to Top of Tab 1 Redo Stack
     * @param g New Tab 3 Redo Canvas
     */
    public static void redoPush(Canvas g){redoStack.push(g);}

    /**
     * Pops Top Canvas from Tab 1 Redo Stack
     */
    public static void redoPop(){redoStack.pop();}

    /**
     * Clears Tab 1 Redo Stack
     */
    public static void redoClear(){redoStack.clear();}
    
    /**
     * Returns Current Canvas in Tab 2 Redo Stack
     * @return Canvas - Current Tab 2 Redo Canvas
     */
    public static Canvas redo1Peek(){return redo1Stack.peek();}

    /**
     * Pushes New Canvas to Top of Tab 2 Redo Stack
     * @param g New Tab 2 Redo Canvas
     */
    public static void redo1Push(Canvas g){redo1Stack.push(g);}

    /**
     * Pops Top Canvas from Tab 2 Redo Stack
     */
    public static void redo1Pop(){redo1Stack.pop();}

    /**
     * Clears Tab 2 Redo Stack
     */
    public static void redo1Clear(){redo1Stack.clear();}
    
    /**
     * Returns Current Canvas in Tab 3 Redo Stack
     * @return Canvas - Current Tab 3 Redo Canvas
     */
    public static Canvas redo2Peek(){return redo2Stack.peek();}

    /**
     * Pushes New Canvas to Top of Tab 3 Redo Stack
     * @param g NEw Tab 3 Redo Canvas
     */
    public static void redo2Push(Canvas g){redo2Stack.push(g);}

    /**
     * Pops Top Canvas from Tab 3 Redo Stack
     */
    public static void redo2Pop(){redo2Stack.pop();}

    /**
     * Clears Tab 3 Redo Stack
     */
    public static void redo2Clear(){redo2Stack.clear();}
    
    /**
     * Returns Current ImageView in Tab 1 Stack
     * @return ImageView - Current Tab 1 ImageView
     */
    public static ImageView coolPeek(){return coolStack.peek();}

    /**
     * Pushes New ImageView to Tab 1 Stack
     * @param g New Tab 1 ImageView
     */
    public static void coolPush(ImageView g){coolStack.push(g);}

    /**
     * Pops Top ImageView from Tab 1 Stack
     */
    public static void coolPop(){coolStack.pop();}

    /**
     * Clears Tab 1 ImageView Stack
     */
    public static void coolClear(){coolStack.clear();}
    
    /**
     * Returns Current ImageView in Tab 2 Stack
     * @return ImageView - Current Tab 2 ImageView
     */
    public static ImageView cool1Peek(){return cool1Stack.peek();}

    /**
     * Pushes New ImageView to Tab 2 Stack
     * @param g New Tab 2 ImageView
     */
    public static void cool1Push(ImageView g){cool1Stack.push(g);}

    /**
     * Pops Top ImageView from Tab 2 Stack
     */
    public static void cool1Pop(){cool1Stack.pop();}

    /**
     * Clears Tab 2 ImageView Stack
     */
    public static void cool1Clear(){cool1Stack.clear();}
    
    /**
     * Returns Current ImageView in Tab 3 Stack
     * @return ImageView - Current Tab 3 ImageView
     */
    public static ImageView cool2Peek(){return cool2Stack.peek();}

    /**
     * Pushes New ImageView to Tab 3 Stack
     * @param g New Tab 3 ImageView
     */
    public static void cool2Push(ImageView g){cool2Stack.push(g);}

    /**
     * Pops Top ImageView from Tab 2 Stack
     */
    public static void cool2Pop(){cool2Stack.pop();}

    /**
     * Clears Tab 3 ImageView Stack
     */
    public static void cool2Clear(){cool2Stack.clear();}
    
    /**
     * Returns Current ImageView in Tab 1 Redo Stack
     * @return ImageView - Current Tab 1 Redo ImageView
     */
    public static ImageView recoolPeek(){return recoolStack.peek();}

    /**
     * Pushes New ImageView to Tab 1 Redo Stack
     * @param g New Tab 1 ImageView Redo
     */
    public static void recoolPush(ImageView g){recoolStack.push(g);}

    /**
     * Pops Top ImageView from Tab 1 Redo Stack
     */
    public static void recoolPop(){recoolStack.pop();}

    /**
     * Clears Tab 1 Redo Stack
     */
    public static void recoolClear(){recoolStack.clear();}
    
    /**
     * Returns Current ImageView in Tab 2 Redo Stack
     * @return ImageView - Current Tab 2 Redo ImageView
     */
    public static ImageView recool1Peek(){return recool1Stack.peek();}

    /**
     * Pushes New ImageView to Tab 2 Redo Stack
     * @param g New Tab 2 ImageView Redo
     */
    public static void recool1Push(ImageView g){recool1Stack.push(g);}

    /**
     * Pops Top ImageView from Tab 2 Redo Stack
     */
    public static void recool1Pop(){recool1Stack.pop();}

    /**
     * Clears Tab 2 Redo Stack
     */
    public static void recool1Clear(){recool1Stack.clear();}
    
    /**
     * Returns Current ImageView in Tab 3 Redo Stack
     * @return ImageView - Current Tab 3 Redo ImageView
     */
    public static ImageView recool2Peek(){return recool2Stack.peek();}

    /**
     * Pushes New ImageView to Tab 3 Redo Stack
     * @param g New Tab 3 ImageView Redo
     */
    public static void recool2Push(ImageView g){recool2Stack.push(g);}

    /**
     * Pops Top ImageView from Tab 3 Redo Stack
     */
    public static void recool2Pop(){recool2Stack.pop();}

    /**
     * Clears Tab 3 Redo Stack
     */
    public static void recool2Clear(){recool2Stack.clear();}
    
    /**
     * Sets Auto Save Timer Visibility Value
     * @param s Boolean Visibility Value
     */
    public static void setSave(boolean s){save = s;}

    /**
     * Returns Auto Save Timer Visibility Value
     * @return save - Auto Save Timer Visibility Value
     */
    public static boolean getSave(){return save;}
    
    /**
     * Returns X Scaling for Main StackPane
     * @return scaleX - X Scaling Value for Main StackPane
     */
    public static double getScaleX(){return scaleX;}

    /**
     * Returns Y Scaling for Main StackPane
     * @return scaleY - Y Scaling Value for Main StackPane
     */
    public static double getScaleY(){return scaleY;}

    /**
     * Sets X Scaling for Main StackPane
     * @param x New X Scaling Value for Main StackPane
     */
    public static void setScaleX(double x){scaleX = x;}

    /**
     * Sets Y Scaling for Main StackPane
     * @param y New Y Scaling Value for Main StackPane
     */
    public static void setScaleY(double y){scaleY = y;}
    
    /**
     * Returns Auto Save Timer Value
     * @return saveName - String Containing Auto Save Timer Value
     */
    public static String getSaveName(){return saveName;}
    
    /**
     * Sets the Auto Save Time
     * @param s Auto Save Time (seconds)
     */
    public static void setSaveTime(int s){
        saveTime = s;
        interval = s;
    }
    
    /**
     * Sets Canvas Dimension Variables
     * @param wtemp New Width
     * @param htemp New Height
     */
    public static void setVariables(double wtemp, double htemp){
        w = wtemp;
        h = htemp;
    }
    
    /**
     * Sets Item to be Logged
     * @param s Item to be Logged
     * @param i Type of Logging (0: Save, 1: Tool, 2: Open, 3: Undo/Redo)
     */
    public static void logItem(String s, int i){
        log = s;
        logType = i;
        logLocked = false;
    }
    
    /**
     * Main PaintFX
     * @param args
     */
    public static void main(String[] args) {                                    // Main
        launch(args);
    }

    /**
     * Sets all Tool Buttons to Unselected
     */
    public static void setButtonSelects(){
        lineBtn.setSelected(false);                                             // Untoggle buttons
        curveBtn.setSelected(false);                                    
        rectBtn.setSelected(false);                                    
        squBtn.setSelected(false);                                     
        ovalBtn.setSelected(false);                                  
        cirBtn.setSelected(false);                                   
        textBtn.setSelected(false);                                     
        dropBtn.setSelected(false);                                 
        dropBtn1.setSelected(false);                                    
        eraserBtn.setSelected(false);
        rRectBtn.setSelected(false);
        polyBtn.setSelected(false);
    }
    
    /**
     * Undo Current Canvas and ImageView
     */
    public static void undo(){
        if(selec == 0){                                                         // For selected tab
            if(canvasStack.peek().equals(canvas)){}                             // If bottom of stack, do nothing
            else{
                redoPush(canvasPeek());                                         // Move top canvas element to redo stack
                canvasPop();                                                    // Remove top canvas element from undo
                if(change == 0){                                                // Update change and tab name
                    tabPane.getTabs().get(0).setText(tabPane.getTabs().get(0).getText() + "*");
                    PaintFX.setChange(1);
                }
                coolCrab.getChildren().remove(1);                               // Display previous canvas
                coolCrab.getChildren().add(1, canvasPeek());
            }
            if(coolStack.peek().equals(cool)){}                                 // If bottom of stack, do nothing
            else{
                recoolPush(coolPeek());                                         // Move top image element to redo stack
                coolPop();                                                      // Remove top image element from undo
                if(change == 0){                                                // Update change and tab name
                    tabPane.getTabs().get(0).setText(tabPane.getTabs().get(0).getText() + "*");
                    PaintFX.setChange(1);
                }
                coolCrab.getChildren().remove(0);                               // Make new image visible
                coolCrab.getChildren().add(0, coolPeek());
            }
            logItem("Undo", 3);
        }
        else if(selec == 1){                                                    // See above case
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
            logItem("Undo", 3);
        }
        else if(selec == 2){                                                    // See above case
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
            logItem("Undo", 3);
        }
    }
    
    /**
     * Redo Current Canvas and ImageView
     */
    public static void redo(){
        if(selec == 0){                                                         // For selected tab
            if(redoStack.empty()){}                                             // If redo stack is empty, do nothing
            else{
                canvasPush(redoPeek());                                         // Move top redo element to canvas stack
                redoPop();                                                      // Remove top redo element
                if(change == 0){                                                // Update tab name and change
                    tabPane.getTabs().get(0).setText(tabPane.getTabs().get(0).getText() + "*");
                    PaintFX.setChange(1);
                }
                coolCrab.getChildren().remove(1);                               // Make current canvas visible
                coolCrab.getChildren().add(1, canvasPeek());
            }
            if(recoolStack.empty()){}                                           // If redo stack is empty, do nothing
            else{
                coolPush(recoolPeek());                                         // Move top redo element to image tack
                recoolPop();                                                    // Remove top redo element
                if(change == 0){                                                // Update change and tab name
                    tabPane.getTabs().get(0).setText(tabPane.getTabs().get(0).getText() + "*");
                    PaintFX.setChange(1);
                }
                coolCrab.getChildren().remove(0);                               // Make current image visible
                coolCrab.getChildren().add(0, coolPeek());
            }
            logItem("Redo", 3);
        }
        else if(selec == 1){                                                    // See above case
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
            logItem("Redo", 3);
        }
        else if(selec == 2){                                                    // See above case
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
            logItem("Redo", 3);
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
        MenuItem menuAuto = new MenuItem("Auto Save Settings");                 // Create "Auto Save" item
        MenuItem menuSaveAs = new MenuItem("Save As...");                       // Create "Save As..." item
        MenuItem menuExit = new MenuItem("Exit");                               // Create "Exit" item
                                                                                // Create Key Commands
        menuOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        menuSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        menuSaveAs.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
        menuAuto.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN));
        menuExit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        
        MenuItem menuUndo = new MenuItem("Undo");                               // Create "Undo" item
        MenuItem menuRedo = new MenuItem("Redo");                               // Create "Redo" item
        MenuItem menuSnap = new MenuItem("Snap to Fit");                        // Create "Snap to Fit" item
        MenuItem menuCanvas = new MenuItem("Resize Canvas");                    // Create "Resize Canvas" item
        MenuItem menuZoomIn = new MenuItem("Zoom In");                          // Create "Zoom In" item
        MenuItem menuZoomOut = new MenuItem("Zoom Out");                        // Create "Zoom Out" item
        MenuItem menuTools = new MenuItem("Toggle Tools");                      // Create "Toggle Tools" item
        MenuItem menuMove = new MenuItem("Cut and Move");                       // Create "Cut and Move" item
        MenuItem menuCopy = new MenuItem("Copy and Paste");                     // Create "Copy and Paste" item
        MenuItem menuInvert = new MenuItem("Invert Colors");                    // Create "Invert Color" item
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
        menuCopy.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));
        menuInvert.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN));
        menuClear.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        
        MenuItem menuHelp = new MenuItem("Help");                               // Create "Help" item
        MenuItem menuAbout = new MenuItem("About");                             // Create "About" item
                                                                                // Create Key Commands
        menuHelp.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
        menuAbout.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN));
        
        menu.getItems().addAll(menuOpen, menuSave,
                menuSaveAs, menuAuto, menuExit);                                // Add items to "File"
        menu3.getItems().addAll(menuUndo, menuRedo, menuSnap,
                menuCanvas, menuZoomIn, menuZoomOut);                           // Add items to "Edit"
        menu2.getItems().addAll(menuTools, menuCopy, menuMove,
                menuInvert, menuClear);                                         // Add items to "Draw"
        menu1.getItems().addAll(menuHelp, menuAbout);                           // Add items to "Help"
        menu.setGraphic(new ImageView("icon.png"));                             // Set menu graphic
        
        canvasStack = new Stack();                                              // Create Canvas Stacks
        canvas1Stack = new Stack();
        canvas2Stack = new Stack();
        
        redoStack = new Stack();                                                // Create Canvas Redo Stacks
        redo1Stack = new Stack();
        redo2Stack = new Stack();
        
        coolStack = new Stack();                                                // Create Image Stacks
        cool1Stack = new Stack();
        cool2Stack = new Stack();
        
        recoolStack = new Stack();                                              // Create Image Redo Stacks
        recool1Stack = new Stack();
        recool2Stack = new Stack();
        
        canvas = new Canvas(w, h*10);                                           // Create Canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();                     // Allow graphics on canvas
        canvasPush(canvas);                                                     // Add canvas to stack
        coolCrab = new StackPane();                                             // Create Node for image and canvas
        cool = new ImageView();                                                 // Create new image view
        ScrollPane sp = new ScrollPane();                                       // Create ScrollPance
        sp.setFitToWidth(true);                                                 // Fit Pane Window
        sp.setFitToHeight(true);
        
        canvas1 = new Canvas(w, h);                                             // Create Canvas1
        GraphicsContext gc1 = canvas1.getGraphicsContext2D();                   // Allow graphics on canvas
        canvas1Push(canvas1);                                                   // Add canvas2 to stack
        cool1 = new ImageView();                                                // Create new image view
        
        canvas2 = new Canvas(w, h);                                             // Create Canvas2
        GraphicsContext gc2 = canvas2.getGraphicsContext2D();                   // Allow graphics on canvas
        canvas2Push(canvas2);                                                   // Add canvas2 to stack
        cool2 = new ImageView();                                                // Create new image view
        
        Image defImg = new Image("default.png");                                // Open default image
        cool.setFitHeight(0);
        cool.setFitWidth(0);
        cool.setImage(defImg);                                                  // Set as default image
        cool.setPreserveRatio(true);                                            // Preserve Image Ratios
        cool1.setFitHeight(0);
        cool1.setFitWidth(0);
        cool1.setImage(defImg);                                                 // Set as default image
        cool1.setPreserveRatio(true);                                           // Preserve Image Ratios
        cool2.setFitHeight(0);
        cool2.setFitWidth(0);
        cool2.setImage(defImg);                                                 // Set as default image
        cool2.setPreserveRatio(true);                                           // Preserve Image Ratios
        
        coolPush(cool);                                                         // Push images to stacks
        cool1Push(cool1);
        cool2Push(cool2);
        
        tabPane = new TabPane();                                                // Create Tab Pane
        Tab tab1 = new Tab("Image 1", sp);                                      // Create 3 Tabs
        Tab tab2 = new Tab("Image 2");
        Tab tab3 = new Tab("Image 3");
        tab1.setClosable(false);                                                // Make tabs not closeable
        tab2.setClosable(false);
        tab3.setClosable(false);
        tabPane.getTabs().addAll(tab1, tab2, tab3);                             // Add Tabs to pane
        VBox tabBox = new VBox(tabPane);
        
        ColorPicker colorPicker = new ColorPicker(Color.BLACK);                 // Create a color picker
        Tooltip.install(colorPicker, new Tooltip(                               // Add tool tip
                "Select Edge Color"));
        HBox hColor = new HBox(colorPicker);
        ColorPicker fillPick = new ColorPicker();                               // Create a color picker
        Tooltip.install(fillPick, new Tooltip(                                  // Add tool tip
                "Select Fill Color"));
        HBox hFill = new HBox(fillPick);
        
        GridPane gP = new GridPane();                                           // Create sidebar grid
        gP.setVgap(10);                                                         // Set vertical padding to 10
        GridPane gPLeft = new GridPane();                                       // Create canvas sidebar grid
        gPLeft.setVgap(10);                                                     // Set vertical padding to 10
        
        ToggleGroup toggleGroup = new ToggleGroup();                            // Create ToggleGroup
        
        CheckBox fillBox = new CheckBox("Fill Shape");                          // Create CheckBox
        Tooltip.install(fillBox, new Tooltip(                                   // Add tool tip
                "Select to add Fill to Shape"));
        CheckBox eraseBox = new CheckBox("Transparent Erase");                  // Create CheckBox
        Tooltip.install(eraseBox, new Tooltip(                                  // Add tool tip
                "Select to Switch from White Erase to Transparent Erase"));
        
        lineBtn = new ToggleButton("Line");                                     // Create Line toggle button
        HBox hLine = new HBox(lineBtn);
        Image lineImg = new Image("line.png");
        ImageView lineView = new ImageView(lineImg);
        lineBtn.setGraphic(lineView);
        Tooltip.install(lineBtn, new Tooltip(
                "Draws a Straight Line between Two Points"));
        
        curveBtn = new ToggleButton("Curve");                                   // Create Curve Toggle Button
        HBox hCurve = new HBox(curveBtn);
        Image curveImg = new Image("curve.png");
        ImageView curveView = new ImageView(curveImg);
        curveBtn.setGraphic(curveView);
        Tooltip.install(curveBtn, new Tooltip(
                "Draws a Curve following the Mouse"));
        
        rectBtn = new ToggleButton("Rectangle");                                // Create Rectangle Toggle Button
        HBox hRect = new HBox(rectBtn);
        Image rectImg = new Image("rectangle.png");
        ImageView rectView = new ImageView(rectImg);
        rectBtn.setGraphic(rectView);
        Tooltip.install(rectBtn, new Tooltip(
                "Draws a Rectangle between Two Points"));
        
        squBtn = new ToggleButton("Square");                                    // Create Square Toggle Button
        HBox hSqu = new HBox(squBtn);
        Image squImg = new Image("square.png");
        ImageView squView = new ImageView(squImg);
        squBtn.setGraphic(squView);
        Tooltip.install(squBtn, new Tooltip(
                "Draws a Square between Two Points"));
        
        ovalBtn = new ToggleButton("Ellipse");                                  // Create Ellipse Toggle Button
        HBox hOval = new HBox(ovalBtn);
        Image ovalImg = new Image("oval.png");
        ImageView ovalView = new ImageView(ovalImg);
        ovalBtn.setGraphic(ovalView);
        Tooltip.install(ovalBtn, new Tooltip(
                "Draws an Ellipse between Two Points"));
        
        cirBtn = new ToggleButton("Circle");                                    // Create Circle Toggle Button
        HBox hCir = new HBox(cirBtn);
        Image cirImg = new Image("circle.png");
        ImageView cirView = new ImageView(cirImg);
        cirBtn.setGraphic(cirView);
        Tooltip.install(cirBtn, new Tooltip(
                "Draws a Circle between Two Points"));
        
        textBtn = new ToggleButton("Text");                                     // Create Text Toggle Button
        HBox hText = new HBox(textBtn);
        Tooltip.install(textBtn, new Tooltip(
                "Places above Text where the Mouse Button is Released"));
        
        dropBtn = new ToggleButton("Color Dropper");                            // Create Main Dropper toggle button
        HBox hDrop = new HBox(dropBtn);
        Image dropImg = new Image("dropper.png");
        ImageView dropView = new ImageView(dropImg);
        dropBtn.setGraphic(dropView);
        Tooltip.install(dropBtn, new Tooltip(
                "Selects Edge Color from Selected Color on the Canvas"));
        
        dropBtn1 = new ToggleButton("Color Dropper");                           // Create Fill Dropper toggle button
        HBox hDrop1 = new HBox(dropBtn1);
        Image dropImg1 = new Image("dropper1.png");
        ImageView dropView1 = new ImageView(dropImg1);
        dropBtn1.setGraphic(dropView1);
        Tooltip.install(dropBtn1, new Tooltip(
                "Selects Fill Color from Selected Color on the Canvas"));
        
        eraserBtn = new ToggleButton("Eraser");                                 // Create Eraser toggle button
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
        
        rRectBtn = new ToggleButton("Rounded Rectangle");                       // Create Rounded Rectangle toggle button
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
        
        Label widLabel = new Label("Enter Width or Font Size:");                // Create width label
        Label sideLabel = new Label("Enter Number of Sides:");                  // Create Sides label
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
        
        canvas.setHeight(800);                                                  // Reset height after cropping fix
        
        try {                                                                   // Try Load AutoSave File
            File time = new File("save.tfx");                                   // Create file
            Scanner myReader = new Scanner(time);                               // Open Reader
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();                              // Read value and save
                setSaveTime(Integer.parseInt(data));
                saveName = data;
            }
            myReader.close();                                                   // Close reader
        } catch (FileNotFoundException e) {
            try{                                                                // If no File
                File time = new File("save.tfx");                               // Create file with default values
                FileWriter myWriter = new FileWriter(time);
                myWriter.write("60");
                saveName = "60";
                myWriter.close();
            } catch (IOException a) {
                System.out.println("File Error");
                e.printStackTrace();
            }
        }
        
        Timer timer = new java.util.Timer();                                    // Create Auto Save Timer
        timer.schedule(new TimerTask() {                                        // Schedule Timer
            public void run() {
                Platform.runLater(new Runnable() {                              // on Thread
                    public void run() {
                        if(interval > 0){
                            if(save){                                           // Every second, update title
                                primaryStage.setTitle("PaintFX - " + interval + "s");
                            }
                            else{
                                primaryStage.setTitle("PaintFX");
                            }                                                   // Set Title
                            interval--;
                        }
                        else{                                                   // When finished, autosave and reset
                            saveHandler.autoSave(coolCrab);
                            interval = saveTime;
                        }
                    }
                });
            }
        }, 0, 1000);
        
        File time = new File("paintfx.log");                                    // Make file
        if (time.delete()) {} else {System.out.println("Del");}                 // File Conditions
        if (time.createNewFile()) {} else {System.out.println("Create");}
        
        Timer logTimer = new java.util.Timer();                                 // Create Logging Timer
        logTimer.schedule(new TimerTask() {                                     // Schedule Timer
            public void run() {
                Platform.runLater(new Runnable() {                              // on Thread
                    public void run() {
                        if(logLocked == false){                                 // If Logging not Locked
                            try{                                                // Write time to file
                                BufferedWriter myWriter = new BufferedWriter(new FileWriter(time, true));                                   // Create file writer
                                String tabName;
                                switch (selec){                                 // Grab Current Tab
                                    case 0: tabName = "Tab 1";
                                            break;
                                    case 1: tabName = "Tab 2";
                                            break;
                                    case 2: tabName = "Tab 3";
                                            break;
                                    default: tabName = "";
                                }                                               // Write Tab Log Based on Provided Info
                                if(logType == 0) myWriter.write("[" + new Date() + "] " + log + " on " + tabName);                          // Write File
                                else if(logType == 1) myWriter.write("[" + new Date() + "] " + log + " Tool Selected on " + tabName);       // Write File
                                else if(logType == 2) myWriter.write("[" + new Date() + "] Image Opened on " + tabName);                    // Write File
                                else if(logType == 3) myWriter.write("[" + new Date() + "] " + log + " Performed on " + tabName);           // Write File
                                else System.out.println("Logging Error 0");
                                myWriter.newLine();                             // Move to new line
                                myWriter.close();                               // Close File Writter
                            } catch (IOException e) {
                                System.out.println("Logging Error 1");
                                e.printStackTrace();
                            }
                            logLocked = true;                                   // Lock Logger
                        }
                    }
                });
            }
        }, 0, 1);
                                                                                // Create Tab Switch
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{
            
            ov.setContent(null);                                                // Switch Tab Contents
            coolCrab.getChildren().clear();
            nv.setContent(sp);
            
            coolCrab.setScaleX(1);                                              // Reset zoom
            coolCrab.setScaleY(1);
            setScaleX(1);
            setScaleY(1);

            if(nv == tab2){                                                     // If switching to tab 2,
                setButtonSelects();                                             // Deselect buttons
                setVariables(canvas1Peek().getWidth(), canvas1Peek().getHeight());// Change variables
                setName(tab2.getText());                                        // Set name of file
                setSelec(1);                                                    // Change select value
                coolCrab.getChildren().addAll(cool1Peek(), canvas1Peek());      // Change content
            }
            else if(nv == tab3){                                                // If switching to tab 3,
                setButtonSelects();                                             // See above case
                setVariables(canvas2Peek().getWidth(), canvas2Peek().getHeight());
                setName(tab3.getText());
                setSelec(2);
                coolCrab.getChildren().addAll(cool2Peek(), canvas2Peek());
            }
            else if(nv == tab1){                                                // If switching to tab 1,
                setButtonSelects();                                             // See above case
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
        menuOpen.setOnAction(new openHandler(primaryStage, cool, cool1, cool2, border, 
                canvas, canvas1, canvas2, vBox, gc, gc1, gc2, coolCrab, sp, tabPane));
        
        
        menuTools.setOnAction(e -> {                                            // If toggle tools button pushed
            if(hold1 == 0){                                                     // If hidden, show
                border.setRight(gP);
                hold1 = 1;
            }
            else{                                                               // If shown, hide
                setButtonSelects();                                             // Set holds
                lineHandler.setHold(2);
                curveHandler.setHold(1);
                rectangleHandler.setHold(2);
                squareHandler.setHold(2);
                ovalHandler.setHold(2);
                circleHandler.setHold(2);
                eraserHandler.setHold(2);
                textHandler.setHold(1);
                rRectangleHandler.setHold(2);
                polygonHandler.setHold(2);
                moveHandler.setHold(2);
                copyHandler.setHold(2);
                border.setRight(null);
                hold1 = 0;
            }
        });
        
        menuCanvas.setOnAction(e -> {                                           // If resize canvas button pushed
            if(hold == 0){                                                      // If hidden, show
                border.setLeft(gPLeft);                                         // Display tools and fill current widths
                if(selec == 0){
                    canW.setText(String.valueOf(canvasPeek().getBoundsInParent().getWidth()));
                    canH.setText(String.valueOf(canvasPeek().getBoundsInParent().getHeight()));
                }
                else if(selec == 1){
                    canW.setText(String.valueOf(canvas1Peek().getBoundsInParent().getWidth()));
                    canH.setText(String.valueOf(canvas1Peek().getBoundsInParent().getHeight()));
                }
                if(selec == 2){
                    canW.setText(String.valueOf(canvas2Peek().getBoundsInParent().getWidth()));
                    canH.setText(String.valueOf(canvas2Peek().getBoundsInParent().getHeight()));
                }
                hold = 1;
            }
            else{                                                               // If shown, hide
                border.setLeft(null);
                hold = 0;
            }
        });
        
        menuUndo.setOnAction(e -> {undo();});                                   // If Undo button pushed
        menuRedo.setOnAction(e -> {redo();});                                   // If Redo canvas button pushed
        
        menuZoomIn.setOnAction(e -> {                                           // If Zoom In triggered
            scaleX = coolCrab.getScaleX()*1.1;                                  // Increase scale
            scaleY = coolCrab.getScaleY()*1.1;
            coolCrab.setScaleX(scaleX);
            coolCrab.setScaleY(scaleY);
        });
        
        menuZoomOut.setOnAction(e -> {                                          // If resize canvas button pushed
            scaleX = coolCrab.getScaleX()/1.1;                                  // Decrease scale
            scaleY = coolCrab.getScaleY()/1.1;
            coolCrab.setScaleX(scaleX);
            coolCrab.setScaleY(scaleY);
        });
        
        canvasBtn.setOnAction(e -> {                                            // If canvas toggle button pushed
            w = Double.parseDouble(canW.getText());                             // Update dimensions
            h = Double.parseDouble(canH.getText());
            if(selec == 0){
                if(change == 0){tab1.setText(tab1.getText() + "*");}            // Update change and tab name
                change = 1;
                canvasPeek().setWidth(w);                                       // Set Canvas to new Size
                canvasPeek().setHeight(h);
            }
            else if(selec == 1){
                if(change1 == 0){tab2.setText(tab2.getText() + "*");}           // Update change and tab name
                change1 = 1;
                canvas1Peek().setWidth(w);                                      // Set Canvas to new Size
                canvas1Peek().setHeight(h);
            }
            else if(selec == 2){
                if(change2 == 0){tab3.setText(tab3.getText() + "*");}           // Update change and tab name
                change2 = 1;
                canvas2Peek().setWidth(w);                                      // Set Canvas to new Size
                canvas2Peek().setHeight(h);
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
        lineBtn.setOnAction(new lineHandler(coolCrab, colorPicker, widthText, lineBtn, tabPane, dropBtn));
        curveBtn.setOnAction(new curveHandler(coolCrab, colorPicker, widthText, curveBtn, tabPane, dropBtn));
        rectBtn.setOnAction(new rectangleHandler(coolCrab, colorPicker, fillPick, widthText, rectBtn, tabPane, fillBox, dropBtn, dropBtn1));
        squBtn.setOnAction(new squareHandler(coolCrab, colorPicker, fillPick, widthText, squBtn, tabPane, fillBox, dropBtn, dropBtn1));
        ovalBtn.setOnAction(new ovalHandler(coolCrab, colorPicker, fillPick, widthText, ovalBtn, tabPane, fillBox, dropBtn, dropBtn1));
        cirBtn.setOnAction(new circleHandler(coolCrab, colorPicker, fillPick, widthText, cirBtn, tabPane, fillBox, dropBtn, dropBtn1));
        textBtn.setOnAction(new textHandler(coolCrab, colorPicker, widthText, textBtn, tabPane, dropBtn, enteredText));
        eraserBtn.setOnAction(new eraserHandler(coolCrab, widthText, eraserBtn, tabPane, eraseBox));
        rRectBtn.setOnAction(new rRectangleHandler(coolCrab, colorPicker, fillPick, widthText, rRectBtn, tabPane, fillBox, dropBtn, dropBtn1));
        polyBtn.setOnAction(new polygonHandler(coolCrab, colorPicker, fillPick, widthText, pointText, polyBtn, tabPane, fillBox, dropBtn, dropBtn1));
        
        menuMove.setOnAction(new moveHandler(coolCrab, tabPane, eraseBox));
        menuCopy.setOnAction(new copyHandler(coolCrab, tabPane));
        menuInvert.setOnAction(new invertHandler(coolCrab, tabPane));
        
        menuClear.setOnAction(e -> {                                            // If Clear Canvas button is pushed
            if(selec == 0){
                canvasPush(canvas);                                             // Reset to initial canvas
                coolCrab.getChildren().remove(1);
                coolCrab.getChildren().add(1, canvasPeek());
            }
            else if (selec == 1){
                canvas1Push(canvas1);                                           // Reset to initial canvas1
                coolCrab.getChildren().remove(1);
                coolCrab.getChildren().add(1, canvas1Peek());
            }
            else if (selec == 2){
                canvas2Push(canvas2);                                           // Reset t initial canvas2
                coolCrab.getChildren().remove(1);
                coolCrab.getChildren().add(1, canvas2Peek());
            }
            else{
                System.out.println("Canvas Clear Error");
            }
        });
                                                                                // Create Menu Action Handlers
        menuSave.setOnAction(new saveHandler(coolCrab, tabPane)); 
        menuSaveAs.setOnAction(new saveAsHandler(coolCrab, primaryStage, tabPane)); 
        primaryStage.setOnCloseRequest(new exitHandler(coolCrab, tabPane));
        
        menuExit.setOnAction(e -> {                                             // If exit button is pushed
            exitHandler.exit(coolCrab, tabPane);
        });
        
        menuSnap.setOnAction(e -> {                                             // If Snap button is pushed
            coolCrab.setScaleX(1);
            coolCrab.setScaleY(1);
            PaintFX.setScaleX(1);
            PaintFX.setScaleY(1);
            if(selec == 0){
                Rectangle2D screenBounds = Screen.getPrimary().getBounds();     // Read monitor dimensions, and if image is larger,
                if((screenBounds.getWidth() < w) || (screenBounds.getHeight() < h)){
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    coolPeek().setFitWidth(screenBounds.getWidth()-200);        // Set image dimensions to monitor size
                    coolPeek().setFitHeight(screenBounds.getHeight()-200);
                    w = coolPeek().getFitWidth();                               // Set Screen dimensions to image
                    h = coolPeek().getFitHeight();
                    canvasPeek().setWidth(w);                                   // Set Canvas to new Size
                    canvasPeek().setHeight(h);
                    if(change == 0){tab1.setText(tab1.getText() + "*");}        // Update change and tab name
                    change = 1;
                }
                else{}
            }
            else if(selec == 1){
                Rectangle2D screenBounds = Screen.getPrimary().getBounds();     // Read monitor dimensions, and if image is larger,
                if((screenBounds.getWidth() < w) || (screenBounds.getHeight() < h)){
                    gc1.clearRect(0, 0, canvas1.getWidth(), canvas1.getHeight());
                    cool1Peek().setFitWidth(screenBounds.getWidth()-200);       // Set image dimensions to monitor size
                    cool1Peek().setFitHeight(screenBounds.getHeight()-200);
                    w = cool1Peek().getFitWidth();                              // Set Screen dimensions to image
                    h = cool1Peek().getFitHeight();
                    canvas1Peek().setWidth(w);                                  // Set Canvas to new Size
                    canvas1Peek().setHeight(h);
                    if(change1 == 0){tab2.setText(tab2.getText() + "*");}       // Update change and file name
                    change1 = 1;
                }
                else{}
            }
            else if(selec == 2){
                Rectangle2D screenBounds = Screen.getPrimary().getBounds();     // Read monitor dimensions, and if image is larger,
                if((screenBounds.getWidth() < w) || (screenBounds.getHeight() < h)){
                    gc2.clearRect(0, 0, canvas2.getWidth(), canvas2.getHeight());
                    cool2Peek().setFitWidth(screenBounds.getWidth()-200);       // Set image dimensions to monitor size
                    cool2Peek().setFitHeight(screenBounds.getHeight()-200);
                    w = cool2Peek().getFitWidth();                              // Set Screen dimensions to image
                    h = cool2Peek().getFitHeight();
                    canvas2Peek().setWidth(w);                                  // Set Canvas to new Size
                    canvas2Peek().setHeight(h);
                    if(change2 == 0){tab3.setText(tab3.getText() + "*");}       // Update change and tab name
                    change2 = 1;
                }
                else{}
            }
            else{
                System.out.println("Snap Error");
            }
        });
        
        menuHelp.setOnAction(new helpHandler());                                // If help button is pushed
        menuAbout.setOnAction(new aboutHandler());                              // If about button is pushed
        menuAuto.setOnAction(new autoHandler());                                // If auto save button is pushed
    }
}