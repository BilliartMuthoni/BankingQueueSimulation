package distribution;

public abstract class Distribution {
    protected double lowerBound;
    protected double upperBound;

    public Distribution(double lowerBound, double upperBound) {
        if (lowerBound >= upperBound)
            throw new IllegalArgumentException("Lower bound must be less than upper bound.");
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public abstract double generate();

    public double getLowerBound() { return lowerBound; }
    public double getUpperBound() { return upperBound; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + lowerBound + ", " + upperBound + "]";
    }
}
