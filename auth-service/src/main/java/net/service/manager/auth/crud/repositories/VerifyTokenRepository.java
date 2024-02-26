package net.service.manager.auth.crud.repositories;

import net.service.manager.auth.crud.entities.UserAccount;
import net.service.manager.auth.crud.entities.VerifyToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifyTokenRepository extends JpaRepository<VerifyToken, Long> {
    @Query("SELECT DISTINCT v FROM VerifyToken v WHERE v.token = :token")
    VerifyToken findByToken(String token);

    @Query("SELECT DISTINCT v FROM VerifyToken v WHERE v.user = :user")
    VerifyToken findByUser(UserAccount user);
}
