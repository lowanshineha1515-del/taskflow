package com.example.taskflow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FirstTest{
	@Test
	void MultiplyNums() {
		int i = 10;
		int j  = 20;
		int result = 200;
		assertEquals(result, i*j);
	}
}