package pl.edu.wat.wcy.tal.alg.algorithms;

import pl.edu.wat.wcy.tal.alg.exceptions.NoResultException;
import pl.edu.wat.wcy.tal.alg.graph.*;
import pl.edu.wat.wcy.tal.alg.interfaces.AlgorithmInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by Konrad on 04.06.14.
 */
public class PrimaDijkstra implements AlgorithmInterface {


    public Graph getSteinerTree(Graph in, double... params){

        double c = params[0];

        Graph mst = new Graph();

        List<Vertex> inVertex = in.getVertices();
        List<Edge> inEdges = in.getEdges();

        List<Vertex> verticesPrim = new ArrayList<Vertex>();
        List<Edge> edgesPrim = new ArrayList<Edge>();



        int verticesCountAll = 0;

        //czeszczenie zaznaczen na wierzcholkach
        for (Vertex v : in.getVertices()){
            v.setMarked(false);
            if (v.getVertexType().equals(VertexType.TERMINAL)){
                verticesCountAll++;
            }
        }
        for (Edge e : in.getEdges()){
            e.setMarked(false);
        }

        //Losowanie wierzchołka początkoweego

        Random random = new Random();

        Vertex currentVertex = null;
        while(currentVertex == null || currentVertex.getVertexType().equals(VertexType.STEINER_POINT)){
            currentVertex = inVertex.get(random.nextInt(inVertex.size()));
        }
        currentVertex.setMarked(true);
        verticesPrim.add(currentVertex.getCopy(mst));
        verticesCountAll--;

        DijkstraAlgorithm algorithm = new DijkstraAlgorithm(in);

        int liczv = 0;
        while (verticesCountAll != 0){

            PriorityQueue<Path> queue = new PriorityQueue<Path>();

            int liczd = 0;
            for (Vertex d : verticesPrim){
                algorithm.execute(in.getVertices().get(in.getVertices().indexOf(d)));
                int liczh = 0;
                for (Vertex h : inVertex){
                    if (!h.isMarked() && h.getVertexType().equals(VertexType.TERMINAL)){
                        //dijkstra
                        try{
                            List<Vertex> verticesD = algorithm.getPath(h);
                            Path p = new Path(c); // from dijkstra
                            for (int m = 0; m < verticesD.size()-1; m++){
                                for (Edge e : verticesD.get(m).getEdges()){
                                    if (e.getFrom().equals(verticesD.get(m)) && e.getTo().equals(verticesD.get(m+1)) ||
                                            e.getFrom().equals(verticesD.get(m+1)) && e.getTo().equals(verticesD.get(m)) ){
                                        p.addEdge(e);
                                        break;
                                    }
                                }
                            }
                            queue.add(p);
                        }catch (NoResultException e){
                            System.out.println(e.getMessage()+" h:"+liczh+"  d:"+liczd+"   v:"+liczv+"  ");
                            System.out.println(h.getNeighbors());
                        }
                    }liczh++;
                }liczd++;
            }

            //min path = queue.poll
            //marked min path elements
            //add elements to ST
            Path toMark = queue.poll();
            if (true){
                for (Edge e : toMark.getEdges()){
                    if (!e.getFrom().isMarked()){
                        e.getFrom().setMarked(true);
                        verticesPrim.add(e.getFrom().getCopy(mst));
                        if (e.getFrom().getVertexType().equals(VertexType.TERMINAL)){
                            verticesCountAll--;
                            liczv++;
                        }
                    }
                    if (!e.getTo().isMarked()){
                        e.getTo().setMarked(true);
                        verticesPrim.add(e.getTo().getCopy(mst));
                        if (e.getTo().getVertexType().equals(VertexType.TERMINAL)){
                            verticesCountAll--;
                            liczv++;
                        }
                    }
                    if (!e.isMarked()){
                        e.setMarked(true);
                        edgesPrim.add(e.getCopy(mst));
                    }
                }
            }
            queue.clear();

        }

        mst.setVertices(verticesPrim);
        mst.setEdges(edgesPrim);

        return mst;
    }

}
