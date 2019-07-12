package api.sitewhere.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.HashMap;

public class Device {
    private String id;
    private String token;

    private int capacity = 0;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private HashMap<String, String> metadata;

    public Device() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HashMap<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(HashMap<String, String> metadata) {
        this.metadata = metadata;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

/*
{
    "metadata": {},
    "id": "a9019bbb-ba67-4e1e-8bd9-4c29717abf31",
    "token": "32071-MT90-274658",
    "createdDate": "2019-07-02T12:15:56.578Z",
    "deviceTypeId": "cf6dfcd0-d28b-4714-9fef-9f71cac8f671",
    "deviceAssignmentId": "f3e64070-5a4f-4d0a-89e7-3a3ae15ba804",
    "deviceElementMappings": [],
    "comments": "Equipment Tracker based on MeiTrack MT90."
}
 */