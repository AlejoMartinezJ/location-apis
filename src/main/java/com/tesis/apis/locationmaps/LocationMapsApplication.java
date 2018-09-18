package com.tesis.apis.locationmaps;

import com.tesis.apis.locationmaps.entity.Location;
import com.tesis.apis.locationmaps.entity.Route;
import com.tesis.apis.locationmaps.entity.UMoviles;
import com.tesis.apis.locationmaps.jpa.LocationRepository;
import com.tesis.apis.locationmaps.jpa.UnitsRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
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
        	/**
	 * Save users and students to H2 DB for testing
	 * @param repository
	 * @return
	 */
	@Bean
	public CommandLineRunner demo(LocationRepository lRepository, UnitsRepository urepository) {
		return (args) -> {
                    /*
			aRepository.save(new Address("Control", "Control"));
			aRepository.save(new Address("Lugar1", "Lugar1"));
                        aRepository.save(new Address("Lugar2", "Lugar2"));
                        aRepository.save(new Address("Lugar3", "Lugar3"));
                        aRepository.save(new Address("Lugar4", "Lugar4"));
                        aRepository.save(new Address("Lugar5", "Lugar5"));
                    */    
                        lRepository.save(new Location("Control","Control", "-11.9622", "-77.08372"));
                        lRepository.save(new Location("Lugar1", "Lugar1","-11.9622", "-77.06986"));
                        lRepository.save(new Location("Lugar2","Lugar2", "-11.95116", "-77.0775"));
                        lRepository.save(new Location("Lugar3","Lugar3", "-11.9481", "-77.06248"));
                        lRepository.save(new Location("Lugar4","Lugar4", "-11.95859", "-77.05789"));
                        lRepository.save(new Location("Lugar5","Lugar5", "-11.96703", "-77.06986"));
                        
                        Route route1 = new Route(1,1);
                        Route route2 = new Route(2,2);
                        Route route3 = new Route(3,3);
                        Route route4 = new Route(5,4);
                        Route route5 = new Route(4,5);
                        Route route6 = new Route(1,6);
                        List<Route> routeList = new ArrayList<>();
                        routeList.add(route1);
                        routeList.add(route2);
                        routeList.add(route3);
                        routeList.add(route4);
                        routeList.add(route5);
                        routeList.add(route6);
                        UMoviles umoviles = new UMoviles(1,"Master", "ACTIVE");
                        umoviles.setRoutes(routeList);
                        urepository.save(umoviles);
                       
		};
	}
        
        
}
