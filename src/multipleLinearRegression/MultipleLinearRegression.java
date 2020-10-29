package multipleLinearRegression;

public class MultipleLinearRegression {
    private int n;
    private boolean trained = false;
    private double[] yData = new double[100];
    private double[][] xData = new double[2][100];
    private double sigmaX1, sigmaX2, sigmaX12, sigmaXsqr1, sigmaXsqr2, d, beta_0, beta_1, beta_2 = 0.0;

    public MultipleLinearRegression(String filename) {
        Dataset data = new Dataset(filename);
        n = data.getSize();
        xData = data.getXData();
        yData = data.getYData();
    }

    private void determinant() {
        if (!trained) {
            for (int i = 0; i < n; i++) {
                sigmaX1 += xData[0][i];
                sigmaX2 += xData[1][i];
                sigmaX12 += xData[0][i] * xData[1][i];
                sigmaXsqr1 += xData[0][i] * xData[0][i];
                sigmaXsqr2 += xData[1][i] * xData[1][i];
            }
            d = ((n * sigmaXsqr1 * sigmaXsqr2) + (sigmaX1 * sigmaX12 * sigmaX2) + (sigmaX2 * sigmaX1 * sigmaX12)
                    - (sigmaX2 * sigmaXsqr1 * sigmaX2) - (n * sigmaX12 * sigmaX12) - (sigmaXsqr2 * sigmaX1 * sigmaX1));
        }
    }

    private void betas() {
        if (!trained) {
            double sigmaY = 0.0;
            double sigmaX1Y = 0.0;
            double sigmaX2Y = 0.0;

            for (int i = 0; i < n; i++) {
                sigmaY += yData[i];
                sigmaX1Y += xData[0][i] * yData[i];
                sigmaX2Y += xData[1][i] * yData[i];
            }
            beta_0 = (((sigmaY * sigmaXsqr1 * sigmaXsqr2) + (sigmaX1 * sigmaX12 * sigmaX2Y)
                    + (sigmaX2 * sigmaX1Y * sigmaX12) - (sigmaX2Y * sigmaXsqr1 * sigmaX2)
                    - (sigmaX12 * sigmaX12 * sigmaY) - (sigmaXsqr2 * sigmaX1Y * sigmaX1)) / d);

            beta_1 = (((n * sigmaX1Y * sigmaXsqr2) + (sigmaY * sigmaX12 * sigmaX2) + (sigmaX2 * sigmaX1 * sigmaX2Y)
                    - (sigmaX2 * sigmaX1Y * sigmaX2) - (sigmaX2Y * sigmaX12 * n) - (sigmaXsqr2 * sigmaX1 * sigmaY))
                    / d);

            beta_2 = (((n * sigmaXsqr1 * sigmaX2Y) + (sigmaX1 * sigmaX1Y * sigmaX2) + (sigmaY * sigmaX1 * sigmaX12)
                    - (sigmaX2 * sigmaXsqr1 * sigmaY) - (sigmaX12 * sigmaX1Y * n) - (sigmaX2Y * sigmaX1 * sigmaX1))
                    / d);
        }
    }

    public String equationMLR() {
        if (trained) {
            StringBuilder s = new StringBuilder();
            s.append(String.format("y = %.2f + %.2fx_1 + %.2fx_2", beta_0, beta_1, beta_2));
            return s.toString();
        }
        return ("MLR is not trained yet!");
    }

    public double predict(double x_1, double x_2) {
        if (trained) {
            return (beta_0 + (beta_1 * x_1) + (beta_2 * x_2));
        }
        return (-1);
    }

    public void train() {
        if (!trained) {
            determinant();
            betas();
            trained = true;
        }
    }

    public boolean isTrained() {
        return trained;
    }
}