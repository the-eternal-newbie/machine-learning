package kNearestNeighbor;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class IntelligentAgentKNN extends Agent {
    private GUI myGui;
    private char predicted_y;
    private static final long serialVersionUID = 1L;
    private static final KNN knn = new KNN("tshirt_dataset.csv", 2);
    
    protected void setup() {
        System.out.println("Agent " + getLocalName() + " started.");
        myGui = new GUI(this);
        myGui.showGui();
    }

    public void predict(final Double x1, final Double x2) {
        addBehaviour(new OneShotBehaviour(){
            private static final long serialVersionUID = 1L;
            public void action() {
                predicted_y = knn.predict(x1, x2);
                // StringBuilder s = new StringBuilder();
                // s.append(String.format("x_1 = %.2f | x_2 = %.2f | y_hat = %c", x1, x2, predicted_y));
                System.out.println(predicted_y);
            }
        });
    }
}
