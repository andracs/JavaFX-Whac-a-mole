/*
 *  Developed by András Ács (acsandras@gmail.com)
 *  Erhvervsakademi Sjælland / www.easj.dk
 *  Licensed under the WFTPL License - http://www.wtfpl.net/txt/copying/ 
 *  2017
 *
 */
package whacamole;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 *
 * @author andrasacs
 */
public class GameBoardController implements Initializable {

    // Not related to the FXML template
    private static int MOLE_CYCLES = 25;
    private static int MOLE_DISPLAY_DURATION = 2000;
    private Image image;
    private ImageView imageView;
    private ArrayList<ImageView> imageList;
    private int score = 0;

    // FXMLLoader will inject the gridPane instance into the instance variable 
    // found on the controller instance, if:
    // 1) The instance variable has an @FXML annotation,
    // 2) The name of the variable exactly matches the value of the fx:id
    @FXML
    private GridPane gridPane;

    @FXML
    private Button button;

    @FXML
    private Label label, scoreLabel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void startGame(ActionEvent event) {

        System.out.println("Game started!");

        score = 0;
        scoreLabel.setText(String.valueOf(score));

        refereshMoles(); // First refereshing the GameBoard

        // Setting up a timer
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(MOLE_DISPLAY_DURATION),
                ae -> refereshMoles()));
        timeline.setCycleCount(MOLE_CYCLES);
        timeline.play();
        timeline.setOnFinished(new EventHandler<ActionEvent>() {

            // When finished with the all game cycles, hide the moles, 
            // and show the "play again" button
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Finished!");
                for (ImageView moleImage : imageList) {
                    moleImage.setVisible(false);
                }
                button.setVisible(true);
            }
        });

    }
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Img from https://koenig-media.raywenderlich.com/uploads/2013/09/WhackAMole1.jpg

        //label.setText("Hello World!");
        //Users/andrasacs/NetBeansProjects/WhacAMole/src/assets
        image = new Image("file:./src/assets/Mole.jpg");

        imageList = new ArrayList();

        for (int i = 0; i < 9; i++) {

            imageView = new ImageView(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.setImage(image);
            imageView.applyCss();
            imageView.setVisible(true);
            imageList.add(imageView);
            int row = i / 3;
            int column = i % 3;
            // System.out.println("Row " + row + " Column " + column);
            gridPane.add(imageView, row, column);
            gridPane.setHalignment(imageView, HPos.CENTER);

            //Creating the mouse event handler 
            EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
                System.out.println("Clicked");
                //imageView.setVisible(false);
                //System.out.println(gridPane.getChildren().toString());
                ImageView iv = (ImageView) e.getSource();
                iv.setVisible(false);
                score++;
                //System.out.println(score);
                scoreLabel.setText(String.valueOf(score));

            };
            //Adding event Filter 
            imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        }

        button = new Button("Play again");
        button.setOnAction((event) -> this.startGame(event));
        //gridPane.add(button, 1, 1);
        button.setLayoutX(200);
        button.setLayoutY(340);
        anchorPane.getChildren().add(button);
        gridPane.setHalignment(button, HPos.CENTER);
        button.setVisible(false);

        //scoreLabel.textProperty().bind(new SimpleIntegerProperty(score).asString());
    }

    private void refereshMoles() {
        System.out.println("Gameboard refreshed");
        // hide all moles
        //   Node node = gridPane.getChildren().get(0);
        for (ImageView moleImage : imageList) {
            moleImage.setVisible(false);
        }

        // reveal some  
        Random randomGenerator = new Random();
        int someMoles = randomGenerator.nextInt(7) + 1;
        for (int i = 0; i < someMoles; i++) {
            int whichMole = randomGenerator.nextInt(9);
            imageList.get(whichMole).setVisible(true);
        }

    }
    
        // Called from the main menu
    public void quitApplication() {
        Platform.exit();
    }
}
