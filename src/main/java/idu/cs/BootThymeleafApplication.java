package idu.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
public class BootThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootThymeleafApplication.class, args);
	}

}
