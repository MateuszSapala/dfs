package file;

import java.util.ArrayList;

public record JsonGraph(boolean isDirected, ArrayList<String> vertices, ArrayList<ArrayList<String>> edges) {
}
