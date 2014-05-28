package pl.edu.wat.wcy.tal.alg.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Konrad on 11.05.14.
 */
public class Graph {

    private List<Vertex> vertices;

    private List<Vertex> stainerPoints;

    private List<Edge> edges;

    public Graph(){
        vertices = new LinkedList<Vertex>();
        edges = new LinkedList<Edge>();
        stainerPoints = new LinkedList<Vertex>();
    }

    public void addVertex(Vertex v){
        vertices.add(v);
    }

    public void addEdge(Edge e){
        edges.add(e);
    }

    public List<Vertex> getVertices() {
        return vertices;
    }
    public List<Edge> getEdges() {
        return edges;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Vertex> getStainerPoints() {
        return stainerPoints;
    }

    public void setStainerPoints(List<Vertex> stainerPoints) {
        this.stainerPoints = stainerPoints;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : vertices){
            sb.append(v.toString()).append("\n");
        }

        return sb.toString();
    }

    /**
     *
     * @param v liczba wierzcholkow
     * @param maxE maksymalna liczba krawedzi - musi byc wieksze lub rowna v
     * @param s liczba punktow steinera - maksymalnie v-2, min 0
     * @return
     */
    public static Graph generateGraph(int v, int maxE, int s){

        if (maxE <= v || s > v-2 || (maxE <= 0 && s < 0 ))
            throw new IllegalArgumentException("Zle parametry");

        Graph g = new Graph();
        List<Vertex> vertices = new ArrayList<Vertex>();
        List<Edge> edges = new ArrayList<Edge>();

        for (int i = 0 ; i < v; i++){
            Vertex vertex = new Vertex("V"+i,VertexType.TERMINAL,g);
            vertices.add(vertex);
        }

        g.setVertices(vertices);

        int i = 0;

        Random r = new Random();
        Vertex vv1 = vertices.get(i);
        for (int m = 1; m <= 3; m++){
            i++;
            while(i < (m * (v/3))){
                Vertex vv2 = vertices.get(i);
                Edge e = new Edge(r.nextInt(Edge.MAX_EDGE_WEIGHT),vv1,vv2,g);
                edges.add(e);
                i++;
            }
            Edge e = new Edge(r.nextInt(Edge.MAX_EDGE_WEIGHT),vv1,vertices.get(i),g);
            edges.add(e);
            vv1 = vertices.get(i);
        }

        while (i <= maxE){

            Vertex vv  = null;
            Vertex vvv = null;

            boolean isToAdd = false;

            while(!isToAdd){
                vv = vertices.get(r.nextInt(vertices.size()));
                vvv = vertices.get(r.nextInt(vertices.size()));
                if (!vv.equals(vvv)){
                    for (Edge e:vv.getEdges()){
                        if (!e.getFrom().equals(vvv) && !e.getTo().equals(vvv)){
                            isToAdd = true;
                        }
                    }
                }
            }
            Edge e = new Edge(r.nextInt(Edge.MAX_EDGE_WEIGHT),vv,vvv,g);
            edges.add(e);
            i++;
        }

        g.setEdges(edges);

        for (i = 0; i < s; i++){

            boolean isMarked = false;

            Vertex vv = vertices.get(r.nextInt(vertices.size()));

            while (isMarked){
                if (vv.getVertexType().equals(VertexType.STEINER_POINT)){
                    vv = vertices.get(r.nextInt(vertices.size()));
                }else{
                    isMarked = true;
                }
            }
            vv.setVertexType(VertexType.STEINER_POINT);
        }

        return g;
    }

    public int getWeight(){
        int out = 0;
        for (Edge e : edges){
            out = out + e.getWeight();
        }

        return out;

    }


}
