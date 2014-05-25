package pl.edu.wat.wcy.tal.alg.graph;

import java.util.*;

/**
 * Created by Konrad on 23.05.14.
 */
public class Hakimi {

    public  static Graph getSteinerTree(Graph in){

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


        }

        return  null;

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
