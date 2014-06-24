package pl.edu.wat.wcy.tal.alg.timer;

import pl.edu.wat.wcy.tal.alg.algorithms.Algorithm;
import pl.edu.wat.wcy.tal.alg.exceptions.BadAlgorithmException;
import pl.edu.wat.wcy.tal.alg.graph.Graph;
import pl.edu.wat.wcy.tal.alg.interfaces.AlgorithmInterface;

/**
 * Created by Konrad on 19.06.14.
 */
public class AlgorithmTimer<T extends AlgorithmInterface> {

    private AlgorithmInterface alg;
    private Runtime runtime;
    private long timebefore;
    private long timeafter;
    private long memorybefore;
    private long memoryafter;

    public void instanceAlgorithmObject(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        alg = clazz.newInstance();
    }

    private void before() {
        System.out.println("\nStart algorithm "+alg.getClass().getName());
        runtime = Runtime.getRuntime();
        timebefore = System.currentTimeMillis();
        memorybefore = (runtime.totalMemory() - runtime.freeMemory());
    }

    /**
     *
     * @param in  Graph
     * @param params parameters to algorithms
     * @return time in nanosecond of algorithm
     */
    public long[] doAlgorithm(Class<T> clazz, Graph in, Algorithm.GraphHolder holder, double... params) throws BadAlgorithmException {

        try {

            if (holder == null){
                holder = new Algorithm.GraphHolder();
            }

            instanceAlgorithmObject(clazz);
            before();
            holder.setOut(alg.getSteinerTree(in,params));
            after();

            long[] results = {timeafter - timebefore, memoryafter - memorybefore};

            return results;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new BadAlgorithmException();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new BadAlgorithmException();
        }

    }

    private void after(){
        timeafter = System.currentTimeMillis();
        memoryafter = (runtime.totalMemory() - runtime.freeMemory());
        System.out.println(".\n.\n.\nStop algorithm "+alg.getClass().getName()+"\n");
    }


}
