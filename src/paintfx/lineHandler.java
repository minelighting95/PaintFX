package PaintFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class lineHandler implements EventHandler<ActionEvent> {

    public Canvas canvas;
    public Canvas canvas1;
    public Canvas canvas2;
    public GraphicsContext gc;
    public GraphicsContext gc1;
    public GraphicsContext gc2;
    public ColorPicker colorPicker;
    public TextField widthText;
    public ToggleButton lineBtn;
    public Stage primaryStage;
    public ToggleButton dropBtn;
    double x1;
    double x2;
    double y1;
    double y2;
    static int hold = 0;
    public TabPane tabPane;
    
    public static void setHold(int h){
        hold = h;
    }
    
    public lineHandler(Canvas canvas, Canvas canvas1, Canvas canvas2, GraphicsContext gc, GraphicsContext gc1, GraphicsContext gc2,
            ColorPicker colorPicker, TextField widthText, ToggleButton lineBtn, Stage primaryStage, TabPane tabPane, ToggleButton dropBtn){

        this.canvas = canvas;
        this.canvas1 = canvas1;
        this.canvas2 = canvas2;
        this.gc = gc;
        this.gc1 = gc1;
        this.gc2 = gc2;
        this.colorPicker = colorPicker;
        this.widthText = widthText;
        this.lineBtn = lineBtn;
        this.primaryStage = primaryStage;
        this.tabPane = tabPane;
        this.dropBtn = dropBtn;
        
    }
    
    @Override
    public void handle(ActionEvent a) {
          
        hold = 0;                                                               // Set Holds
        curveHandler.setHold(1);
        rectangleHandler.setHold(2);
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
                    gc.setStroke(colorPicker.getValue());                       // Set Line Color to selected value
                }
                
                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gc.setLineWidth(lineWidth);                                     // Set line to desired width
                gc.strokeLine(x1, y1, x2, y2);                                  // Create line between user selected points
                lineBtn.setSelected(false);                                     // Untoggle button
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
                    gc1.setStroke(colorPicker.getValue());                      // Set Line Color to selected value
                }
                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gc1.setLineWidth(lineWidth);                                    // Set line to desired width
                gc1.strokeLine(x1, y1, x2, y2);                                 // Create line between user selected points
                lineBtn.setSelected(false);                                     // Untoggle button
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
                    gc2.setStroke(colorPicker.getValue());                      // Set Line Color to selected value
                }
                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gc2.setLineWidth(lineWidth);                                    // Set line to desired width
                gc2.strokeLine(x1, y1, x2, y2);                                 // Create line between user selected points
                lineBtn.setSelected(false);                                     // Untoggle button
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
