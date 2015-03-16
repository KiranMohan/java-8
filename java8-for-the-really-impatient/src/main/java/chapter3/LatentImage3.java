/**
 * file       : LatentImage3.java
 * author     : "Kiran Mohan"
 * created on : Mar 16, 2015
 */
package chapter3;

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
    private PixelReader reader;
    private final int width;
    private final int height;

    /**
     * @param reader
     * @param width
     * @param height
     */
    public LatentImage3(Image in) {
        super();
        this.reader = in.getPixelReader();
        this.width = (int) in.getWidth();
        this.height = (int) in.getHeight();
    }

    public static LatentImage3 from(Image in) {
        return new LatentImage3(in);
    }

    LatentImage3 transform(UnaryOperator<Color> f) {
        return transform((x, y, reader) -> f.apply(reader.getColor(x, y)));
    }

    LatentImage3 transform(PixelTransformer t) {
        reader = new TransformingPixelReader(width, height, reader, t);
        return this;
    }

    public Image toImage() {
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
               out.getPixelWriter()
                  .setColor(x, y, reader.getColor(x, y));
            }
        return out;
    }
}
