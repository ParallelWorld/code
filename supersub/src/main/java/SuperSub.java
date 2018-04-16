public class SuperSub {
    public static void main(String[] args) {
        Sub s = new Sub();
        /**
         * 输出
         * 加载Super的静态块
         * 加载Super的静态变量
         * 加载Sub的静态变量
         * 加载Sub的静态块
         * 加载Super的普通块
         * 加载Super的实例变量
         * 加载Super的构造器
         * 加载Sub的实例变量
         * 加载Sub的普通块
         * 加载Sub的构造器
         */

        /**
         * 结论
         * 1 加载super的静态块、静态变量，是按照代码出现顺序执行的。
         * 2 加载sub的静态块、静态变量，是按照代码出现顺序执行的。
         * 3 加载super的普通块、实例变量，是按照代码出现顺序执行的。
         * 4 加载super的构造函数。
         * 5 加载sub的普通块、实例变量，是按照代码出现顺序执行的。
         * 6 加载sub的构造函数。
         */
    }


    static class Super {

        static {
            System.out.println("加载Super的静态块");
        }

        static int a = getA();

        {
            System.out.println("加载Super的普通块");
        }

        int b = getB();

        Super() {
            System.out.println("加载Super的构造器");
        }

        static int getA() {
            System.out.println("加载Super的静态变量");
            return 1;
        }

        static int getB() {
            System.out.println("加载Super的实例变量");
            return 2;
        }
    }

    static class Sub extends Super {
        Sub() {
            System.out.println("加载Sub的构造器");
        }

        static int c = getC();

        static {
            System.out.println("加载Sub的静态块");
        }

        int d = getD();

        {
            System.out.println("加载Sub的普通块");
        }

        static int getC() {
            System.out.println("加载Sub的静态变量");
            return 1;
        }

        static int getD() {
            System.out.println("加载Sub的实例变量");
            return 2;
        }
    }
}

