package pl.edu.wat.wcy.tal.alg.pd;

import pl.edu.wat.wcy.tal.alg.graph.*;

import java.util.PriorityQueue;

/**
 * Created by Konrad on 11.05.14.
 */
public class Main {


    public static void main(String[] args) {
        System.out.println("prim");

        Graph graph = new Graph();

        Vertex vA = new Vertex("A", VertexType.TERMINAL,graph);
        Vertex vB = new Vertex("B",VertexType.TERMINAL,graph);
        Vertex vC = new Vertex("C",VertexType.TERMINAL,graph);
        Vertex vD = new Vertex("D",VertexType.STEINER_POINT,graph);
        Vertex vE = new Vertex("E",VertexType.STEINER_POINT,graph);

        Edge eAB = new Edge(2,vA,vB,graph);
        Edge eAD = new Edge(1,vA,vD,graph);
        Edge eAC = new Edge(2,vA,vC,graph);
        Edge eBC = new Edge(4,vB,vC,graph);
        Edge eBD = new Edge(3,vB,vD,graph);
        Edge eBE = new Edge(3,vB,vE,graph);
        Edge eCE = new Edge(2,vC,vE,graph);
        Edge eDE = new Edge(17,vD,vE,graph);

        System.out.print("\n\n\n\n\n\n\n\n\n");

        Hakimi.getSteinerTree(graph);


        /*PrimMST.getMST(graph);*/



       // System.out.print(graph.toString());

        /*System.out.print("\n\nGENERATOR\n\n\n");
        long time = System.nanoTime();
        Graph g = Graph.generateGraph(160,269,50);
        long time2 = System.nanoTime();
        //System.out.println(g);
        System.out.println("\n\n\nczas: "+(time2-time)+" [n]\nGenerowanie MST algorytmem Prima...");
        time = System.nanoTime();
        PrimMST.getMST(g);
        time2 = System.nanoTime();
        //System.out.println(g);
        System.out.println("\n\n\nczas alg Prima: "+(time2-time)+" [n]");*/


    }

}
