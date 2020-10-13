package simpleLinearRegression;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class IntelligentAgentSLR extends Agent {
    private static final long serialVersionUID = 1L;
    private int i = 0;
    private double sigma_x = 0.0, sigma_y = 0.0, sigma_xy = 0.0, sigma_x2 = 0.0;
    private static double beta_1 = 0.0;
    private static double beta_0 = 0.0;
    private final static double[] x = { 1.1, 1.3, 1.5, 2.0, 2.2, 2.9, 3.0, 3.2, 3.2, 3.7, 3.9, 4.0, 4.0, 4.1, 4.5, 4.9,
            5.1, 5.3, 5.9, 6.0, 6.8, 7.1, 7.9, 8.2, 8.7, 9.0, 9.5, 9.6, 10.3, 10.5 };
    private final static double[] y = { 39343.00, 46205.00, 37731.00, 43525.00, 39891.00, 56642.00, 60150.00, 54445.00,
            64445.00, 57189.00, 63218.00, 55794.00, 56957.00, 57081.00, 61111.00, 67938.00, 66029.00, 83088.00,
            81363.00, 93940.00, 91738.00, 98273.00, 101302.00, 113812.00, 109431.00, 105582.00, 116969.00, 112635.00,
            122391.00, 121872.00 };
    private int n = x.length;
    private double predictX = 0.0;

    protected void setup() {
        System.out.println("Agent " + getLocalName() + " started.");

        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            addBehaviour(new Train());
            addBehaviour(new Predict());
            predictX = Float.parseFloat((String) args[0]);
            System.out.println("x to predict = " + predictX);
        }

    }

    private class Train extends Behaviour {
        private static final long serialVersionUID = 1L;

        @Override
        public void action() {
            sigma_x += x[i];
            sigma_x2 += x[i] * x[i];
            sigma_xy += x[i] * y[i];
            sigma_y += y[i];
            i++;
        }

        @Override
        public boolean done() {
            return i == n;
        }

        @Override
        public int onEnd() {
            beta_1 = (n * sigma_xy - sigma_x * sigma_y) / (n * sigma_x2 - sigma_x * sigma_x);
            beta_0 = (sigma_y - beta_1 * sigma_x) / n;
            return super.onEnd();
        }
    }

    private class Predict extends Behaviour {
        private static final long serialVersionUID = 1L;

        @Override
        public void action() {
            if (i < n) {
                System.out.println("Training yet, I cannot predict!");
            } else {
                StringBuilder s = new StringBuilder();
                s.append(String.format("%.2f + %.2fx", beta_0, beta_1));
                System.out.print("\nSimple linear regression equation: " + s.toString());
            }
        }

        @Override
        public boolean done() {
            return i == n;
        }

        @Override
        public int onEnd() {
            double predicted_y = (beta_1 * predictX) + beta_0;
            System.out.print(String.format("\nx: %.2f | predicted y: %.2f\n", predictX, predicted_y));
            myAgent.doDelete();
            return super.onEnd();
        }
    }
}
