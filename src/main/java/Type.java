import com.google.gson.annotations.SerializedName;

public enum Type {
    @SerializedName(value = "source", alternate = "0")
    SOURCE, // 0

    @SerializedName(value = "sink", alternate = "1")
    SINK,   // 1

    @SerializedName(value = "none", alternate = "2")
    NONE    // 2
}
