import java.util.HashMap;

public class Resource {
    private int id;
    private String label;
    private int capacity;
    private Placement placement;
    private Type type;
    private String location;
    private HashMap<QueryType, Double> costs = new HashMap<>();

    public Resource() { }

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public HashMap<QueryType, Double> getCosts() {
        return costs;
    }

    public void setCosts(HashMap<QueryType, Double> costs) {
        this.costs = costs;
    }

}