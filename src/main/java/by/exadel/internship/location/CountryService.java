package by.exadel.internship.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository repository;

    public List<Country>  getAll(){
        return repository.findAll();
    }

    public Country save(Country country){
        return repository.save(country);
    }

//    public List<Country> save(List<Country> countries){
//         return repository.saveAll(countries);
//    }
}
