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

/**
 * Event Handler to Draw a Line Onscreen
 * 
 * @author Alex Appel
 */
public class lineHandler implements EventHandler<ActionEvent> {

    private ColorPicker colorPicker;
    private StackPane coolCrab;
    private TextField widthText;
    private ToggleButton lineBtn;
    private Stage primaryStage;
    private ToggleButton dropBtn;
    double x1;
    double x2;
    double x3;
    double y1;
    double y2;
    double y3;
    static int hold = 0;
    private TabPane tabPane;
    private WritableImage image;
    
    /**
     * Function to set phase of lineHandler (Normally 2)
     * @param h Hold Value (h = 2 will cause this handler to end)
     */
    public static void setHold(int h){
        hold = h;
    }
    
    /**
     * Event Handler to Place a Line on Screen
     * @param coolCrab Main Program StackPane
     * @param colorPicker Edge Color Picker
     * @param widthText Toolbar Width Textbox
     * @param lineBtn Line Handler Button
     * @param primaryStage Main Program Stage
     * @param tabPane Main Program TabPane
     * @param dropBtn Edge Color Dropper Button
     */
    public lineHandler(StackPane coolCrab, ColorPicker colorPicker, TextField widthText, ToggleButton lineBtn, Stage primaryStage, TabPane tabPane, ToggleButton dropBtn){

        this.coolCrab = coolCrab;
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
        eraserHandler.setHold(2);
        textHandler.setHold(1);
        rRectangleHandler.setHold(2);
        polygonHandler.setHold(2);
        
        Canvas canTemp = new Canvas(PaintFX.getW(), PaintFX.getH());                                       // Create Canvas
        GraphicsContext gcTemp = canTemp.getGraphicsContext2D();

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        if(PaintFX.getSelec() == 0){
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

        coolCrab.getChildren().remove(1);
        coolCrab.getChildren().add(1, canTemp);
                
        canTemp.setOnMousePressed((event) ->{                                // When mouse clicked
            if(hold == 0){                                                      // 1st time
                x1 = event.getX();                                              // Record Coordinates
                y1 = event.getY();
            }
        });
        
        canTemp.setOnMouseDragged((event) ->{
            if(hold == 0){                                                 // 2nd time
                gcTemp.clearRect(canTemp.getLayoutBounds().getMinX(), canTemp.getLayoutBounds().getMinY(), canTemp.getWidth(), canTemp.getHeight());
                gcTemp.drawImage(image, 0, 0);
                x2 = event.getX();                                              // Record Coordinates
                y2 = event.getY();
                if(dropBtn.isSelected()){                                       // Get Color
                    gcTemp.setStroke(PaintFX.getColor());
                }
                else{
                    gcTemp.setStroke(colorPicker.getValue());                       // Set Line Color to selected value
                }

                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gcTemp.setLineWidth(lineWidth);                                     // Set line to desired width
                gcTemp.strokeLine(x1, y1, x2, y2);                                  // Create line between user selected points
            }
        });
        
        canTemp.setOnMouseReleased((event) ->{
            if(hold == 0){                                                 // 2nd time
                x3 = event.getX();                                              // Record Coordinates
                y3 = event.getY();
                if(dropBtn.isSelected()){                                       // Get Color
                    gcTemp.setStroke(PaintFX.getColor());
                }
                else{
                    gcTemp.setStroke(colorPicker.getValue());                       // Set Line Color to selected value
                }

                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gcTemp.setLineWidth(lineWidth);                                     // Set line to desired width
                gcTemp.strokeLine(x1, y1, x3, y3);                                  // Create line between user selected points
                lineBtn.setSelected(false);                                     // Untoggle button
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
                hold = 2;
            }
        });
    }
}
