import com.google.gson.annotations.SerializedName;

public enum QueryType {
    @SerializedName(value = "filter", alternate = "0")
    FILTER,             // 0

    @SerializedName(value = "sequence", alternate = "1")
    SEQUENCE,           // 1

    @SerializedName(value = "pattern", alternate = "2")
    PATTERN,            // 2

    @SerializedName(value = "batch_aggregate", alternate = "3")
    BATCH_AGGREGATE,    // 3

    @SerializedName(value = "sliding_aggregate", alternate = "4")
    SLIDING_AGGREGATE   // 4
}
