package multipleLinearRegression;

import java.io.FileReader;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Dataset {
    private final static double[][] x_data = new double[3][100];
    private final static double[] y_data = new double[100];

    public Dataset(String csvFile) {
        String line = "";
        String cvsSplitBy = ",";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br = new BufferedReader(new FileReader("../data/" + csvFile));
            int n = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                if (n > 0) {
                    x_data[0][n] = 1.0;
                    x_data[1][n] = Float.parseFloat(data[0]);
                    x_data[2][n] = Float.parseFloat(data[1]);
                    y_data[n] = Float.parseFloat(data[2]);
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

    public static void main(String[] args) {
        Dataset data = new Dataset("startups.csv");
        System.out.println(data.getXData()[0][1]);
        System.out.println(data.getYData()[1]);
    }
}
