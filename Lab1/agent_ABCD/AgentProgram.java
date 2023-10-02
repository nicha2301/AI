package Lab1.agent_ABCD;

public class AgentProgram {

    public Action execute(Percept p) {// location, status
        if(p.getLocationState().equals(Environment.LocationState.DIRTY))
            return Environment.SUCK_DIRT;
        else {
            Action randomAction = Environment.getRandomAction();
            System.out.println(randomAction);
        }
        return NoOpAction.NO_OP;

    }

}