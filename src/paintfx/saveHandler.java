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
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * Event Handler to Control Saving
 * 
 * @author Alex Appel
 */
public class saveHandler implements EventHandler<ActionEvent>{
    
    private StackPane coolCrab;
    private Stage primaryStage;
    private TabPane tabPane;
    
    /**
     * Event Handler to Control Saving
     * @param coolCrab Main Program StackPane
     * @param primaryStage Main Program Stage
     * @param tabPane Main Program TabPane
     */
    public saveHandler(StackPane coolCrab, Stage primaryStage, TabPane tabPane){
        
        this.coolCrab = coolCrab;
        this.primaryStage = primaryStage;
        this.tabPane = tabPane;
        
    }
    
    /**
     * Function to Save Selected Tab
     * @param coolCrab Main Program StackPane
     * @param primaryStage Main Program Stage
     * @param tabPane Main Program TabPane
     */
    public static void save(StackPane coolCrab, Stage primaryStage, TabPane tabPane){
        if(PaintFX.getSelec() == 0){
            PaintFX.redoClear();
            Bounds bnd = PaintFX.coolPeek().getBoundsInParent();                              // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
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
            } catch (Exception s) {}
        }
        else if(PaintFX.getSelec() == 1){
            PaintFX.redo1Clear();
            Bounds bnd = PaintFX.cool1Peek().getBoundsInParent();                             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvas1Peek().getWidth(), (int)PaintFX.canvas1Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File(PaintFX.getFileName1());                // Grab original user file
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
            } catch (Exception s) {}
        }
        else if(PaintFX.getSelec() == 2){
            PaintFX.redo2Clear();
            Bounds bnd = PaintFX.cool2Peek().getBoundsInParent();                             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvas2Peek().getWidth(), (int)PaintFX.canvas2Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File(PaintFX.getFileName2());                // Grab original user file
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
            } catch (Exception s) {}
        }
        else{
            System.out.println("Save Error 0");
        }
    }
    
    /**
     * Function to Save All Tabs when PaintFX is Exited
     * @param coolCrab Main Program StackPane
     * @param primaryStage Main Program Stage
     * @param tabPane Main Program TabPane
     */
    public static void finalSave(StackPane coolCrab, Stage primaryStage, TabPane tabPane){
        if(PaintFX.getChange() == 1){
            PaintFX.redoClear();
            coolCrab.getChildren().clear();                                     // Remove previous image
            coolCrab.getChildren().addAll(PaintFX.coolPeek(), PaintFX.canvasPeek());
            Bounds bnd = PaintFX.coolPeek().getBoundsInParent();                              // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
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
            } catch (Exception s) {}
        }
        if(PaintFX.getChange1() == 1){
            PaintFX.redo1Clear();
            coolCrab.getChildren().clear();                                     // Remove previous image
            coolCrab.getChildren().addAll(PaintFX.cool1Peek(), PaintFX.canvas1Peek());
            Bounds bnd = PaintFX.cool1Peek().getBoundsInParent();                             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvas1Peek().getWidth(), (int)PaintFX.canvas1Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File(PaintFX.getFileName1());                // Grab original user file
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
            } catch (Exception s) {}
        }
        if(PaintFX.getChange2() == 1){
            PaintFX.redo2Clear();
            coolCrab.getChildren().clear();                                     // Remove previous image
            coolCrab.getChildren().addAll(PaintFX.cool2Peek(), PaintFX.canvas2Peek());
            Bounds bnd = PaintFX.cool2Peek().getBoundsInParent();                             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvas2Peek().getWidth(), (int)PaintFX.canvas2Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File(PaintFX.getFileName2());                // Grab original user file
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
            } catch (Exception s) {}
        }
    }
    
    /**
     * Function to Auto Save
     * @param coolCrab Main Program StackPane
     * @param primaryStage Main Program Stage
     */
    public static void autoSave(StackPane coolCrab, Stage primaryStage){
        if(PaintFX.getSelec() == 0){
            Bounds bnd = PaintFX.coolPeek().getBoundsInParent();                              // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvasPeek().getWidth(), (int)PaintFX.canvasPeek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File("Temp.png");                // Grab original user file
                sP.setFill(Color.TRANSPARENT);                              // Make backgroud transparent
                coolCrab.snapshot(sP, wim);                                 // Take snapshot and save image
                ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", saveFile);
            } catch (Exception s) {s.printStackTrace();}
        }
        else if(PaintFX.getSelec() == 1){
            PaintFX.redo1Clear();
            Bounds bnd = PaintFX.cool1Peek().getBoundsInParent();                             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvas1Peek().getWidth(), (int)PaintFX.canvas1Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File("Temp1.png");                // Grab original user file
                sP.setFill(Color.TRANSPARENT);                              // Make backgroud transparent
                coolCrab.snapshot(sP, wim);                                 // Take snapshot and save image
                ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", saveFile);
            } catch (Exception s) {System.out.println("Auto Save Error 2");}
        }
        else if(PaintFX.getSelec() == 2){
            PaintFX.redo2Clear();
            Bounds bnd = PaintFX.cool2Peek().getBoundsInParent();                             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvas2Peek().getWidth(), (int)PaintFX.canvas2Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            try {
                File saveFile = new File("Temp2.png");                // Grab original user file
                sP.setFill(Color.TRANSPARENT);                              // Make backgroud transparent
                coolCrab.snapshot(sP, wim);                                 // Take snapshot and save image
                ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", saveFile);
            } catch (Exception s) {System.out.println("Auto Save Error 3");}
        }
        else{
            System.out.println("Save Error Auto");
        }
    }
    
    @Override
    public void handle(ActionEvent e){
        
            if(PaintFX.getSelec() == 0){
                PaintFX.redoClear();
                Bounds bnd = PaintFX.coolPeek().getBoundsInParent();                          // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
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
                } catch (Exception s) {}
            }
            else if(PaintFX.getSelec() == 1){
                PaintFX.redo1Clear();
                Bounds bnd = PaintFX.cool1Peek().getBoundsInParent();                         // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
                WritableImage wim = new WritableImage((int)PaintFX.canvas1Peek().getWidth(), (int)PaintFX.canvas1Peek().getHeight());
                SnapshotParameters sP = new SnapshotParameters();               // Set the snapshot viewport and set to coordinates of image
                sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
                try {
                    File saveFile = new File(PaintFX.getFileName1());            // Grab original user file
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
                } catch (Exception s) {}
            }
            else if(PaintFX.getSelec() == 2){
                PaintFX.redo2Clear();
                Bounds bnd = PaintFX.cool2Peek().getBoundsInParent();                         // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
                WritableImage wim = new WritableImage((int)PaintFX.canvas2Peek().getWidth(), (int)PaintFX.canvas2Peek().getHeight());
                SnapshotParameters sP = new SnapshotParameters();               // Set the snapshot viewport and set to coordinates of image
                sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
                try {
                    File saveFile = new File(PaintFX.getFileName2());            // Grab original user file
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
                } catch (Exception s) {}
            }
            else{
                System.out.println("Save Error 1");
            }
        
    }
    
}
