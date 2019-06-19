import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            Input instance = read("input.json");
        } catch (FileNotFoundException e) {
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
