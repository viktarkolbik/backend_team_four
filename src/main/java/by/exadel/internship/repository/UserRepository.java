package by.exadel.internship.repository;

import by.exadel.internship.dto.enums.Skill;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Modifying
    @Query("UPDATE User u SET u.deleted = false WHERE u.id = :userId")
    void activateUserById(@Param("userId") UUID userId);

    @EntityGraph(attributePaths = {"skills", "userTimeSlots"})
    List<User> findAllByDeletedTrue();

    @EntityGraph(attributePaths = {"skills", "userTimeSlots"})
    Optional<User> findByIdAndDeletedTrue(UUID userId);

    @EntityGraph(attributePaths = {"skills", "userTimeSlots"})
    Optional<User> findByIdAndDeletedFalse(UUID userId);

    @Modifying
    @Query("UPDATE User u SET u.deleted=true WHERE u.id= :userId")
    void deleteById(@Param("userId") UUID userId);

    @EntityGraph(attributePaths = {"skills", "userTimeSlots"})
    Optional<User> findByLogin(String login);

    @EntityGraph(attributePaths = {"skills"})
    List<User> findDistinctByDeletedFalse();

    @EntityGraph(attributePaths = {"skills", "userTimeSlots"})
    @Query("SELECT DISTINCT u FROM User u JOIN u.internships i WHERE i.id = :id AND u.userRole = :role AND u.deleted = false")
    List<User> findAllWithSkillByInternshipId(@Param("id") UUID internshipId, @Param("role") UserRole role);

    @EntityGraph(attributePaths = {"skills", "userTimeSlots"})
    List<User> findAllByUserRole(UserRole userRole);

    @EntityGraph(attributePaths = {"skills", "userTimeSlots"})
    List<User> getUsersBySkillsInAndDeletedFalse(Set<Skill> skills);
}
