package pw.parallelworld.singleton;

public class LazySingleton {
    private static LazySingleton singleton;

    private LazySingleton() {

    }

    public static Object getSingleton() {
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }
}
