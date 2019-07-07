package solver;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Placement {
    @JsonProperty(value = "cloud")
    CLOUD, // 0

    @JsonProperty(value = "edge")
    EDGE   // 1
}
