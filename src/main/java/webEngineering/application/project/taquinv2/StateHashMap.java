package webEngineering.application.project.taquinv2;

import java.util.Arrays;
import java.util.HashMap;

public class StateHashMap extends HashMap<Integer, State> {

    public static int hashKey(int[] in) {

       int hash = Arrays.hashCode(in);
        return hash;
    }
}
