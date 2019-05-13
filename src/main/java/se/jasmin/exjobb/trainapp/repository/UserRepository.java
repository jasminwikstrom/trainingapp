package se.jasmin.exjobb.trainapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.jasmin.exjobb.trainapp.repository.entity.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value =
            "SELECT b FROM User b " +

                    "WHERE (:username IS NULL OR b.username = :username) ")
    List<User> findByQuery(
            @Param("username") String username);

}
