import java.util.ArrayList;
import java.util.List;
//User class
class User {
    private String name;
    private int age;
    private double weight;
    private double height;
    private int goalCalories;
    private int goalSteps;
    private List<Workout> workoutHistory;

    public User(String name, int age, double weight, double height, int goalCalories, int goalSteps) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.goalCalories = goalCalories;
        this.goalSteps = goalSteps;
        this.workoutHistory = new ArrayList<>();
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public int getGoalCalories() {
        return goalCalories;
    }

    public int getGoalSteps() {
        return goalSteps;
    }

    public List<Workout> getWorkoutHistory() {
        return workoutHistory;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setGoalCalories(int goalCalories) {
        this.goalCalories = goalCalories;
    }

    public void setGoalSteps(int goalSteps) {
        this.goalSteps = goalSteps;
    }

    public void addWorkout(Workout workout) {
        workoutHistory.add(workout);
    }

    public void viewProgress() {
        System.out.println("User Progress:");
        for (Workout workout : workoutHistory) {
            System.out.println(workout);
        }
    }

    public double calculateBMI() {
        return weight / (height * height);
    }
}

import java.time.LocalDate;
//Workout class
class Workout {
    private String workoutType;
    private int duration;
    int caloriesBurned;
    private LocalDate dateLogged;

    public Workout(String workoutType, int duration, int caloriesBurned, LocalDate dateLogged) {
        this.workoutType = workoutType;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.dateLogged = dateLogged;
    }

    public String toString() {
        return "Workout: " + workoutType + ", Duration: " + duration + " minutes, Calories Burned: " + caloriesBurned + ", Date: " + dateLogged;
    }
}

import java.util.List;
//Statistics class
class Statistics {
    public static void generateReport(List<Workout> workouts) {
        System.out.println("Workout Report:");
        for (Workout workout : workouts) {
            System.out.println(workout);
        }
    }

    public static void trackProgress(List<Workout> workouts) {
        int totalCalories = 0;
        for (Workout workout : workouts) {
            totalCalories += workout.caloriesBurned;
        }
        System.out.println("Total Calories Burned: " + totalCalories);
    }
}

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
public class FitnessTrackerApp extends Application {

    private User currentUser;

    @Override
    public void start(Stage primaryStage) {
        // Create a sample user
        currentUser = new User("John Doe", 30, 180.0, 1.75, 2000, 10000);

        TabPane tabPane = new TabPane();

        // Profile tab with option to update profile details
        Tab profileTab = new Tab("Profile", createProfilePane(primaryStage));
        profileTab.setClosable(false);

        // Workout Log tab for entering new workouts
        Tab workoutTab = new Tab("Log Workout", createWorkoutPane());
        workoutTab.setClosable(false);

        // Statistics tab with text report and a BarChart for visualizing calories burned over time
        Tab statsTab = new Tab("Statistics", createStatisticsPane());
        statsTab.setClosable(false);

        tabPane.getTabs().addAll(profileTab, workoutTab, statsTab);

        Scene scene = new Scene(tabPane, 800, 600);
        scene.getStylesheets().add("style.css"); // Link to your CSS file for styling

        primaryStage.setScene(scene);
        primaryStage.setTitle("Fitness Tracker App");
        primaryStage.show();
    }

    // Creates the profile pane that displays user info and an "Edit Profile" button
    private Pane createProfilePane(Stage owner) {
        VBox profileBox = new VBox(10);
        profileBox.setPadding(new Insets(10));

        Label nameLabel = new Label("Name: " + currentUser.getName());
        Label ageLabel = new Label("Age: " + currentUser.getAge());
        Label weightLabel = new Label("Weight: " + currentUser.getWeight() + " lbs");
        Label heightLabel = new Label("Height: " + currentUser.getHeight() + " m");
        Label bmiLabel = new Label("BMI: " + String.format("%.2f", currentUser.calculateBMI()));

        Button editButton = new Button("Edit Profile");
        editButton.setOnAction(e -> openEditProfileWindow(owner));

        profileBox.getChildren().addAll(nameLabel, ageLabel, weightLabel, heightLabel, bmiLabel, editButton);
        return profileBox;
    }

