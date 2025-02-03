public class Circle extends GeometricObject implements Comparable<Circle> {
    private double radius;

    // No-arg constructor
    public Circle() {
    }

    // Constructor with specified radius
    public Circle(double radius) {
        this.radius = radius;
    }

    // Return radius
    public double getRadius() {
        return radius;
    }

    // Set a new radius
    public void setRadius(double radius) {
        this.radius = radius;
    }

    // Return area
    @Override
    public double getArea() {
        return radius * radius * Math.PI;
    }

    // Return diameter
    public double getDiameter() {
        return 2 * radius;
    }

    // Return perimeter
    @Override
    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    // Implement compareTo method for Comparable
    @Override
    public int compareTo(Circle other) {
        return Double.compare(this.radius, other.radius);
    }

    // Override equals method: Two circles are equal if their radii are the same
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Circle) {
            Circle other = (Circle) obj;
            return this.radius == other.radius;
        }
        return false;
    }

    // Print the circle info
    public void printCircle() {
        System.out.println("The circle is created and the radius is " + radius);
    }
}