package solver;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum QueryType {
    @JsonProperty(value = "filter")
    FILTER,             // 0

    @JsonProperty(value = "sequence")
    SEQUENCE,           // 1

    @JsonProperty(value = "pattern")
    PATTERN,            // 2

    @JsonProperty(value = "batch_aggregate")
    BATCH_AGGREGATE,    // 3

    @JsonProperty(value = "sliding_aggregate")
    SLIDING_AGGREGATE   // 4
}
