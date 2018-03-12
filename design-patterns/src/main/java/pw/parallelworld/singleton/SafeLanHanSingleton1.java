package pw.parallelworld.singleton;

public class SafeLanHanSingleton1 {
    private static SafeLanHanSingleton1 singleton;

    private SafeLanHanSingleton1() {

    }

    public static synchronized Object getSingleton() {
        if (singleton == null) {
            singleton = new SafeLanHanSingleton1();
        }
        return singleton;
    }
}
