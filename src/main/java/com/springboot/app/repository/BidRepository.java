package com.springboot.app.repository;

import com.springboot.app.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query("select b from Bid b where b.status = 'New'")
    List<Bid> packagesWithStatusNew();

}
