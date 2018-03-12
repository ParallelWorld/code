package pw.parallelworld.singleton;

public class SafeLanHanSingleton2 {
    private static SafeLanHanSingleton2 singleton;

    private SafeLanHanSingleton2() {

    }

    public static Object getSingleton() {
        synchronized (SafeLanHanSingleton2.class) {
            if (singleton == null) {
                singleton = new SafeLanHanSingleton2();
            }
        }
        return singleton;
    }
}
