package kNearestNeighbor;

import dataset.Dataset;

public class KNN {
    private int n;
    private boolean trained = false;
    private double[] yData = new double[200];
    private double[][] xData = new double[20][200];

    public KNN(String filename, int dim) {
        Dataset data = new Dataset(filename, dim);
        n = data.getSize();
        data.std();
        xData = data.getXData();
        yData = data.getYData();
    }

    public static void main(String[] args) {
        Dataset data = new Dataset("tshirt_dataset", 2);
        int n = data.getSize();
        data.std();
        double[][] xData = data.getXData();
        double[] yData = data.getYData();
        for (int i = 0; i < n; i++) {
            System.out.println(yData[i]);            
            for(int j = 0; j < 2; j++) {
                System.out.println(xData[j][i]);
            }    
        }
    }
}
