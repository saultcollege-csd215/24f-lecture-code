package java._04_stratified_design.megamart.stratified_with_abstractionbarrier;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    public static <T,U> Map<T,U> put(Map<T,U> original, T key, U value) {
        var copy = new HashMap<T,U>(original);
        copy.put(key, value);

        return copy;
    }

    public static <T, U> Map<T, U> remove(Map<T, U> original, T key) {
        var copy = new HashMap<T, U>(original);
        copy.remove(key);
        return copy;
    }

}
