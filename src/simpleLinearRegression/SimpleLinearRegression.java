package simpleLinearRegression;

public class SimpleLinearRegression {
    private final double beta_1, beta_0;

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public SimpleLinearRegression(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }
        int n = x.length;

        // Calculate the summatory of x, x^2, xy and y
        double sigma_x = 0.0, sigma_y = 0.0, sigma_xy = 0.0, sigma_x2 = 0.0;
        for (int i = 0; i < n; i++) {
            sigma_x += x[i];
            sigma_x2 += x[i] * x[i];
            sigma_xy += x[i] * y[i];
            sigma_y += y[i];
        }

        beta_1 = (sigma_xy - sigma_x * sigma_y) / (sigma_x2 - sigma_x * sigma_x);
        beta_0 = (sigma_y - beta_1 * sigma_x) / n;
    }

    public double beta_1() {
        return beta_1;
    }

    public double beta_0() {
        return beta_0;
    }

    public double predict(double x) {
        return beta_0 * x + beta_1;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("%.2f n + %.2f", beta_0(), beta_1()));
        return s.toString();
    }
}