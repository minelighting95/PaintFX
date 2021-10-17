package PaintFX;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TabPane;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;

/**
 * Event Handler to Control Saving
 * 
 * @author Alex Appel
 */
public class saveHandler implements EventHandler<ActionEvent>{
    
    private StackPane coolCrab;
    private TabPane tabPane;
    
    /**
     * Event Handler to Control Saving
     * @param coolCrab Main Program StackPane
     * @param tabPane Main Program TabPane
     */
    public saveHandler(StackPane coolCrab, TabPane tabPane){
        
        this.coolCrab = coolCrab;
        this.tabPane = tabPane;
        
    }
    
    /**
     * Save Selected Tab
     * @param coolCrab Main Program StackPane
     * @param tabPane Main Program TabPane
     */
    public static void save(StackPane coolCrab, TabPane tabPane){
        coolCrab.setScaleX(1);                                                  // Remove zoom
        coolCrab.setScaleY(1);
        if(PaintFX.getSelec() == 0){
            PaintFX.redoClear();                                                // Clear redos
            PaintFX.recoolClear();
            Bounds bnd = PaintFX.canvasPeek().getBoundsInParent();              // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvasPeek().getWidth(), (int)PaintFX.canvasPeek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File(PaintFX.getFileName());                // Grab original user file
                String file = saveFile.toURI().toString();                      // Grab file extension
                String fileExt = file.substring(file.length()-3);
                if(fileExt.equals("png")){                                      // If the image was a PNG
                    sP.setFill(Color.TRANSPARENT);                              // Make backgroud transparent
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot and save image
                    ImageIO.write(SwingFXUtils.fromFXImage(wim, null), fileExt, saveFile);
                }
                else{                                                           // If the image was JPG or BMP
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot
                    BufferedImage bufARGB = SwingFXUtils.fromFXImage(wim, null);// Convert from ARGB image to RGB image to fix colors
                    BufferedImage bufRGB = new BufferedImage(bufARGB.getWidth(), bufARGB.getHeight(), BufferedImage.OPAQUE);
                    Graphics2D graphics = bufRGB.createGraphics();              // Create graphics from picture
                    graphics.drawImage(bufARGB, 0, 0, null);                    // Draw image to RGB image
                    ImageIO.write(bufRGB, fileExt, saveFile);                   // Save RGB image
                }
                PaintFX.setChange(0);
                tabPane.getTabs().get(0).setText(PaintFX.getName());            // Set Tab Name
                PaintFX.logItem("Save", 0);                                     // Log Save
            } catch (Exception s) {}
        }
        else if(PaintFX.getSelec() == 1){
            PaintFX.redo1Clear();                                               // Clear Redos
            PaintFX.recool1Clear();
            Bounds bnd = PaintFX.canvas1Peek().getBoundsInParent();             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvas1Peek().getWidth(), (int)PaintFX.canvas1Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File(PaintFX.getFileName1());               // Grab original user file
                String file = saveFile.toURI().toString();                      // Grab file extension
                String fileExt = file.substring(file.length()-3);
                if(fileExt.equals("png")){                                      // If the image was a PNG
                    sP.setFill(Color.TRANSPARENT);                              // Make backgroud transparent
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot and save image
                    ImageIO.write(SwingFXUtils.fromFXImage(wim, null), fileExt, saveFile);
                }
                else{                                                           // If the image was JPG or BMP
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot
                    BufferedImage bufARGB = SwingFXUtils.fromFXImage(wim, null);// Convert from ARGB image to RGB image to fix colors
                    BufferedImage bufRGB = new BufferedImage(bufARGB.getWidth(), bufARGB.getHeight(), BufferedImage.OPAQUE);
                    Graphics2D graphics = bufRGB.createGraphics();              // Create graphics from picture
                    graphics.drawImage(bufARGB, 0, 0, null);                    // Draw image to RGB image
                    ImageIO.write(bufRGB, fileExt, saveFile);                   // Save RGB image
                }
                PaintFX.setChange1(0);
                tabPane.getTabs().get(1).setText(PaintFX.getName());            // Set Tab Name
                PaintFX.logItem("Save", 0);                                     // Log Save
            } catch (Exception s) {}
        }
        else if(PaintFX.getSelec() == 2){
            PaintFX.redo2Clear();                                               // Clear Redos
            PaintFX.recool2Clear();
            Bounds bnd = PaintFX.canvas2Peek().getBoundsInParent();             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvas2Peek().getWidth(), (int)PaintFX.canvas2Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File(PaintFX.getFileName2());               // Grab original user file
                String file = saveFile.toURI().toString();                      // Grab file extension
                String fileExt = file.substring(file.length()-3);
                if(fileExt.equals("png")){                                      // If the image was a PNG
                    sP.setFill(Color.TRANSPARENT);                              // Make backgroud transparent
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot and save image
                    ImageIO.write(SwingFXUtils.fromFXImage(wim, null), fileExt, saveFile);
                }
                else{                                                           // If the image was JPG or BMP
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot
                    BufferedImage bufARGB = SwingFXUtils.fromFXImage(wim, null);// Convert from ARGB image to RGB image to fix colors
                    BufferedImage bufRGB = new BufferedImage(bufARGB.getWidth(), bufARGB.getHeight(), BufferedImage.OPAQUE);
                    Graphics2D graphics = bufRGB.createGraphics();              // Create graphics from picture
                    graphics.drawImage(bufARGB, 0, 0, null);                    // Draw image to RGB image
                    ImageIO.write(bufRGB, fileExt, saveFile);                   // Save RGB image
                }
                PaintFX.setChange2(0);
                tabPane.getTabs().get(2).setText(PaintFX.getName());            // Set Tab Name
                PaintFX.logItem("Save", 0);                                     // Log Save
                
            } catch (Exception s) {}
        }
        else{
            System.out.println("Save Error 0");
        }
        coolCrab.setScaleX(PaintFX.getScaleX());                                // Reset zoom
        coolCrab.setScaleY(PaintFX.getScaleY());
    }
    
    /**
     * Save All Tabs when PaintFX is Exited
     * @param coolCrab Main Program StackPane
     * @param tabPane Main Program TabPane
     */
    public static void finalSave(StackPane coolCrab, TabPane tabPane){
        coolCrab.setScaleX(1);                                                  // Remove zoom
        coolCrab.setScaleY(1);
        if(PaintFX.getChange() == 1){
            PaintFX.redoClear();                                                // Clear redos
            PaintFX.recoolClear();                                                
            coolCrab.getChildren().clear();                                     // Remove previous image
            coolCrab.getChildren().addAll(PaintFX.coolPeek(), PaintFX.canvasPeek());
            Bounds bnd = PaintFX.canvasPeek().getBoundsInParent();              // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvasPeek().getWidth(), (int)PaintFX.canvasPeek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File(PaintFX.getFileName());                // Grab original user file
                String file = saveFile.toURI().toString();                      // Grab file extension
                String fileExt = file.substring(file.length()-3);
                if(fileExt.equals("png")){                                      // If the image was a PNG
                    sP.setFill(Color.TRANSPARENT);                              // Make backgroud transparent
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot and save image
                    ImageIO.write(SwingFXUtils.fromFXImage(wim, null), fileExt, saveFile);
                }
                else{                                                           // If the image was JPG or BMP
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot
                    BufferedImage bufARGB = SwingFXUtils.fromFXImage(wim, null);// Convert from ARGB image to RGB image to fix colors
                    BufferedImage bufRGB = new BufferedImage(bufARGB.getWidth(), bufARGB.getHeight(), BufferedImage.OPAQUE);
                    Graphics2D graphics = bufRGB.createGraphics();              // Create graphics from picture
                    graphics.drawImage(bufARGB, 0, 0, null);                    // Draw image to RGB image
                    ImageIO.write(bufRGB, fileExt, saveFile);                   // Save RGB image
                }
                PaintFX.logItem("Final Save", 0);                               // Log Save
            } catch (Exception s) {}
        }
        if(PaintFX.getChange1() == 1){
            PaintFX.redo1Clear();                                               // Clear redos
            PaintFX.recool1Clear();
            coolCrab.getChildren().clear();                                     // Remove previous image
            coolCrab.getChildren().addAll(PaintFX.cool1Peek(), PaintFX.canvas1Peek());
            Bounds bnd = PaintFX.canvas1Peek().getBoundsInParent();             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvas1Peek().getWidth(), (int)PaintFX.canvas1Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File(PaintFX.getFileName1());               // Grab original user file
                String file = saveFile.toURI().toString();                      // Grab file extension
                String fileExt = file.substring(file.length()-3);
                if(fileExt.equals("png")){                                      // If the image was a PNG
                    sP.setFill(Color.TRANSPARENT);                              // Make backgroud transparent
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot and save image
                    ImageIO.write(SwingFXUtils.fromFXImage(wim, null), fileExt, saveFile);
                }
                else{                                                           // If the image was JPG or BMP
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot
                    BufferedImage bufARGB = SwingFXUtils.fromFXImage(wim, null);// Convert from ARGB image to RGB image to fix colors
                    BufferedImage bufRGB = new BufferedImage(bufARGB.getWidth(), bufARGB.getHeight(), BufferedImage.OPAQUE);
                    Graphics2D graphics = bufRGB.createGraphics();              // Create graphics from picture
                    graphics.drawImage(bufARGB, 0, 0, null);                    // Draw image to RGB image
                    ImageIO.write(bufRGB, fileExt, saveFile);                   // Save RGB image
                }
                PaintFX.logItem("Final Save", 0);                               // Log Save
            } catch (Exception s) {}
        }
        if(PaintFX.getChange2() == 1){
            PaintFX.redo2Clear();                                               // Clear redos
            PaintFX.recool2Clear();
            coolCrab.getChildren().clear();                                     // Remove previous image
            coolCrab.getChildren().addAll(PaintFX.cool2Peek(), PaintFX.canvas2Peek());
            Bounds bnd = PaintFX.canvas2Peek().getBoundsInParent();             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvas2Peek().getWidth(), (int)PaintFX.canvas2Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File(PaintFX.getFileName2());               // Grab original user file
                String file = saveFile.toURI().toString();                      // Grab file extension
                String fileExt = file.substring(file.length()-3);
                if(fileExt.equals("png")){                                      // If the image was a PNG
                    sP.setFill(Color.TRANSPARENT);                              // Make backgroud transparent
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot and save image
                    ImageIO.write(SwingFXUtils.fromFXImage(wim, null), fileExt, saveFile);
                }
                else{                                                           // If the image was JPG or BMP
                    coolCrab.snapshot(sP, wim);                                 // Take snapshot
                    BufferedImage bufARGB = SwingFXUtils.fromFXImage(wim, null);// Convert from ARGB image to RGB image to fix colors
                    BufferedImage bufRGB = new BufferedImage(bufARGB.getWidth(), bufARGB.getHeight(), BufferedImage.OPAQUE);
                    Graphics2D graphics = bufRGB.createGraphics();              // Create graphics from picture
                    graphics.drawImage(bufARGB, 0, 0, null);                    // Draw image to RGB image
                    ImageIO.write(bufRGB, fileExt, saveFile);                   // Save RGB image
                }
                PaintFX.logItem("Final Save", 0);                               // Log Save
            } catch (Exception s) {}
        }
        coolCrab.setScaleX(PaintFX.getScaleX());                                // Reset Scale
        coolCrab.setScaleY(PaintFX.getScaleY());
    }
    
    /**
     * Auto Save current PaintFX Tab
     * @param coolCrab Main Program StackPane
     */
    public static void autoSave(StackPane coolCrab){
        coolCrab.setScaleX(1);                                                  // Remove zoom
        coolCrab.setScaleY(1);  
        if(PaintFX.getSelec() == 0){
            Bounds bnd = PaintFX.canvasPeek().getBoundsInParent();              // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvasPeek().getWidth(), (int)PaintFX.canvasPeek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File("Temp.png");                           // Create Temp File
                sP.setFill(Color.TRANSPARENT);                                  // Make backgroud transparent
                coolCrab.snapshot(sP, wim);                                     // Take snapshot and save image
                ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", saveFile);
                PaintFX.logItem("Auto Save", 0);                                // Log Save
            } catch (Exception s) {s.printStackTrace();}
        }
        else if(PaintFX.getSelec() == 1){
            Bounds bnd = PaintFX.canvas1Peek().getBoundsInParent();             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvas1Peek().getWidth(), (int)PaintFX.canvas1Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File("Temp1.png");                          // Create Temp File
                sP.setFill(Color.TRANSPARENT);                                  // Make backgroud transparent
                coolCrab.snapshot(sP, wim);                                     // Take snapshot and save image
                ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", saveFile);
                PaintFX.logItem("Auto Save", 0);                                // Log Save
            } catch (Exception s) {System.out.println("Auto Save Error 2");}
        }
        else if(PaintFX.getSelec() == 2){
            Bounds bnd = PaintFX.canvas2Peek().getBoundsInParent();             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvas2Peek().getWidth(), (int)PaintFX.canvas2Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File("Temp2.png");                          // Create Temp File
                sP.setFill(Color.TRANSPARENT);                                  // Make backgroud transparent
                coolCrab.snapshot(sP, wim);                                     // Take snapshot and save image
                ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", saveFile);
                PaintFX.logItem("Auto Save", 0);                                // Log Save
            } catch (Exception s) {System.out.println("Auto Save Error 3");}
        }
        else{
            System.out.println("Auto Save Error");
        }
        coolCrab.setScaleX(PaintFX.getScaleX());                                // Reset zoom
        coolCrab.setScaleY(PaintFX.getScaleY());
    }
    
    @Override
    public void handle(ActionEvent e){
        
            coolCrab.setScaleX(1);                                              // Remove zoom
            coolCrab.setScaleY(1);
            if(PaintFX.getSelec() == 0){
                PaintFX.redoClear();                                            // Clear redos
                PaintFX.recoolClear();
                Bounds bnd = PaintFX.canvasPeek().getBoundsInParent();          // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
                WritableImage wim = new WritableImage((int)PaintFX.canvasPeek().getWidth(), (int)PaintFX.canvasPeek().getHeight());
                SnapshotParameters sP = new SnapshotParameters();               // Set the snapshot viewport and set to coordinates of image
                sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
                try {
                    File saveFile = new File(PaintFX.getFileName());            // Grab original user file
                    String file = saveFile.toURI().toString();                  // Grab file extension
                    String fileExt = file.substring(file.length()-3);
                    if(fileExt.equals("png")){                                  // If the image was a PNG
                        sP.setFill(Color.TRANSPARENT);                          // Make backgroud transparent
                        coolCrab.snapshot(sP, wim);                             // Take snapshot and save image
                        ImageIO.write(SwingFXUtils.fromFXImage(wim, null), fileExt, saveFile);
                    }
                    else{                                                       // If the image was JPG or BMP
                        coolCrab.snapshot(sP, wim);                             // Take snapshot
                        BufferedImage bufARGB = SwingFXUtils.fromFXImage(wim, null);// Convert from ARGB image to RGB image to fix colors
                        BufferedImage bufRGB = new BufferedImage(bufARGB.getWidth(), bufARGB.getHeight(), BufferedImage.OPAQUE);
                        Graphics2D graphics = bufRGB.createGraphics();          // Create graphics from picture
                        graphics.drawImage(bufARGB, 0, 0, null);                // Draw image to RGB image
                        ImageIO.write(bufRGB, fileExt, saveFile);               // Save RGB image
                    }
                    PaintFX.setChange(0);
                    tabPane.getTabs().get(0).setText(PaintFX.getName());        // Set Tab Name
                    PaintFX.logItem("Save", 0);                                 // Log Save
                } catch (Exception s) {}
            }
            else if(PaintFX.getSelec() == 1){
                PaintFX.redo1Clear();                                           // Clear redos
                PaintFX.recool1Clear();
                Bounds bnd = PaintFX.canvas1Peek().getBoundsInParent();         // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
                WritableImage wim = new WritableImage((int)PaintFX.canvas1Peek().getWidth(), (int)PaintFX.canvas1Peek().getHeight());
                SnapshotParameters sP = new SnapshotParameters();               // Set the snapshot viewport and set to coordinates of image
                sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
                try {
                    File saveFile = new File(PaintFX.getFileName1());           // Grab original user file
                    String file = saveFile.toURI().toString();                  // Grab file extension
                    String fileExt = file.substring(file.length()-3);
                    if(fileExt.equals("png")){                                  // If the image was a PNG
                        sP.setFill(Color.TRANSPARENT);                          // Make backgroud transparent
                        coolCrab.snapshot(sP, wim);                             // Take snapshot and save image
                        ImageIO.write(SwingFXUtils.fromFXImage(wim, null), fileExt, saveFile);
                    }
                    else{                                                       // If the image was JPG or BMP
                        coolCrab.snapshot(sP, wim);                             // Take snapshot
                        BufferedImage bufARGB = SwingFXUtils.fromFXImage(wim, null);// Convert from ARGB image to RGB image to fix colors
                        BufferedImage bufRGB = new BufferedImage(bufARGB.getWidth(), bufARGB.getHeight(), BufferedImage.OPAQUE);
                        Graphics2D graphics = bufRGB.createGraphics();          // Create graphics from picture
                        graphics.drawImage(bufARGB, 0, 0, null);                // Draw image to RGB image
                        ImageIO.write(bufRGB, fileExt, saveFile);               // Save RGB image
                    }
                    PaintFX.setChange1(0);
                    tabPane.getTabs().get(1).setText(PaintFX.getName());        // Set Tab Name
                    PaintFX.logItem("Save", 0);                                 // Log Save
                } catch (Exception s) {}
            }
            else if(PaintFX.getSelec() == 2){
                PaintFX.redo2Clear();                                           // Clear redos
                PaintFX.recool2Clear();
                Bounds bnd = PaintFX.canvas2Peek().getBoundsInParent();         // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
                WritableImage wim = new WritableImage((int)PaintFX.canvas2Peek().getWidth(), (int)PaintFX.canvas2Peek().getHeight());
                SnapshotParameters sP = new SnapshotParameters();               // Set the snapshot viewport and set to coordinates of image
                sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
                try {
                    File saveFile = new File(PaintFX.getFileName2());           // Grab original user file
                    String file = saveFile.toURI().toString();                  // Grab file extension
                    String fileExt = file.substring(file.length()-3);
                    if(fileExt.equals("png")){                                  // If the image was a PNG
                        sP.setFill(Color.TRANSPARENT);                          // Make backgroud transparent
                        coolCrab.snapshot(sP, wim);                             // Take snapshot and save image
                        ImageIO.write(SwingFXUtils.fromFXImage(wim, null), fileExt, saveFile);
                    }
                    else{                                                       // If the image was JPG or BMP
                        coolCrab.snapshot(sP, wim);                             // Take snapshot
                        BufferedImage bufARGB = SwingFXUtils.fromFXImage(wim, null);// Convert from ARGB image to RGB image to fix colors
                        BufferedImage bufRGB = new BufferedImage(bufARGB.getWidth(), bufARGB.getHeight(), BufferedImage.OPAQUE);
                        Graphics2D graphics = bufRGB.createGraphics();          // Create graphics from picture
                        graphics.drawImage(bufARGB, 0, 0, null);                // Draw image to RGB image
                        ImageIO.write(bufRGB, fileExt, saveFile);               // Save RGB image
                    }
                    PaintFX.setChange2(0);
                    tabPane.getTabs().get(2).setText(PaintFX.getName());        // Set Tab Name
                    PaintFX.logItem("Save", 0);                                 // Log Save
                } catch (Exception s) {}
            }
            else{
                System.out.println("Save Error 1");
            }
            coolCrab.setScaleX(PaintFX.getScaleX());                            // Reset zoom
            coolCrab.setScaleY(PaintFX.getScaleY());
        
    }
    
}
