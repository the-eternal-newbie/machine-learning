import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;

/**
 * This example shows the basic usage of JADE behaviours.<br>
 * More in details this agent executes a <code>CyclicBehaviour</code> that shows
 * a printout at each round and a generic behaviour that performs four
 * successive "dummy" operations. The second operation in particular involves
 * adding a <code>OneShotBehaviour</code>. When the generic behaviour completes
 * the agent terminates.
 * 
 * @author Giovanni Caire - TILAB
 */
public class SimpleAgent extends Agent {

  private int iterator = 1;

  protected void setup() {
    System.out.println("Agent " + getLocalName() + " started.");

    // Add the CyclicBehaviour
    addBehaviour(new CyclicBehaviour(this) {
      public void action() {
        System.out.println("Cycling");
      }
    });
    // Add a stopping behaviour
    addBehaviour(new StopBehaviour());
  }

  private class StopBehaviour extends Behaviour {
    // The action is simple: just repeat the behaviour until the iterator reach the
    // desired
    // amount of printings
    public void action() {
      iterator++;
      if (iterator == 5) {
        // This OneShotBehaviour is added to the queue of behaviours and ends the
        // cycling
        myAgent.addBehaviour(new OneShotBehaviour(myAgent) {
          public void action() {
            System.out.println("Cycling - This one corresponds to the OneShotBehaviour");
            myAgent.doDelete();
          }
        });
      }
    }

    public boolean done() {
      return iterator == 5;
    }
  }
}
