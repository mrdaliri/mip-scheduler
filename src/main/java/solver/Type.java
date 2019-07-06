package solver;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Type {
    @JsonProperty(value = "source")
    SOURCE, // 0

    @JsonProperty(value = "sink")
    SINK,   // 1

    @JsonProperty(value = "none")
    NONE    // 2
}
