package com.dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.model.user.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User , Integer>{
    Optional<User> findByMail(String mail);

    Optional<User> findById(int idUser);
}
