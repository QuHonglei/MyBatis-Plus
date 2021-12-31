package com.itheibai.test;

public class MyJavaTest {
    public static void main(String[] args) {
        System.out.println("Hello itheibai!");
        Integer a1 = 100;
        Integer a2 = 100;
        Integer a3 = 200;
        Integer a4 = 200;
        Integer n5 = new Integer(100);
        Integer n6 = new Integer(100);
        Integer n7 = new Integer(200);
        Integer n8 = new Integer(200);

        int b1 = 100;
        int b3 = 200;


        System.out.println("(非new生成的Integer 100.equals(100) ? )equals : " + a1.equals(a2));
        System.out.println("(非new生成的Integer 200.equals(200) ? )equals : " + a3.equals(a4));
        System.out.println("(new生成的Integer 100.equals(100) ? )equals : " + n5.equals(n6));
        System.out.println("(new生成的Integer 100.equals(100) ? )equals : " + n7.equals(n8));


        //非new生成的两个Integer变量，做 == 比较时，如果两个变量的值在区间 -128到127之间，结果为 true,否则为 false
        //当在IDEA中开启代码检测的时候，会提示你将 == 替换为 equals
        System.out.println("(非new生成的Integer 100 == 100 ? )== : " + (a1 == a2));
        System.out.println("(非new生成的Integer 200 == 200 ? )== : " + (a3 == a4));

        //new生成的 Integer 做 == 比较，比较的是两个对象的地址
        System.out.println("(new生成的Integer 100 == 100 ? )== : " + (n5 == n6));
        System.out.println("(new生成的Integer 200 == 200 ? )== : " + (n7 == n8));

        //Integer 变量与 int 变量做 == 比较，Integer 变量类型会自动拆箱为 int 类型
        System.out.println("(非new生成的Integer & int 100 == 100 ? )== : " + (a1 == b1));
        System.out.println("(非new生成的Integer & int 200 == 200 ? )== : " + (a3 == b3));
        System.out.println("(new生成的Integer & int 100 == 100 ? )== : " + (n5 == b1));
        System.out.println("(new生成的Integer & int 200 == 200 ? )== : " + (n7 == b3));

        /**
         *  总结：
         *  1. new的对象存放在 堆内存 中，不同的对象，堆内存 的地址不同；
         *  2. 普通变量存放在 常量池 中（如 int a，int b 存放在常量池中）；
         *  3. == 比较 非new 生成的基本类型变量，比较的是值是否相等；
         *  4. == 比较 new 生成的对象，比较的是引用地址是否相等；
         *  5. equals 比较 非new 生成的基本类型变量，等同于 == 作比较；
         *  6. equals 比较 new 生成的对象，比较的是 对象的内容 是否相同；
         *  7. java在编译 Integer a1 = 100;时，会翻译成 Integer a1 = Integer.valueOf(100);
         *     valueOf方法在Java api 中会对 -128到127 之间的数进行缓存，
         *     如Integer a1 = 127时，会将127进行缓存，下次再写 Integer a1 = 127 时，就会直接从缓存中取，就不会 new 了。
         *  8. Integer的默认值是 null, int 的默认值是 0；
         *  9. Integer 是 int 的包装类，int 则是Java的一种基本类型
         *  10. Integer 变量必须实例化后才能使用，而int变量不需要
         *  11. Integer 实际是对象的引用，当 new 一个 Integer 时，实际是生成一个指针指向此对象；
         *      而int则是直接存储数据值。
         */
    }
}
