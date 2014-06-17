package pl.edu.wat.wcy.tal.alg.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 04.06.14.
 */
public class Path implements Comparable<Path>{

    protected List<Edge> edges;

    protected double weight = 0;

    protected double c;

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public double getWeight() {
        weight = 0;
        double h = 0.0;
        for (Edge e : edges){
            weight += e.getWeight();
            h += 1;
        }

        weight = h*c + weight;

        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Path(double c){
        this.c = c;
        edges = new ArrayList<Edge>();
    }

    public Path(List<Edge> edges, double c) {
        this.edges = edges;
        this.c = c;
        getWeight();
    }

    public Path() {
        this.edges = new ArrayList<Edge>();
        weight = 0;
        c = 0.5;
    }

    public void addEdge(Edge e){
        edges.add(e);



    }

    @Override
    public int compareTo(Path o) {
        if (this.getWeight() == o.getWeight())
            return 0;
        else if (this.getWeight() > o.getWeight())
            return 1;
        else
            return -1;
    }
}
