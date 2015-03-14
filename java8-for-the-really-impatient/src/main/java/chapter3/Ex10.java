/**
 * file       : Ex10.java
 * author     : "Kiran Mohan"
 * created on : Mar 15, 2015
 */
package chapter3;

import java.util.function.Function;
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
public class Ex10 {
    
    /* UnaryOperator.compose() returns a Function<T,R> not a UnaryOperator<T> */
    public static Image transform(Image in, Function<Color, Color> f) {
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
        UnaryOperator<Color> op = Color::brighter;
        Image brightenedImage = transform(image, op.compose(Color::grayscale));
//        Image brightenedImage = transform(image, Color::grayscale);
        
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(brightenedImage))));
        stage.show();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Ex8_ImageFrame.class, args);
    }


}
