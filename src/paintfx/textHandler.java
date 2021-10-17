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
import javafx.scene.text.Font;

/**
 * Event Handler to Place Text Onscreen
 * 
 * @author Alex Appel
 */
public class textHandler implements EventHandler<ActionEvent> {

    private StackPane coolCrab;
    private ColorPicker colorPicker;
    private TextField widthText;
    private ToggleButton lineBtn;
    private ToggleButton dropBtn;
    double x1;
    double x2;
    double y1;
    double y2;
    private TabPane tabPane;
    private TextField enteredText;
    private static int hold;
    private WritableImage image;
    
    /**
     * Set phase of TextHandler (Normally 1)
     * @param h Hold Value (h = 1 will cause this handler to end)
     */
    public static void setHold(int h){
        hold = h;
    }
    
    /**
     * Event Handler to Place Text on Screen
     * @param coolCrab Main Program StackPane
     * @param colorPicker Edge Color Picker
     * @param widthText Toolbar Width Textbox
     * @param lineBtn Text Handler Button
     * @param tabPane Main Program TabPane
     * @param dropBtn Edge Color Dropper Button
     * @param enteredText User Entered Textbox
     */
    public textHandler(StackPane coolCrab, ColorPicker colorPicker, TextField widthText, ToggleButton lineBtn, TabPane tabPane, ToggleButton dropBtn, TextField enteredText){

        this.coolCrab = coolCrab;
        this.colorPicker = colorPicker;
        this.widthText = widthText;
        this.lineBtn = lineBtn;
        this.tabPane = tabPane;
        this.dropBtn = dropBtn;
        this.enteredText = enteredText;
        
    }
    
    @Override
    public void handle(ActionEvent a) {
          
        hold = 0;                                                               // Set Hold
        PaintFX.logItem("Text", 1);                                             // Log Save
        curveHandler.setHold(1);                                                // Set Holds for tools
        rectangleHandler.setHold(2);
        squareHandler.setHold(2);
        ovalHandler.setHold(2);
        circleHandler.setHold(2);
        eraserHandler.setHold(2);
        rRectangleHandler.setHold(2);
        polygonHandler.setHold(2);
        moveHandler.setHold(2);
        copyHandler.setHold(2);
        
        Canvas canTemp = new Canvas(PaintFX.getW(), PaintFX.getH());            // Create New Canvas and Graphics Context
        GraphicsContext gcTemp = canTemp.getGraphicsContext2D();

        SnapshotParameters params = new SnapshotParameters();                   // Create Snapshot parameters
        params.setFill(Color.TRANSPARENT);

        if(PaintFX.getSelec() == 0){                                            // For Selected tab
            image = PaintFX.canvasPeek().snapshot(params, null);                // Take snapshot and draw on new canvas
            gcTemp.drawImage(image, 0, 0);
        }
        else if(PaintFX.getSelec() == 1){
            image = PaintFX.canvas1Peek().snapshot(params, null);
            gcTemp.drawImage(image, 0, 0);
        }
        else if(PaintFX.getSelec() == 2){
            image = PaintFX.canvas2Peek().snapshot(params, null);
            gcTemp.drawImage(image, 0, 0);
        }

        coolCrab.getChildren().remove(1);                                       // Display new canvas
        coolCrab.getChildren().add(1, canTemp);
        
        canTemp.setOnMouseClicked((event) ->{                                   // When mouse is clicked
            if(hold == 0 && lineBtn.isSelected()){                              // Clear previous live draw
                gcTemp.clearRect(canTemp.getLayoutBounds().getMinX(), canTemp.getLayoutBounds().getMinY(), canTemp.getWidth(), canTemp.getHeight());
                gcTemp.drawImage(image, 0, 0);
                x1 = event.getX();                                              // Record Coordinates
                y1 = event.getY();

                if(dropBtn.isSelected()){                                       // Get Color
                    gcTemp.setStroke(PaintFX.getColor());
                }
                else{
                    gcTemp.setStroke(colorPicker.getValue());                   // Set Text Color to selected value
                }

                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gcTemp.setLineWidth(1);
                gcTemp.setFont(new Font("Ariel", lineWidth));
                gcTemp.strokeText(enteredText.getText(), x1, y1);               // Create live draw text at user selected point
            }
        });
        
        canTemp.setOnMouseDragged((event) ->{
            if(hold == 0 && lineBtn.isSelected()){                              // If mouse dragged, clear previous live draw
                gcTemp.clearRect(canTemp.getLayoutBounds().getMinX(), canTemp.getLayoutBounds().getMinY(), canTemp.getWidth(), canTemp.getHeight());
                gcTemp.drawImage(image, 0, 0);
                x1 = event.getX();                                              // Record Coordinates
                y1 = event.getY();

                if(dropBtn.isSelected()){                                       // Get Color
                    gcTemp.setStroke(PaintFX.getColor());
                }
                else{
                    gcTemp.setStroke(colorPicker.getValue());                   // Set Text Color to selected value
                }

                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gcTemp.setLineWidth(1);
                gcTemp.setFont(new Font("Ariel", lineWidth));
                gcTemp.strokeText(enteredText.getText(), x1, y1);               // Create text at user selected point
            }
        });
                
        canTemp.setOnMouseReleased((event) ->{                                  // When mouse released
            if(hold == 0 && lineBtn.isSelected()){                              // Clear live draw
                gcTemp.clearRect(canTemp.getLayoutBounds().getMinX(), canTemp.getLayoutBounds().getMinY(), canTemp.getWidth(), canTemp.getHeight());
                gcTemp.drawImage(image, 0, 0);
                x1 = event.getX();                                              // Record Coordinates
                y1 = event.getY();

                if(dropBtn.isSelected()){                                       // Get Color
                    gcTemp.setStroke(PaintFX.getColor());
                }
                else{
                    gcTemp.setStroke(colorPicker.getValue());                   // Set Text Color to selected value
                }

                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gcTemp.setLineWidth(1);
                gcTemp.setFont(new Font("Ariel", lineWidth));
                gcTemp.strokeText(enteredText.getText(), x1, y1);               // Create text at user selected point
                lineBtn.setSelected(false);                                     // Untoggle button
                
                if(PaintFX.getSelec() == 0){                                    // On selected tab,
                    PaintFX.redoClear();                                        // Clear Redo
                    PaintFX.recool1Clear();
                    if(PaintFX.getChange() == 0){                               // Update change and tab
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
                hold = 1;
            }
        });
    }
}
