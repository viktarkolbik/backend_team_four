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
            Map<Country, Set<City>> locationMap = new TreeMap<>();
            try {
                Object obj = new JSONParser().parse(new FileReader("src/main/java/by/exadel/internship/location/world-cities_json.json"));
                JSONArray jsonObjectList = (JSONArray) obj;
                for (Object o : jsonObjectList) {
                    JSONObject jsonObject = (JSONObject) o;
                    Object countryObject =  jsonObject.get("country");
                    Country country = (Country) countryObject;
                    City city = (City) jsonObject.get("name");

                    if (!locationMap.containsKey(country)) {
                        locationMap.put(country, new TreeSet<>());
                    }
                    locationMap.get(country).add(city);
                }

                for (Map.Entry<Country, Set<City>> entry : locationMap.entrySet()) {
                    countryService.save(entry.getKey());
                    cityService.save(entry.getValue());
                }
            } catch (ParseException | IOException e) {
                log.error(e.getMessage());
            }
        };
    }


}