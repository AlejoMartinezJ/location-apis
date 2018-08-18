package com.tesis.apis.locationmaps;

import java.util.ArrayList;
import java.util.List;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LocationMapsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationMapsApplication.class, args);
	}
        
        @Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.APPLICATION_JSON);

		MappingJackson2HttpMessageConverter jacksonConverter =
				new MappingJackson2HttpMessageConverter();
		jacksonConverter.setSupportedMediaTypes(mediaTypes);

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(jacksonConverter);

		restTemplate.setMessageConverters(messageConverters);
                
		return restTemplate;
	}
        @Bean
        public FlywayMigrationStrategy cleanMigrateStrategy() {
            FlywayMigrationStrategy strategy = new FlywayMigrationStrategy() {
                @Override
                public void migrate(Flyway flyway) {
                    flyway.clean();
                    flyway.migrate();
                }
            };

            return strategy;
        }
}
