package solver;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = ProblemDeserializer.class)
public class Problem {
    private CommunicationCost cost;

    private DirectedGraph<Node, EdgeProperty> nodesGraph = new DirectedGraph<>();
    private DirectedGraph<Resource, LinkProperty> resourcesGraph = new DirectedGraph<>();

    public Problem() { }

    public CommunicationCost getCost() {
        return cost;
    }

    public void setCost(CommunicationCost cost) {
        this.cost = cost;
    }

    public DirectedGraph<Node, EdgeProperty> getNodesGraph() {
        return nodesGraph;
    }

    public DirectedGraph<Resource, LinkProperty> getResourcesGraph() {
        return resourcesGraph;
    }
}
