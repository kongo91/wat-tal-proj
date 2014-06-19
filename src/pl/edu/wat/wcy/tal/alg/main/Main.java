package pl.edu.wat.wcy.tal.alg.main;

import pl.edu.wat.wcy.tal.alg.algorithms.Hakimi;
import pl.edu.wat.wcy.tal.alg.algorithms.PrimaDijkstra;
import pl.edu.wat.wcy.tal.alg.exceptions.BadAlgorithmException;
import pl.edu.wat.wcy.tal.alg.graph.Graph;
import pl.edu.wat.wcy.tal.alg.timer.AlgorithmTimer;

/**
 * Created by Konrad on 11.05.14.
 */
public class Main {


    public static void main(String[] args) {
        /*Graph graph = new Graph();

        Vertex vA = new Vertex("A", VertexType.TERMINAL,graph);
        Vertex vB = new Vertex("B",VertexType.STEINER_POINT,graph);
        Vertex vC = new Vertex("C",VertexType.TERMINAL,graph);
        Vertex vD = new Vertex("D",VertexType.STEINER_POINT,graph);
        Vertex vE = new Vertex("E",VertexType.TERMINAL,graph);

        Edge eAB = new Edge(2,vA,vB,graph);
        Edge eAD = new Edge(1,vA,vD,graph);
        Edge eAC = new Edge(2,vA,vC,graph);
        Edge eBC = new Edge(4,vB,vC,graph);
        Edge eBD = new Edge(3,vB,vD,graph);
        Edge eBE = new Edge(3,vB,vE,graph);
        Edge eCE = new Edge(2,vC,vE,graph);
        Edge eDC = new Edge(17,vD,vC,graph);

        System.out.print("\n\n\n\n\n\n\n\n\n");

        System.out.println(PrimaDijkstra.getSteinerTree(graph,0.12));

        System.out.println(Hakimi.getSteinerTree(graph));*/


        /*DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(vA);
        LinkedList<Vertex> path = null;
        try {
            path = dijkstra.getPath(vE);
            for (Vertex vertex : path) {
                System.out.println(vertex);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        }*/




        /*PrimMST.getMST(graph);*/

       // System.out.print(graph.toString());

        int mb = 1024*1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [MB] #####");

        //Print used memory
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        System.out.println("Free Memory:"
                + runtime.freeMemory() / mb);

        //Print total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);

        //Print Maximum available memory
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);


        long timeH = 0;
        long timePD = 0;
        Graph g = Graph.generateGraph(26,34,10);

        System.out.print("\n\nGraph genereted! "+"\n\n\n");

        AlgorithmTimer<Hakimi> algorithmHakimiTimer = new AlgorithmTimer<Hakimi>();
        AlgorithmTimer<PrimaDijkstra> algorithmPrimaDijkstraTimer = new AlgorithmTimer<PrimaDijkstra>();
        try {
            timeH = algorithmHakimiTimer.doAlgorithm(Hakimi.class,g,0);
            timePD = algorithmPrimaDijkstraTimer.doAlgorithm(PrimaDijkstra.class,g,0.5);
        } catch (BadAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println("Hakimi time: \t\t"+timeH+" [ns]");
        System.out.println("PrimDijkstra time: \t"+timePD+" [ns]");
        System.out.println("Difference time: \t"+(timePD - timeH)+" [ns]");

        System.out.println("\n\n\nSERIA OBLICZEN\n\n\n");
        int vertex = 8;
        int edges = 12;
        int steinerPoints = 3;

        long timesH[] = new long[30];
        long timesPD[] = new long[30];

        for (int j = 0; j < 15; j++){

            Graph gIn = Graph.generateGraph(vertex,edges,steinerPoints);

            try {
                timesH[j] = algorithmHakimiTimer.doAlgorithm(Hakimi.class,gIn,0);
                timesPD[j] = algorithmPrimaDijkstraTimer.doAlgorithm(PrimaDijkstra.class,gIn,0.5);
            } catch (BadAlgorithmException e) {
                e.printStackTrace();
            }

            vertex++;
            edges += vertex/2;
            steinerPoints += j%2;

        }

        StringBuilder builderH = new StringBuilder();
        builderH.append("| Hakimi").append(" \t| ").append("PrimDijkstra").append(" \t| ").append("D").append(" \t|\n");
        for (int j = 0; j < 15; j++){
            builderH.append("| ").append(timesH[j]).append(" \t\t| ").append(timesPD[j]).append(" \t\t\t| ").append(timesH[j]-timesPD[j]).append(" \t|\n");
        }
        System.out.println(builderH+"\n");


        //Getting the runtime reference from system
        runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [MB] #####");

        //Print used memory
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        System.out.println("Free Memory:"
                + runtime.freeMemory() / mb);

        //Print total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);

        //Print Maximum available memory
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);
    }

}
