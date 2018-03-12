package pw.parallelworld.singleton;

public class LanHanSingleton {
    private static LanHanSingleton singleton;

    private LanHanSingleton() {

    }

    public static Object getSingleton() {
        if (singleton == null) {
            singleton = new LanHanSingleton();
        }
        return singleton;
    }
}
