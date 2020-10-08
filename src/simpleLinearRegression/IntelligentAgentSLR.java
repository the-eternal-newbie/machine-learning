package simpleLinearRegression;

import jade.core.Agent;
import simpleLinearRegression.*;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class IntelligentAgentSLR extends Agent {
    private static final long serialVersionUID = 1L;

    protected void setup() {
        addBehaviour(new SimpleLinearRegressionBehaviour());
    }
}

class SimpleLinearRegressionBehaviour extends Behaviour {
    private static final long serialVersionUID = 1L;

    @Override
    public void action() {
    }

    @Override
    public boolean done() {
        return false;
    }
}
