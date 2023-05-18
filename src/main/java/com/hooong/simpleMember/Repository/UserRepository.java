package com.hooong.simpleMember.Repository;

import com.hooong.simpleMember.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
