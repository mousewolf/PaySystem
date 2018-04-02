package com.chenshun.test.design.singleton;

/**
 * User: mew <p />
 * Time: 18/3/28 16:55  <p />
 * Version: V1.0  <p />
 * Description: 静态内部类单例实现 <p />
 */
public class Singleton4 {

    /**
     * 1、私有化构造器
     */
    private Singleton4() {
    }

    /**
     * 2、声明一个静态内部类,在静态内部类内部提供一个外部类的实例（常量，不可改变）
     * 初始化Singleton4 的时候不会初始化SingletonClassInstance，实现了延时加载。并且线程安全
     */
    private static class SingletonClassInstance {
        // 该实例只读，不管谁都不能修改
        private static final Singleton4 instance = new Singleton4();
    }

    /**
     * 3、对外提供一个获取实例的方法：直接返回静态内部类中的那个常量实例
     * 调用的时候没有同步等待，所以效率也高
     *
     * @return
     */
    public static Singleton4 getInstance() {
        return SingletonClassInstance.instance;
    }

}
