package pl.edu.wat.wcy.tal.alg.vg;

import org.junit.Test;
import pl.edu.wat.wcy.tal.alg.exceptions.NoResultException;
import pl.edu.wat.wcy.tal.alg.graph.Edge;
import pl.edu.wat.wcy.tal.alg.graph.Graph;
import pl.edu.wat.wcy.tal.alg.graph.Vertex;
import pl.edu.wat.wcy.tal.alg.graph.VertexType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Konrad on 07.06.14.
 */
public class Main {
    List<Vertex> nodes;
    List<Edge> edges;
    Graph g;

    public void test() {

        g = new Graph();
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < 11; i++) {
            Vertex location = new Vertex("i : "+i, VertexType.TERMINAL,g);
            nodes.add(location);
        }

        addLane("Edge_0", 0, 1, 85);
        addLane("Edge_1", 0, 2, 217);
        addLane("Edge_2", 0, 4, 173);
        addLane("Edge_3", 2, 6, 186);
        addLane("Edge_4", 2, 7, 103);
        addLane("Edge_5", 3, 7, 183);
        addLane("Edge_6", 5, 8, 250);
        addLane("Edge_7", 8, 9, 84);
        addLane("Edge_8", 7, 9, 167);
        addLane("Edge_9", 4, 9, 502);
        addLane("Edge_10", 9, 10, 40);
        addLane("Edge_11", 1, 10, 600);

        // Lets check from location Loc_1 to Loc_10
        g.setEdges(edges);
        g.setVertices(nodes);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(g);
        dijkstra.execute(nodes.get(0));
        LinkedList<Vertex> path = null;
        try {
            path = dijkstra.getPath(nodes.get(10));
            for (Vertex vertex : path) {
                System.out.println(vertex);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        }



    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo,
                         int duration) {
        Edge lane = new Edge(duration, nodes.get(sourceLocNo), nodes.get(destLocNo), g);
        edges.add(lane);
    }



    public static void main(String[] args){
        Main m = new Main();
        m.test();
    }



}
