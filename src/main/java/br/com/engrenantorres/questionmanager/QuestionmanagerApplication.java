package br.com.engrenantorres.questionmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class QuestionmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionmanagerApplication.class, args);
	}

}
