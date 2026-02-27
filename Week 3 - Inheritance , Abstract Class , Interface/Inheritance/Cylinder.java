public class Cylinder extends Circle {
    private double height;

    // Default consturctor
    public Cylinder() {
        super();
        this.height = 1.0;
    }

    // Constructor height
    public Cylinder(double height) {
        super();
        this.height = height; 
    }

    // Constructer radius dan height
    public Cylinder(double radius, double height) {
        super(radius);
        this.height = height; 
    }

    public double getHeight() { 
        return height;
    }

    public double getVolume() {
        return super.getArea() * height;
    }

    @Override
    public double getArea() {
        double radius = getRadius();
        return 2 * Math.PI * radius * height + 2 * super.getArea();
    }

    @Override
    public String toString() {
        return "Cylinder: subclass of " + super.toString()
            + " height=" + height;
    }
}