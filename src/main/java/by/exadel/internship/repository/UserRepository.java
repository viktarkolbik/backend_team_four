package by.exadel.internship.repository;

import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT distinct u FROM User u LEFT JOIN FETCH u.skills")
    List<User> findAllWithSkill();

    @Query("SELECT distinct u FROM User u LEFT JOIN FETCH u.skills join u.internships i where i.id = :id and u.userRole = :role")
    List<User> findAllWithSkillByInternshipId(@Param("id") UUID internshipId, @Param("role")UserRole role);

}
