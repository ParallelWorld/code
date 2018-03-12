package pw.parallelworld.singleton;

public class TestSingleton {

    public static void main(String[] args) {
        // 线程不安全
        testForClass(LazySingleton.class);        // 懒加载

        // 线程安全
        testForClass(HangerSingleton.class);          // 类加载时就加载
        testForClass(InnerClassSingleton.class);    // 内部类来保证线程安全，方式跟饿汉方式差不多。不过也是资源懒加载型。
        testForClass(SafeLanHanSingleton1.class);   // 方法加synchronized关键字
        testForClass(SafeLanHanSingleton2.class);   // 方法体内加synchronized关键字
        testForClass(DCLSingleton.class);   // 双重检查，这个跟2的区别是最外层提前判断是否为null，提高了效率。特别注意，此处的volatile的关键字
        testForClass(SafeLanHanSingleton4.class);   // ThreadLocal
    }

    private static void testForClass(Class cls) {
        int count = 10;
        Thread[] threads = new Thread[count];
        for (int i$ = 0; i$ < count; i$++) {
            threads[i$] = new TestThread(cls);
        }

        for (Thread t : threads) {
            t.start();
        }
    }
}
