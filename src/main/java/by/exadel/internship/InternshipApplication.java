package by.exadel.internship;

import by.exadel.internship.location.City;
import com.fasterxml.jackson.core.type.TypeReference;
import by.exadel.internship.location.CityService;
import by.exadel.internship.location.Country;
import by.exadel.internship.location.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
@SpringBootApplication
public class InternshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternshipApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CountryService countryService, CityService cityService) {
        return args -> {
            Map<Country, Set<City>> locationMap = new HashMap<>();

            Map<String, Country> newMap = new HashMap<>();

            try {
                Object obj = new JSONParser().parse(new FileReader("src/main/java/by/exadel/internship/location/world-cities_json.json"));
                JSONArray jsonObjectList = (JSONArray) obj;
                for (Object o : jsonObjectList) {
                    JSONObject jsonObject = (JSONObject) o;
                    String cityName = (String) jsonObject.get("name");


                    City city = new City();
                    city.setName(cityName);
                    String countryName = (String) jsonObject.get("country");
                    newMap.computeIfAbsent(countryName, name -> {
                        Country country = new Country();
                        country.setName(name);
                        country.setCitySet(new ArrayList<>());
                        return country;
                    });

                    Country country = newMap.get(countryName);
                    city.setCountry(country);
                    country.getCitySet().add(city);


//                    if (!locationMap.containsKey(country)) {
//                        locationMap.put(country, new HashSet<>());
//                    }
//                    locationMap.get(country).add(city);
                }

                for (Map.Entry<String, Country> entry : newMap.entrySet()) {
                    countryService.save(entry.getValue());
//                    Country save = countryService.save(entry.getKey());
//                    entry.getValue().forEach(city -> city.setCountry(save));
//                    cityService.save(entry.getValue());
                }
            } catch (ParseException | IOException e) {
                log.error(e.getMessage());
            }
        };
    }


}