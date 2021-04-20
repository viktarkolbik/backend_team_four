package by.exadel.internship.service.location;

import by.exadel.internship.comparator.CityComparator;
import by.exadel.internship.entity.location.City;
import by.exadel.internship.entity.location.Country;
import by.exadel.internship.repository.location.CountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository repository;

    @PostConstruct
    public void init() {
        Map<String, Country> newMap = new TreeMap<>();
        try {
            Resource resource = new ClassPathResource("location/world-cities_json.json");
            Object obj = new JSONParser().parse(new FileReader(resource.getFile()));
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
                    country.setCityList(new TreeSet<>(new CityComparator()));
                    return country;
                });
                Country country = newMap.get(countryName);
                city.setCountry(country);
                country.getCityList().add(city);
            }
            newMap.values().forEach(this::save);
        } catch (ParseException | IOException e) {
            log.error(e.getMessage());
        }
    }

    public List<Country> getAll() {
        return repository.findAll();
    }

    public Country save(Country country) {
        return repository.save(country);
    }
}
