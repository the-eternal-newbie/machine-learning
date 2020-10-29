package multipleLinearRegression;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.core.behaviours.OneShotBehaviour;

public class IntelligentAgentMLR extends Agent {
    private String equation;
    private IntelligentAgentGUI myGui;
    private double predicted_y;
    private static final long serialVersionUID = 1L;

    protected void setup() {
        System.out.println("Agent " + getLocalName() + " started.");
        myGui = new IntelligentAgentGUI(this);
        myGui.showGui();
    }

    protected void takeDown() {
        // Deregister from the yellow pages
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
        // Close the GUI
        myGui.dispose();
        // Printout a dismissal message
        System.out.println("Seller-agent " + getAID().getName() + " terminating.");
    }

    public void predict(final Double x1, final Double x2) {
        addBehaviour(new OneShotBehaviour() {
            private static final long serialVersionUID = 1L;
            private MultipleLinearRegression mlr = new MultipleLinearRegression("startups.csv");

            public void action() {
                mlr.train();
                equation = mlr.equationMLR();
                predicted_y = mlr.predict(x1, x2);
                System.out.println("MLR equation: " + equation);
                StringBuilder s = new StringBuilder();
                s.append(String.format("x_1 = %.2f | x_2 = %.2f | y_hat = %.2f", x1, x2, predicted_y));
                System.out.println(s.toString());
            }
        });
    }
}
