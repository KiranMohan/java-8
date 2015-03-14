/**
 * file       : Ex6.java
 * author     : "Kiran Mohan"
 * created on : Mar 14, 2015
 */
package chapter3;

import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author "Kiran Mohan"
 *
 */
public class Ex6 extends Application {
    
    /*
     * Problem solution
     */
    public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(
           width, height);
        for (int x = 0; x < width; x++)
           for (int y = 0; y < height; y++)
              out.getPixelWriter().setColor(x, y, 
                 f.apply(in.getPixelReader().getColor(x, y), arg));
        return out;
     }

    public static Image transform(Image in, UnaryOperator<Color> f) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(
           width, height);
        for (int x = 0; x < width; x++)
           for (int y = 0; y < height; y++)
              out.getPixelWriter().setColor(x, y, 
                 f.apply(in.getPixelReader().getColor(x, y)));
        return out;
     }
    
     public void start(Stage stage) {
        Image image = new Image("queen-mary.png");
        Image brightenedImage1 = transform(image, (c, factor) -> c.deriveColor(0, 1, factor, 1), 1.2);
        Image brightenedImage2 = transform(image, Color::brighter);
        
        stage.setScene(new Scene(
                           new HBox(
                               new ImageView(image), 
                               new ImageView(brightenedImage1), 
                               new ImageView(brightenedImage2))));
        stage.show();
     }
    /**
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Ex6.class, args);

    }

}
