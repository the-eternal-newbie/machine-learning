package multipleLinearRegression;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class IntelligentAgentMLR extends Agent {
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

    private class Predict extends Behaviour {
        private static final long serialVersionUID = 1L;

        @Override
        public void action() {
        }

        @Override
        public boolean done() {}

        @Override
        public int onEnd() {
            myAgent.doDelete();
            return super.onEnd();
        }
    }
}
