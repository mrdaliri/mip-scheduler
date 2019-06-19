import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import ilog.concert.IloException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            Input input = read("input.json");
            Model model = new Model(input);
            model.solve();
            System.out.println(model.getSolution());
            System.out.println("Feasible? " + model.isFeasible());
        } catch (FileNotFoundException | IloException e) {
            e.printStackTrace();
        }
    }


    public static Input read(String path) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<Input>(){}.getType(), new InputDeserializer())
                .create();
        return gson.fromJson(bufferedReader, Input.class);
    }
}
