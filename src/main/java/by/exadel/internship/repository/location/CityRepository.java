package by.exadel.internship.repository.location;

import by.exadel.internship.entity.location.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CityRepository extends JpaRepository<City, UUID> {

    @Query("SELECT DISTINCT c FROM City c LEFT JOIN FETCH c.country WHERE c.country.name = :countryName")
   List<City> findAllByCountry(@Param("countryName") String countryName);

}
