package pl.edu.wat.wcy.tal.alg.timer;

import pl.edu.wat.wcy.tal.alg.exceptions.BadAlgorithmException;
import pl.edu.wat.wcy.tal.alg.graph.Graph;
import pl.edu.wat.wcy.tal.alg.interfaces.AlgorithmInterface;

/**
 * Created by Konrad on 19.06.14.
 */
public class AlgorithmTimer<T extends AlgorithmInterface> {

    private AlgorithmInterface alg;
    private long timebefore;
    private long timeafter;

    public void instanceAlgorithmObject(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        alg = clazz.newInstance();
    }

    private void before() {
        System.out.println("\nStart algorithm "+alg.getClass().getName());
        timebefore = System.currentTimeMillis();
    }

    /**
     *
     * @param in  Graph
     * @param params parameters to algorithms
     * @return time in nanosecond of algorithm
     */
    public long doAlgorithm(Class<T> clazz, Graph in, double... params) throws BadAlgorithmException {

        try {

            instanceAlgorithmObject(clazz);
            before();
            alg.getSteinerTree(in,params);
            after();

            return timeafter - timebefore;

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
        System.out.println(".\n.\n.\nStop algorithm "+alg.getClass().getName()+"\n");
    }


}
