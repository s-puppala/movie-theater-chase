package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.jpmc.theater.util.LocalDateProvider;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentTime() {
        System.out.println("current time is - " + LocalDateProvider.singleton().currentDate());
    }
    
    @Test
	void singletonTest() {
		LocalDateProvider instance1 = LocalDateProvider.singleton();
		LocalDateProvider instance2 = LocalDateProvider.singleton();
		assertSame(instance1, instance2);
	}
}
