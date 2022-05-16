package com.springboot.app.service.impl;

import com.springboot.app.entities.Bid;
import java.util.List;

public interface BidServerInterface {
    List<Bid> getAllBids();

    void saveOrUpdateBid(Bid bid);

    Bid getBid(long id);

    void deleteBid(long id);

    List<Bid> packagesWithStatusNew();

    void updateStatus(Bid bid);
}
