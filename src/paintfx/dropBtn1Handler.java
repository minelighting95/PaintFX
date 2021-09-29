package PaintFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;

public class dropBtn1Handler implements EventHandler<ActionEvent> {

    public ImageView cool;
    public ImageView cool1;
    public ImageView cool2;
    public StackPane coolCrab;
    public Canvas canvas;
    public Canvas canvas1;
    public Canvas canvas2;
    
    public dropBtn1Handler(ImageView cool, ImageView cool1, ImageView cool2, StackPane coolCrab, Canvas canvas, 
            Canvas canvas1, Canvas canvas2){
        
        this.cool = cool;
        this.cool1 = cool1;
        this.cool2 = cool2;
        this.coolCrab = coolCrab;
        this.canvas = canvas;
        this.canvas1 = canvas1;
        this.canvas2 = canvas2;
        
    }
    
    @Override
    public void handle(ActionEvent e){
        
        if(PaintFX.getSelec() == 0){                                                     
            canvas.setOnMouseClicked((event) ->{                            // When mouse clicked
                Bounds bnd = cool.getBoundsInParent();                      // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
                WritableImage wim = new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight());
                SnapshotParameters sP = new SnapshotParameters();           // Set the snapshot viewport and set to coordinates of image
                sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
                sP.setFill(Color.TRANSPARENT);                              // Make backgroud transparent
                coolCrab.snapshot(sP, wim);                                 // Take snapshot and save image

                double x = event.getX();                                    // Record Coordinates
                double y = event.getY();
                if((wim.getPixelReader().getColor((int)x, (int)y)) == Color.TRANSPARENT){
                    PixelReader r = PaintFX.getImage().getPixelReader();                  // If canvas transparent, grab image color
                    PaintFX.setColor1(r.getColor((int)x, (int)y));
                }
                else{
                    PaintFX.setColor1(wim.getPixelReader().getColor((int)x, (int)y));  // If not, grab canvas color
                }
            });
        }
        else if(PaintFX.getSelec() == 1){                                                
            canvas1.setOnMouseClicked((event) ->{                           // When mouse clicked
                Bounds bnd = cool1.getBoundsInParent();                     // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
                WritableImage wim = new WritableImage((int)canvas1.getWidth(), (int)canvas1.getHeight());
                SnapshotParameters sP = new SnapshotParameters();           // Set the snapshot viewport and set to coordinates of image
                sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
                sP.setFill(Color.TRANSPARENT);                              // Make backgroud transparent
                coolCrab.snapshot(sP, wim);                                 // Take snapshot and save image

                double x = event.getX();                                    // Record Coordinates
                double y = event.getY();
                if((wim.getPixelReader().getColor((int)x, (int)y)) == Color.TRANSPARENT){
                    PixelReader r = PaintFX.getImage1().getPixelReader();                 // If canvas transparent, grab image color
                    PaintFX.setColor1(r.getColor((int)x, (int)y));
                }
                else{
                    PaintFX.setColor1(wim.getPixelReader().getColor((int)x, (int)y));  // If not, grab canvas color
                }
            });
        }
        else if(PaintFX.getSelec() == 2){                                                
            canvas2.setOnMouseClicked((event) ->{                           // When mouse clicked
                Bounds bnd = cool2.getBoundsInParent();                     // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
                WritableImage wim = new WritableImage((int)canvas2.getWidth(), (int)canvas2.getHeight());
                SnapshotParameters sP = new SnapshotParameters();           // Set the snapshot viewport and set to coordinates of image
                sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
                sP.setFill(Color.TRANSPARENT);                              // Make backgroud transparent
                coolCrab.snapshot(sP, wim);                                 // Take snapshot and save image

                double x = event.getX();                                    // Record Coordinates
                double y = event.getY();
                if((wim.getPixelReader().getColor((int)x, (int)y)) == Color.TRANSPARENT){
                    PixelReader r = PaintFX.getImage2().getPixelReader();                 // If canvas transparent, grab image color
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
