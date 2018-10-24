package com.tesis.apis.locationmaps;

import com.tesis.apis.locationmaps.entity.Location;
import com.tesis.apis.locationmaps.entity.Spots;
import com.tesis.apis.locationmaps.entity.UMoviles;
import com.tesis.apis.locationmaps.jpa.LocationRepository;
import com.tesis.apis.locationmaps.jpa.UnitsRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaAuditing
@PropertySource("classpath:application.properties")
@ComponentScan
public class LocationMapsApplication {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public static void main(String[] args) {
        SpringApplication.run(LocationMapsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(MediaType.APPLICATION_JSON);

        MappingJackson2HttpMessageConverter jacksonConverter
                = new MappingJackson2HttpMessageConverter();
        jacksonConverter.setSupportedMediaTypes(mediaTypes);

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(jacksonConverter);

        restTemplate.setMessageConverters(messageConverters);

        return restTemplate;
    }
    /**
     * Save users and students to H2 DB for testing
     *
     * @param repository
     * @return
     */
    /*
	@Bean
	public CommandLineRunner demo(LocationRepository lRepository, UnitsRepository urepository) {
		return (args) -> { 
                        lRepository.save(new Location("Control","Control", "-11.9622", "-77.08372"));
                        lRepository.save(new Location("Lugar1", "Lugar1","-11.9622", "-77.06986"));
                        lRepository.save(new Location("Lugar2","Lugar2", "-11.95116", "-77.0775"));
                        lRepository.save(new Location("Lugar3","Lugar3", "-11.9481", "-77.06248"));
                        lRepository.save(new Location("Lugar4","Lugar4", "-11.95859", "-77.05789"));
                        lRepository.save(new Location("Lugar5","Lugar5", "-11.96703", "-77.06986"));
                        
                        Spots spot1 = new Spots(1,1);
                        Spots spot2 = new Spots(4,2);
                        Spots spot3 = new Spots(3,3);
                        Spots spot4 = new Spots(5,4);
                        Spots spot5 = new Spots(4,5);
                       
                        List<Spots> spotList = new ArrayList<>();
                        spotList.add(spot1);
                        spotList.add(spot2);
                        spotList.add(spot3);
                        spotList.add(spot4);
                        spotList.add(spot5);
                        UMoviles umovil1 = new UMoviles(1,"UI1002", "HOLD");
                        umovil1.setSpots(spotList);
                        urepository.save(umovil1);
                        UMoviles umovil2 = new UMoviles(2,"UI1003", "HOLD");
                        umovil2.setSpots(spotList);
                        urepository.save(umovil2);                        
                        UMoviles umovil3 = new UMoviles(3,"UI2001", "HOLD");
                        umovil3.setSpots(spotList);
                        urepository.save(umovil3);                       
		};
	}
     */
}
