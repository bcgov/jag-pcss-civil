package ca.bc.gov.open.pcss.pcsswebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PCSSWebServiceApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PCSSWebServiceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PCSSWebServiceApplication.class, args);
	}

}
