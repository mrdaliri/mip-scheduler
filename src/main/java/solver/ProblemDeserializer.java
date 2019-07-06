package solver;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
        ArrayList<Node> nodes = new ArrayList<>(Arrays.asList(ctxt.readValue(nodesJSON, Node[].class)));
        for (Node node : nodes) {
            problem.getNodesGraph().addNode(node);
        }

        JsonNode edgesArray = root.get("edges");
        for (final JsonNode edge : edgesArray) {
            int fromId = edge.get("from").asInt();
            int toId = edge.get("to").asInt();
            double bandwidth = edge.get("bandwidth").asDouble();

            problem.getNodesGraph().addArc(nodes.get(fromId), nodes.get(toId), new EdgeProperty(bandwidth));
            problem.getNodesGraph().addArc(nodes.get(toId), nodes.get(fromId), new EdgeProperty(bandwidth));
        }

        JsonParser resourcesJSON = root.get("resources").traverse(jp.getCodec());
        resourcesJSON.nextToken();
        ArrayList<Resource> resources = new ArrayList<>(Arrays.asList(ctxt.readValue(resourcesJSON, Resource[].class)));
        for (Resource resource : resources) {
            problem.getResourcesGraph().addNode(resource);
        }

        JsonNode linksArray = root.get("links");
        for (final JsonNode link : linksArray) {
            int fromId = link.get("from").asInt();
            int toId = link.get("to").asInt();
            double bandwidth = link.get("bandwidth").asDouble();
            double latency = link.get("latency").asDouble();
            boolean isBidirectional = link.get("bidirectional").asBoolean();

            problem.getResourcesGraph().addArc(resources.get(fromId), resources.get(toId), new LinkProperty(bandwidth, latency));
            if (isBidirectional) {
                problem.getResourcesGraph().addArc(resources.get(toId), resources.get(fromId), new LinkProperty(bandwidth, latency));
            }
        }

        return problem;
    }
}