package pw.parallelworld.agent;


import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String args, Instrumentation instrumentation) {

        System.out.println("========== Call MyAgent start ==========");
        AgentConfig.init();
        instrumentation.addTransformer(new Transformer());
        System.out.println("========== Call MyAgent end ==========");
    }
}
