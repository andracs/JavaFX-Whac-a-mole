/*
 *  Developed by András Ács (acsandras@gmail.com)
 *  Erhvervsakademi Sjælland / www.easj.dk
 *  Licensed under the WFTPL License - http://www.wtfpl.net/txt/copying/ 
 *  2017
 *  
 *  Opgavebeskrivelse: https://goo.gl/DS4oLg 
 *
 *  About SceneBuilder 
 *  https://blogs.oracle.com/jmxetc/entry/connecting_scenebuilder_edited_fxml_to
 *
 *  About timers in JavaFX 
 *  http://tomasmikula.github.io/blog/2014/06/04/timers-in-javafx-and-reactfx.html
 *  
 *  Event handling in JavaFX
 *  http://code.makery.ch/blog/javafx-8-event-handling-examples/
 *  https://www.tutorialspoint.com/javafx/javafx_event_handling.htm
 *
 */
package whacamole;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author andrasacs 
 */
public class WhacAMole extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GameBoard.fxml"));

        Scene scene = new Scene(root);
        
        // Using a hammer as a cursor on the hwole scene
        Image image = new Image("file:./src/assets/Hammer.png");  
        ImageCursor cursor = new ImageCursor(image);
        scene.setCursor(cursor);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}