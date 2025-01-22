public class RegularPolygon {
    // Private fields
    private int n;
    private double side; 
    private double x; 
    private double y; 
    // No-arg constructor
    public RegularPolygon() {
        this.n = 3; 
        this.side = 1; 
        this.x = 0; 
    }
    // Constructor with specified number of sides and length of side
    public RegularPolygon(int n, double side) {
        this.n = n;
        this.side = side;
        this.x = 0;
        this.y = 0;
    }
    // Constructor with specified number of sides, length of side, and center coordinates
    public RegularPolygon(int n, double side, double x, double y) {
        this.n = n;
        this.side = side;
        this.x = x;
        this.y = y;
    }
    // Getters and Setters
    public int getN() {
        return n;
    }
    public void setN(int n) {
        this.n = n;
    }
    public double getSide() {
        return side;
    }
    public void setSide(double side) {
        this.side = side;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    // Method to calculate perimeter
    public double getPerimeter() {
        return n * side;
    }
    // Method to calculate area
    public double getArea() {
        return (n * Math.pow(side, 2)) / (4 * Math.tan(Math.PI / n));
    }
    // Test the RegularPolygon class
    public static void main(String[] args) {
        // Create three RegularPolygon objects
        RegularPolygon polygon1 = new RegularPolygon(); 
        RegularPolygon polygon2 = new RegularPolygon(6, 4); 
        RegularPolygon polygon3 = new RegularPolygon(10, 4, 5.6, 7.8); 
        // Display perimeter and area of each polygon
        System.out.printf("Polygon 1:");
        System.out.printf("Perimeter: " + polygon1.getPerimeter());
        System.out.printf(" Area: " + polygon1.getArea());
        System.out.printf("\nPolygon 2:");
        System.out.printf("Perimeter: " + polygon2.getPerimeter());
        System.out.printf(" Area: " + polygon2.getArea());
        System.out.printf("\nPolygon 3:");
        System.out.printf("Perimeter: " + polygon3.getPerimeter());
        System.out.printf(" Area: " + polygon3.getArea());
    }
}