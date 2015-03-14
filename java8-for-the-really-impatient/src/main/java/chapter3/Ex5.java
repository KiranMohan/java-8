/**
 * file       : Ex5.java
 * author     : "Kiran Mohan"
 * created on : Mar 14, 2015
 */
package chapter3;

import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * First, implement a variant of the transform method of Section 3.3, “Choosing
a Functional Interface,” on page 50, with a ColorTransformer instead of an
UnaryOperator<Color> . Then call it with an appropriate lambda expression to put
a 10 pixel gray frame replacing the pixels on the border of an image.
 * @author "Kiran Mohan"
 *
 */
public class Ex5 extends Application {
    
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
        Image image2 = transform(image, 
           (x, y, c) -> {
               if ( x < 10 || x + 10 > image.getWidth() || y < 10 || y + 10 > image.getHeight())
                   return Color.RED;
               return c;
           });
        
        stage.setScene(new Scene(new AnchorPane(new ImageView(image2))));
        stage.show();
     }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Ex5.class, args);
    }

}
