package solver;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class ProblemDeserializer extends StdDeserializer<Problem> {
    public ProblemDeserializer() {
        this(null);
    }

    public ProblemDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Problem deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode root = jp.getCodec().readTree(jp);
        Problem problem = new Problem();

        JsonParser costsJSON = root.get("costs").traverse(jp.getCodec());
        costsJSON.nextToken();
        problem.setCost(ctxt.readValue(costsJSON, CommunicationCost.class));

        JsonParser nodesJSON = root.get("nodes").traverse(jp.getCodec());
        nodesJSON.nextToken();
        HashMap<Integer, Node> nodes = new HashMap<>();
        for (Node node : ctxt.readValue(nodesJSON, Node[].class)) {
            nodes.put(node.getId(), node);
            problem.getNodesGraph().addNode(node);
        }

        JsonNode edgesArray = root.get("edges");
        for (final JsonNode edge : edgesArray) {
            int sourceId = edge.get("source").asInt();
            int targetId = edge.get("target").asInt();
            double bandwidth = edge.get("bandwidth").asDouble();

            problem.getNodesGraph().addArc(nodes.get(sourceId), nodes.get(targetId), new EdgeProperty(bandwidth));
            problem.getNodesGraph().addArc(nodes.get(targetId), nodes.get(sourceId), new EdgeProperty(bandwidth));
        }

        JsonParser resourcesJSON = root.get("resources").traverse(jp.getCodec());
        resourcesJSON.nextToken();
        HashMap<Integer, Resource> resources = new HashMap<>();
        for (Resource resource : ctxt.readValue(resourcesJSON, Resource[].class)) {
            resources.put(resource.getId(), resource);
            problem.getResourcesGraph().addNode(resource);
        }

        JsonNode linksArray = root.get("links");
        for (final JsonNode link : linksArray) {
            int sourceId = link.get("source").asInt();
            int targetId = link.get("target").asInt();
            double bandwidth = link.get("bandwidth").asDouble();
            double latency = link.get("latency").asDouble();
            boolean isBidirectional = link.get("bidirectional").asBoolean();

            problem.getResourcesGraph().addArc(resources.get(sourceId), resources.get(targetId), new LinkProperty(bandwidth, latency));
            if (isBidirectional) {
                problem.getResourcesGraph().addArc(resources.get(targetId), resources.get(sourceId), new LinkProperty(bandwidth, latency));
            }
        }

        return problem;
    }
}