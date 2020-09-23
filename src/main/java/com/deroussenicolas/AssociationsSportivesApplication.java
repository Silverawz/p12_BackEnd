package com.deroussenicolas;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * implements CommandLineRunner
 * 
 * @author deroussen nicolas
 * @version 1.0.0
 */
@SpringBootApplication
public class AssociationsSportivesApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(AssociationsSportivesApplication.class, args);
	}
	
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AssociationsSportivesApplication.class);
    }
}
