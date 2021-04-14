package by.exadel.internship.repository;
import by.exadel.internship.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE user_detail SET u_deleted = false WHERE u_id = :userId",
            nativeQuery = true)
    void updateDeletedById(UUID userId);

    @Query(value = "SELECT * FROM user_detail WHERE u_deleted = true",
            nativeQuery = true)
    List<User> findAllDeleted();

    @Query(value = "SELECT * FROM user_detail WHERE u_deleted = true AND u_id = :userId",
            nativeQuery = true)
    Optional<User> findDeletedById(@Param("userId") UUID userId);
}
