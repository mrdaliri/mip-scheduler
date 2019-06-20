public class LinkProperty extends EdgeProperty {
    private final double latency;

    public LinkProperty(double bandwidth, double latency) {
        super(bandwidth);
        this.latency = latency;
    }

    public double getLatency() {
        return latency;
    }

    public double getNormalizedLatency(double max) {
        return latency / max;
    }

    public boolean canEqual(Object other) {
        return (other instanceof LinkProperty);
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof LinkProperty) {
            LinkProperty that = (LinkProperty) other;
            result = (that.canEqual(this) && this.getBandwidth() == that.getBandwidth() && this.getLatency() == that.getLatency());
        }
        return result;
    }
}
