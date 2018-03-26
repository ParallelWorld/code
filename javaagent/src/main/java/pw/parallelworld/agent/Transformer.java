package pw.parallelworld.agent;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Modifier;
import java.security.ProtectionDomain;
import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicBoolean;

public class Transformer implements ClassFileTransformer {

    private static ClassPool classPool = ClassPool.getDefault();
    private static AtomicBoolean hasAddPropsClassPath = new AtomicBoolean();
    private static AtomicBoolean hasAddWebappClassPath = new AtomicBoolean();

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        String clazzName = className.replace("/", ".");
        try {
            AddPropsClassPath();
            AddWebappClassPath();

            if (isClazzNeedModify(clazzName)) {

                CtClass ctClass = classPool.get(clazzName);
                if (ctClass != null && !ctClass.isInterface() && !ctClass.isAnnotation()) {
                    CtClass e = classPool.get("java.lang.Exception");
                    CtMethod[] ctMethods = ctClass.getDeclaredMethods();
                    if (ctMethods != null && ctMethods.length > 0) {
                        for (CtMethod ctMethod : ctMethods) {
                            if (!ctMethod.isEmpty() && !Modifier.isAbstract(ctMethod.getModifiers()) && !Modifier.isNative(ctMethod.getModifiers())) {
                                ctMethod.insertBefore(MessageFormat.format("pw.parallelworld.agent.Inject.before(\"{0}\", \"{1}\", $args);", ctClass.getName(), ctMethod.getName()));
//								ctMethod.addCatch(MessageFormat.format("com.xiao1zhao2.myjavaagent.MyInject.exception(\"{0}\", \"{1}\", $e); throw $e;", ctClass.getName(), ctMethod.getName()), e);
                                ctMethod.insertAfter(MessageFormat.format("pw.parallelworld.agent.Inject.after(\"{0}\", \"{1}\", $args);", ctClass.getName(), ctMethod.getName()), true);
                            }
                        }
                    }
                    byte[] newClass = ctClass.toBytecode();
                    ctClass.defrost();
                    return newClass;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isClazzNeedModify(String clazzName) throws Exception {

        boolean isNeedModify = false;
        for (String include : AgentConfig.includePatterns) {
            if (clazzName.matches(include)) {
                isNeedModify = true;
                break;
            }
        }

        for (String exclude : AgentConfig.excludePatterns) {
            if (clazzName.matches(exclude)) {
                isNeedModify = false;
                break;
            }
        }
        return isNeedModify;
    }

    private void AddWebappClassPath() throws Exception {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (!hasAddWebappClassPath.get() && classLoader != null && classLoader.getClass().getSimpleName().equalsIgnoreCase("webappclassloader")) {
            LoaderClassPath loaderClassPath = new LoaderClassPath(classLoader);
            classPool.insertClassPath(loaderClassPath);
            hasAddWebappClassPath.set(true);
        }
    }

    private void AddPropsClassPath() throws Exception {

        if (!hasAddPropsClassPath.get()) {
            for (String classpath : AgentConfig.classPaths) {
                classPool.appendClassPath(classpath);
            }
            ClassClassPath classClassPath = new ClassClassPath(this.getClass());
            classPool.insertClassPath(classClassPath);//http://www.aichengxu.com/java/7580053.htm
            hasAddPropsClassPath.set(true);
        }
    }

}
