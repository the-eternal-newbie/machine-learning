package dataset;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.lang.Math;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Dataset {
    private final static double[][] x_data = new double[20][200];
    private final static double[] y_data = new double[200];
    public double[] stdDeviationX = new double[200];
    public double[] miuX = new double[200];
    public double stdDeviationY = 0.0;
    public double miuY = 0;
    private int n = 0;
    private int x_dim = 0;

    public Dataset(String csvFile, int dim) {
        x_dim = dim;
        String line = "";
        String cvsSplitBy = ",";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br = new BufferedReader(new FileReader("data/" + csvFile));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                if (n > 0) {
                    int dim_lim = 0;
                    while (dim_lim < dim) {
                        x_data[dim_lim][n] = Float.parseFloat(data[dim_lim]);
                        dim_lim++;
                    }
                    y_data[n] = Float.parseFloat(data[dim]);
                }
                n++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public double[] getYData() {
        return y_data;
    }

    public double[][] getXData() {
        return x_data;
    }

    public int getSize() {
        return n;
    }

    private double miu(char data, int dim) {
        double mean = 0;
        for (int i = 1; i < n; i++) {
            if(data == 'x') {
                mean += x_data[dim][i];  
            } else {
                mean += y_data[i];
            }
        }
        return mean / (n - 1);
    }

    private double stdDeviation(char data, int dim) {
        double std_deviation = 0;
        double mean = miu(data, dim);
        for (int i = 1; i < n; i++) {
            if(data == 'x') {
                std_deviation += Math.pow((x_data[dim][i] - mean), 2);
            } else {
                std_deviation += Math.pow((y_data[i] - mean), 2);
            }
        }
        return Math.sqrt((std_deviation / (n - 1)));
    }

    public void std() {
        stdDeviationY = stdDeviation('y', 0);
        miuY = miu('y', 0);
        for(int i = 0; i < x_dim; i++) {
            stdDeviationX[i] =  stdDeviation('x', i);
            miuX[i] = miu('x', i);
        }
        for (int i = 1; i < n; i++) {
            // y_data[i] = (y_data[i] - miuY) / stdDeviationY;
            for(int j = 0; j < x_dim; j++) {
                x_data[j][i] =  (x_data[j][i] - miuX[j]) / stdDeviationX[j];
            }    
        }
    }
}
