package pl.edu.wat.wcy.tal.alg.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Konrad on 11.05.14.
 */
public class Vertex {

    private String name;
    private Graph graph;
    protected VertexType vertexType;
    protected boolean marked;


    public List<Edge> getEdges(){

        List<Edge> edges = new ArrayList<Edge>();
        for (Edge e : graph.getEdges()){
            if (e.getFrom().getName().equals(name) || e.getTo().getName().equals(name))
                edges.add(e);
        }

        return edges;

    }

    public List<Vertex> getNeighbors(){

        List<Vertex> neighbors = new ArrayList<Vertex>();

        for (Edge e : getEdges()){
            if (!e.getTo().getName().equals(name))
                neighbors.add(e.getTo());
            else if (!e.getFrom().getName().equals(name))
                neighbors.add(e.getFrom());
        }

        return neighbors;

    }

    public String getName() {
        return name;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setName(String name) {
        this.name = vertexType==VertexType.STEINER_POINT ? name+"^" : name;
    }

    public Vertex(String name, VertexType type,Graph g){
        System.out.println("Creating ["+type+"] with name: ["+name+"]");
        this.vertexType = type;
        this.name = vertexType==VertexType.STEINER_POINT ? name+"^" : name;
        graph = g;
        graph.addVertex(this);
    }

    public VertexType getVertexType() {
        return vertexType;
    }

    public void setVertexType(VertexType vertexType) {
        this.vertexType = vertexType;
        if (vertexType.equals(VertexType.STEINER_POINT))
            name = name + "^";
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (Edge e : getEdges()){
            sb.append(name).append("--").append(e.getWeight()).append("--");
            if (e.getFrom().equals(this))
                sb.append(e.getTo().getName());
            else
                sb.append(e.getFrom().getName());
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (name != null ? !name.equals(vertex.name) : vertex.name != null) return false;
        if (vertexType != vertex.vertexType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (vertexType != null ? vertexType.hashCode() : 0);
        return result;
    }
}
