package com.example.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoTest {

	public static int clientTotal = 5000 ;
	public static int threadTotal = 200 ;
	public static int count = 0 ;
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
				co.countDown();
			});
		}
		co.await();
		//log.info("sss");
		System.out.println("count:"+count);


	}
	private static void add() {
		count++;
	}

}
