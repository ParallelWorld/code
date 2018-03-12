package pw.parallelworld.singleton;

public class SafeLanHanSingleton4 {
    private static SafeLanHanSingleton4 singleton;
    private static ThreadLocal<SafeLanHanSingleton4> threadLocal = new ThreadLocal<SafeLanHanSingleton4>();


    private SafeLanHanSingleton4() {

    }

    public static Object getSingleton() {
        if (threadLocal.get() == null) {
            if (singleton == null) {
                singleton = new SafeLanHanSingleton4();
            }
            threadLocal.set(singleton);
        }
        return threadLocal.get();
    }
}
