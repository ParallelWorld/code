package pw.parallelworld.singleton;

public class SafeLanHanSingleton3 {
    private static volatile SafeLanHanSingleton3 singleton;

    private SafeLanHanSingleton3() {

    }

    public static Object getSingleton() {
        if (singleton == null) {
            synchronized (SafeLanHanSingleton3.class) {
                if (singleton == null) {
                    singleton = new SafeLanHanSingleton3();
                }
            }
        }

        return singleton;
    }
}
