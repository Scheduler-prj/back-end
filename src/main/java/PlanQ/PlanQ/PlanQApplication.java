package PlanQ.PlanQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // OAuth2 기본 로그인 페이지 사용 안함
public class PlanQApplication {
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "classpath:application-keys.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(PlanQApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}
/*	public static void main(String[] args) {
		SpringApplication.run(PlanQApplication.class,args);
	}*/
}
