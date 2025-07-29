package com.EMS.EmployeeMS;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "The AwwalDevs EMS App",
				description = "Backend REST APIs for Employee Management System",
				version = "v1.0",
				contact = @Contact(
						name = "Awwal Mudashir",
						email = "awwalmudashir@gmail.com"
				)
		)
)
public class EmployeeMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMsApplication.class, args);
	}

//	see the swagger ui at
//	http://localhost:8080/swagger-ui/index.html
}
