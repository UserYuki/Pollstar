package com.Toine.pollstar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

@SpringBootTest
class PollstarApplicationTests {


	@Test
	public void twoStringsTest()
	{
		String str1 = "Hello";
		String str2 = "Hello";
		Assertions.assertEquals(str1,str2);
	}


}
