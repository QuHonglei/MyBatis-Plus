package com.itheibai.test;

public class StringTest {
    public static void main(String[] args) {
        String str = "abc";
        System.out.println("str 连接之前：" + str);
        System.out.println("str 哈希值：" + str.hashCode());
        str = str + "de";
        System.out.println("str 连接之后：" + str);
        System.out.println("str 哈希值：" + str.hashCode());


    }
}
