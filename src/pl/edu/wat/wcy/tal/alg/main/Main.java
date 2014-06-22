package pl.edu.wat.wcy.tal.alg.main;

import pl.edu.wat.wcy.tal.alg.mode.Mode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Konrad on 11.05.14.
 */
public class Main {

    public static void main(String[] args) {

        ArrayList<String> lines = new ArrayList<String>();

        String param3 = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(args[1]));
            bufferedReader.readLine();
            String currentLine = bufferedReader.readLine();
            while(currentLine != null){
                lines.add(currentLine);
                currentLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        if (args[0].equalsIgnoreCase("graph")){
            double c = 0.5;
            if (args.length == 3){
                try{
                    c = Double.parseDouble(args[2]);
                }catch (Exception e ){}
            }
            Mode.userGraph(lines,c);
        }else if (args[0].equalsIgnoreCase("generate")){
            Mode.generate(lines.get(0));
        }else if (args[0].equalsIgnoreCase("auto")){
            Mode.auto(lines.get(0));
        }else {
            System.out.println("Bad mode!");
        }
    }

}

        /*
        int mb = 1024*1024;

        Runtime runtime = null;
        runtime = Runtime.getRuntime();
        System.out.println("##### Heap utilization statistics [MB] #####");
        System.out.println("Used Memory:"+ (runtime.totalMemory() - runtime.freeMemory()) / mb);
        System.out.println("Free Memory:"+ runtime.freeMemory() / mb);
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);*/

    /*  Graph graph = new Graph();

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
        Edge eDC = new Edge(17,vD,vC,graph); */
