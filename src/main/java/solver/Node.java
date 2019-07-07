package solver;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Node {
    private int id;
    private String label;
    private int consumption;
    private Type type;

    @JsonProperty("query_type")
    private QueryType queryType;

    public Node() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    @Override
    public String toString() {
        return String.format("solver.Node#%d (%s)", id, label);
    }
}
