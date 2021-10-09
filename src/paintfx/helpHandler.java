package PaintFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Event Handler to Open the Help Window
 * 
 * @author Alex Appel
 */
public class helpHandler implements EventHandler<ActionEvent>{
    
    public void handle(ActionEvent e){
        
        Stage aboutWindow = new Stage();                                        // Create new window
            aboutWindow.setTitle("Help");                                       // Set Title and Logo
            aboutWindow.getIcons().add(new Image(PaintFX.class.getResourceAsStream("logosmall.png")));
            
            GridPane help = new GridPane();                                     // Create grid
            help.setAlignment(Pos.CENTER);                                      // Center the grid
            help.setHgap(10);                                                   // Set the gaps between grid elements to 10
            help.setVgap(10);
            help.setPadding(new Insets(25, 25, 25, 25));                        // Set border padding to 25 
            
            ScrollPane spHelp = new ScrollPane();                               // Create new scroll pane
            
            Label helpVar = new Label("Help");                                  // Create a new label "Help"
            helpVar.setFont(new Font("Arial", 36));                             // Set Font and Size
            help.add(helpVar, 0, 0);                                            // Add label to top
            
            Text info = new Text(                                               // Help File
                " - Open an Image: To open an image, select File->Open... or CTRL-O, and select your image from the File Explorer Window.\n" +
                "   Supported formats are: PNG, JPG, BMP, and GIF.\n" +
                "\n" +
                " - Save an Image: To save the current image with changes to the original location, choose File->Save or CTRL-S.\n" + 
                "   Note: This will override the original image. Supported Save formats are: PNG, JPG, and BMP.\n" +
                "\n" +
                " - Save an Image as: To save the current image as a different format and/or change the name, choose File->Save as... or CTRL-A.\n" +
                "   This will then put the new image in the window. Supported Save formats are: PNG, JPG, and BMP.\n" +
                "\n" +
                " - Exit the Program: To exit the program, click the red X in the top right corner, CTRL-X, or press File->Exit.\n" +
                "\n" +
                " - To clear the current canvas: Press Draw->Clear Canvas or CTRL-C.\n" +
                "\n" +
                " - To snap a large image to fit the window: Press Edit->Snap to Fit or CTRL-F. This will clear the current canvas.\n" +
                "\n" +
                " - To Show Drawing Tools: Press Draw->Toggle Tools or CTRL-T.\n" +
                "\n" +
                " - In order to draw a shape, once tools are visible, Choose a Color, Enter a Width, and Select the shape.\n" +
                "   This will allow to you draw one shape. In order to draw another, press the shape again.\n" +
                "\n" +
                " - To enable fill, click the Fill Shape textbox under the toolbar and select the color.\n" +
                "\n" +
                " - To write text, enter your desired text in the box and press the text button. Width will adjust font size\n" +
                "\n" +
                " - To manually resize the canvas, toggle the option under Edit and enter a size.\n" +
                "\n" +
                " - To zoom in or out, use the buttons under Edit or CTRL-Plus or Minus.\n" +
                "\n" +
                " - To Cut and Move part of an Image, Select Draw->Cut and Move, or press CTRL-M.\n" +
                "   Drag the rectangle over the part of the image you want to move, then drag the Cut section to the desired location.\n" +
                "\n" +
                " - To Select a Color on the canvas to draw with, select the color dropper from below either the Edge or Fill Color Dialog\n" +
                "   Then, click on the selection you want to pick the color from. Untoggle the button to stop using this color.\n" +
                "\n" +
                " - To Undo an action, select Edit->Undo, or press CTRL-Z.\n" + 
                "\n" +
                " - To Redo an action, select Edit->Redo, or press CTRL-Y. Note: If an action is performed after Undoing an action, Redo will be reset.\n" +
                "\n" +
                " - To Change Auto Save Settings, Press File->Auto Save Settings, or CTRL-P.\n");
            info.setFont(new Font("Arial", 18));                                // Set Font and Size
            info.setTextAlignment(TextAlignment.LEFT);                          // Align left
            spHelp.setContent(info);                                            // Display Text in scroll pane
            help.add(spHelp, 0, 1);                                             // Add text to middle
            
            Scene aboutScene = new Scene(help, 640, 480);                       // Create a 640 x 480 window
            aboutWindow.setScene(aboutScene);                                   // Place the scene on the about stage
            aboutWindow.show();
            
            Button btn = new Button("Close");                                   // Create a button saying "Close"
            HBox hbBtn = new HBox(10);                                          // Create a horizontal box for button
            hbBtn.setAlignment(Pos.CENTER);                                     // Align horizontal box to bottom center
            hbBtn.getChildren().add(btn);                                       // Place the button in the horizontal box
            help.add(hbBtn, 0, 2);                                              // Place the horizontal box on the bottom grid box

            btn.setOnAction(new EventHandler<ActionEvent>() {                   // When Close button pressed

                @Override
                public void handle(ActionEvent a) {
                    aboutWindow.close();                                        // Close Help Window
                    }
            });
        
    }
    
}
