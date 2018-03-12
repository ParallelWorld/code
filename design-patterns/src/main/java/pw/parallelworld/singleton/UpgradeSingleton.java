package pw.parallelworld.singleton;

import java.io.Serializable;

public class UpgradeSingleton implements Serializable, Cloneable {
    private static final UpgradeSingleton instance = new UpgradeSingleton();
    private static volatile boolean isInitialized;

    public static UpgradeSingleton getSingleton() {
        return instance;
    }

    //私有构造函数抛出异常，保证不会通过反射实例化。
    private UpgradeSingleton() {
        throw new UnsupportedOperationException();
    }

    //重写clone方法，禁止clone
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    //单例对象有必要序列化的话，实现readResolve方法保证反序列化为原来对象
    private Object readResolve() {
        return instance;
    }

}
