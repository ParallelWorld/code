package pw.parallelworld.agent;


import java.lang.instrument.Instrumentation;

public class PWAgent {

    public static void premain(String args, Instrumentation instrumentation) {

        System.out.println("========== Call MyAgent start ==========");
        AgentConfig.init();
        instrumentation.addTransformer(new PWTransformer());
        System.out.println("========== Call MyAgent end ==========");
    }
}
