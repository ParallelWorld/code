package pw.parallelworld.singleton;

/**
 * 饿汉式单例
 */
public class HangerSingleton {
    private static HangerSingleton singleton = new HangerSingleton();

    private HangerSingleton() {

    }

    public static Object getSingleton() {
        return singleton;
    }
}
