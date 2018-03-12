package pw.parallelworld.singleton;

public enum EnumSingleton {
    instance;

    public static EnumSingleton getSingleton() {
        return instance;
    }
}
