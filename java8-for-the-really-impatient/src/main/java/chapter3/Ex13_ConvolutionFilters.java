/**
 * file       : Ex13_ConvolutionFilters.java
 * author     : "Kiran Mohan"
 * created on : Mar 15, 2015
 */
package chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
 * Convolution filters such as blur or edge detection compute a pixel from
 * neighboring pixels. To blur an image, replace each color value by the average
 * of itself and its eight neighbors. For edge detection, replace each color
 * value c with 4c – n – e – s – w, where the other colors are those of the
 * pixel to the north, east, south, and west. Note that these cannot be
 * implemented lazily, using the approach of Section 3.6, “Laziness,” on page
 * 56, since they require the image from the previous stage (or at least the
 * neighboring pixels) to have been computed. Enhance the lazy image processing
 * to deal with these operations. Force computation of the previous stage when
 * one of these operators is evaluated.
 * 
 * @author "Kiran Mohan"
 *
 */
public class Ex13_ConvolutionFilters extends Application {

    public static ColorTransformer blur(Image image) {
        return (x, y, c) -> {
            int w = (int) image.getWidth();
            int h = (int) image.getHeight();
            double red = 0.0, blue = 0.0, green = 0.0;
            for (int i = -1; i < 2; ++i) {
                for (int j = -1 ; j < 2; ++j ) {
                    if (0 < x+i && x+i < w && 0 < y+j && y+j < h) {
                        Color cell = image.getPixelReader().getColor(x+i, y+j); 
                        red   += cell.getRed();
                        blue  += cell.getBlue();
                        green += cell.getGreen();
                    }
                }
            }
            c = Color.color(red/9, blue/9, green/9);
            return c;      
        };
    }
    
    public static ColorTransformer edgeDetect(Image image) {
        return (x, y, c) -> {
            Color n = 0 <= y-1               ? image.getPixelReader().getColor(x, y-1) : Color.BLACK;
            Color e = x+1 < image.getWidth() ? image.getPixelReader().getColor(x+1, y) : Color.BLACK;
            Color w = 0 <= x-1               ? image.getPixelReader().getColor(x-1, y) : Color.BLACK;
            Color s = y+1 < image.getHeight()? image.getPixelReader().getColor(x, y+1) : Color.BLACK;
            double red   = 4 * c.getRed()   - n.getRed()   - e.getRed()  - w.getRed()  - s.getRed(), 
                   blue  = 4 * c.getBlue()  - n.getBlue()  - e.getBlue() - w.getBlue() - s.getBlue(), 
                   green = 4 * c.getGreen() - n.getGreen() - e.getGreen()- w.getGreen()- s.getGreen();
           
            c = Color.color(red   < 0.0 ? 0.0 : 1.0 < red   ? 1.0 : red,
                            blue  < 0.0 ? 0.0 : 1.0 < blue  ? 1.0 : blue,
                            green < 0.0 ? 0.0 : 1.0 < green ? 1.0 : green);

            return c;
        };
    }
    

    public void start(Stage stage) {

        Image image = new Image("queen-mary.png");
        Image finalImage = LatentImage2.from(image)
                                       .transform(Color::brighter)
                                       .transformEagerly(blur(image))
                                       .transform(Color::grayscale)
                                       .transformEagerly(edgeDetect(image))
                                       .toImage();
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(finalImage))));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(Ex13_ConvolutionFilters.class, args);
    }
}

class LatentImage2 {
    private Image in;
    private List<List<ColorTransformer>> pendingOperations;

    public static LatentImage2 from(Image in) {
        LatentImage2 result = new LatentImage2();
        result.in = in;
        result.pendingOperations = new ArrayList<>();
        result.pendingOperations.add(new ArrayList<>());
        return result;
    }

    LatentImage2 transform(UnaryOperator<Color> f) {
        pendingOperations.get(pendingOperations.size() -1).add((x, y, c) -> f.apply(c));
        return this;
    }

    LatentImage2 transform(ColorTransformer c) {
        pendingOperations.get(pendingOperations.size() -1).add(c);
        return this;
    }
    
    LatentImage2 transformEagerly(ColorTransformer c) {
        // add eager operation in a separate list of it own
        if (pendingOperations.get(pendingOperations.size() -1).size() == 0)
            pendingOperations.get(pendingOperations.size() -1).add(c);
        else 
            pendingOperations.add(Arrays.asList(c)); 
        
        // add an empty list for the next set of operations
        pendingOperations.add(new ArrayList<ColorTransformer>());
        return this;
    }
    
    public Image toImage() {
        Image image = in;
        // the last list of operations can be empty. if so, remove it.
        if (pendingOperations.get(pendingOperations.size() -1).size() == 0) {
            pendingOperations.remove(pendingOperations.size() -1);
        }
        for (List<ColorTransformer> executeOperations : pendingOperations) {
            image = applyTransforms(image, executeOperations);
        }
        return image;
    }

    private Image applyTransforms(Image image, List<ColorTransformer> executeOperations) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                Color c = image.getPixelReader().getColor(x, y);
                for (ColorTransformer f : executeOperations)
                    c = f.apply(x, y, c);
                out.getPixelWriter().setColor(x, y, c);
            }
        return out;
    }
}