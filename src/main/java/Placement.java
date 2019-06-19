import com.google.gson.annotations.SerializedName;

public enum Placement {
    @SerializedName(value = "cloud", alternate = "0")
    CLOUD, // 0

    @SerializedName(value = "edge", alternate = "1")
    EDGE   // 1
}
