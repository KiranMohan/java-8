/**
 * file       : LatentImage3.java
 * author     : "Kiran Mohan"
 * created on : Mar 16, 2015
 */
package chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * @author "Kiran Mohan"
 *
 */


class LatentImage3 {
    Image in;
    List<PixelTransformer> pendingOperations;

    public static LatentImage3 from(Image in) {
        LatentImage3 result = new LatentImage3();
        result.in = in;
        result.pendingOperations = new ArrayList<>();
        return result;
    }

    LatentImage3 transform(UnaryOperator<Color> f) {
        pendingOperations.add((x, y, reader) -> f.apply(reader.getColor(x, y)));
        return this;
    }

    LatentImage3 transform(PixelTransformer p) {
        pendingOperations.add(p);
        return this;
    }

    public Image toImage() {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        PixelReader reader = in.getPixelReader();
        for (int i = 0; i < pendingOperations.size(); ++i) {
            reader = new TransformingPixelReader(width, height, reader, pendingOperations.get(i));       
        }
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
               out.getPixelWriter().setColor(x, y, reader.getColor(x, y));
            }
        return out;
    }
}
