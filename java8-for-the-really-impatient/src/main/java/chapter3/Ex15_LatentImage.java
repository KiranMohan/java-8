package chapter3;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class Ex15_LatentImage {
   private Image in;
   private List<UnaryOperator<Color>> pendingOperations;

   public static Ex15_LatentImage from(Image in) {
      Ex15_LatentImage result = new Ex15_LatentImage();
      result.in = in;
      result.pendingOperations = new ArrayList<>();
      return result;
   }

   Ex15_LatentImage transform(UnaryOperator<Color> f) {
      pendingOperations.add(f);
      return this;
   }

   public Image toImage() {
      int width = (int) in.getWidth();
      int height = (int) in.getHeight();
      Color[][] c = new Color[width][height];
      for (int x = 0; x < width; x++)
         for (int y = 0; y < height; y++) {
            c[x][y] = in.getPixelReader().getColor(x, y);
         }
      for (UnaryOperator<Color> f : pendingOperations) 
          	c = parallelTransform(c, f);
      WritableImage out = new WritableImage(width, height);
      for (int x = 0; x < width; x++)
          for (int y = 0; y < height; y++) 
              out.getPixelWriter().setColor(x, y, c[x][y]);
      return out;
   }
   
   public static Color[][] parallelTransform(Color[][] in, UnaryOperator<Color> f) {
       int n = Runtime.getRuntime().availableProcessors();
       int height = in.length;
       int width = in[0].length;
       Color[][] out = new Color[height][width];
       try {
           ExecutorService pool = Executors.newCachedThreadPool();
           for (int i = 0; i < n; i++) {
               int fromY = i * height / n;
               int toY = (i + 1) * height / n;
               pool.submit(() -> {
                   for (int x = 0; x < width; x++)
                       for (int y = fromY; y < toY; y++)
                           out[y][x] = f.apply(in[y][x]);
               });
           }
           pool.shutdown();
           pool.awaitTermination(1, TimeUnit.HOURS);
       } catch (InterruptedException ex) {
           ex.printStackTrace();
       }
       return out;
   }
}