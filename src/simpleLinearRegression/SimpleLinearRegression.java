package simpleLinearRegression;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SimpleLinearRegression {
    private static double beta_1 = 0.0;
    private static double beta_0 = 0.0;
    private final static double[] x_data = new double[100];
    private final static double[] y_data = new double[100];

    public static void main(String[] args) {
        String csvFile = "data/salary_dataset.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            int n = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                if (n > 0) {
                    System.out.println("Year Experience = " + data[0] + ", Salary = " + data[1]);
                    x_data[n] = Float.parseFloat(data[0]);
                    y_data[n] = Float.parseFloat(data[1]);
                } else {
                    System.out.println("Year Experience  | Salary ");
                }
                n++;
            }
            process(x_data, y_data);
            System.out.print("\nSimple linear regression equation: " + equation());
            for (int i = 0; i < n; i++) {
                System.out.print(String.format("\nx: %.2f | predicted y: %.2f", x_data[i], predict(x_data[i])));
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

    public static void old_main(String[] args) throws IOException {
        int n = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please specify the dataset size: ");
        try {
            n = Integer.parseInt(br.readLine());
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid Format!");
        }

        for (int i = 0; i < n; i++) {
            System.out.print("Enter x: ");
            try {
                x_data[i] = Integer.parseInt(br.readLine());
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format!");
            }
            System.out.print("Enter y: ");
            try {
                y_data[i] = Integer.parseInt(br.readLine());
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format!");
            }
        }
        process(x_data, y_data);
        System.out.print("\nSimple linear regression equation: " + equation());
        for (int i = 0; i < n; i++) {
            System.out.print(String.format("\nx: %.2f | predicted y: %.2f", x_data[i], predict(x_data[i])));
        }
    }

    public static void process(double[] x, double[] y) {
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

        beta_1 = (n * sigma_xy - sigma_x * sigma_y) / (n * sigma_x2 - sigma_x * sigma_x);
        beta_0 = (sigma_y - beta_1 * sigma_x) / n;
    }

    public static double beta_1() {
        return beta_1;
    }

    public static double beta_0() {
        return beta_0;
    }

    public static double predict(double x) {
        return (beta_1 * x) + beta_0;
    }

    public static String equation() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("%.2f + %.2f x", beta_0(), beta_1()));
        return s.toString();
    }
}