package PaintFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Event Handler to Draw a Rounded Rectangle Onscreen
 * 
 * @author Alex Appel
 */
public class rRectangleHandler implements EventHandler<ActionEvent> {

    private StackPane coolCrab;
    private ColorPicker colorPicker;
    private ColorPicker fillPick;
    private TextField widthText;
    private ToggleButton rectBtn;
    private ToggleButton dropBtn;
    private ToggleButton dropBtn1;
    double x1;
    double x2;
    double y1;
    double y2;
    static int hold = 0;
    private TabPane tabPane;
    private CheckBox fillBox;
    private WritableImage image;
    
    /**
     * Set phase of rRectangleHandler (Normally 2)
     * @param h Hold Value (h = 2 will cause this handler to end)
     */
    public static void setHold(int h){
        hold = h;
    }
    
    /**
     * Event Handler to Place a Rounded Rectangle on Screen
     * @param coolCrab Main Program StackPane
     * @param colorPicker Edge Color Picker
     * @param fillPick Fill Color Picker
     * @param widthText Toolbar Width Textbox
     * @param rectBtn Rounded Rectangle Handler Button
     * @param tabPane Main Program TabPane
     * @param fillBox Toolbar Fill CheckBox
     * @param dropBtn Edge Color Dropper Button
     * @param dropBtn1 Fill Color Dropper Button
     */
    public rRectangleHandler(StackPane coolCrab, ColorPicker colorPicker, ColorPicker fillPick, TextField widthText, ToggleButton rectBtn, TabPane tabPane, CheckBox fillBox, ToggleButton dropBtn, ToggleButton dropBtn1){

        this.coolCrab = coolCrab;
        this.colorPicker = colorPicker;
        this.fillPick = fillPick;
        this.widthText = widthText;
        this.rectBtn = rectBtn;
        this.tabPane = tabPane;
        this.fillBox = fillBox;
        this.dropBtn = dropBtn;
        this.dropBtn1 = dropBtn1;
        
    }
    
