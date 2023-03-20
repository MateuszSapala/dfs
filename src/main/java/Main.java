import file.FileToGraph;
import graph.Graph;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("Do you want to load the test graph? (y/n)");
        var answer1 = reader.readLine().equalsIgnoreCase("y");
        Graph graph;
        if (answer1) {
            System.out.println("Enter the name of the graph:");
            var name = reader.readLine();
            graph = FileToGraph.readGraph(name);
        } else {
            System.out.println("Should the graph be directional? (y/n)");
            var answer2 = reader.readLine().equalsIgnoreCase("y");
            graph = new Graph(answer2);
        }
        while (true) {
            printMenu();
            var choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter the vertex name:");
                    graph.addVertex(reader.readLine());
                }
                case 2 -> {
                    System.out.println("Enter the edge: (e.g. 'a,b')");
                    var input = reader.readLine().split(",");
                    graph.addEdge(input[0], input[1]);
                }
                case 3 -> {
                    graph.dfs();
                    System.out.println("Graph:");
                    System.out.println(graph);
                }
                case 4 -> {
                    graph.dfs();
                    System.out.println("DFS:");
                    System.out.println(graph.dfsString());
                }
                case 5 -> {
                    System.out.println("Should the graph be directional? (y/n)");
                    var answer3 = reader.readLine().equalsIgnoreCase("y");
                    graph = new Graph(answer3);
                }
                default -> {
                    return;
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add vertex");
        System.out.println("2. Add edge");
        System.out.println("3. Display the vertices along with the vertices that can be directly accessed from them");
        System.out.println("4. DFS");
        System.out.println("5. Reset graph");
        System.out.println("6. exit");
    }
}
