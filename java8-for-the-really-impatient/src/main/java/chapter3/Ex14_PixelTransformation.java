/**
 * file       : Ex14_PixelTransformation.java
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
 * To deal with lazy evaluation on a per-pixel basis, change the transformers so
 * that they are passed a PixelReader object from which they can read other
 * pixels in the image. For example, (x, y, reader) -> reader.get(width - x, y)
 * is a mirroring operation. The convolution filters from the preceding
 * exercises can be easily implemented in terms of such a reader. The
 * straightforward operations would simply have the form (x, y, reader) ->
 * reader.get(x, y).grayscale() , and you can provide an adapter from
 * UnaryOperation<Color> . A PixelReader is at a particular level in the
 * pipeline of operations. Keep a cache of recently read pixels at each level in
 * the pipeline. If a reader is asked for a pixel, it looks in the cache (or in
 * the original image at level 0); if that fails, it constructs a reader that
 * asks the previous transform.
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex14_PixelTransformation extends Application {

    public static PixelTransformer blur(Image image) {
        return (x, y, reader) -> {
            int w = (int) image.getWidth();
            int h = (int) image.getHeight();
            double red = 0.0, blue = 0.0, green = 0.0;
            for (int i = -1; i < 2; ++i) {
                for (int j = -1; j < 2; ++j) {
                    if (0 < x + i && x + i < w && 0 < y + j && y + j < h) {
                        Color cell = reader.getColor(x + i, y + j);
                        red   += cell.getRed();
                        blue  += cell.getBlue();
                        green += cell.getGreen();
                    }
                }
            }
            Color c = Color.color(red / 9, blue / 9, green / 9);
            return c;
        };
    }
    
    public static PixelTransformer edgeDetect(Image image) {
        return (x, y, reader) -> {
            Color c = reader.getColor(x, y);
            Color n = 0 <= y - 1                ? reader.getColor(x, y - 1) : Color.BLACK;
            Color e = x + 1 < image.getWidth()  ? reader.getColor(x + 1, y) : Color.BLACK;
            Color w = 0 <= x - 1                ? reader.getColor(x - 1, y) : Color.BLACK;
            Color s = y + 1 < image.getHeight() ? reader.getColor(x, y + 1) : Color.BLACK;
            double red   = 4 * c.getRed()   - n.getRed()   - e.getRed()   - w.getRed()   - s.getRed(), 
                   blue  = 4 * c.getBlue()  - n.getBlue()  - e.getBlue()  - w.getBlue()  - s.getBlue(), 
                   green = 4 * c.getGreen() - n.getGreen() - e.getGreen() - w.getGreen() - s.getGreen();

            c = Color.color(red   < 0.0 ? 0.0 : 1.0 < red   ? 1.0 : red, 
                            blue  < 0.0 ? 0.0 : 1.0 < blue  ? 1.0 : blue,
                            green < 0.0 ? 0.0 : 1.0 < green ? 1.0 : green);

            return c;
        };
    }

    public void start(Stage stage) {

        Image image = new Image("queen-mary.png");
        // the actual solution for this question is in 
        // class LatentImage3 and class TransformingPixelReader 
        Image finalImage = LatentImage3.from(image)
                                       .transform(Color::brighter)
                                       .transform(blur(image))
                                       .transform(edgeDetect(image))
                                       .transform(Color::grayscale)
                                       .toImage();
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(finalImage))));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(Ex14_PixelTransformation.class, args);
    }
}

