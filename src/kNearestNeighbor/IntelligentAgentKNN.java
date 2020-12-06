package kNearestNeighbor;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class IntelligentAgentKNN extends Agent {
    private String equation;
    private GUI myGui;
    private double predicted_y;
    private static final long serialVersionUID = 1L;
    private static final KNN knn = new KNN("startups.csv");
    
    protected void setup() {}

    public void predict(final Double x1, final Double x2) {
        addBehaviour(new OneShotBehaviour(){
            private static final long serialVersionUID = 1L;
            public void action() {}
        });
    }
}
