import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class VennDiagram {
    // Union combines set a and b
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }

    // Intersection finds common elements between set a and b
    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

}
