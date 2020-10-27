package multipleLinearRegression;

public class MultipleLinearRegression {
    private double[] coefficients = {0.0, 0.0, 0.0};
    private double[][] xData = new double[3][100];
    private double[] yData = new double[100]; 

    public MultipleLinearRegression(String filename) {
        Dataset data = new Dataset(filename);
        xData = data.getXData();
        yData = data.getYData();
    }

    public static void main(String[] args) {
        MultipleLinearRegression mlr = new MultipleLinearRegression("startups.csv");
    }
}