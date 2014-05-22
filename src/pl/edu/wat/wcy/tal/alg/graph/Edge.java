package pl.edu.wat.wcy.tal.alg.graph;

/**
 * Created by Konrad on 11.05.14.
 */
public class Edge implements Comparable<Edge>{

    private int weight;
    private Vertex from;
    private Vertex to;
    private Graph graph;
    private boolean marked;

    public final static int MAX_EDGE_WEIGHT = 20;

    public Edge(int weight, Vertex from, Vertex to, Graph g) {
        System.out.println("Creating edge width weight: ["+weight+"] from vertex ["+from.getName()+"] to ["+to.getName()+"]");
        this.weight = weight;
        this.from = from;
        this.to = to;
        this.graph = g;
        graph.addEdge(this);
    }

    public Vertex getFrom() {
        return from;
    }

    public Vertex getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    @Override
    public int compareTo(Edge o) {
        int objWeight = o.getWeight();
        if (objWeight==this.weight){
            return 0;
        }else if (objWeight>this.weight){
            return -1;
        }else if (objWeight<this.weight){
            return 1;
        }
        return 0;
    }

    public Graph getGraph() {
        return graph;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(from.getName()).append("--").append(weight).append("--").append(to.getName()).append("]");
        return sb.toString();
    }
}
