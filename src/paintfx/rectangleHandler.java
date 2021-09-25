package PaintFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class rectangleHandler implements EventHandler<ActionEvent> {

    public Canvas canvas;
    public Canvas canvas1;
    public Canvas canvas2;
    public GraphicsContext gc;
    public GraphicsContext gc1;
    public GraphicsContext gc2;
    public ColorPicker colorPicker;
    public ColorPicker fillPick;
    public TextField widthText;
    public ToggleButton rectBtn;
    public ToggleButton dropBtn;
    public ToggleButton dropBtn1;
    public Stage primaryStage;
    double x1;
    double x2;
    double y1;
    double y2;
    static int hold = 0;
    public TabPane tabPane;
    public CheckBox fillBox;
    
    public static void setHold(int h){
        hold = h;
    }
    
    public rectangleHandler(Canvas canvas, Canvas canvas1, Canvas canvas2, GraphicsContext gc, GraphicsContext gc1, GraphicsContext gc2,
            ColorPicker colorPicker, ColorPicker fillPick, TextField widthText, ToggleButton rectBtn, Stage primaryStage, TabPane tabPane, CheckBox fillBox, ToggleButton dropBtn, ToggleButton dropBtn1){

        this.canvas = canvas;
        this.canvas1 = canvas1;
        this.canvas2 = canvas2;
        this.gc = gc;
        this.gc1 = gc1;
        this.gc2 = gc2;
        this.colorPicker = colorPicker;
        this.fillPick = fillPick;
        this.widthText = widthText;
        this.rectBtn = rectBtn;
        this.primaryStage = primaryStage;
        this.tabPane = tabPane;
        this.fillBox = fillBox;
        this.dropBtn = dropBtn;
        this.dropBtn1 = dropBtn1;
        
    }
    
    @Override
    public void handle(ActionEvent a) {
          
        hold = 0;
        lineHandler.setHold(2);
        curveHandler.setHold(1);
        squareHandler.setHold(2);
        ovalHandler.setHold(2);
        circleHandler.setHold(2);
                
        if(PaintFX.getSelec() == 0){
            canvas.setOnMouseClicked((event) ->{                                // When mouse clicked
            if(hold == 0){                                                      // 1st time
                x1 = event.getX();                                              // Record Coordinates
                y1 = event.getY();
                hold = 1;
            }
            else if(hold == 1){                                                 // 2nd time
                x2 = event.getX();                                              // Record Coordinates
                y2 = event.getY();
                hold = 2;
                if(dropBtn.isSelected()){                                       // Get Color
                    gc.setStroke(PaintFX.getColor());
                }
                else{
                    gc.setStroke(colorPicker.getValue());                       // Set Rectangle Color to selected value
                }
                if(dropBtn1.isSelected()){                                      // Get Fill Color
                    gc.setFill(PaintFX.getColor1());
                }
                else{
                    gc.setFill(fillPick.getValue());                            // Set Line Color to selected value
                }
                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gc.setLineWidth(lineWidth);                                     // Set rectangle to desired width
                
                if((x2 < x1) && (y2 < y1)){
                    gc.strokeRect(x2, y2, x1-x2, y1-y2);                        // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gc.fillRect(x2, y2, x1-x2, y1-y2);                      // Create rectangle between user selected points
                    }
                }
                else if(y2 < y1){
                    gc.strokeRect(x1, y2, x2-x1, y1-y2);                        // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gc.fillRect(x1, y2, x2-x1, y1-y2);                      // Create rectangle between user selected points
                    }  
                }
                else if(x2 < x1){
                    gc.strokeRect(x2, y1, x1-x2, y2-y1);                        // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gc.fillRect(x2, y1, x1-x2, y2-y1);
                    }
                }
                else{
                    gc.strokeRect(x1, y1, x2-x1, y2-y1);                        // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gc.fillRect(x1, y1, x2-x1, y2-y1);
                    }
                }

                rectBtn.setSelected(false);                                     // Untoggle button
                if(PaintFX.getChange() == 0){
                    tabPane.getTabs().get(0).setText(tabPane.getTabs().get(0).getText() + "*");
                    PaintFX.setChange(1);
                }
            }
        });
        }
        else if(PaintFX.getSelec() == 1){
            canvas1.setOnMouseClicked((event) ->{                               // When mouse clicked
            if(hold == 0){                                                      // 1st time
                x1 = event.getX();                                              // Record Coordinates
                y1 = event.getY();
                hold = 1;
            }
            else if(hold == 1){                                                 // 2nd time
                x2 = event.getX();                                              // Record Coordinates
                y2 = event.getY();
                hold = 2;
                if(dropBtn.isSelected()){                                       // Get Color
                    gc1.setStroke(PaintFX.getColor());
                }
                else{
                    gc1.setStroke(colorPicker.getValue());                      // Set Text Color to selected value
                }
                if(dropBtn1.isSelected()){                                      // Get Fill Color
                    gc1.setFill(PaintFX.getColor1());
                }
                else{
                    gc1.setFill(fillPick.getValue());                           // Set rectangle Color to selected value
                }
                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gc1.setLineWidth(lineWidth);                                    // Set line to desired width
                
                if((x2 < x1) && (y2 < y1)){
                    gc1.strokeRect(x2, y2, x1-x2, y1-y2);                       // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gc1.fillRect(x2, y2, x1-x2, y1-y2);                     // Create rectangle between user selected points
                    }
                }
                else if(y2 < y1){
                    gc1.strokeRect(x1, y2, x2-x1, y1-y2);                       // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gc1.fillRect(x1, y2, x2-x1, y1-y2);                     // Create rectangle between user selected points
                    }  
                }
                else if(x2 < x1){
                    gc1.strokeRect(x2, y1, x1-x2, y2-y1);                       // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gc1.fillRect(x2, y1, x1-x2, y2-y1);
                    }
                }
                else{
                    gc1.strokeRect(x1, y1, x2-x1, y2-y1);                       // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gc1.fillRect(x1, y1, x2-x1, y2-y1);
                    }
                }

                rectBtn.setSelected(false);                                     // Untoggle button
                if(PaintFX.getChange1() == 0){
                    tabPane.getTabs().get(1).setText(tabPane.getTabs().get(1).getText() + "*");
                    PaintFX.setChange1(1);
                }
            }
        });
        }
        else if(PaintFX.getSelec() == 2){
            canvas2.setOnMouseClicked((event) ->{                               // When mouse clicked
            if(hold == 0){                                                      // 1st time
                x1 = event.getX();                                              // Record Coordinates
                y1 = event.getY();
                hold = 1;
            }
            else if(hold == 1){                                                 // 2nd time
                x2 = event.getX();                                              // Record Coordinates
                y2 = event.getY();
                hold = 2;
                if(dropBtn.isSelected()){                                       // Get Color
                    gc2.setStroke(PaintFX.getColor());
                }
                else{
                    gc2.setStroke(colorPicker.getValue());                      // Set rectangle Color to selected value
                }
                if(dropBtn1.isSelected()){
                    gc2.setFill(PaintFX.getColor1());
                }
                else{
                    gc2.setFill(fillPick.getValue());                           // Set rectangle Color to selected value
                }
                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gc2.setLineWidth(lineWidth);                                    // Set rectangle to desired width
                
                if((x2 < x1) && (y2 < y1)){
                    gc2.strokeRect(x2, y2, x1-x2, y1-y2);                       // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gc2.fillRect(x2, y2, x1-x2, y1-y2);                     // Create rectangle between user selected points
                    }
                }
                else if(y2 < y1){
                    gc2.strokeRect(x1, y2, x2-x1, y1-y2);                       // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gc2.fillRect(x1, y2, x2-x1, y1-y2);                     // Create rectangle between user selected points
                    }  
                }
                else if(x2 < x1){
                    gc2.strokeRect(x2, y1, x1-x2, y2-y1);                       // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gc2.fillRect(x2, y1, x1-x2, y2-y1);
                    }
                }
                else{
                    gc2.strokeRect(x1, y1, x2-x1, y2-y1);                       // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gc2.fillRect(x1, y1, x2-x1, y2-y1);
                    }
                }

                rectBtn.setSelected(false);                                     // Untoggle button
                if(PaintFX.getChange2() == 0){
                    tabPane.getTabs().get(2).setText(tabPane.getTabs().get(2).getText() + "*");
                    PaintFX.setChange2(1);
                }
            }
        });
        }
        else{
            System.out.println("Line Handler Error 0");
        }
    }
}
