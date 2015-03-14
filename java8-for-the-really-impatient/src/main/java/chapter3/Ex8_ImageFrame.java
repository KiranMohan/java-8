/**
 * file       : Ex5.java
 * author     : "Kiran Mohan"
 * created on : Mar 14, 2015
 */
package chapter3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Generalize Exercise 5 by writing a static method that yields a
 * ColorTransformer that adds a frame of arbitrary thickness and color to an
 * image
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex8_ImageFrame extends Application {
    
    public static ColorTransformer addImageFrame(double origWidth, double origHeight, int frameSize, Color color) {
        return (x, y, c) -> {
            if (x < frameSize 
             || x + frameSize > origWidth 
             || y < frameSize 
             || y + frameSize > origHeight)
                return color;
            return c;
        };
    }

    public static Image transform(Image in, ColorTransformer f) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                out.getPixelWriter().setColor(x, y, f.apply(x, y, in.getPixelReader().getColor(x, y)));
        return out;
    }

    public void start(Stage stage) {
        Image image = new Image("queen-mary.png");
        Image image2 = transform(image, addImageFrame(image.getWidth(), image.getHeight(), 5, Color.BEIGE));

        stage.setScene(new Scene(new AnchorPane(new ImageView(image2))));
        stage.show();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Ex8_ImageFrame.class, args);
    }

}
