import com.google.gson.annotations.SerializedName;

public class Input {
    @SerializedName("costs")
    private CommunicationCost cost;

    private DirectedGraph<Node, EdgeProperty> nodesGraph = new DirectedGraph<>();
    private DirectedGraph<Resource, LinkProperty> resourcesGraph = new DirectedGraph<>();

    public Input() { }

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
