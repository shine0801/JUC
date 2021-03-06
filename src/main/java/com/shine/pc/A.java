package com.shine.pc;

/**
 * 线程之间的通信问题：生产者和消费者问题！等待唤醒，通知唤醒
 * 线程交替执行A,B操作同一个变量 num =0
 *A num + 1
 * B num - 1
 * */
public class A {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}

/**
 * 判断等待、业务、通知
 * **/
class Data{  //数字、资源类
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"已经调用了+方法");
        while(number!=0){ //防止虚假唤醒问题
            this.wait(); //将会释放掉锁
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其他线程，我+1完毕了
        this.notify();
    }

    public synchronized void decrement() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"已经调用了-方法");
        while(number==0){
           //等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其他线程，我-1完毕了
        this.notify();
    }

}
