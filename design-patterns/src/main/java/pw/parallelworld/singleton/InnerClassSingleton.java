package pw.parallelworld.singleton;

public class InnerClassSingleton {
    private static class Holder {
        private static InnerClassSingleton singleton = new InnerClassSingleton();
    }

    private InnerClassSingleton() {

    }

    public static Object getSingleton() {
        return Holder.singleton;
    }
}
