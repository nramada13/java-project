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