    @Override
    public void handle(ActionEvent a) {
          
        hold = 0;
        PaintFX.logItem("Rounded Rectangle", 1);                                // Log Save
        lineHandler.setHold(2);                                                 // Set holds
        curveHandler.setHold(1);
        squareHandler.setHold(2);
        ovalHandler.setHold(2);
        circleHandler.setHold(2);
        eraserHandler.setHold(2);
        textHandler.setHold(1);
        polygonHandler.setHold(2);
        moveHandler.setHold(2);
        copyHandler.setHold(2);
        
        Canvas canTemp = new Canvas(PaintFX.getW(), PaintFX.getH());            // Create New Canvas and Graphics Context
        GraphicsContext gcTemp = canTemp.getGraphicsContext2D();

        SnapshotParameters params = new SnapshotParameters();                   // Define snapshot parameters
        params.setFill(Color.TRANSPARENT);

        if(PaintFX.getSelec() == 0){                                            // For selected tab, take screenshot and display
            image = PaintFX.canvasPeek().snapshot(params, null);
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
                
        canTemp.setOnMousePressed((event) ->{                                   // When mouse clicked
            if(hold == 0 && rectBtn.isSelected()){                              
                x1 = event.getX();                                              // Record Coordinates
                y1 = event.getY();
            }
        });
        
        canTemp.setOnMouseDragged((event) ->{
            if(hold == 0 && rectBtn.isSelected()){                              // When Mouse Dragged
                gcTemp.clearRect(canTemp.getLayoutBounds().getMinX(), canTemp.getLayoutBounds().getMinY(), canTemp.getWidth(), canTemp.getHeight());
                gcTemp.drawImage(image, 0, 0);
                x2 = event.getX();                                              // Record Coordinates
                y2 = event.getY();
                if(dropBtn.isSelected()){                                       // Get Color
                    gcTemp.setStroke(PaintFX.getColor());
                }
                else{
                    gcTemp.setStroke(colorPicker.getValue());                   // Set rounded rectangle Color to selected value
                }
                if(dropBtn1.isSelected()){                                      // Get Fill Color
                    gcTemp.setFill(PaintFX.getColor1());
                }
                else{
                    gcTemp.setFill(fillPick.getValue());                        // Set Line Color to selected value
                }
                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gcTemp.setLineWidth(lineWidth);                                 // Set rounded rectangle to desired width
                
                if((x2 < x1) && (y2 < y1)){
                    gcTemp.strokeRoundRect(x2, y2, x1-x2, y1-y2, 20, 20);       // Create rounded rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRoundRect(x2, y2, x1-x2, y1-y2, 20, 20);     // Fill rounded rectangle between user selected points
                    }
                }
                else if(y2 < y1){
                    gcTemp.strokeRoundRect(x1, y2, x2-x1, y1-y2, 20, 20);       // Create rounded rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRoundRect(x1, y2, x2-x1, y1-y2, 20, 20);     // Fill rounded rectangle between user selected points
                    }  
                }
                else if(x2 < x1){
                    gcTemp.strokeRoundRect(x2, y1, x1-x2, y2-y1, 20, 20);       // Create rounded rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRoundRect(x2, y1, x1-x2, y2-y1, 20, 20);     // Fill rounded rectangle between user selected points
                    }
                }
                else{
                    gcTemp.strokeRoundRect(x1, y1, x2-x1, y2-y1, 20, 20);       // Create rounded rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRoundRect(x1, y1, x2-x1, y2-y1, 20, 20);     // Fill rounded rectangle between user selected points
                    }
                }
            }
        });
        
        canTemp.setOnMouseReleased((event) ->{
            if(hold == 0 && rectBtn.isSelected()){                              // On mouse release
                x2 = event.getX();                                              // Record Coordinates
                y2 = event.getY();
                if(dropBtn.isSelected()){                                       // Get Color
                    gcTemp.setStroke(PaintFX.getColor());
                }
                else{
                    gcTemp.setStroke(colorPicker.getValue());                   // Set rounded rectangle Color to selected value
                }
                if(dropBtn1.isSelected()){                                      // Get Fill Color
                    gcTemp.setFill(PaintFX.getColor1());
                }
                else{
                    gcTemp.setFill(fillPick.getValue());                        // Set Line Color to selected value
                }
                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gcTemp.setLineWidth(lineWidth);                                 // Set rounded rectangle to desired width
                
                if((x2 < x1) && (y2 < y1)){
                    gcTemp.strokeRoundRect(x2, y2, x1-x2, y1-y2, 20, 20);       // Create rounded rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRoundRect(x2, y2, x1-x2, y1-y2, 20, 20);     // Fill rounded rectangle between user selected points
                    }
                }
                else if(y2 < y1){
                    gcTemp.strokeRoundRect(x1, y2, x2-x1, y1-y2, 20, 20);       // Create rounded rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRoundRect(x1, y2, x2-x1, y1-y2, 20, 20);     // Fill rounded rectangle between user selected points
                    }  
                }
                else if(x2 < x1){
                    gcTemp.strokeRoundRect(x2, y1, x1-x2, y2-y1, 20, 20);       // Create rounded rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRoundRect(x2, y1, x1-x2, y2-y1, 20, 20);     // Fill rounded rectangle between user selected points
                    }
                }
                else{
                    gcTemp.strokeRoundRect(x1, y1, x2-x1, y2-y1, 20, 20);       // Create rounded rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRoundRect(x1, y1, x2-x1, y2-y1, 20, 20);     // Fill rounded rectangle between user selected points
                    }
                }

                rectBtn.setSelected(false);                                     // Untoggle button
                if(PaintFX.getSelec() == 0){                                    // For selected tab,
                    PaintFX.redoClear();                                        // Clear Redos
                    PaintFX.recoolClear();
                    if(PaintFX.getChange() == 0){                               // If change update with tab names
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
            }
        });
    }
}
