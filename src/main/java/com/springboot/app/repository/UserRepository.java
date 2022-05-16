package com.springboot.app.repository;

import com.springboot.app.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Person, Long> {}
