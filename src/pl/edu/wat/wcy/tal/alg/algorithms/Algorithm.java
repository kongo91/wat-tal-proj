package pl.edu.wat.wcy.tal.alg.algorithms;

import pl.edu.wat.wcy.tal.alg.exceptions.BadAlgorithmException;
import pl.edu.wat.wcy.tal.alg.graph.Graph;
import pl.edu.wat.wcy.tal.alg.timer.AlgorithmTimer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Konrad on 22.06.14.
 */
public class Algorithm {

    public static void copmareAlgorithmOnGraph(Graph g, double c, int repeats){

        double timeH = 0.0;
        double timePD = 0.0;

        long resultsH[] = new long[repeats];
        long resultsPD[] = new long[repeats];

        AlgorithmTimer<Hakimi> algorithmHakimiTimer = new AlgorithmTimer<Hakimi>();
        AlgorithmTimer<PrimaDijkstra> algorithmPrimaDijkstraTimer = new AlgorithmTimer<PrimaDijkstra>();

        long sumH = 0;
        long sumPD = 0;

        for (int r = 0 ; r < repeats; r++){
            try {
                resultsH[r] = algorithmHakimiTimer.doAlgorithm(Hakimi.class,g,0)[0];
                resultsPD[r] = algorithmPrimaDijkstraTimer.doAlgorithm(PrimaDijkstra.class,g,c)[0];

                sumH += resultsH[r];
                sumPD += resultsPD[r];

            } catch (BadAlgorithmException e) {
                e.printStackTrace();
            }
        }

        timeH = (double)sumH/(double)repeats;
        timePD = (double)sumPD/(double)repeats;


        System.out.println("Hakimi time: \t\t"+timeH+" [ns]");
        System.out.println("PrimDijkstra time: \t"+timePD+" [ns]");
        System.out.println("Difference time: \t"+(timePD - timeH)+" [ns]");

        StringBuilder builderH = new StringBuilder();
        StringBuilder builderH2 = new StringBuilder();

        NumberFormat nf4 = NumberFormat.getInstance();
        nf4.setMaximumFractionDigits(4);
        NumberFormat nf2 = NumberFormat.getInstance();
        nf2.setMaximumFractionDigits(2);

        builderH.append("| S").append(" \t| ").append("Hakimi").append(" \t\t\t| ").append("PrimDijkstra").append(" \t\t\t| ").append("D").append(" \t|\n");
        for (int j = 0; j < repeats; j++){
            builderH.append("| ").append(j + 1).append(" \t| ").append(nf4.format(resultsH[j])).append(" \t\t\t| ").append(nf4.format(resultsPD[j])).append(" \t\t\t| ").append(nf4.format(resultsH[j]-resultsPD[j])).append(" \t|\n");
        }

        System.out.println(builderH.toString());
    }

