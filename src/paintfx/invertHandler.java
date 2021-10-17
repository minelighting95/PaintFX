package PaintFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TabPane;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Event Handler to Invert Onscreen Colors
 * 
 * @author Alex Appel
 */
public class invertHandler implements EventHandler<ActionEvent> {

    private StackPane coolCrab;
    static int hold2 = 0;
    private TabPane tabPane;
    
    /**
     * Event Handler to Invert Colors Onscreen
     * @param coolCrab Main Program StackPane
     * @param tabPane Main Program TabPane
     */
    public invertHandler(StackPane coolCrab, TabPane tabPane){

        this.coolCrab = coolCrab;
        this.tabPane = tabPane;
        
    }
    
    @Override
    public void handle(ActionEvent a) {

        hold2 = 0;                                                              // Set Holds
        PaintFX.logItem("Invert", 1);                                            // Log Save
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

        coolCrab.setScaleX(1);                                              // Remove Zoom
        coolCrab.setScaleY(1);
        PaintFX.setScaleX(1);                                               // Remove Scale
        PaintFX.setScaleY(1);
        
        Canvas canTemp = new Canvas(PaintFX.getW(), PaintFX.getH());            // Create new canvas and graphics context
        GraphicsContext gcTemp = canTemp.getGraphicsContext2D();

        SnapshotParameters params = new SnapshotParameters();                   // Define snapshot parameters
        params.setFill(Color.TRANSPARENT);

        if(PaintFX.getSelec() == 0){                                            // For selected tab,
            Bounds bnd = PaintFX.canvasPeek().getBoundsInParent();              // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage image = new WritableImage((int)PaintFX.canvasPeek().getWidth(), (int)PaintFX.canvasPeek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            coolCrab.snapshot(sP, image);                                       // Take snapshot and save image
            
            PixelReader reader = image.getPixelReader();                        // Create pixel reader
            int w = (int)image.getWidth();                                      // Get Coordinates
            int h = (int)image.getHeight();
            
            WritableImage wImage = new WritableImage(w, h);                     // Create new writable image
            PixelWriter writer = wImage.getPixelWriter();                       // Create writer
            
            for(int y = 0; y < h; y++) {                                        // Invert selected pixel
               for(int x = 0; x < w; x++) {
                  Color color = reader.getColor(x, y);
                  writer.setColor(x, y, color.invert());
               }
            }
            gcTemp.drawImage(wImage, 0, 0);                                     // Draw new image
            
        }
        else if(PaintFX.getSelec() == 1){
            Bounds bnd = PaintFX.canvas1Peek().getBoundsInParent();              // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage image = new WritableImage((int)PaintFX.canvas1Peek().getWidth(), (int)PaintFX.canvas1Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            coolCrab.snapshot(sP, image);                                       // Take snapshot and save image
            
            PixelReader reader = image.getPixelReader();                        // Create pixel reader
            int w = (int)image.getWidth();                                      // Get Coordinates
            int h = (int)image.getHeight();
            
            WritableImage wImage = new WritableImage(w, h);                     // Create new writable image
            PixelWriter writer = wImage.getPixelWriter();                       // Create writer
            
            for(int y = 0; y < h; y++) {                                        // Invert selected pixel
               for(int x = 0; x < w; x++) {
                  Color color = reader.getColor(x, y);
                  writer.setColor(x, y, color.invert());
               }
            }
            gcTemp.drawImage(wImage, 0, 0);
        }
        else if(PaintFX.getSelec() == 2){
            Bounds bnd = PaintFX.canvas2Peek().getBoundsInParent();              // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage image = new WritableImage((int)PaintFX.canvas2Peek().getWidth(), (int)PaintFX.canvas2Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            coolCrab.snapshot(sP, image);                                       // Take snapshot and save image
            
            PixelReader reader = image.getPixelReader();                        // Create pixel reader
            
            int w = (int)image.getWidth();                                      // Get Coordinates
            int h = (int)image.getHeight();
            
            WritableImage wImage = new WritableImage(w, h);                     // Create new writable image
            PixelWriter writer = wImage.getPixelWriter();                       // Create writer
            
            for(int y = 0; y < h; y++) {                                        // Invert selected pixel
               for(int x = 0; x < w; x++) {
                  Color color = reader.getColor(x, y);
                  writer.setColor(x, y, color.invert());
               }
            }
            gcTemp.drawImage(wImage, 0, 0);
        }

        coolCrab.getChildren().remove(1);                                       // Display new canvas
        coolCrab.getChildren().add(1, canTemp);

        
        if(PaintFX.getSelec() == 0){                                            // For selected tab,
            PaintFX.redoClear();                                                // Clear redo
            PaintFX.recoolClear();
            if(PaintFX.getChange() == 0){                                       // If change, update tab name
                tabPane.getTabs().get(0).setText(tabPane.getTabs().get(0).getText() + "*");
                PaintFX.setChange(1);
            }
            PaintFX.canvasPush(canTemp);                                        // Push new canvas
        }
        else if(PaintFX.getSelec() == 1){                                       // See above case
            PaintFX.redo1Clear();
            PaintFX.recool1Clear();
            if(PaintFX.getChange1() == 0){
                tabPane.getTabs().get(1).setText(tabPane.getTabs().get(1).getText() + "*");
                PaintFX.setChange1(1);
            }
            PaintFX.canvas1Push(canTemp);
        }
        else if(PaintFX.getSelec() == 2){                                       // See above case
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
}
