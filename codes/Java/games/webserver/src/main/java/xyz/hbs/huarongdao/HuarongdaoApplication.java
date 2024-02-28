package xyz.hbs.huarongdao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class HuarongdaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HuarongdaoApplication.class, args);
	}

}
