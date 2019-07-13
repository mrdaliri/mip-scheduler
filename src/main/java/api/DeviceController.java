package api;

import api.sitewhere.models.Device;
import api.sitewhere.SiteWhereService;
import api.sitewhere.models.DeviceGroupElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class DeviceController {
    @Autowired
    private SiteWhereService siteWhereService;

    @GetMapping("/integration/sitewhere/devices")
    public Map<String, List<Device>> listDevices(@RequestParam String instance, @RequestParam String username, @RequestParam String password, @RequestParam String tenantToken, @RequestParam String tenantAuth) {
        siteWhereService.setInstance(instance);
        siteWhereService.authenticate(username, password);
        List<Device> allDevices = siteWhereService.listAllDevices(tenantToken, tenantAuth);
        List<DeviceGroupElement> cloudElements = siteWhereService.listGroupElements(tenantToken, tenantAuth, "cloud_resources");
        List<DeviceGroupElement> edgeElements = siteWhereService.listGroupElements(tenantToken, tenantAuth, "edge_resources");

        Map<String, List<Device>> groups = new HashMap<>();

        List<Device> cloudDevices = new ArrayList<>();
        for (DeviceGroupElement element : cloudElements) {
            Device device = allDevices.stream().filter(d -> element.getDeviceId().equals(d.getId())).findFirst().orElse(null);
            if (device != null) {
                device.setCapacity(Integer.valueOf(device.getMetadata().get("capacity")));
                cloudDevices.add(device);
            }
        }
        groups.put("cloud", cloudDevices);

        List<Device> edgeDevices = new ArrayList<>();
        for (DeviceGroupElement element : edgeElements) {
            Device device = allDevices.stream().filter(d -> element.getDeviceId().equals(d.getId())).findFirst().orElse(null);
            if (device != null) {
                device.setCapacity(Integer.valueOf(device.getMetadata().get("capacity")));
                edgeDevices.add(device);
            }
        }
        groups.put("edge", edgeDevices);

        return groups;
    }
}
