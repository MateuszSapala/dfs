package file;

import com.google.gson.Gson;
import graph.Graph;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileToGraph {
    private static final Gson gson = new Gson();

    @SneakyThrows
    private static String readFile(String name) {
        File myObj = new File("./src/main/resources/" + name);
        Scanner myReader = new Scanner(myObj);
        StringBuilder str = new StringBuilder();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            str.append(data);
        }
        myReader.close();
        return str.toString();
    }

    private static JsonGraph readJsonGraph(String name) {
        return gson.fromJson(readFile(name), JsonGraph.class);
    }

    public static Graph readGraph(String name) {
        var json = readJsonGraph(name);
        var graph = new Graph(json.isDirected());
        for (String vName : json.vertices()) {
            graph.addVertex(vName);
        }
        for (ArrayList<String> edge : json.edges()) {
            graph.addEdge(edge.get(0), edge.get(1));
        }
        return graph;
    }
}
