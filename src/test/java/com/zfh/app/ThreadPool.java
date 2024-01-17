package com.zfh.app;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

	private static int count = 1;
	public static void main(String[] args) {
		// 线程池维护线程的最少数量
		int corePoolSize = 10;
		// 线程池维护线程的最大数量
		int maximumPoolSize = 20;
		// 线程池维护线程所允许的空闲时间
		long keepAliveTime = 4;
		// 线程池维护线程所允许的空闲时间的单位
		TimeUnit unit = TimeUnit.SECONDS;
		// 维持队列的数量
		BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(1000);
		// AbortPolicy 策略
		// 处理程序遭到拒绝将丢弃当前任务
		AbortPolicy handler = new ThreadPoolExecutor.AbortPolicy();
		// 初始化线程池
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				taskQueue, handler);

		int totalPage = 1000;
		final int total = totalPage;
		// 线程池执行
		while (totalPage > 0) {
			totalPage = totalPage - 1;
			final int pageIndex = totalPage;
			threadPool.execute(new Thread(new Runnable() {
				@Override
				public void run() {
					++count;
					try {
						new Task().save(count);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}));
		}

		
		threadPool.shutdown();
		
		try {
			while(!threadPool.awaitTermination(10, TimeUnit.MILLISECONDS)) {
				System.out.println("pool not all shutdown");
			}
			System.err.println("allend: " + count);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Task {

	public static void save(int i) throws InterruptedException {
		Thread.sleep(10);
		System.out.println(i);
	}
}
