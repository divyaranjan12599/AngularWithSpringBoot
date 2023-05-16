package lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class LibraryManagementSystemProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemProjectApplication.class, args);

	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("*").maxAge(3600L)
						.allowedHeaders("*").exposedHeaders("Authorization").allowCredentials(true);
			}
		};
	}

	@Bean
	public JavaMailSender javaMailSender() { 
		return new JavaMailSenderImpl(); 
	}
	
	
}
