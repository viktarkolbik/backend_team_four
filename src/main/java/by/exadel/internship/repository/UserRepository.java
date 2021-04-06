package by.exadel.internship.repository;

import by.exadel.internship.dto.enums.Technology;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.entity.TechUser;
import by.exadel.internship.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> getByUserRole(UserRole userRole);
    List<TechUser> getByUserRoleAndTechTechnology(UserRole userRole, Technology technology);

}
