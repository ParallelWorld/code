package pw.parallelworld.singleton;

import java.lang.reflect.Method;
import java.util.Set;

public class TestThread extends Thread {
    private Class cls = null;

    public TestThread(Class cls) {
        this.cls = cls;
    }

    @Override
    public void run() {
        try {
            Method method = cls.getMethod("getSingleton");
            Object o = method.invoke(this.cls);
            System.out.println(cls.getSimpleName() + " " + o.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
