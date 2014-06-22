package pl.edu.wat.wcy.tal.alg.graph;

import java.util.*;

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
            while(i < ((m * (v/3))-1)+v%3){
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

        for (Vertex vvvv : g.getVertices()){
            if (vvvv.getEdges().size()==0) throw new IllegalArgumentException("v: "+vvvv.getName());
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

    public static Graph genereteGraphFromParams(List<String> lines){

        ArrayList<String> vertexLines = new ArrayList<String>();
        ArrayList<String> edgesLines = new ArrayList<String>();

        for (String line : lines){

            String[] vTab = line.split(";");
            if (vTab[0].equalsIgnoreCase("v") || vTab[0].equalsIgnoreCase("t") || vTab[0].equalsIgnoreCase("s")){
                vertexLines.add(line);
            }else if (vTab[0].equalsIgnoreCase("e")){
                edgesLines.add(line);
            }

        }

        Graph out = new Graph();

        List<Vertex> vertices = new ArrayList<Vertex>();
        LinkedList<Edge> edges = new LinkedList<Edge>();

        for (String line : vertexLines){

            Vertex v = new Vertex();
            v.setGraph(out);
            v.setMarked(false);

            String vTab[] = line.split(";");
            if (vTab.length!= 2) throw new IllegalArgumentException("Bad number of parameters in line: "+line);
            if (vTab[0].equalsIgnoreCase("v") || vTab[0].equalsIgnoreCase("t")){
                v.setVertexType(VertexType.TERMINAL);
            }else if (vTab[0].equalsIgnoreCase("s")){
                v.setVertexType(VertexType.STEINER_POINT);
            }else {
                throw new IllegalArgumentException("Unknown type of vertex in line: "+line);
            }

            if (vTab[1]!=null){
                v.setName(vTab[1]);
            }else throw new IllegalArgumentException("Could not read vertex name i line: "+line);

            vertices.add(v);
        }

        for(String line : edgesLines){

            Edge e = new Edge();
            e.setGraph(out);
            e.setMarked(false);

            String eTab[] = line.split(";");
            if (eTab.length != 4) throw new IllegalArgumentException("Bad number of parameter in line"+line);
            if (eTab[1] != null){
                Integer weight = Integer.parseInt(eTab[1]);
                e.setWeight(weight);
            }else{
                throw new IllegalArgumentException("Could not read weight of edge in line: "+line);
            }

            if (eTab[2]==null) throw new IllegalArgumentException("Could not read vertex \"from\" of edge in line: "+line);
            if (eTab[3]==null) throw new IllegalArgumentException("Could not read vertex \"to\" of edge in line: "+line);

            for (Vertex v : vertices){
                if (v.getName().equalsIgnoreCase(eTab[2])) e.setFrom(v);
                if (v.getName().equalsIgnoreCase(eTab[3])) e.setTo(v);
            }
            if (e.getTo()==null)  throw new IllegalArgumentException("Vertex \"to\" of edge is not defined in line: "+line);
            if (e.getFrom()==null)  throw new IllegalArgumentException("Vertex \"from\" of edge is not defined in line: "+line);

            edges.add(e);
        }

        out.setEdges(edges);
        out.setVertices(vertices);

        return out;
    }


}