    // Opens a modal window to allow the user to update their profile details
    private void openEditProfileWindow(Stage owner) {
        Stage editStage = new Stage();
        editStage.initModality(Modality.WINDOW_MODAL);
        editStage.initOwner(owner);
        editStage.setTitle("Edit Profile");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        TextField nameField = new TextField(currentUser.getName());
        TextField ageField = new TextField(String.valueOf(currentUser.getAge()));
        TextField weightField = new TextField(String.valueOf(currentUser.getWeight()));
        TextField heightField = new TextField(String.valueOf(currentUser.getHeight()));

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Age:"), 0, 1);
        grid.add(ageField, 1, 1);
        grid.add(new Label("Weight (lbs):"), 0, 2);
        grid.add(weightField, 1, 2);
        grid.add(new Label("Height (m):"), 0, 3);
        grid.add(heightField, 1, 3);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            try {
                currentUser.setName(nameField.getText());
                currentUser.setAge(Integer.parseInt(ageField.getText()));
                currentUser.setWeight(Double.parseDouble(weightField.getText()));
                currentUser.setHeight(Double.parseDouble(heightField.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Profile updated successfully!");
                alert.showAndWait();
                editStage.close();
                // In a full implementation, you may refresh the profile pane here.
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid numeric values for age, weight, and height.");
                alert.showAndWait();
            }
        });

        grid.add(saveButton, 1, 4);

        Scene scene = new Scene(grid, 300, 250);
        editStage.setScene(scene);
        editStage.show();
    }

    // Creates the workout logging pane with enhanced input validation and error handling
    private Pane createWorkoutPane() {
        VBox workoutBox = new VBox(10);
        workoutBox.setPadding(new Insets(10));

        TextField workoutTypeField = new TextField();
        workoutTypeField.setPromptText("Workout Type");

        TextField durationField = new TextField();
        durationField.setPromptText("Duration (minutes)");

        TextField caloriesField = new TextField();
        caloriesField.setPromptText("Calories Burned");

        Button logWorkoutButton = new Button("Log Workout");
        logWorkoutButton.setOnAction(e -> {
            try {
                String workoutType = workoutTypeField.getText().trim();
                if (workoutType.isEmpty()) {
                    throw new IllegalArgumentException("Workout type cannot be empty.");
                }
                int duration = Integer.parseInt(durationField.getText().trim());
                int calories = Integer.parseInt(caloriesField.getText().trim());
                Workout workout = new Workout(workoutType, duration, calories, LocalDate.now());
                currentUser.addWorkout(workout);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Workout logged successfully!");
                alert.showAndWait();
                workoutTypeField.clear();
                durationField.clear();
                caloriesField.clear();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid numeric values for duration and calories.");
                alert.showAndWait();
            } catch (IllegalArgumentException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.showAndWait();
            }
        });

        workoutBox.getChildren().addAll(
            new Label("Log a New Workout:"),
            workoutTypeField,
            durationField,
            caloriesField,
            logWorkoutButton
        );
        return workoutBox;
    }

    // Creates a statistics pane that displays a text report and a BarChart of calories burned per workout
    private Pane createStatisticsPane() {
        VBox statsBox = new VBox(10);
        statsBox.setPadding(new Insets(10));

        Button generateReportButton = new Button("Generate Report");
        TextArea reportArea = new TextArea();
        reportArea.setEditable(false);
        reportArea.setPrefHeight(150);

        // Set up a BarChart to visualize calories burned over time
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("Calories Burned");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Calories Burned Over Time");

        generateReportButton.setOnAction(e -> {
            // Generate a text report using the workout history
            StringBuilder report = new StringBuilder("Workout Report:\n");
            List<Workout> workouts = currentUser.getWorkoutHistory();
            for (Workout workout : workouts) {
                report.append(workout.toString()).append("\n");
            }
            int totalCalories = workouts.stream().mapToInt(w -> w.caloriesBurned).sum();
            report.append("\nTotal Calories Burned: ").append(totalCalories);
            reportArea.setText(report.toString());

            // Update the BarChart with workout data
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Calories Burned");
            barChart.getData().clear();
            for (Workout workout : workouts) {
                series.getData().add(new XYChart.Data<>(workout.dateLogged.toString(), workout.caloriesBurned));
            }
            barChart.getData().add(series);
        });

        statsBox.getChildren().addAll(new Label("Statistics:"), generateReportButton, reportArea, barChart);
        return statsBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
