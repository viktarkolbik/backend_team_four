package by.exadel.internship.repository.location;

import by.exadel.internship.entity.location.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {

}
