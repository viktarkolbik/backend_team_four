package by.exadel.internship.repository;

import by.exadel.internship.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository <UserRole, String>{
}
