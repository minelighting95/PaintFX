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

public class curveHandler implements EventHandler<ActionEvent> {

    public Canvas canvas;
    public Canvas canvas1;
    public Canvas canvas2;
    public GraphicsContext gc;
    public GraphicsContext gc1;
    public GraphicsContext gc2;
    public ColorPicker colorPicker;
    public TextField widthText;
    public ToggleButton curveBtn;
    public Stage primaryStage;
    static int hold2 = 0;
    public TabPane tabPane;
    public ToggleButton dropBtn;
    
    public static void setHold(int h){
        hold2 = h;
    }
    
    public curveHandler(Canvas canvas, Canvas canvas1, Canvas canvas2, GraphicsContext gc, GraphicsContext gc1, GraphicsContext gc2,
            ColorPicker colorPicker, TextField widthText, ToggleButton curveBtn, Stage primaryStage, TabPane tabPane, ToggleButton dropBtn){

        this.canvas = canvas;
        this.canvas1 = canvas1;
        this.canvas2 = canvas2;
        this.gc = gc;
        this.gc1 = gc1;
        this.gc2 = gc2;
        this.colorPicker = colorPicker;
        this.widthText = widthText;
        this.curveBtn = curveBtn;
        this.primaryStage = primaryStage;
        this.tabPane = tabPane;
        this.dropBtn = dropBtn;
        
    }
    
            @Override
            public void handle(ActionEvent a) {
                
                hold2 = 0;                                                      // Set Holds
                lineHandler.setHold(2);
                rectangleHandler.setHold(2);
                squareHandler.setHold(2);
                ovalHandler.setHold(2);
                circleHandler.setHold(2);
                
                if(PaintFX.getSelec() == 0){
                    gc.beginPath();                                             // Being Curve Path
                    if(dropBtn.isSelected()){                                   // Get Color
                        gc.setStroke(PaintFX.getColor());
                    }
                    else{
                        gc.setStroke(colorPicker.getValue());                   // Set Curve Color to selected value
                    }
                    int lineWidth = Integer.parseInt(widthText.getText());      // Convert width text to integer
                    if(lineWidth <= 0){                                         // If invalid value,
                        lineWidth = 1;                                          // Set to width = 1
                        widthText.setText("1");
                    }
                    gc.setLineWidth(lineWidth);                                 // Set curve to desired width

                    canvas.setOnMouseClicked((event) ->{                        // When mouse clicked
                        if(hold2 == 0){
                            gc.beginPath();                                     // Create Path
                            gc.moveTo(event.getX(), event.getY());
                            gc.stroke();
                        }
                    });

                    canvas.setOnMouseDragged((event) ->{                        // When mouse clicked
                        if(hold2 == 0){
                            gc.lineTo(event.getX(), event.getY());              // Follow mouse
                            gc.stroke();
                            gc.closePath();
                            gc.beginPath();
                            gc.moveTo(event.getX(), event.getY());
                        }
                    });

                    canvas.setOnMouseReleased((event) ->{                       // When mouse clicked
                        if(hold2 == 0){
                            gc.lineTo(event.getX(), event.getY());              // End Path
                            gc.stroke();
                            gc.closePath();
                            curveBtn.setSelected(false);                        // Untoggle button
                            if(PaintFX.getChange() == 0){
                                tabPane.getTabs().get(0).setText(tabPane.getTabs().get(0).getText() + "*");
                                PaintFX.setChange(1);
                            }
                            hold2 = 1;
                        }
                    });
                }
                else if(PaintFX.getSelec() == 1){
                    gc1.beginPath();                                            // Being Curve Path
                    if(dropBtn.isSelected()){                                   // Get Color
                        gc1.setStroke(PaintFX.getColor());
                    }
                    else{
                        gc1.setStroke(colorPicker.getValue());                  // Set Curve Color to selected value
                    }
                    int lineWidth = Integer.parseInt(widthText.getText());      // Convert width text to integer
                    if(lineWidth <= 0){                                         // If invalid value,
                        lineWidth = 1;                                          // Set to width = 1
                        widthText.setText("1");
                    }
                    gc1.setLineWidth(lineWidth);                                // Set curve to desired width

                    canvas1.setOnMouseClicked((event) ->{                       // When mouse clicked
                        if(hold2 == 0){
                            gc1.beginPath();                                    // Create Path
                            gc1.moveTo(event.getX(), event.getY());
                            gc1.stroke();
                        }
                    });

                    canvas1.setOnMouseDragged((event) ->{                       // When mouse clicked
                        if(hold2 == 0){
                            gc1.lineTo(event.getX(), event.getY());             // Follow mouse
                            gc1.stroke();
                            gc1.closePath();
                            gc1.beginPath();
                            gc1.moveTo(event.getX(), event.getY());
                        }
                    });

                    canvas1.setOnMouseReleased((event) ->{                      // When mouse clicked
                        if(hold2 == 0){
                            gc1.lineTo(event.getX(), event.getY());             // End Path
                            gc1.stroke();
                            gc1.closePath();
                            curveBtn.setSelected(false);                        // Untoggle button
                            if(PaintFX.getChange1() == 0){
                                tabPane.getTabs().get(1).setText(tabPane.getTabs().get(1).getText() + "*");
                                PaintFX.setChange1(1);
                            }
                            hold2 = 1;
                        }
                    });
                }
                else if(PaintFX.getSelec() == 2){
                    gc2.beginPath();                                            // Being Curve Path
                    if(dropBtn.isSelected()){                                   // Get Color
                        gc2.setStroke(PaintFX.getColor());
                    }
                    else{
                        gc2.setStroke(colorPicker.getValue());                  // Set Curve Color to selected value
                    }
                    int lineWidth = Integer.parseInt(widthText.getText());      // Convert width text to integer
                    if(lineWidth <= 0){                                         // If invalid value,
                        lineWidth = 1;                                          // Set to width = 1
                        widthText.setText("1");
                    }
                    gc2.setLineWidth(lineWidth);                                // Set curve to desired width

                    canvas2.setOnMouseClicked((event) ->{                       // When mouse clicked
                        if(hold2 == 0){
                            gc2.beginPath();                                    // Create Path
                            gc2.moveTo(event.getX(), event.getY());
                            gc2.stroke();
                        }
                    });

                    canvas2.setOnMouseDragged((event) ->{                       // When mouse clicked
                        if(hold2 == 0){
                            gc2.lineTo(event.getX(), event.getY());             // Follow mouse
                            gc2.stroke();
                            gc2.closePath();
                            gc2.beginPath();
                            gc2.moveTo(event.getX(), event.getY());
                        }
                    });

                    canvas2.setOnMouseReleased((event) ->{                      // When mouse clicked
                        if(hold2 == 0){
                            gc2.lineTo(event.getX(), event.getY());             // End Path
                            gc2.stroke();
                            gc2.closePath();
                            curveBtn.setSelected(false);                        // Untoggle button
                            if(PaintFX.getChange2() == 0){
                                tabPane.getTabs().get(2).setText(tabPane.getTabs().get(2).getText() + "*");
                                PaintFX.setChange2(1);
                            }
                            hold2 = 1;
                        }
                    });
                }
                else{
                    System.out.println("Open Error 2");
                }
                hold2 = 0;
            }
}
