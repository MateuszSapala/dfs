package graph;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class Graph {
    private final ArrayList<Vertex> vertices = new ArrayList<>();
    private final boolean isDirected;

    private Vertex findEdgeByName(String name) {
        return vertices
                .stream()
                .filter(v -> v.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Vertex " + name + " not in graph"));
    }

    public void addVertex(String name) {
        vertices.add(new Vertex(name));
    }

    public void addEdge(String v1Name, String v2Name) {
        var v1 = findEdgeByName(v1Name);
        var v2 = findEdgeByName(v2Name);
        v1.getAdjacencyList().add(v2);
        if (!isDirected) {
            v2.getAdjacencyList().add(v1);
        }
    }

    public void resetVerticesColorAndTime() {
        vertices.forEach(v -> {
            v.setColor(Color.WHITE);
            v.setEnterTime(null);
            v.setExitTime(null);
        });
    }

    public void dfs() {
        resetVerticesColorAndTime();
        AtomicInteger count = new AtomicInteger(0);
        for (var v : vertices) {
            if (v.getColor() == Color.WHITE) {
                dfsVisit(v, count);
            }
        }
    }

    public void dfsVisit(Vertex v, AtomicInteger count) {
        v.setColor(Color.GRAY);
        v.setEnterTime(count.addAndGet(1));
        for (var adjV : v.getAdjacencyList()) {
            if (adjV.getColor() == Color.WHITE) {
                dfsVisit(adjV, count);
            }
        }
        v.setColor(Color.BLACK);
        v.setExitTime(count.addAndGet(1));
    }

    public String dfsString() {
        StringBuilder builder = new StringBuilder();
        for (var v : vertices) {
            builder.append(v.dfsString()).append("\n");
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (var v : vertices) {
            builder.append(v).append("\n");
        }
        return builder.toString();
    }
}
