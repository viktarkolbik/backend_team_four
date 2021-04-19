package by.exadel.internship.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository repository;

    public List<City> getAll(){
        return repository.findAll();
    }

//    public City save(City city){
//        return repository.save(city);
//    }

    public Iterable<City> save(Set<City> cities){
        return repository.saveAll(cities);
    }
}
