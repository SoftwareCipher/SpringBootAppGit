package com.springboot.app.service;

import com.springboot.app.entities.Bid;
import com.springboot.app.entities.Notification;
import com.springboot.app.exception_handling.NoSuchEntityException;
import com.springboot.app.repository.BidRepository;
import com.springboot.app.repository.NotificationRepository;
import com.springboot.app.service.impl.BidServerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BidServiceImpl implements BidServerInterface {

    BidRepository bidRepository;
    NotificationRepository notificationRepository;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository,
                          NotificationRepository notificationRepository) {
        this.bidRepository = bidRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    @Override
    public void saveOrUpdateBid(Bid bid) {
        notificationRepository.save(
                new Notification(bid.getId(), "Execute", "new"));
        bid.setStatus("New");
        bid.setDateChange(LocalDateTime.now());
        bid.setDateCreation(LocalDateTime.now());
        bidRepository.saveAndFlush(bid);
    }

    @Override
    public Bid getBid(long id) {
        Bid bid = null;
        Optional<Bid> optional = bidRepository.findById(id);
        if(optional.isPresent()){
            bid = optional.get();
        }else{
            throw new NoSuchEntityException("There is no Bid with id = "
                    + id + " in database");
        }
        return bid;
    }

    @Override
    public void deleteBid(long id) {
        bidRepository.deleteById(id);
        notificationRepository.deleteById(id);
    }

    @Override
    public List<Bid> packagesWithStatusNew() {
        return bidRepository.packagesWithStatusNew();
    }

    @Override
    public void updateStatus(Bid bid) {
        bid.setStatus("OLD");
        bid.setDateChange(LocalDateTime.now());
        bidRepository.save(bid);
    }

}
