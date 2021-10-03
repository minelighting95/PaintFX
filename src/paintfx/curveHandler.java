package PaintFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class curveHandler implements EventHandler<ActionEvent> {

    public StackPane coolCrab;
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
    
    public curveHandler(StackPane coolCrab, ColorPicker colorPicker, TextField widthText, ToggleButton curveBtn, Stage primaryStage, TabPane tabPane, ToggleButton dropBtn){

        this.coolCrab = coolCrab;
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
                eraserHandler.setHold(2);
                rRectangleHandler.setHold(2);
                textHandler.setHold(1);
                polygonHandler.setHold(2);

                Canvas canTemp = new Canvas(PaintFX.getW(), PaintFX.getH());                                       // Create Canvas
                GraphicsContext gcTemp = canTemp.getGraphicsContext2D();

                SnapshotParameters params = new SnapshotParameters();
                params.setFill(Color.TRANSPARENT);

                if(PaintFX.getSelec() == 0){
                    WritableImage image = PaintFX.canvasPeek().snapshot(params, null);
                    gcTemp.drawImage(image, 0, 0);
                }
                else if(PaintFX.getSelec() == 1){
                    WritableImage image = PaintFX.canvas1Peek().snapshot(params, null);
                    gcTemp.drawImage(image, 0, 0);
                }
                else if(PaintFX.getSelec() == 2){
                    WritableImage  image = PaintFX.canvas2Peek().snapshot(params, null);
                    gcTemp.drawImage(image, 0, 0);
                }

                coolCrab.getChildren().remove(1);
                coolCrab.getChildren().add(1, canTemp);

                gcTemp.beginPath();                                             // Being Curve Path
                if(dropBtn.isSelected()){                                   // Get Color
                    gcTemp.setStroke(PaintFX.getColor());
                }
                else{
                    gcTemp.setStroke(colorPicker.getValue());                   // Set Curve Color to selected value
                }
                int lineWidth = Integer.parseInt(widthText.getText());      // Convert width text to integer
                if(lineWidth <= 0){                                         // If invalid value,
                    lineWidth = 1;                                          // Set to width = 1
                    widthText.setText("1");
                }
                gcTemp.setLineWidth(lineWidth);                                 // Set curve to desired width

                canTemp.setOnMouseClicked((event) ->{                        // When mouse clicked
                    if(hold2 == 0){
                        gcTemp.beginPath();                                     // Create Path
                        gcTemp.moveTo(event.getX(), event.getY());
                        gcTemp.stroke();
                    }
                });

                canTemp.setOnMouseDragged((event) ->{                        // When mouse clicked
                    if(hold2 == 0){
                        gcTemp.lineTo(event.getX(), event.getY());              // Follow mouse
                        gcTemp.stroke();
                        gcTemp.closePath();
                        gcTemp.beginPath();
                        gcTemp.moveTo(event.getX(), event.getY());
                    }
                });

                canTemp.setOnMouseReleased((event) ->{                       // When mouse clicked
                    if(hold2 == 0){
                        gcTemp.lineTo(event.getX(), event.getY());              // End Path
                        gcTemp.stroke();
                        gcTemp.closePath();
                        curveBtn.setSelected(false);                        // Untoggle button

                        if(PaintFX.getSelec() == 0){
                            PaintFX.redoClear();
                            if(PaintFX.getChange() == 0){
                                tabPane.getTabs().get(0).setText(tabPane.getTabs().get(0).getText() + "*");
                                PaintFX.setChange(1);
                            }
                            PaintFX.canvasPush(canTemp);
                        }
                        else if(PaintFX.getSelec() == 1){
                            PaintFX.redo1Clear();
                            if(PaintFX.getChange1() == 0){
                                tabPane.getTabs().get(1).setText(tabPane.getTabs().get(1).getText() + "*");
                                PaintFX.setChange1(1);
                            }
                            PaintFX.canvas1Push(canTemp);
                        }
                        else if(PaintFX.getSelec() == 2){
                            PaintFX.redo2Clear();
                            if(PaintFX.getChange2() == 0){
                                tabPane.getTabs().get(2).setText(tabPane.getTabs().get(2).getText() + "*");
                                PaintFX.setChange2(1);
                            }
                            PaintFX.canvas2Push(canTemp);
                        }
                        
                        hold2 = 1;
                    }
                });
            }
}
