package PaintFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Event Handler to Control Fill Color Dropper
 * 
 * @author Alex Appel
 */
public class dropBtn1Handler implements EventHandler<ActionEvent> {

    private StackPane coolCrab;
    
    /**
     * Event Handler to Control Fill Color Dropper
     * @param coolCrab Main Program StackPane
     */
    public dropBtn1Handler(StackPane coolCrab){
        
        this.coolCrab = coolCrab;
        
    }
    
    @Override
    public void handle(ActionEvent e){
        
        if(PaintFX.getSelec() == 0){                                                     
            PaintFX.canvasPeek().setOnMouseClicked((event) ->{                  // When mouse clicked
                Bounds bnd = PaintFX.coolPeek().getBoundsInParent();            // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
                WritableImage wim = new WritableImage((int)PaintFX.canvasPeek().getWidth(), (int)PaintFX.canvasPeek().getHeight());
                SnapshotParameters sP = new SnapshotParameters();               // Set the snapshot viewport and set to coordinates of image
                sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
                sP.setFill(Color.TRANSPARENT);                                  // Make backgroud transparent
                coolCrab.snapshot(sP, wim);                                     // Take snapshot and save image

                double x = event.getX();                                        // Record Coordinates
                double y = event.getY();
                if((wim.getPixelReader().getColor((int)x, (int)y)) == Color.TRANSPARENT){
                    PixelReader r = PaintFX.getImage().getPixelReader();        // If canvas transparent, grab image color
                    PaintFX.setColor1(r.getColor((int)x, (int)y));
                }
                else{
                    PaintFX.setColor1(wim.getPixelReader().getColor((int)x, (int)y));   // If not, grab canvas color
                }
            });
        }
        else if(PaintFX.getSelec() == 1){                                                
            PaintFX.canvas1Peek().setOnMouseClicked((event) ->{                 // When mouse clicked
                Bounds bnd = PaintFX.cool1Peek().getBoundsInParent();           // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
                WritableImage wim = new WritableImage((int)PaintFX.canvas1Peek().getWidth(), (int)PaintFX.canvas1Peek().getHeight());
                SnapshotParameters sP = new SnapshotParameters();               // Set the snapshot viewport and set to coordinates of image
                sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
                    sP.setFill(Color.TRANSPARENT);                              // Make backgroud transparent
                coolCrab.snapshot(sP, wim);                                     // Take snapshot and save image

                double x = event.getX();                                        // Record Coordinates
                double y = event.getY();
                if((wim.getPixelReader().getColor((int)x, (int)y)) == Color.TRANSPARENT){
                    PixelReader r = PaintFX.getImage1().getPixelReader();       // If canvas transparent, grab image color
                    PaintFX.setColor1(r.getColor((int)x, (int)y));
                }
                else{
                    PaintFX.setColor1(wim.getPixelReader().getColor((int)x, (int)y));  // If not, grab canvas color
                }
            });
        }
        else if(PaintFX.getSelec() == 2){                                                
            PaintFX.canvas2Peek().setOnMouseClicked((event) ->{                 // When mouse clicked
                Bounds bnd = PaintFX.cool2Peek().getBoundsInParent();           // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
                WritableImage wim = new WritableImage((int)PaintFX.canvas2Peek().getWidth(), (int)PaintFX.canvas2Peek().getHeight());
                SnapshotParameters sP = new SnapshotParameters();               // Set the snapshot viewport and set to coordinates of image
                sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
                sP.setFill(Color.TRANSPARENT);                                  // Make backgroud transparent
                coolCrab.snapshot(sP, wim);                                     // Take snapshot and save image

                double x = event.getX();                                        // Record Coordinates
                double y = event.getY();
                if((wim.getPixelReader().getColor((int)x, (int)y)) == Color.TRANSPARENT){
                    PixelReader r = PaintFX.getImage2().getPixelReader();       // If canvas transparent, grab image color
                    PaintFX.setColor1(r.getColor((int)x, (int)y));
                }
                else{
                    PaintFX.setColor1(wim.getPixelReader().getColor((int)x, (int)y));  // If not, grab canvas color
                }
            });
        }
        else{                                                               
            System.out.println("Dropper Button Error");
        }
        
    }
}
