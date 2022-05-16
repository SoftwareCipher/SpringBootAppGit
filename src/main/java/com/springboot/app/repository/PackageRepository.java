package com.springboot.app.repository;

import com.springboot.app.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Bid, Long> {}
