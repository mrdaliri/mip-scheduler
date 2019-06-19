import com.google.gson.annotations.SerializedName;

public class CommunicationCost {
    @SerializedName("cloud_cloud")
    private int cloudCloud;

    @SerializedName("cloud_edge")
    private int cloudEdge;

    @SerializedName("edge_edge")
    private int edgeEdge;

    public CommunicationCost() { }

    public int getCloudCloud() {
        return cloudCloud;
    }

    public void setCloudCloud(int cloudCloud) {
        this.cloudCloud = cloudCloud;
    }

    public int getCloudEdge() {
        return cloudEdge;
    }

    public void setCloudEdge(int cloudEdge) {
        this.cloudEdge = cloudEdge;
    }

    public int getEdgeEdge() {
        return edgeEdge;
    }

    public void setEdgeEdge(int edgeEdge) {
        this.edgeEdge = edgeEdge;
    }
}
