package kNearestNeighbor;

import dataset.Dataset;
import java.lang.Math;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class KNN {
    private int n;
    private double K;
    private double[] yData = new double[200];
    private double[][] xData = new double[20][200];
    private List<Double> distances = new ArrayList<>();
    private List<Double> distancesCopy = new ArrayList<>();

    private Dataset data = null;

    public KNN(String filename, int dim) {
        data = new Dataset(filename, dim);
        n = data.getSize();
        K = (Math.sqrt(n) / 2);
        K = K % 2 != 0 ? K : K - 1;
        data.std();
        xData = data.getXData();
        yData = data.getYData();
    }

    private double getDistances(double[] pointA, double[] pointB) {
        return Math.sqrt(
            Math.pow((Math.round(pointB[1]) - Math.round(pointA[0])), 2) +
            Math.pow((Math.round(pointB[1]) - Math.round(pointB[0])), 2)
        );
    }

    public char predict(double x, double y) {
        int large = 0;
        int medium = 0;
        int small = 0;
        for (int i = 0; i < n; i++) {
            double[] pointA = {(x - data.miuX[0]) / data.stdDeviationX[0], (x - data.miuX[1]) / data.stdDeviationX[1]};
            double[] pointB = {xData[0][i], xData[1][i]};
            double distance = getDistances(pointA, pointB);
            distances.add(distance);
            distancesCopy.add(distance);
        }
        Collections.sort(distancesCopy);
        for (int i = 0; i < K; i++) {
            double near = distancesCopy.get(i);
            int index = distances.indexOf(near);
            double size = yData[index];
            if(size == 1.0) {
                medium++;
            }
            if(size == 2.0) {
                large++;
            }
        }
        if(large > medium & large > small) { return 'L'; }
        if(medium > large & medium > small) { return 'M'; }
        return 'S';
    }
}
