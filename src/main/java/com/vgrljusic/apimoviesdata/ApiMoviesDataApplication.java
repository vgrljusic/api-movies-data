package com.vgrljusic.apimoviesdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@SpringBootApplication
@EnableSwagger2
public class ApiMoviesDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiMoviesDataApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Docket swaggerConfiguration(){

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/**"))
				.apis(RequestHandlerSelectors.basePackage("com.vgrljusic"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails(){
		return new ApiInfo(
				"Database of movie searches API",
				"You can make a search which will look in the database for existing\n" +
						"data and throw results for a movie which matches the name with search term.\n" +
						"Each movie entry has a way of displaying movie details. If a movie is not found locally,\n" +
						" it searches for the movie online using one of the available online resources. When a new movie is found, \n" +
						"the data of the movie is stored in a local database. Search results can show listings of all \n" +
						"movies that match the search criteria with details about the movie.",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Valentino Grljusic", "https://github.com/vgrljusic/api-movies-data", "vgrljusic@gmail.com" ),
				"API License",
				"https://github.com/vgrljusic",
				Collections.emptyList());



	}

}
