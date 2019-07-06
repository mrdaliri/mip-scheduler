package solver;

import com.fasterxml.jackson.databind.ObjectMapper;
import ilog.concert.IloException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Problem problem = read("samples/problem_1.json");
            Model model = new Model(problem);
            model.solve();
            System.out.println(model.getSolution());
            System.out.println("Feasible? " + model.isFeasible());
        } catch (IOException | IloException e) {
            e.printStackTrace();
        }
    }


    public static Problem read(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(bufferedReader, Problem.class);
    }
}
