/**
 * file       : MyPixelReader.java
 * author     : "Kiran Mohan"
 * created on : Mar 16, 2015
 */
package chapter3;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

class TransformingPixelReader implements javafx.scene.image.PixelReader {
    private final javafx.scene.image.PixelReader origReader;
    private Color[][] color;
    private final PixelTransformer transformer;

    public TransformingPixelReader(
            final int width, 
            final int height, 
            final PixelReader parentPixelReader, 
            final PixelTransformer transformer) {
        this.origReader = parentPixelReader;
        color = new Color[width][height];
        this.transformer = transformer;
    }

    /* (non-Javadoc)
     * @see javafx.scene.image.PixelReader#getColor()
     */
    @Override
    public Color getColor(int x, int y) {
        if (color[x][y] == null) {
            color[x][y] = transformer.apply(x,y,origReader);
        }
        return color[x][y];
    }
    


    /* (non-Javadoc)
     * @see javafx.scene.image.PixelReader#getArgb(int, int)
     */
    @Override
    public int getArgb(int x, int y) {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see javafx.scene.image.PixelReader#getPixels(int, int, int, int, javafx.scene.image.WritablePixelFormat, java.nio.Buffer, int)
     */
    @Override
    public <T extends Buffer> void getPixels(int x, int y, int w, int h, WritablePixelFormat<T> pixelformat, T buffer,
            int scanlineStride) {
        throw new UnsupportedOperationException(); 
    }

    /* (non-Javadoc)
     * @see javafx.scene.image.PixelReader#getPixels(int, int, int, int, javafx.scene.image.WritablePixelFormat, byte[], int, int)
     */
    @Override
    public void getPixels(int x, int y, int w, int h, WritablePixelFormat<ByteBuffer> pixelformat, byte[] buffer,
            int offset, int scanlineStride) {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see javafx.scene.image.PixelReader#getPixels(int, int, int, int, javafx.scene.image.WritablePixelFormat, int[], int, int)
     */
    @Override
    public void getPixels(int x, int y, int w, int h, WritablePixelFormat<IntBuffer> pixelformat, int[] buffer,
            int offset, int scanlineStride) {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see javafx.scene.image.PixelReader#getPixelFormat()
     */
    @Override
    public PixelFormat getPixelFormat() {
        throw new UnsupportedOperationException();
    }
    
}