    public static void copmareAlgorithmOnGraphSeries(double c, int n, int series, int repeats){

        AlgorithmTimer<Hakimi> algorithmHakimiTimer = new AlgorithmTimer<Hakimi>();
        AlgorithmTimer<PrimaDijkstra> algorithmPrimaDijkstraTimer = new AlgorithmTimer<PrimaDijkstra>();

        int vertex = 3+n;
        int edges = vertex + (vertex/2)+vertex%n;
        int steinerPoints = 1+n;

        double timesH[] = new double[series];
        double timesPD[] = new double[series];

        double memoryH[] = new double[series];
        double memoryPD[] = new double[series];

        long resultsH[][] = new long[series][repeats];
        long resultsPD[][] = new long[series][repeats];

        long resultsMemH[][] = new long[series][repeats];
        long resultsMemPD[][] = new long[series][repeats];

        for (int j = 0; j < series; j++){

            Graph gIn = Graph.generateGraph(vertex,edges,steinerPoints);

            long sumH = 0;
            long sumPD = 0;

            long sumHM = 0;
            long sumPDM = 0;

            for (int r = 0 ; r < repeats ; r++){
                try {
                    resultsH[j][r] = algorithmHakimiTimer.doAlgorithm(Hakimi.class,gIn,0)[0];
                    resultsPD[j][r] = algorithmPrimaDijkstraTimer.doAlgorithm(PrimaDijkstra.class,gIn,c)[0];

                    resultsMemH[j][r] = algorithmHakimiTimer.doAlgorithm(Hakimi.class,gIn,0)[1];
                    resultsMemPD[j][r] = algorithmPrimaDijkstraTimer.doAlgorithm(PrimaDijkstra.class,gIn,c)[1];

                    sumH += resultsH[j][r];
                    sumPD += resultsPD[j][r];

                    sumHM += resultsMemH[j][r];
                    sumPDM += resultsMemPD[j][r];

                } catch (BadAlgorithmException e) {
                    e.printStackTrace();
                }
            }

            timesH[j] = (double)sumH/(double)repeats;
            timesPD[j] = (double)sumPD/(double)repeats;

            memoryH[j] = (double)sumHM/(double)repeats;
            memoryPD[j] = (double)sumPDM/(double)repeats;

            vertex++;
            edges += vertex/2;
            steinerPoints++;//= j%2;

        }

        StringBuilder builderH = new StringBuilder();
        StringBuilder builderH2 = new StringBuilder();

        NumberFormat nf4 = NumberFormat.getInstance();
        nf4.setMaximumFractionDigits(4);
        NumberFormat nf2 = NumberFormat.getInstance();
        nf2.setMaximumFractionDigits(2);

        builderH.append("| N").append(" \t| ").append("Hakimi").append(" \t\t\t| ").append("PrimDijkstra").append(" \t\t\t| ").append("D").append(" \t|\n");
        builderH2.append("N").append(";").append("Hakimi").append(";").append("PrimDijkstra").append(";").append("Lepszy").append(";").append("%").append("\n");

        for (int j = 0; j < series; j++){
            builderH.append("| ").append(j + 1).append(" \t| ").append(nf4.format(timesH[j])).append(" \t\t\t| ").append(nf4.format(timesPD[j])).append(" \t\t\t| ").append(nf4.format(timesH[j]-timesPD[j])).append(" \t|\n");
            builderH2.append(j+1).append(";").append(nf4.format(timesH[j])).append(";").append(nf4.format(timesPD[j])).append(";");
            if (timesH[j] > timesPD[j]){
                builderH2.append("PrimDijkstra").append(";");
                double p = ((timesH[j]-timesPD[j])/timesH[j])*100.0;
                builderH2.append(nf2.format(p)+"%").append(";");
            }else if (timesH[j] < timesPD[j]){
                builderH2.append("Hakimi").append(";");
                double p = ((timesPD[j]-timesH[j])/timesPD[j])*100.0;
                builderH2.append(nf2.format(p)+"%").append(";");
            }else{
                builderH2.append("Brak").append(";");
                builderH2.append("0%").append(";");
            }
            builderH2.append("\n");
        }
        //System.out.println(builderH+"\n");


        StringBuilder builderM = new StringBuilder();
        StringBuilder builderM2 = new StringBuilder();


        builderM.append("| N").append(" \t| ").append("Hakimi").append(" \t\t\t| ").append("PrimDijkstra").append(" \t\t\t| ").append("D").append(" \t|\n");
        builderM2.append("N").append(";").append("Hakimi").append(";").append("PrimDijkstra").append(";").append("Lepszy").append(";").append("%").append("\n");

        for (int j = 0; j < series; j++){
            builderM.append("| ").append(j + 1).append(" \t| ").append(nf4.format(memoryH[j])).append(" \t\t\t| ").append(nf4.format(memoryPD[j])).append(" \t\t\t| ").append(nf4.format(memoryH[j]-memoryPD[j])).append(" \t|\n");
            builderM2.append(j+1).append(";").append(nf4.format(memoryH[j])).append(";").append(nf4.format(memoryPD[j])).append(";");
            if (memoryH[j] > memoryPD[j]){
                builderM2.append("PrimDijkstra").append(";");
                double p = ((memoryH[j]-memoryPD[j])/memoryH[j])*100.0;
                builderM2.append(nf2.format(p)+"%").append(";");
            }else if (memoryH[j] < memoryPD[j]){
                builderM2.append("Hakimi").append(";");
                double p = ((memoryPD[j]-memoryH[j])/memoryPD[j])*100.0;
                builderM2.append(nf2.format(p)+"%").append(";");
            }else{
                builderM2.append("Brak").append(";");
                builderM2.append("0%").append(";");
            }
            builderM2.append("\n");
        }




        try {

            String content = builderH2.toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
            String d = sdf.format(new Date());

            File file = new File("./results/time_series_"+d+".csv");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content.replace(".",","));
            bw.close();


            String content2 = builderM2.toString();
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd_hhmmss");
            String d2 = sdf.format(new Date());

            File file2 = new File("./results/memory_series_"+d+".csv");

            if (!file2.exists()) {
                file2.createNewFile();
            }

            FileWriter fw2 = new FileWriter(file2.getAbsoluteFile());
            BufferedWriter bw2 = new BufferedWriter(fw2);
            bw2.write(content2.replace(".",","));
            bw2.close();

            System.out.println("Wynik w formacie CSV wyeksportowano do plików: \n(1.) "+file.getAbsolutePath()+"\n(1.) "+file2.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
