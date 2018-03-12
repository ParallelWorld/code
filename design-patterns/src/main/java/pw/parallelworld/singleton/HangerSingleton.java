package pw.parallelworld.singleton;

public class HangerSingleton {
    private static HangerSingleton singleton = new HangerSingleton();

    private HangerSingleton() {

    }

    public static Object getSingleton() {
        return singleton;
    }
}
