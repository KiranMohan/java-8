package chapter3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Ex12 extends Application {

	/*
	 * 12. Enhance the LatentImage class in Section 3.6, “Laziness,” on page 56,
	 * so that it supports both UnaryOperator<Color> and ColorTransformer.
	 */
	public void start(Stage stage) {

		ColorTransformer brightner = (x, y, c) -> {
			c.brighter();
			return c;
		};

		Image image = new Image("eiffel-tower.jpg");
		Image finalImage = LatentImage.from(image).transform(brightner)
				.transform(Color::grayscale).toImage();
		stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(
				finalImage))));
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(ImageDemo.class, args);
	}
}
