import java.util.HashMap;
//Each node has x, y coordinates in between 0 and 1
public class Node {
    public double x;
    public double y;

    public Node(double x, double y) {
        super();
        assert (x < 1 && x > 0);
        assert (y < 1 && y > 0);
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Node) {
            Node other = (Node) o;
            return (this.x == other.x && this.y == other.y);
        }

        return false;
    }

    public int hashCode() {

        return (int) (this.x * this.y * 100000);

    }
}
