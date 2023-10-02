package Lab1.agent_ABCD;

import java.util.Random;

public class Environment {
    public static final Action MOVE_LEFT = new DynamicAction("LEFT");
    public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
    public static final Action MOVE_UP = new DynamicAction("UP");
    public static final Action MOVE_DOWN = new DynamicAction("DOWN");
    public static final Action SUCK_DIRT = new DynamicAction("SUCK");
    public static final String LOCATION_A = "A";
    public static final String LOCATION_B = "B";
    public static final String LOCATION_C = "C";
    public static final String LOCATION_D = "D";

    public static final Action ACTIONS[] = {MOVE_LEFT, MOVE_RIGHT, MOVE_UP, MOVE_DOWN, SUCK_DIRT};

    public static Action getRandomAction() {
        Random random = new Random();
        int index = random.nextInt(ACTIONS.length);
        return ACTIONS[index];
    }

    public enum LocationState {
        CLEAN, DIRTY
    }

    private EnvironmentState envState;
    private int score = 0;
    private boolean isDone = false;// all squares are CLEAN
    private Agent agent = null;

    public Environment(LocationState locAState, LocationState locBState, LocationState locCState, LocationState locDState) {
        envState = new EnvironmentState(locAState, locBState, locCState, locDState);
    }

    // add an agent into the enviroment
    public void addAgent(Agent agent, String location) {
        this.agent = agent;
        envState.setAgentLocation(location);
    }

    public EnvironmentState getCurrentState() {
        return this.envState;
    }

    // Update enviroment state when agent do an action
    public EnvironmentState executeAction(Action action) {
        String agentLocation = envState.getAgentLocation();
        Environment.LocationState locationState = envState.getLocationState(agentLocation);

        if (action.equals(SUCK_DIRT)) {
            if (locationState == Environment.LocationState.DIRTY) {
                envState.setLocationState(agentLocation, Environment.LocationState.CLEAN);
                score += 500;
            } else {
                score -= 10;
            }
        } else if (action.equals(MOVE_LEFT) && !agentLocation.equals(LOCATION_A)) {
            envState.setAgentLocation(LOCATION_A);
        } else if (action.equals(MOVE_RIGHT) && !agentLocation.equals(LOCATION_D)) {
            envState.setAgentLocation(LOCATION_D);
        } else if (action.equals(MOVE_UP) && (agentLocation.equals(LOCATION_B) || agentLocation.equals(LOCATION_C))) {
            envState.setAgentLocation(LOCATION_A);
        } else if (action.equals(MOVE_DOWN) && (agentLocation.equals(LOCATION_A) || agentLocation.equals(LOCATION_B))) {
            envState.setAgentLocation(LOCATION_D);
        } else {
            score -= 10;
        }

        return envState;
    }

    // get percept<AgentLocation, LocationState> at the current location where agent
    // is in.
    public Percept getPerceptSeenBy() {
        return new Percept(envState.getAgentLocation(), envState.getLocationState(envState.getAgentLocation()));
    }
    public int getScore() {
        return score;
    }

    public void step() {
        envState.display();
        String agentLocation = envState.getAgentLocation();
        Action anAction = agent.execute(getPerceptSeenBy());
        EnvironmentState es = executeAction(anAction);

        System.out.println("Performance measure: " + getScore());
        System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

        if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
                && (es.getLocationState(LOCATION_B) == LocationState.CLEAN) && (es.getLocationState(LOCATION_C) == LocationState.CLEAN)
                && (es.getLocationState(LOCATION_D) == LocationState.CLEAN))
            isDone = true;// if both squares are clean, then agent do not need to do any action
        es.display();
    }

    public void step(int n) {
        for (int i = 0; i < n; i++) {
            step();
            System.out.println("-------------------------");
        }
    }

    public void stepUntilDone() {
        int i = 0;

        while (!isDone) {
            System.out.println("step: " + i++);
            step();
        }
    }
}