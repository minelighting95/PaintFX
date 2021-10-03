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
import javafx.stage.Stage;

public class rectangleHandler implements EventHandler<ActionEvent> {

    public StackPane coolCrab;
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
    public WritableImage image;
    
    public static void setHold(int h){
        hold = h;
    }
    
    public rectangleHandler(StackPane coolCrab, ColorPicker colorPicker, ColorPicker fillPick, TextField widthText, ToggleButton rectBtn, Stage primaryStage, TabPane tabPane, CheckBox fillBox, ToggleButton dropBtn, ToggleButton dropBtn1){

        this.coolCrab = coolCrab;
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
        eraserHandler.setHold(2);
        rRectangleHandler.setHold(2);
        textHandler.setHold(1);
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
                    gcTemp.setStroke(colorPicker.getValue());                       // Set Rectangle Color to selected value
                }
                if(dropBtn1.isSelected()){                                      // Get Fill Color
                    gcTemp.setFill(PaintFX.getColor1());
                }
                else{
                    gcTemp.setFill(fillPick.getValue());                            // Set Line Color to selected value
                }
                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gcTemp.setLineWidth(lineWidth);                                     // Set rectangle to desired width

                if((x2 < x1) && (y2 < y1)){
                    gcTemp.strokeRect(x2, y2, x1-x2, y1-y2);                        // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRect(x2, y2, x1-x2, y1-y2);                      // Create rectangle between user selected points
                    }
                }
                else if(y2 < y1){
                    gcTemp.strokeRect(x1, y2, x2-x1, y1-y2);                        // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRect(x1, y2, x2-x1, y1-y2);                      // Create rectangle between user selected points
                    }  
                }
                else if(x2 < x1){
                    gcTemp.strokeRect(x2, y1, x1-x2, y2-y1);                        // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRect(x2, y1, x1-x2, y2-y1);
                    }
                }
                else{
                    gcTemp.strokeRect(x1, y1, x2-x1, y2-y1);                        // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRect(x1, y1, x2-x1, y2-y1);
                    }
                }
            }
        });
        
        canTemp.setOnMouseReleased((event) ->{
            if(hold == 0){                                                 // 2nd time
                gcTemp.clearRect(canTemp.getLayoutBounds().getMinX(), canTemp.getLayoutBounds().getMinY(), canTemp.getWidth(), canTemp.getHeight());
                gcTemp.drawImage(image, 0, 0);
                x2 = event.getX();                                              // Record Coordinates
                y2 = event.getY();
                hold = 2;
                
                if(dropBtn.isSelected()){                                       // Get Color
                    gcTemp.setStroke(PaintFX.getColor());
                }
                else{
                    gcTemp.setStroke(colorPicker.getValue());                       // Set Rectangle Color to selected value
                }
                if(dropBtn1.isSelected()){                                      // Get Fill Color
                    gcTemp.setFill(PaintFX.getColor1());
                }
                else{
                    gcTemp.setFill(fillPick.getValue());                            // Set Line Color to selected value
                }
                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gcTemp.setLineWidth(lineWidth);                                     // Set rectangle to desired width

                if((x2 < x1) && (y2 < y1)){
                    gcTemp.strokeRect(x2, y2, x1-x2, y1-y2);                        // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRect(x2, y2, x1-x2, y1-y2);                      // Create rectangle between user selected points
                    }
                }
                else if(y2 < y1){
                    gcTemp.strokeRect(x1, y2, x2-x1, y1-y2);                        // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRect(x1, y2, x2-x1, y1-y2);                      // Create rectangle between user selected points
                    }  
                }
                else if(x2 < x1){
                    gcTemp.strokeRect(x2, y1, x1-x2, y2-y1);                        // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRect(x2, y1, x1-x2, y2-y1);
                    }
                }
                else{
                    gcTemp.strokeRect(x1, y1, x2-x1, y2-y1);                        // Create rectangle between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillRect(x1, y1, x2-x1, y2-y1);
                    }
                }

                rectBtn.setSelected(false);                                     // Untoggle button
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
            }
        });
    }
}
