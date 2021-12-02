package com.Toine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication()
public class PollstarApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollstarApplication.class, args);
}

}
