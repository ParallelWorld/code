package pw.parallelworld.singleton;

public class DCLSingleton {
    private static volatile DCLSingleton singleton;

    private DCLSingleton() {

    }

    public static Object getSingleton() {
        if (singleton == null) {
            synchronized (DCLSingleton.class) {
                if (singleton == null) {
                    singleton = new DCLSingleton();
                }
            }
        }

        return singleton;
    }
}
