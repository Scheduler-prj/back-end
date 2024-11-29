package PlanQ.PlanQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // OAuth2 기본 로그인 페이지 사용 안함
public class PlanQApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanQApplication.class, args);
	}

}
