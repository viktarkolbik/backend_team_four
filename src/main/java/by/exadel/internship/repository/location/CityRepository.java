package by.exadel.internship.repository.location;

import by.exadel.internship.entity.location.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
@Repository
public interface CityRepository extends JpaRepository<City, UUID> {

   List<City> findAllByCountryId(UUID id);


}
