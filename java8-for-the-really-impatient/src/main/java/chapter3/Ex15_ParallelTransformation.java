/**
 * file       : Ex15_ParallelTransformation.java
 * author     : "Kiran Mohan"
 * created on : Mar 16, 2015
 */
package chapter3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Combine the lazy evaluation of Section 3.6, “Laziness,” on page 56, with the
 * parallel evaluation of Section 3.7, “Parallelizing Operations,” on page 57.
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex15_ParallelTransformation  extends Application {

        public void start(Stage stage) {

            /* solution implementation in class Ex15_LatentImage */
            Image image = new Image("queen-mary.png");            
            Image finalImage = Ex15_LatentImage.from(image)
                                          .transform(Color::brighter)
                                          .transform(Color::grayscale)
                                          .toImage();
            stage.setScene(new Scene(new HBox(new ImageView(image),
                                              new ImageView(finalImage))));
            stage.show();
        }
        
        

        public static void main(String[] args) {
            Application.launch(Ex15_ParallelTransformation.class, args);
        }

}
