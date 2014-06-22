package pl.edu.wat.wcy.tal.alg.algorithms;

import pl.edu.wat.wcy.tal.alg.exceptions.BadAlgorithmException;
import pl.edu.wat.wcy.tal.alg.graph.Graph;
import pl.edu.wat.wcy.tal.alg.timer.AlgorithmTimer;

/**
 * Created by Konrad on 22.06.14.
 */
public class Algorithm {

    public static void copmareAlgorithmOnGraph(Graph g, double c){

        long timeH = 0;
        long timePD = 0;

        AlgorithmTimer<Hakimi> algorithmHakimiTimer = new AlgorithmTimer<Hakimi>();
        AlgorithmTimer<PrimaDijkstra> algorithmPrimaDijkstraTimer = new AlgorithmTimer<PrimaDijkstra>();
        try {
            timeH = algorithmHakimiTimer.doAlgorithm(Hakimi.class,g,0);
            timePD = algorithmPrimaDijkstraTimer.doAlgorithm(PrimaDijkstra.class,g,c);
        } catch (BadAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println("Hakimi time: \t\t"+timeH+" [ns]");
        System.out.println("PrimDijkstra time: \t"+timePD+" [ns]");
        System.out.println("Difference time: \t"+(timePD - timeH)+" [ns]");
    }

    public static void copmareAlgorithmOnGraphSeries(double c, int n, int series){

        AlgorithmTimer<Hakimi> algorithmHakimiTimer = new AlgorithmTimer<Hakimi>();
        AlgorithmTimer<PrimaDijkstra> algorithmPrimaDijkstraTimer = new AlgorithmTimer<PrimaDijkstra>();

        int vertex = 3+n;
        int edges = vertex + (vertex/2)+vertex%n;
        int steinerPoints = 1;

        long timesH[] = new long[series];
        long timesPD[] = new long[series];

        for (int j = 0; j < series; j++){

            Graph gIn = Graph.generateGraph(vertex,edges,steinerPoints);

            try {
                timesH[j] = algorithmHakimiTimer.doAlgorithm(Hakimi.class,gIn,0);
                timesPD[j] = algorithmPrimaDijkstraTimer.doAlgorithm(PrimaDijkstra.class,gIn,c);
            } catch (BadAlgorithmException e) {
                e.printStackTrace();
            }

            vertex++;
            edges += vertex/2;
            steinerPoints += j%2;

        }

        StringBuilder builderH = new StringBuilder();
        builderH.append("| N").append(" \t| ").append("Hakimi").append(" \t| ").append("PrimDijkstra").append(" \t| ").append("D").append(" \t|\n");
        for (int j = 0; j < series; j++){
            builderH.append("| ").append(j+1).append(" \t| ").append(timesH[j]).append(" \t\t| ").append(timesPD[j]).append(" \t\t| ").append(timesH[j]-timesPD[j]).append(" \t|\n");
        }
        System.out.println(builderH+"\n");
    }

}
