package org.example;

/**
 * -XX:+PrintGC
 */
public class ReferenceCountingGC {
    private ReferenceCountingGC instance;
    private static final int _1MB = 1024 * 1024;

    /**
     * 这个成员属性的唯一意义就是占点内存，
     * 以便能在GC日志中看清楚是否有回收过
     */
    private byte[] bigSize = new byte[_1MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA=null;
        objB=null;

        // 假设在这行发生GC，
        // objA和objB是否能被回收？
        System.gc();
    }
}
