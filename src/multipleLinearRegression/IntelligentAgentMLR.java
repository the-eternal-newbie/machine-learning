package multipleLinearRegression;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class IntelligentAgentMLR extends Agent {
    private static final long serialVersionUID = 1L;
    private String equation;
    
    protected void setup() {
        System.out.println("Agent " + getLocalName() + " started.");

        addBehaviour(new MLR());

    }

    private class MLR extends Behaviour {
        private static final long serialVersionUID = 1L;
        private MultipleLinearRegression mlr = new MultipleLinearRegression("startups.csv");

        @Override
        public void action() {
            mlr.train();
            equation = mlr.equationMLR();
        }

        @Override
        public boolean done() {
            return mlr.isTrained();
        }

        @Override
        public int onEnd() {
            myAgent.doDelete();
            System.out.println(equation);
            return super.onEnd();
        }
    }
}
