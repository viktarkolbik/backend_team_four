package by.exadel.internship.service.location;

import by.exadel.internship.entity.location.City;
import by.exadel.internship.repository.location.CityRepository;
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
}
