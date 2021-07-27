package com.shine.pool;



import java.util.concurrent.*;

//Executors 工具类、3大方法
//使用了线程池之后，使用线程池来创建线程
public class Demo01 {
    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());

        //自定义线程池
            ExecutorService threadPool = new ThreadPoolExecutor(
                    2,
                    5,
                    3,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(3),
                    Executors.defaultThreadFactory(),
                    //new ThreadPoolExecutor.AbortPolicy() //默认的拒绝策略，队列满了，线程池满了，不处理这个人的，抛出异常
                    //new ThreadPoolExecutor.CallerRunsPolicy() //哪来的去哪里，main线程执行
                    //new ThreadPoolExecutor.DiscardPolicy() // 队列满了，不会抛出异常，直接丢掉
                    new ThreadPoolExecutor.DiscardOldestPolicy() //尝试和最早的竞争，竞争失败就丢掉，不会抛出异常
                    );//可伸缩的

        try {
            //最大承载：队列+
            for (int i = 0; i < 11; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完，关闭线程池+max
            threadPool.shutdown();
        }


    }
}
