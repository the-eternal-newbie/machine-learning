package multipleLinearRegression;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class IntelligentAgentMLR extends Agent {
    private String equation;
    private IntelligentAgentGUI myGui;
    private double predicted_y;
    private static final long serialVersionUID = 1L;
    private static final MultipleLinearRegression mlr = new MultipleLinearRegression("startups.csv");

    protected void setup() {
        System.out.println("Agent " + getLocalName() + " started.");
        myGui = new IntelligentAgentGUI(this);
        myGui.showGui();
    }

    public void predict(final Double x1, final Double x2) {
        addBehaviour(new OneShotBehaviour() {
            private static final long serialVersionUID = 1L;
            public void action() {
                if(!mlr.isTrained()) {
                    mlr.train();
                    equation = mlr.equationMLR();
                    System.out.println("MLR equation: " + equation);
                }
                predicted_y = mlr.predict(x1, x2);
                StringBuilder s = new StringBuilder();
                s.append(String.format("x_1 = %.2f | x_2 = %.2f | y_hat = %.2f", x1, x2, predicted_y));
                System.out.println(s.toString());
            }
        });
    }
}
