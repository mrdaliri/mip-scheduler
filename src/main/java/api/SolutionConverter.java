package api;

import java.io.IOException;
import java.util.Map;

import javax.persistence.AttributeConverter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import solver.Node;
import solver.Resource;

public class SolutionConverter implements AttributeConverter<Map<Node, Resource>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<Node, Resource> solution) {
        String solutionJSON = null;
        try {
            solutionJSON = objectMapper.writeValueAsString(solution);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return solutionJSON;
    }

    @Override
    public Map<Node, Resource> convertToEntityAttribute(String solutionJSON) {
        Map<Node, Resource> solution = null;
        try {
            solution = objectMapper.readValue(solutionJSON, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return solution;
    }

}