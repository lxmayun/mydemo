package com.example.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoTest2 {

	public static int clientTotal = 5000 ;
	public static int threadTotal = 200 ;
	//public static AtomicInteger count = new AtomicInteger(0);
	//public static AtomicLong count = new AtomicLong(0);
	public static LongAdder count = new LongAdder();
	public static void main(String[] args) throws Exception {
		final ExecutorService exec = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch co = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++) {
			exec.execute(() ->{
				try {
					semaphore.acquire();
					add();
					semaphore.release();
				} catch (Exception e) {

					System.out.println("message"+ e);

				}
				//System.out.println("i:"+i);
				co.countDown();
			});
		}
		co.await();
		//log.info("sss");
		//System.out.println("count:"+count.get());

		System.out.println("count:"+count);
	}
	private static void add() {
		//count.incrementAndGet();
		count.increment();
	}

}
