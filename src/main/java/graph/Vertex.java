package graph;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@RequiredArgsConstructor
public class Vertex {
    private final String name;
    private final ArrayList<Vertex> adjacencyList = new ArrayList<>();
    @Setter
    private Color color = Color.WHITE;
    @Setter
    private Integer enterTime;
    @Setter
    private Integer exitTime;

    public String dfsString() {
        return "'" + name + "': (" + enterTime + "," + exitTime + ")";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(name + " = [");
        for (int i = 0; i < adjacencyList.size() - 1; i++) {
            builder.append(adjacencyList.get(i).name).append(",");
        }
        if (!adjacencyList.isEmpty()) {
            builder.append(adjacencyList.get(adjacencyList.size() - 1).name);
        }
        builder.append("]");
        return builder.toString();
    }
}
