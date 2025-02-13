package com.w2m.naves.user.infrastructure;

import com.w2m.naves.user.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository <UserAccount,Long> {
    Optional<UserAccount> findByUsername(String username);
}
