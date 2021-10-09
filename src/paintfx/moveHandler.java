package PaintFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Event Handler to Move Part of the Onscreen Image
 * 
 * @author Alex Appel
 */
public class moveHandler implements EventHandler<ActionEvent> {

    private StackPane coolCrab;
    private Stage primaryStage;
    double x1;
    double x2;
    double y1;
    double y2;
    double width;
    double height;
    static int hold = 0;
    private TabPane tabPane;
    private WritableImage image;
    private WritableImage cut;
    private ImageView currImage;
    private GraphicsContext gcTemp;
    private Canvas canTemp;
    private Bounds localbnd;
    private CheckBox eraseBox;
    
    /**
     * Function to set phase of moveHandler (Normally 2)
     * @param h Hold Value (h = 2 will cause this handler to end)
     */
    public static void setHold(int h){
        hold = h;
    }
    
    /**
     * Event Handler to Move Part of the Onscreen Image
     * @param coolCrab Main Program StackPane
     * @param primaryStage Main Program Stage
     * @param tabPane Main Program TabPane
     * @param eraseBox Toolbar Eraser Transparency CheckBox
     */
    public moveHandler(StackPane coolCrab, Stage primaryStage, TabPane tabPane, CheckBox eraseBox){

        this.coolCrab = coolCrab;
        this.primaryStage = primaryStage;
        this.tabPane = tabPane;
        this.eraseBox = eraseBox;
        
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
        
        canTemp = new Canvas(PaintFX.getW(), PaintFX.getH());                                       // Create Canvas
        gcTemp = canTemp.getGraphicsContext2D();

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        
        SnapshotParameters sP = new SnapshotParameters();
        sP.setFill(Color.TRANSPARENT);
        

        if(PaintFX.getSelec() == 0){
            Bounds bnd = PaintFX.coolPeek().getBoundsInParent();                          // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            image = new WritableImage((int)PaintFX.canvasPeek().getWidth(), (int)PaintFX.canvasPeek().getHeight());
            params.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            image = PaintFX.canvasPeek().snapshot(params, null);
            gcTemp.drawImage(image, 0, 0);
        }
        else if(PaintFX.getSelec() == 1){
            Bounds bnd = PaintFX.cool1Peek().getBoundsInParent();                          // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            image = new WritableImage((int)PaintFX.canvas1Peek().getWidth(), (int)PaintFX.canvas1Peek().getHeight());
            params.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            image = PaintFX.canvas1Peek().snapshot(params, null);
            gcTemp.drawImage(image, 0, 0);
        }
        else if(PaintFX.getSelec() == 2){
            Bounds bnd = PaintFX.cool2Peek().getBoundsInParent();                          // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            image = new WritableImage((int)PaintFX.canvas2Peek().getWidth(), (int)PaintFX.canvas2Peek().getHeight());
            params.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
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
            else if(hold == 1){
                gcTemp.clearRect(canTemp.getLayoutBounds().getMinX(), canTemp.getLayoutBounds().getMinY(), canTemp.getWidth(), canTemp.getHeight());
                gcTemp.drawImage(image, 0, 0);
                double xCurr = event.getX();                                              // Record Coordinates
                double yCurr = event.getY();
                gcTemp.drawImage(cut, xCurr, yCurr);
            }
        });
        
        canTemp.setOnMouseDragged((event) ->{
            if(hold == 0){                                                 // 2nd time
                gcTemp.clearRect(canTemp.getLayoutBounds().getMinX(), canTemp.getLayoutBounds().getMinY(), canTemp.getWidth(), canTemp.getHeight());
                gcTemp.drawImage(image, 0, 0);
                x2 = event.getX();                                              // Record Coordinates
                y2 = event.getY();
                
                gcTemp.setLineWidth(1);                                     // Set rectangle to desired width

                if((x2 < x1) && (y2 < y1)){
                    gcTemp.strokeRect(x2, y2, x1-x2, y1-y2);                        // Create rectangle between user selected points
                }
                else if(y2 < y1){
                    gcTemp.strokeRect(x1, y2, x2-x1, y1-y2);                        // Create rectangle between user selected points
                }
                else if(x2 < x1){
                    gcTemp.strokeRect(x2, y1, x1-x2, y2-y1);                        // Create rectangle between user selected points
                }
                else{
                    gcTemp.strokeRect(x1, y1, x2-x1, y2-y1);                        // Create rectangle between user selected points
                }
            }
            else if(hold == 1){
                gcTemp.clearRect(canTemp.getLayoutBounds().getMinX(), canTemp.getLayoutBounds().getMinY(), canTemp.getWidth(), canTemp.getHeight());
                gcTemp.drawImage(image, 0, 0);
                double xCurr = event.getX();                                              // Record Coordinates
                double yCurr = event.getY();
                gcTemp.drawImage(cut, xCurr, yCurr);
            }
        });
        
