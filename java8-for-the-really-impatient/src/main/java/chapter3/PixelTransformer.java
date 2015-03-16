/**
 * file       : PixelTransformer.java
 * author     : "Kiran Mohan"
 * created on : Mar 16, 2015
 */
package chapter3;

import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

@FunctionalInterface
interface PixelTransformer {
    Color apply(int x, int y, PixelReader reader);
}