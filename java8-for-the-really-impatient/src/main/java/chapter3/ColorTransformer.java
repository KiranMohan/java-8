package chapter3;
import javafx.scene.paint.Color;


@FunctionalInterface
public interface ColorTransformer {
   Color apply(int x, int y, Color colorAtXY);
}