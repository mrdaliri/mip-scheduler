package api.sitewhere.models;

public class DeviceGroupElement {
    private String id;
    private String deviceId;

    public DeviceGroupElement() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}

/*
{
    "id": "e32b2648-0fa9-4f23-9868-517354a78090",
    "groupId": "18c2207a-561b-4796-a469-89fa446e149e",
    "deviceId": "720a7be0-7181-4413-8860-489e045f9c49",
    "roles": []
}
 */