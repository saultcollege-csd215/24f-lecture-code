package java._03_staying_immutable.mutablejava.immutable;

import java.util.HashMap;
import java.util.Map;

public class ImmutableMapUtils {

    public static <T,U> Map<T,U> put(Map<T,U> original, T key, U value) {
        var copy = new HashMap<T,U>(original);
        copy.put(key, value);
        return copy;
    }

}
