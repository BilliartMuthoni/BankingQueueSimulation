package distribution;

import java.util.Random;

public class UniformDistribution extends Distribution {
    private static final Random random = new Random();

    public UniformDistribution(double a, double b) {
        super(a, b);
    }

    @Override
    public double generate() {
        return lowerBound + (upperBound - lowerBound) * random.nextDouble();
    }

    @Override
    public String toString() {
        return "Uniform(" + lowerBound + ", " + upperBound + ")";
    }
}
