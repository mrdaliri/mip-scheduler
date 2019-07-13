package api.sitewhere;

import api.sitewhere.models.Device;
import api.sitewhere.models.DeviceGroupElement;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SiteWhereService {

    private String instance;
    private final RestTemplate restTemplate;
    private String token;

    private String getURL(String path) {
        return this.instance + "/sitewhere/" + path;
    }

    private HttpHeaders getHeaders(String tenantName, String tenantToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.token);
        headers.add("X-SiteWhere-Tenant-Id", tenantName);
        headers.add("X-SiteWhere-Tenant-Auth", tenantToken);
        return headers;
    }

    public SiteWhereService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void authenticate(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(getURL("authapi/jwt"), HttpMethod.GET, entity, String.class);
        this.token = result.getHeaders().get("X-Sitewhere-JWT").get(0);
    }

    public List<Device> listAllDevices(String tenantToken, String tenantAuth) {
        ResponseEntity<SiteWhereAPIResponse<Device>> response = restTemplate.exchange(
                getURL("api/devices"),
            HttpMethod.GET,
                new HttpEntity<String>("parameters", getHeaders(tenantToken, tenantAuth)),
            new ParameterizedTypeReference<SiteWhereAPIResponse<Device>>(){});
        return response.getBody().getResults();
    }

    public List<DeviceGroupElement> listGroupElements(String tenantToken, String tenantAuth, String group) {
        ResponseEntity<SiteWhereAPIResponse<DeviceGroupElement>> response = restTemplate.exchange(
                getURL(String.format("api/devicegroups/%s/elements", group)),
                HttpMethod.GET,
                new HttpEntity<String>("parameters", getHeaders(tenantToken, tenantAuth)),
                new ParameterizedTypeReference<SiteWhereAPIResponse<DeviceGroupElement>>(){});
        return response.getBody().getResults();
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }
}
