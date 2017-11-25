package com.intellect.user.details.UserDetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.intellect.user.details.repository.UserDetailsRepository;

/**
 * 
 * @author Sumit23
 *
 */
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses=UserDetailsRepository.class)
@ComponentScan(basePackages={"com.intellect.user.details"})
public class UserDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDetailsApplication.class, args);
	}
}
