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
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class saveAsHandler implements EventHandler<ActionEvent>{
    
    public ImageView cool;
    public ImageView cool1;
    public ImageView cool2;
    public StackPane coolCrab;
    public Canvas canvas;
    public Canvas canvas1;
    public Canvas canvas2;
    public Stage primaryStage;
    public int change;
    public TabPane tabPane;
    
    public saveAsHandler(ImageView cool, ImageView cool1, ImageView cool2, StackPane coolCrab, Canvas canvas, Canvas canvas1, Canvas canvas2, Stage primaryStage, TabPane tabPane){
        
        this.cool = cool;
        this.cool1 = cool1;
        this.cool2 = cool2;
        this.coolCrab = coolCrab;
        this.canvas = canvas;
        this.canvas1 = canvas1;
        this.canvas2 = canvas2;
        this.primaryStage = primaryStage;
        this.tabPane = tabPane;
        
    }
    
    @Override
    public void handle(ActionEvent e){
        if(PaintFX.getSelec() == 0){
            Bounds bnd = cool.getBoundsInParent();                              // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight());
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
            Bounds bnd = cool1.getBoundsInParent();                             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)canvas1.getWidth(), (int)canvas1.getHeight());
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
            Bounds bnd = cool2.getBoundsInParent();                             // Aquire bounds of image with respect to parent and make writeable image the size of the canvas
            WritableImage wim = new WritableImage((int)canvas2.getWidth(), (int)canvas2.getHeight());
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
