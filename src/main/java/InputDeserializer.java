import com.google.gson.*;

import java.util.ArrayList;

class InputDeserializer implements JsonDeserializer<Input> {
    @Override
    public Input deserialize(JsonElement jsonElement, java.lang.reflect.Type type, JsonDeserializationContext context) throws JsonParseException {
        Input input = new Gson().fromJson(jsonElement, Input.class);

        ArrayList<Node> nodes = new ArrayList<>();
        JsonArray nodesJSON = jsonElement.getAsJsonObject().get("nodes").getAsJsonArray();
        for (JsonElement nodeElement : nodesJSON) {
            Node node = context.deserialize(nodeElement, Node.class);
            input.getNodesGraph().addNode(node);
            nodes.add(node);
        }

        JsonArray edgesArray = jsonElement.getAsJsonObject().get("edges").getAsJsonArray();
        for (JsonElement edgeElement : edgesArray) {
            JsonObject edgeObject = edgeElement.getAsJsonObject();
            int fromId = edgeObject.get("from").getAsInt();
            int toId = edgeObject.get("to").getAsInt();
            double bandwidth = edgeObject.get("bandwidth").getAsDouble();

            input.getNodesGraph().addArc(nodes.get(fromId), nodes.get(toId), new EdgeProperty(bandwidth));
            input.getNodesGraph().addArc(nodes.get(toId), nodes.get(fromId), new EdgeProperty(bandwidth));
        }

        ArrayList<Resource> resources = new ArrayList<>();
        JsonArray resourcesJSON = jsonElement.getAsJsonObject().get("resources").getAsJsonArray();
        for (JsonElement resourceElement : resourcesJSON) {
            Resource resource = context.deserialize(resourceElement, Resource.class);
            input.getResourcesGraph().addNode(resource);
            resources.add(resource);
        }

        JsonArray linksArray = jsonElement.getAsJsonObject().get("links").getAsJsonArray();
        for (JsonElement linkElement : linksArray) {
            JsonObject linkObject = linkElement.getAsJsonObject();
            int fromId = linkObject.get("from").getAsInt();
            int toId = linkObject.get("to").getAsInt();
            double bandwidth = linkObject.get("bandwidth").getAsDouble();
            double latency = linkObject.get("latency").getAsDouble();
            boolean isBidirectional = linkObject.get("bidirectional").getAsBoolean();

            input.getResourcesGraph().addArc(resources.get(fromId), resources.get(toId), new LinkProperty(bandwidth, latency));
            if (isBidirectional) {
                input.getResourcesGraph().addArc(resources.get(toId), resources.get(fromId), new LinkProperty(bandwidth, latency));
            }
        }

        return input;
    }
}