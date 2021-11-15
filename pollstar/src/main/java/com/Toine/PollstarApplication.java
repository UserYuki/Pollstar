package com.Toine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {"com.Toine"})
public class PollstarApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollstarApplication.class, args);
}

}
