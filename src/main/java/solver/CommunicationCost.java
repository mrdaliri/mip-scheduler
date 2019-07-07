package solver;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunicationCost {
    @JsonProperty("cloud_cloud")
    private int cloudCloud;

    @JsonProperty("cloud_edge")
    private int cloudEdge;

    @JsonProperty("edge_edge")
    private int edgeEdge;

    public CommunicationCost() { }

    public int getCloudCloud() {
        return cloudCloud;
    }

    public double getNormalizedCloudCloud() {
        return (double) cloudEdge / getMaximum();
    }

    public void setCloudCloud(int cloudCloud) {
        this.cloudCloud = cloudCloud;
    }

    public int getCloudEdge() {
        return cloudEdge;
    }

    public double getNormalizedCloudEdge() {
        return (double) cloudEdge / getMaximum();
    }

    public void setCloudEdge(int cloudEdge) {
        this.cloudEdge = cloudEdge;
    }

    public int getEdgeEdge() {
        return edgeEdge;
    }

    public double getNormalizedEdgeEdge() {
        return (double) edgeEdge / getMaximum();
    }

    public void setEdgeEdge(int edgeEdge) {
        this.edgeEdge = edgeEdge;
    }

    public int getMaximum() {
        return Math.max(Math.max(cloudCloud, cloudEdge), edgeEdge);
    }
}