        canTemp.setOnMouseReleased((event) ->{
            if(hold == 0){                                                 // 2nd time
                gcTemp.clearRect(canTemp.getLayoutBounds().getMinX(), canTemp.getLayoutBounds().getMinY(), canTemp.getWidth(), canTemp.getHeight());
                gcTemp.drawImage(image, 0, 0);
                x2 = event.getX();                                              // Record Coordinates
                y2 = event.getY();
                hold = 1;
                
                if(PaintFX.getSelec() == 0){
                    localbnd = PaintFX.coolPeek().getBoundsInParent();                          // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
                }
                else if(PaintFX.getSelec() == 1){
                    localbnd = PaintFX.cool1Peek().getBoundsInParent();                          // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
                }
                else if(PaintFX.getSelec() == 2){
                    localbnd = PaintFX.cool2Peek().getBoundsInParent();                          // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
                }
                
                if((x2 < x1) && (y2 < y1)){
                    width = x1-x2;
                    height = y1-y2;
                    sP.setViewport(new Rectangle2D(localbnd.getMinX()+x2, localbnd.getMinY()+y2, width, height));
                    cut = coolCrab.snapshot(sP, null);
                }
                else if(y2 < y1){
                    width = x2-x1;
                    height = y1-y2;
                    sP.setViewport(new Rectangle2D(localbnd.getMinX()+x1, localbnd.getMinY()+y2, width, height));
                    cut = coolCrab.snapshot(sP, null);
                }
                else if(x2 < x1){
                    width = x1-x2;
                    height = y2-y1;
                    sP.setViewport(new Rectangle2D(localbnd.getMinX()+x2, localbnd.getMinY()+y1, width, height));
                    cut = coolCrab.snapshot(sP, null);
                }
                else{
                    width = x2-x1;
                    height = y2-y1;
                    sP.setViewport(new Rectangle2D(localbnd.getMinX()+x1, localbnd.getMinY()+y1, width, height));
                    cut = coolCrab.snapshot(sP, null);
                }
            }
            else if(hold == 1){
                gcTemp.clearRect(canTemp.getLayoutBounds().getMinX(), canTemp.getLayoutBounds().getMinY(), canTemp.getWidth(), canTemp.getHeight());
                gcTemp.drawImage(image, 0, 0);
                double xCurr = event.getX();                                              // Record Coordinates
                double yCurr = event.getY();
                gcTemp.drawImage(cut, xCurr, yCurr);
                
                hold = 2;
                
                if((x2 < x1) && (y2 < y1)){
                    gcTemp.setFill(Color.WHITE);
                    gcTemp.fillRect(x2, y2, width, height);
                }
                else if(y2 < y1){
                    gcTemp.setFill(Color.WHITE);
                    gcTemp.fillRect(x1, y2, width, height);
                }
                else if(x2 < x1){
                    gcTemp.setFill(Color.WHITE);
                    gcTemp.fillRect(x2, y1, width, height);
                }
                else{
                    gcTemp.setFill(Color.WHITE);
                    gcTemp.fillRect(x1, y1, width, height);
                }
                
                if(PaintFX.getSelec() == 0){
                    PaintFX.redoClear();
                    PaintFX.recoolClear();
                    if(PaintFX.getChange() == 0){
                        tabPane.getTabs().get(0).setText(tabPane.getTabs().get(0).getText() + "*");
                        PaintFX.setChange(1);
                    }
                    PaintFX.canvasPush(canTemp);
                }
                else if(PaintFX.getSelec() == 1){
                    PaintFX.redo1Clear();
                    PaintFX.recool1Clear();
                    if(PaintFX.getChange1() == 0){
                        tabPane.getTabs().get(1).setText(tabPane.getTabs().get(1).getText() + "*");
                        PaintFX.setChange1(1);
                    }
                    PaintFX.canvas1Push(canTemp);
                }
                else if(PaintFX.getSelec() == 2){
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
