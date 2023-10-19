package JavaPattern.structural;

/**
 * Bridge is about delegation/reference-pointing, it serves as an abstraction and is provided to clients
 * The point is multiple class might appear to be in a contain-relationship,
 * instead of implementing inheritance, bridge pattern does the delegation work
 */
public class BridgePattern {
    public interface Shape {
        double getArea();
    }

    public interface Color {
        String getColor();
    }
    /**
     * Wrong pattern
     */
    public class RedSquare implements Shape, Color {
        private final static String COLOR = "RED";

        @Override
        public double getArea() {
            return 0;
        }

        public String getColor() {
            return COLOR;
        }
    }

    /**
     * Right pattern
     */
    public class Red implements Color {
        private final static String RED = "RED";

        @Override
        public String getColor() {
            return RED;
        }
    }
    public class Square implements Shape {
        private Color color;
        public Square(final Color color) {
            this.color = color;
        }

        @Override
        public double getArea() {
            return 0;
        }

        public String getColor() {
            return color.getColor();
        }
    }
}
