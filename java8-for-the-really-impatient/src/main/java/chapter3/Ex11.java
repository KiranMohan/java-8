/**
 * file       : Ex11.java
 * author     : "Kiran Mohan"
 * created on : Mar 15, 2015
 */
package chapter3;

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
 * Implement static methods that can compose two ColorTransformer objects, and a
 * static method that turns a UnaryOperator<Color> into a ColorTransformer that
 * ignores the x- and y-coordinates. Then use these methods to add a gray
 * frame to a brightened image. (See Exercise 5 for the gray frame.)
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex11 extends Application {
    
    /**
     * turns a UnaryOperator<Color> into a ColorTransformer that ignores the x- and y-coordinates
     * @param operator
     * @return
     */
    public static ColorTransformer unaryOperatorToColorTransformer(UnaryOperator<Color> operator) {
        return (x, y, c) -> operator.apply(c);
    }
    
    /**
     * static methods that can compose two ColorTransformer objects
     * @param t1
     * @param t2
     * @return
     */
    public static ColorTransformer compose(ColorTransformer t1, ColorTransformer t2) {
        return (x, y, c) -> t2.apply(x, y, t1.apply(x, y, c));
    }
    
    
    public static Image transform(Image in, ColorTransformer f) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                out.getPixelWriter()
                   .setColor(x, y, f.apply(x, y, in.getPixelReader().getColor(x, y)));
        return out;
    }

    public void start(Stage stage) {
        Image image = new Image("queen-mary.png");
        ColorTransformer brightner = unaryOperatorToColorTransformer(Color::brighter);
        ColorTransformer imageFrame = (x, y, c) -> {
            if (x < 10 || x + 10 > image.getWidth() || y < 10 || y + 10 > image.getHeight())
                return Color.RED;
            return c;
        };

        Image image2 = transform(image, compose(brightner, imageFrame));

        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(image2))));
        stage.show();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Ex11.class, args);
    }


}
