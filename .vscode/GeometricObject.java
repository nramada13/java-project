// Abstract class GeometricObject

public abstract class GeometricObject {
    private String color = "white";
    private boolean filled;
    // Default constructor
    protected GeometricObject() {}
    // Constructor with parameters
    protected GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }
    // Getter and Setter for color
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    // Getter and Setter for filled
    public boolean isFilled() {
        return filled;
    }
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    // Abstract methods for subclasses to implement
    public abstract double getArea();
    public abstract double getPerimeter();
}