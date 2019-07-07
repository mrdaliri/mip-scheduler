package solver;

public class EdgeProperty {
    private final double bandwidth;

    public EdgeProperty(double bandwidth) {
        this.bandwidth = bandwidth;
    }

    public double getBandwidth() {
        return bandwidth;
    }

    @Override
    public int hashCode() {
        return 41 * (37 + (int) getBandwidth());
    }

    public boolean canEqual(Object other) {
        return (other instanceof EdgeProperty);
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof EdgeProperty) {
            EdgeProperty that = (EdgeProperty) other;
            result = (that.canEqual(this) && this.getBandwidth() == that.getBandwidth());
        }
        return result;
    }
}
