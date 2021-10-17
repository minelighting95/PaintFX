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
 * Event Handler to Draw a Polygon of (n) Sides Onscreen
 * 
 * @author Alex Appel
 */
public class polygonHandler implements EventHandler<ActionEvent> {

    private StackPane coolCrab;
    private ColorPicker colorPicker;
    private ColorPicker fillPick;
    private TextField widthText;
    private TextField pointText;
    private ToggleButton rectBtn;
    private ToggleButton dropBtn;
    private ToggleButton dropBtn1;
    double x1;
    double x2;
    double y1;
    double y2;
    double width;
    double height;
    static int hold = 0;
    private TabPane tabPane;
    private CheckBox fillBox;
    private WritableImage image;
    
    /**
     * Set phase of polygonHandler (Normally 2)
     * @param h Hold Value (h = 2 will cause this handler to end)
     */
    public static void setHold(int h){
        hold = h;
    }
    
    /**
     * Event Handler to Place a Polygon on Screen
     * @param coolCrab Main Program StackPane
     * @param colorPicker Edge Color Picker
     * @param fillPick Fill Color Picker
     * @param widthText Toolbar Width Textbox
     * @param pointText Toolbar Point Number
     * @param rectBtn Polygon Handler Button
     * @param tabPane Main Program TabPane
     * @param fillBox Toolbar Fill CheckBox
     * @param dropBtn Edge Color Dropper Button
     * @param dropBtn1 Fill Color Dropper Button
     */
    public polygonHandler(StackPane coolCrab, ColorPicker colorPicker, ColorPicker fillPick, TextField widthText, TextField pointText, ToggleButton rectBtn, TabPane tabPane, CheckBox fillBox, ToggleButton dropBtn, ToggleButton dropBtn1){

        this.coolCrab = coolCrab;
        this.colorPicker = colorPicker;
        this.fillPick = fillPick;
        this.widthText = widthText;
        this.pointText = pointText;
        this.rectBtn = rectBtn;
        this.tabPane = tabPane;
        this.fillBox = fillBox;
        this.dropBtn = dropBtn;
        this.dropBtn1 = dropBtn1;
        
    }
    
