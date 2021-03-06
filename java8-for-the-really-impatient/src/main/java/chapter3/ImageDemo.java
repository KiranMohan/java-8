package chapter3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImageDemo extends Application {
   public void start(Stage stage) {
      Image image = new Image("eiffel-tower.jpg");
      Image finalImage = LatentImage.from(image)
         .transform(Color::brighter).transform(Color::grayscale)
         .toImage();      
      stage.setScene(new Scene(new HBox(
         new ImageView(image),
         new ImageView(finalImage))));
      stage.show();
   }
   
   public static void main(String[] args) {
	Application.launch(ImageDemo.class, args);
   }
}
