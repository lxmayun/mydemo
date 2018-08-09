package com.example.demo;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


import lombok.Getter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicTest3 {

	private static AtomicIntegerFieldUpdater<AtomicTest3> update = 
			AtomicIntegerFieldUpdater.newUpdater(AtomicTest3.class, "count");
	@Getter
	public volatile int count = 100;
	
	public int getCount() {
		return count;
		
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	
	
	public static void main(String[] args){
		 AtomicTest3 test3 = new AtomicTest3();
		if(update.compareAndSet(test3, 100, 120)) {
			System.out.println("up1::"+test3.getCount());
		}
		if(update.compareAndSet(test3, 100, 120)) {
			System.out.println("up2::"+test3.getCount());
		}else {
			System.out.println("up3::"+test3.getCount());
		}
		
	}
	

}
