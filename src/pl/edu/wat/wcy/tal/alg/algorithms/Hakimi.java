package pl.edu.wat.wcy.tal.alg.algorithms;

import pl.edu.wat.wcy.tal.alg.graph.*;
import pl.edu.wat.wcy.tal.alg.interfaces.AlgorithmInterface;

import java.util.*;

/**
 * Created by Konrad on 23.05.14.
 */
public class Hakimi implements AlgorithmInterface {

    public Graph getSteinerTree(Graph in, double... params){

        Set<Set<Vertex>> subsets = null;
        List<Vertex> steinerPointsList = new LinkedList<Vertex>();
        for (Vertex v : in.getVertices()){
            if (v.getVertexType().equals(VertexType.STEINER_POINT))
                steinerPointsList.add(v);
        }

        Set<Vertex> steinerPoints = new HashSet<Vertex>(steinerPointsList);

        subsets = powerSet(steinerPoints);

        int min = -1;

        Graph minGraph = null;


        for (Set<Vertex> vL : subsets){
            //przygotuj graf
            //w petli usun vierzcholki z grafu
            //prim
            //porownanie

            Graph g = new Graph();
            List<Vertex> vertices = new ArrayList<Vertex>();
            List<Edge> edges = new ArrayList<Edge>();

            if (vL.size()!=0){


                for (Vertex v : in.getVertices()){
                    for (Vertex vVL : vL){
                        if (vVL.equals(v) || v.getVertexType().equals(VertexType.TERMINAL)){
                            Vertex vCopy = v.getCopy();
                            vCopy.setGraph(g);
                            vertices.add(vCopy);
                            break;
                        }
                    }
                }
            }else{
                for (Vertex v : in.getVertices()){
                    if (v.getVertexType().equals(VertexType.TERMINAL)){
                        Vertex vCopy = v.getCopy();
                        vCopy.setGraph(g);
                        vertices.add(vCopy);
                    }

                }
            }

            for (Edge e : in.getEdges()){
                boolean isAdded = false;
                for (Vertex v : vertices){
                    if (e.getFrom().equals(v)){
                        for (Vertex v1 : vertices){
                            if (e.getTo().equals(v1)){
                                Edge ee = new Edge(e.getWeight(),v,v1,g);
                                edges.add(ee);
                                isAdded = true;
                                break;
                            }
                        }
                    }
                    if (isAdded) break;
                }

            }

            g.setEdges(edges);
            g.setVertices(vertices);

            PrimMST algorithm = new PrimMST();

            Graph tmp = algorithm.getMST(g);

            int tmpWeight = -1;
            if (tmp!= null)  tmpWeight = tmp.getWeight();
            if ((tmpWeight != -1) && (min > tmpWeight || min == -1)){
                min = tmpWeight;
                minGraph = tmp;
            }

        }

        return  minGraph;

    };


    public static Set<Set<Vertex>> powerSet(Set<Vertex> originalSet) {
        Set<Set<Vertex>> sets = new HashSet<Set<Vertex>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<Vertex>());
            return sets;
        }
        List<Vertex> list = new ArrayList<Vertex>(originalSet);
        Vertex head = list.get(0);
        Set<Vertex> rest = new HashSet<Vertex>(list.subList(1, list.size()));
        for (Set<Vertex> set : powerSet(rest)) {
            Set<Vertex> newSet = new HashSet<Vertex>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }


}
