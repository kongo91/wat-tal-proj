package pl.edu.wat.wcy.tal.alg.mode;

import pl.edu.wat.wcy.tal.alg.algorithms.Algorithm;
import pl.edu.wat.wcy.tal.alg.graph.Graph;

import java.util.List;

/**
 * Created by Konrad on 22.06.14.
 */
public class Mode {

    public static void userGraph(List<String> lines,Double c){

        Graph g = Graph.genereteGraphFromParams(lines);
        Algorithm.copmareAlgorithmOnGraph(g,c);

    }

    public static void auto(String line) {

        double c = 0.5;

        String params[] = line.split(";");
        if (params.length < 3 && params.length > 4) throw new IllegalArgumentException("Bad number of parameers in line "+line);

        if (params[0] == null) throw new IllegalArgumentException("Bad number of vertices in line "+line);
        if (params[1] == null) throw new IllegalArgumentException("Bad number of edges in line "+line);
        if (params[2] == null) params[2] = "0";
        if (params[3] != null){
            c = Double.parseDouble(params[3]);
        }
        Graph g = Graph.generateGraph(Integer.parseInt(params[0]),Integer.parseInt(params[1]),Integer.parseInt(params[2]));

        Algorithm.copmareAlgorithmOnGraph(g,c);
    }

    public static void generate(String line){

        double c = 0.5;

        String params[] = line.split(";");
        if (params.length < 2 && params.length > 3) throw new IllegalArgumentException("Bad number of parameers in line "+line);

        if (params[0] == null) throw new IllegalArgumentException("Bad number of series in line "+line);
        if (params[1] == null) throw new IllegalArgumentException("Bad number of problem size in line "+line);
        if (params[2] != null){
            c = Double.parseDouble(params[2]);
        }

        Algorithm.copmareAlgorithmOnGraphSeries(c,Integer.parseInt(params[1]),Integer.parseInt(params[0]));

    }
}
