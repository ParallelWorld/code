package pw.parallelworld.singleton;

public class EhanSingleton {
    private static EhanSingleton singleton = new EhanSingleton();

    private EhanSingleton() {

    }

    public static Object getSingleton() {
        return singleton;
    }
}
