package com.symc.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //변경 된 내역이 나와야 하는데
    Optional<User> findByEmail(String email) ;
}
