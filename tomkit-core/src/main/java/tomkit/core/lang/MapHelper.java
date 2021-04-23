//package tomkit.core.lang;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author yh
// * @since 2021/4/13
// */
//public class MapHelper<M extends Map<K, V>, K, V> {
//
//    private final M map;
//
//    private MapHelper() {
//        this.map = new HashMap<>();
//    }
//
//    private MapHelper(M map) {
//        this.map = map;
//    }
//
//    public MapHelper<M, K, V> create() {
//        return new MapHelper<>();
//    }
//
//    public M get() {
//        return this.map;
//    }
//
//}
