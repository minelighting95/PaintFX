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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * Event Handler to Control Save As
 * 
 * @author Alex Appel
 */
public class saveAsHandler implements EventHandler<ActionEvent>{
    
    private StackPane coolCrab;
    private Stage primaryStage;
    private int change;
    private TabPane tabPane;
    
/**
     * Event Handler to Control Save As
     * @param coolCrab Main Program StackPane
     * @param primaryStage Main Program Stage
     * @param tabPane Main Program TabPane
     */
    public saveAsHandler(StackPane coolCrab, Stage primaryStage, TabPane tabPane){
        
        this.coolCrab = coolCrab;
        this.primaryStage = primaryStage;
        this.tabPane = tabPane;
        
    }
    
    @Override
    public void handle(ActionEvent e){
        if(PaintFX.getSelec() == 0){
            PaintFX.redoClear();
            Bounds bnd = PaintFX.coolPeek().getBoundsInParent();                              // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvasPeek().getWidth(), (int)PaintFX.canvasPeek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            FileChooser saveChooser = new FileChooser();                        // Create file chooser
            saveChooser.setTitle("Save Picture As...");                         // Named Save Picture As
            saveChooser.setInitialFileName("untilted");                         // Default the name to untitled
            saveChooser.getExtensionFilters().addAll(                           // Add image filters
                    new FileChooser.ExtensionFilter("PNG Images", "*.png")
                    ,new FileChooser.ExtensionFilter("JPG Images", "*.jpg")
                    ,new FileChooser.ExtensionFilter("BitMap Images", "*.bmp")
                );
            
            try {
                File saveFile = saveChooser.showSaveDialog(primaryStage);       // Grab user selected file and path
                String file = saveFile.toURI().toString();                      // Grab file extension
                PaintFX.setFileName(file.substring(6));
                tabPane.getTabs().get(0).setText(saveFile.getName());
                String fileExt = file.substring(file.length()-3);
                if(fileExt.equals("png")){                                      // If the image is a PNG
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
                tabPane.getTabs().get(0).setText(saveFile.getName());           // Set Tab Name
            } catch (Exception s) {}
        }
        else if(PaintFX.getSelec() == 1){
            PaintFX.redo1Clear();
            Bounds bnd = PaintFX.cool1Peek().getBoundsInParent();                             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvas1Peek().getWidth(), (int)PaintFX.canvas1Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            FileChooser saveChooser = new FileChooser();                        // Create file chooser
            saveChooser.setTitle("Save Picture As...");                         // Named Save Picture As
            saveChooser.setInitialFileName("untilted");                         // Default the name to untitled
            saveChooser.getExtensionFilters().addAll(                           // Add image filters
                    new FileChooser.ExtensionFilter("PNG Images", "*.png")
                    ,new FileChooser.ExtensionFilter("JPG Images", "*.jpg")
                    ,new FileChooser.ExtensionFilter("BitMap Images", "*.bmp")
                );
            
            try {
                File saveFile = saveChooser.showSaveDialog(primaryStage);       // Grab user selected file and path
                String file = saveFile.toURI().toString();                      // Grab file extension
                PaintFX.setFileName(file.substring(6));
                tabPane.getTabs().get(1).setText(saveFile.getName());
                String fileExt = file.substring(file.length()-3);
                if(fileExt.equals("png")){                                      // If the image is a PNG
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
                tabPane.getTabs().get(1).setText(saveFile.getName());           // Set Tab Name
            } catch (Exception s) {}
        }
        else if(PaintFX.getSelec() == 2){
            PaintFX.redo2Clear();
            Bounds bnd = PaintFX.cool2Peek().getBoundsInParent();                             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)PaintFX.canvas2Peek().getWidth(), (int)PaintFX.canvas2Peek().getHeight());
            SnapshotParameters sP = new SnapshotParameters();                   // Set the snapshot viewport and set to coordinates of image
            sP.setViewport(new Rectangle2D(bnd.getMinX(), bnd.getMinY(), bnd.getMaxX(), bnd.getMaxY()));
            
            FileChooser saveChooser = new FileChooser();                        // Create file chooser
            saveChooser.setTitle("Save Picture As...");                         // Named Save Picture As
            saveChooser.setInitialFileName("untilted");                         // Default the name to untitled
            saveChooser.getExtensionFilters().addAll(                           // Add image filters
                    new FileChooser.ExtensionFilter("PNG Images", "*.png")
                    ,new FileChooser.ExtensionFilter("JPG Images", "*.jpg")
                    ,new FileChooser.ExtensionFilter("BitMap Images", "*.bmp")
                );
            
            try {
                File saveFile = saveChooser.showSaveDialog(primaryStage);       // Grab user selected file and path
                String file = saveFile.toURI().toString();                      // Grab file extension
                PaintFX.setFileName(file.substring(6));
                tabPane.getTabs().get(2).setText(saveFile.getName());
                String fileExt = file.substring(file.length()-3);
                if(fileExt.equals("png")){                                      // If the image is a PNG
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
                tabPane.getTabs().get(2).setText(saveFile.getName());           // Set Tab Name
            } catch (Exception s) {}
        }
        else{
            System.out.println("Save As Error 0");
        }
        
    }
    
}
