import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class CircleColorChange extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a Circle
        Circle circle = new Circle(50, Color.WHITE);
        circle.setStroke(Color.BLACK); // Outline the circle

        // Set up mouse press event (Change to Black)
        circle.setOnMousePressed((MouseEvent e) -> {
            circle.setFill(Color.BLACK);
        });

        // Set up mouse release event (Change back to White)
        circle.setOnMouseReleased((MouseEvent e) -> {
            circle.setFill(Color.WHITE);
        });

        // Add Circle to a layout pane
        StackPane root = new StackPane();
        root.getChildren().add(circle);

        // Create a Scene and place it in the Stage
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Circle Color Change");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
