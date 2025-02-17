import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GridPaneImage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a GridPane
        GridPane gridPane = new GridPane();

        // Load images
        Image img1 = new Image("file:germany.png");  
        Image img2 = new Image("file:china.png");
        Image img3 = new Image("file:france.png");
        Image img4 = new Image("file:usa.png");

        // Create ImageViews
        ImageView imgView1 = new ImageView(img1);
        ImageView imgView2 = new ImageView(img2);
        ImageView imgView3 = new ImageView(img3);
        ImageView imgView4 = new ImageView(img4);

        // Set preferred sizes
        imgView1.setFitWidth(200);
        imgView1.setFitHeight(150);
        imgView2.setFitWidth(200);
        imgView2.setFitHeight(150);
        imgView3.setFitWidth(200);
        imgView3.setFitHeight(150);
        imgView4.setFitWidth(200);
        imgView4.setFitHeight(150);

        // Add ImageViews to GridPane (row, column)
        gridPane.add(imgView1, 0, 0);
        gridPane.add(imgView2, 1, 0);
        gridPane.add(imgView3, 0, 1);
        gridPane.add(imgView4, 1, 1);

        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setTitle("Grid Pane Images");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
