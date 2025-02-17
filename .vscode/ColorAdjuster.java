import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ColorAdjuster extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a text label
        Text text = new Text("Show Colors");
        text.setFont(new Font(24));

        // Create sliders for Red, Green, Blue, and Opacity
        Slider redSlider = createSlider();
        Slider greenSlider = createSlider();
        Slider blueSlider = createSlider();
        Slider opacitySlider = createSlider();
        
        // Labels
        Label redLabel = new Label("Red");
        Label greenLabel = new Label("Green");
        Label blueLabel = new Label("Blue");
        Label opacityLabel = new Label("Opacity");

        // ChangeListener to update text color dynamically
        ChangeListener<Number> colorChangeListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                text.setFill(Color.rgb(
                    (int) redSlider.getValue(), 
                    (int) greenSlider.getValue(), 
                    (int) blueSlider.getValue(), 
                    opacitySlider.getValue() / 255.0 // Opacity in range [0,1]
                ));
            }
        };

        // Attach listeners to all sliders
        redSlider.valueProperty().addListener(colorChangeListener);
        greenSlider.valueProperty().addListener(colorChangeListener);
        blueSlider.valueProperty().addListener(colorChangeListener);
        opacitySlider.valueProperty().addListener(colorChangeListener);

        // Layout
        VBox layout = new VBox(10, text, 
            redLabel, redSlider, 
            greenLabel, greenSlider, 
            blueLabel, blueSlider, 
            opacityLabel, opacitySlider);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Scene setup
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Color Adjuster");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to create a slider (0-255 range for RGB)
    private Slider createSlider() {
        Slider slider = new Slider(0, 255, 127);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(50);
        slider.setBlockIncrement(10);
        return slider;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