    @Override
    public void handle(ActionEvent a) {
          
        hold = 0;                                                               // Set Holds
        PaintFX.logItem("Polygon", 1);                                          // Log Save
        lineHandler.setHold(2);
        curveHandler.setHold(1);
        rectangleHandler.setHold(2);
        ovalHandler.setHold(2);
        squareHandler.setHold(2);
        eraserHandler.setHold(2);
        rRectangleHandler.setHold(2);
        textHandler.setHold(1);
        moveHandler.setHold(2);
        copyHandler.setHold(2);
        
        Canvas canTemp = new Canvas(PaintFX.getW(), PaintFX.getH());            // Create new canvas and graphics context
        GraphicsContext gcTemp = canTemp.getGraphicsContext2D();

        SnapshotParameters params = new SnapshotParameters();                   // Define snapshot parameters
        params.setFill(Color.TRANSPARENT);

        if(PaintFX.getSelec() == 0){                                            // For selected tab, take and draw snapshot
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

        coolCrab.getChildren().remove(1);                                       // Display snapshot
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
                    gcTemp.setStroke(colorPicker.getValue());                   // Set polygon Color to selected value
                }
                if(dropBtn1.isSelected()){                                      // Get Fill Color
                    gcTemp.setFill(PaintFX.getColor1());
                }
                else{
                    gcTemp.setFill(fillPick.getValue());                        // Set polygon Color to selected value
                }
                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gcTemp.setLineWidth(lineWidth);                                 // Set polygon to desired width
                
                int n = Integer.parseInt(pointText.getText());                  // Convert width text to integer
                if(n < 3){
                    pointText.setText("3");
                    n = 3;
                }
                
                if((x2 < x1) && (y2 < y1)){                                     // Calculate different drag dimensions
                    width = x1-x2;
                    height = y1-y2;
                    if(width < height){height = width;}
                    else{width = height;}
                    
                    double[] x = new double[n];                                 // calculate radius
                    double[] y = new double[n];
                    double r = width/2;
                    
                    for (int i = 0; i < n; i++) {                               // Calculate point coordinates
                        x[i] = x2 + r * Math.cos(2 * Math.PI * i / n);
                        y[i] = y2 + r * Math.sin(2 * Math.PI * i / n);
                    }
                    
                    gcTemp.strokePolygon(x, y, n);                              // Create polgyon between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillPolygon(x, y, n);                            // Fill polygon between user selected points
                    }
                }
                else if(y2 < y1){                                               // See above case
                    width = x2-x1;
                    height = y1-y2;
                    if(width < height){height = width;}
                    else{width = height;}
                    
                    double[] x = new double[n];
                    double[] y = new double[n];
                    double r = width/2;
                    
                    for (int i = 0; i < n; i++) {
                        x[i] = x1 + r * Math.cos(2 * Math.PI * i / n);
                        y[i] = y2 + r * Math.sin(2 * Math.PI * i / n);
                    }
                    
                    gcTemp.strokePolygon(x, y, n);                              // Create polygon between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillPolygon(x, y, n);                            // Fill polygon between user selected points
                    }
                }
                else if(x2 < x1){                                               // See above case
                    width = x1-x2;
                    height = y2-y1;
                    if(width < height){height = width;}
                    else{width = height;}
                    
                    double[] x = new double[n];
                    double[] y = new double[n];
                    double r = width/2;
                    
                    for (int i = 0; i < n; i++) {
                        x[i] = x2 + r * Math.cos(2 * Math.PI * i / n);
                        y[i] = y1 + r * Math.sin(2 * Math.PI * i / n);
                    }
                    
                    gcTemp.strokePolygon(x, y, n);                              // Create polygon between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillPolygon(x, y, n);                            // Fill polygon between user selected points
                    }
                }
                else{                                                           // See above case
                    width = x2-x1;
                    height = y2-y1;
                    if(width < height){height = width;}
                    else{width = height;}
                    
                    double[] x = new double[n];
                    double[] y = new double[n];
                    double r = width/2;
                    
                    for (int i = 0; i < n; i++) {
                        x[i] = x1 + r * Math.cos(2 * Math.PI * i / n);
                        y[i] = y1 + r * Math.sin(2 * Math.PI * i / n);
                    }
                    
                    gcTemp.strokePolygon(x, y, n);                              // Create polygon between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillPolygon(x, y, n);                            // Fill polygon between user selected points
                    }
                }
            }
        });
        
        canTemp.setOnMouseReleased((event) ->{
           if(hold == 0 && rectBtn.isSelected()){                               // On mouse release
                gcTemp.clearRect(canTemp.getLayoutBounds().getMinX(), canTemp.getLayoutBounds().getMinY(), canTemp.getWidth(), canTemp.getHeight());
                gcTemp.drawImage(image, 0, 0);
                x2 = event.getX();                                              // Record Coordinates
                y2 = event.getY();
                hold = 2;
                if(dropBtn.isSelected()){                                       // Get Color
                    gcTemp.setStroke(PaintFX.getColor());
                }
                else{
                    gcTemp.setStroke(colorPicker.getValue());                   // Set polygon Color to selected value
                }
                if(dropBtn1.isSelected()){                                      // Get Fill Color
                    gcTemp.setFill(PaintFX.getColor1());
                }
                else{
                    gcTemp.setFill(fillPick.getValue());                        // Set polygon Color to selected value
                }
                int lineWidth = Integer.parseInt(widthText.getText());          // Convert width text to integer
                if(lineWidth <= 0){                                             // If invalid value,
                    lineWidth = 1;                                              // Set to width = 1
                    widthText.setText("1");
                }
                gcTemp.setLineWidth(lineWidth);                                 // Set polygon to desired width
                
                int n = Integer.parseInt(pointText.getText());                  // Convert width text to integer
                if(n < 3){
                    pointText.setText("3");
                    n = 3;
                }
                
                if((x2 < x1) && (y2 < y1)){                                     // See above cases
                    width = x1-x2;
                    height = y1-y2;
                    if(width < height){height = width;}
                    else{width = height;}
                    
                    double[] x = new double[n];
                    double[] y = new double[n];
                    double r = width/2;
                    
                    for (int i = 0; i < n; i++) {
                        x[i] = x2 + r * Math.cos(2 * Math.PI * i / n);
                        y[i] = y2 + r * Math.sin(2 * Math.PI * i / n);
                    }
                    
                    gcTemp.strokePolygon(x, y, n);                              // Create polygon between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillPolygon(x, y, n);                            // Fill polygon between user selected points
                    }
                }
                else if(y2 < y1){                                               // See above cases
                    width = x2-x1;
                    height = y1-y2;
                    if(width < height){height = width;}
                    else{width = height;}
                    
                    double[] x = new double[n];
                    double[] y = new double[n];
                    double r = width/2;
                    
                    for (int i = 0; i < n; i++) {
                        x[i] = x1 + r * Math.cos(2 * Math.PI * i / n);
                        y[i] = y2 + r * Math.sin(2 * Math.PI * i / n);
                    }
                    
                    gcTemp.strokePolygon(x, y, n);                              // Create polygon between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillPolygon(x, y, n);                            // Fill polygon between user selected points
                    }
                }
                else if(x2 < x1){                                               // See above cases
                    width = x1-x2;
                    height = y2-y1;
                    if(width < height){height = width;}
                    else{width = height;}
                    
                    double[] x = new double[n];
                    double[] y = new double[n];
                    double r = width/2;
                    
                    for (int i = 0; i < n; i++) {
                        x[i] = x2 + r * Math.cos(2 * Math.PI * i / n);
                        y[i] = y1 + r * Math.sin(2 * Math.PI * i / n);
                    }
                    
                    gcTemp.strokePolygon(x, y, n);                              // Create polygon between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillPolygon(x, y, n);                            // Fill polygon between user selected points
                    }
                }
                else{                                                           // See above cases
                    width = x2-x1;
                    height = y2-y1;
                    if(width < height){height = width;}
                    else{width = height;}
                    
                    double[] x = new double[n];
                    double[] y = new double[n];
                    double r = width/2;
                    
                    for (int i = 0; i < n; i++) {
                        x[i] = x1 + r * Math.cos(2 * Math.PI * i / n);
                        y[i] = y1 + r * Math.sin(2 * Math.PI * i / n);
                    }
                    
                    gcTemp.strokePolygon(x, y, n);                              // Create polygon between user selected points
                    if(fillBox.isSelected()){
                        gcTemp.fillPolygon(x, y, n);                            // Fill polygon between user selected points
                    }
                }

                rectBtn.setSelected(false);                                     // Untoggle button
                if(PaintFX.getSelec() == 0){                                    // For selected tab,
                    PaintFX.redoClear();                                        // Clear redos
                    PaintFX.recoolClear();
                    if(PaintFX.getChange() == 0){                               // If change, update tab names
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
