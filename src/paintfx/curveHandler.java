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

/**
 * Event Handler to Draw a Curve Onscreen
 * 
 * @author Alex Appel
 */
public class curveHandler implements EventHandler<ActionEvent> {

    private StackPane coolCrab;
    private ColorPicker colorPicker;
    private TextField widthText;
    private ToggleButton curveBtn;
    static int hold2 = 0;
    private TabPane tabPane;
    private ToggleButton dropBtn;
    
    /**
     * Set phase of curveHandler (Normally 1)
     * @param h Hold Value (h = 1 will cause this handler to end)
     */
    public static void setHold(int h){
        hold2 = h;
    }
    
    /**
     * Event Handler to Place a Curve on Screen
     * @param coolCrab Main Program StackPane
     * @param colorPicker Edge Color Picker
     * @param widthText Toolbar Width Textbox
     * @param curveBtn Curve Handler Button
     * @param tabPane Main Program TabPane
     * @param dropBtn Edge Color Dropper Button
     */
    public curveHandler(StackPane coolCrab, ColorPicker colorPicker, TextField widthText, ToggleButton curveBtn, TabPane tabPane, ToggleButton dropBtn){

        this.coolCrab = coolCrab;
        this.colorPicker = colorPicker;
        this.widthText = widthText;
        this.curveBtn = curveBtn;
        this.tabPane = tabPane;
        this.dropBtn = dropBtn;
        
    }
    
    @Override
    public void handle(ActionEvent a) {

        hold2 = 0;                                                              // Set Holds
        PaintFX.logItem("Curve", 1);                                            // Log Save
        lineHandler.setHold(2);
        rectangleHandler.setHold(2);
        squareHandler.setHold(2);
        ovalHandler.setHold(2);
        circleHandler.setHold(2);
        eraserHandler.setHold(2);
        rRectangleHandler.setHold(2);
        textHandler.setHold(1);
        polygonHandler.setHold(2);
        moveHandler.setHold(2);
        copyHandler.setHold(2);

        Canvas canTemp = new Canvas(PaintFX.getW(), PaintFX.getH());            // Create new canvas and graphics context
        GraphicsContext gcTemp = canTemp.getGraphicsContext2D();

        SnapshotParameters params = new SnapshotParameters();                   // Define snapshot parameters
        params.setFill(Color.TRANSPARENT);

        if(PaintFX.getSelec() == 0){                                            // For selected tab,
            WritableImage image = PaintFX.canvasPeek().snapshot(params, null);  // take snapshot and draw image
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

        coolCrab.getChildren().remove(1);                                       // Display new canvas
        coolCrab.getChildren().add(1, canTemp);

        gcTemp.beginPath();                                                     // Being Curve Path
        if(dropBtn.isSelected()){                                               // Get Color
            gcTemp.setStroke(PaintFX.getColor());
        }
        else{
            gcTemp.setStroke(colorPicker.getValue());                           // Set Curve Color to selected value
        }
        int lineWidth = Integer.parseInt(widthText.getText());                  // Convert width text to integer
        if(lineWidth <= 0){                                                     // If invalid value,
            lineWidth = 1;                                                      // Set to width = 1
            widthText.setText("1");
        }
        gcTemp.setLineWidth(lineWidth);                                         // Set curve to desired width

        canTemp.setOnMouseClicked((event) ->{                                   // When mouse clicked
            if(hold2 == 0 && curveBtn.isSelected()){
                gcTemp.beginPath();                                             // Create Path
                gcTemp.moveTo(event.getX(), event.getY());
                gcTemp.stroke();
            }
        });

        canTemp.setOnMouseDragged((event) ->{                                   // When mouse drug
            if(hold2 == 0 && curveBtn.isSelected()){
                gcTemp.lineTo(event.getX(), event.getY());                      // Follow mouse
                gcTemp.stroke();
                gcTemp.closePath();
                gcTemp.beginPath();
                gcTemp.moveTo(event.getX(), event.getY());
            }
        });

        canTemp.setOnMouseReleased((event) ->{                                  // When mouse released
            if(hold2 == 0 && curveBtn.isSelected()){
                gcTemp.lineTo(event.getX(), event.getY());                      // End Path
                gcTemp.stroke();
                gcTemp.closePath();
                curveBtn.setSelected(false);                                    // Untoggle button

                if(PaintFX.getSelec() == 0){                                    // For selected tab,
                    PaintFX.redoClear();                                        // Clear redo
                    PaintFX.recoolClear();
                    if(PaintFX.getChange() == 0){                               // If change, update tab name
                        tabPane.getTabs().get(0).setText(tabPane.getTabs().get(0).getText() + "*");
                        PaintFX.setChange(1);
                    }
                    PaintFX.canvasPush(canTemp);                                // Push new canvas
                }
                else if(PaintFX.getSelec() == 1){                               // See above case
                    PaintFX.redo1Clear();
                    PaintFX.recool1Clear();
                    if(PaintFX.getChange1() == 0){
                        tabPane.getTabs().get(1).setText(tabPane.getTabs().get(1).getText() + "*");
                        PaintFX.setChange1(1);
                    }
                    PaintFX.canvas1Push(canTemp);
                }
                else if(PaintFX.getSelec() == 2){                               // See above case
                    PaintFX.redo2Clear();
                    PaintFX.recool2Clear();
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
