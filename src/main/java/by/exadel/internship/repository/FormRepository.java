package by.exadel.internship.repository;

import by.exadel.internship.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface FormRepository extends JpaRepository<Form, UUID> {


}
