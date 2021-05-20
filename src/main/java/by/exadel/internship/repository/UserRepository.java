package by.exadel.internship.repository;

import by.exadel.internship.dto.enums.Skill;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Modifying
    @Query("UPDATE User u SET u.deleted = false WHERE u.id = :userId")
    void activateUserById(@Param("userId") UUID userId);

    List<User> findAllByDeletedTrue();

    List<User> findAllByDeletedFalse();

    Optional<User> findByIdAndDeletedTrue(UUID userId);

    @Query(value = "SELECT DISTINCT u.* FROM user_detail as u INNER JOIN user_time_slot as ts   " +
            "ON u.u_id = ts.ust_u_id " +
            "LEFT JOIN user_skill as us ON u.u_id = us.us_u_id " +
            "WHERE ( CAST(ts.ust_start_date as TIMESTAMP)  >= current_timestamp) AND  (u.u_id = :userId ) AND u.u_deleted = false ", nativeQuery = true)
    Optional<User> findUserByIdWithCurrentTimeSlots(@Param("userId") UUID userId);

//    @Query("SELECT DISTINCT u FROM User  u INNER JOIN u.userTimeSlots  ts " +
//            "WHERE ( CAST(ts. as TIMESTAMP)  >= current_timestamp) AND  (u.u_id = :userId ) AND u.u_deleted = false ")
//    Optional<User> findUserByIdWithCurrentTimeSlots(@Param("userId") UUID userId);

    @Modifying
    @Query("UPDATE User u SET u.deleted=true WHERE u.id= :userId")
    void deleteById(@Param("userId") UUID userId);

    Optional<User> findByLogin(String login);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.skills WHERE u.deleted = false ")
    List<User> findAllWithSkill();

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.skills JOIN u.internships i WHERE i.id = :id AND u.userRole = :role AND u.deleted = false")
    List<User> findAllWithSkillByInternshipId(@Param("id") UUID internshipId, @Param("role") UserRole role);

    List<User> findAllByUserRole(UserRole userRole);

    @Query(value = "SELECT DISTINCT u.* FROM user_detail as u JOIN user_skill as s ON u.u_id = s.us_u_id WHERE CAST (s.us_name AS VARCHAR) IN :skills AND u.u_deleted = false", nativeQuery = true)
    List<User> getUsersBySkills(@Param("skills") List<String> skills);
}
