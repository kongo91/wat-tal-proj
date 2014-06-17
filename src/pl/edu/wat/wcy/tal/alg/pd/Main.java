package pl.edu.wat.wcy.tal.alg.pd;

import pl.edu.wat.wcy.tal.alg.exceptions.NoResultException;
import pl.edu.wat.wcy.tal.alg.graph.*;
import pl.edu.wat.wcy.tal.alg.vg.DijkstraAlgorithm;

import java.util.LinkedList;
import java.util.PriorityQueue;

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

        for (int i = 0 ; i < 10; i++){
            System.out.print("\n\nGENERATOR\n\n\n");
            long time = System.nanoTime();
            Graph g = Graph.generateGraph(34,56,13);
            long time2 = System.nanoTime();

            //System.out.println(g);
            String errors = "";
            int licznik = 0;
            //for (int j = 0 ; j < 100; j++){

                System.out.println("\n\n\nczas: "+(time2-time)+" [n]\nGenerowanie MST algorytmem Hakimi...");
                time = System.nanoTime();
                try{
                    Hakimi.getSteinerTree(g);
                }catch (Exception e){
                    e.printStackTrace();
                    errors=errors +"\n "+i;
                    licznik++;
                }
                time2 = System.nanoTime();
                //System.out.println(g);
                System.out.println("\n\n\nczas alg Hakimi: "+(time2-time)+" [n]");

                System.out.println("\n\n\nczas: "+(time2-time)+" [n]\nGenerowanie MST algorytmem PD...");
                time = System.nanoTime();
                try{
                    PrimaDijkstra.getSteinerTree(g,0.5);
                }catch (Exception e){
                    e.printStackTrace();
                    errors=errors +"\n "+i;
                    licznik++;
                }
                time2 = System.nanoTime();
                //System.out.println(g);
                System.out.println("\n\n\nczas alg PD: "+(time2-time)+" [n]");
            //}
            System.out.println("Licznik błędów: "+licznik);
            System.out.print("Iteracje błędne: "+errors);
        }


    }

}
