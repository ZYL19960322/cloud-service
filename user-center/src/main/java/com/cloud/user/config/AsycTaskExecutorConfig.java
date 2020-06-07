package com.cloud.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.concurrent.*;

/**
 * 线程池配置、启用异步
 * 
 * @author 小威老师
 *
 */
@EnableAsync(proxyTargetClass = true)
@Configuration
public class AsycTaskExecutorConfig {

	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(50);
		taskExecutor.setMaxPoolSize(100);
//		ThreadPoolExecutor executor = new ThreadPoolExecutor();
//		Future<?> submit = executor.submit(new Runnable() {
//			@Override
//			public void run() {
//
//			}
//		});
//		try {
//			Object o = submit.get();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		}
//		executor.execute(new Runnable() {
//			@Override
//			public void run() {
//
//			}
//		});
//		executor.invokeAny();

		return taskExecutor;
	}

	public static void main(String[] args) {

		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(2);
		taskExecutor.setMaxPoolSize(3);


		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
		ThreadFactory factory = r -> new Thread(r, "test-thread-pool");
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3,
				0L, TimeUnit.SECONDS, queue, factory,new ThreadPoolExecutor.CallerRunsPolicy());
		for (int i = 0; i < 20; i++) {
			int a=i;

			try {
				executor.submit(() -> {
					try {
						System.out.println(Thread.currentThread().getName() + ":执行任务==i的值="+a);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
			}catch (RejectedExecutionException e){
				System.out.println("线程出错了");

			}
			HashMap<Object, Object> map = new HashMap<>();
			map.get("");


		}
	}
}
