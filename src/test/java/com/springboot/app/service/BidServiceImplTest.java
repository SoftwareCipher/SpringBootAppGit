package com.springboot.app.service;

import com.springboot.app.entities.Bid;
import com.springboot.app.entities.Notification;
import com.springboot.app.repository.BidRepository;
import com.springboot.app.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BidServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BidServiceImplTest {

    Bid bid;

    @MockBean
    private BidRepository bidRepository;

    @Autowired
    private BidServiceImpl bidServiceImpl;

    @MockBean
    private NotificationRepository notificationRepository;

    @BeforeEach
    public void setUp(){
        bid = new Bid();
        bid.setDateChange(LocalDateTime.of(1, 1, 1, 1, 1));
        bid.setDateCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        bid.setId(123L);
        bid.setRecipientDepart("Recipient Depart");
        bid.setRecipientName("Recipient Name");
        bid.setRecipientPhoneNumber(1L);
        bid.setSenderDepart("Sender Depart");
        bid.setSenderName("Sender Name");
        bid.setStatus("new");
    }

    @Test
    void testGetAllBids() {
        ArrayList<Bid> bidList = new ArrayList<>();
        when(this.bidRepository.findAll()).thenReturn(bidList);

        List<Bid> actualAllBids = this.bidServiceImpl.getAllBids();
        assertSame(bidList, actualAllBids);
        assertTrue(actualAllBids.isEmpty());
        verify(this.bidRepository).findAll();
    }

    @Test
    void testSaveOrUpdateBid() {
        Notification notification = new Notification();
        notification.setId(123L);
        notification.setNotification("Notification");
        notification.setStatus("Status");

        when(this.notificationRepository.save((Notification) any())).thenReturn(notification);
        when(this.bidRepository.saveAndFlush((Bid) any())).thenReturn(bid);

        Bid bid1 = new Bid();
        bid1.setDateChange(LocalDateTime.of(1, 1, 1, 1, 1));
        bid1.setDateCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        bid1.setId(123L);
        bid1.setRecipientDepart("Recipient Depart");
        bid1.setRecipientName("Recipient Name");
        bid1.setRecipientPhoneNumber(1L);
        bid1.setSenderDepart("Sender Depart");
        bid1.setSenderName("Sender Name");
        bid1.setStatus("new");

        this.bidServiceImpl.saveOrUpdateBid(bid);
        verify(this.notificationRepository).save((Notification) any());
        verify(this.bidRepository).saveAndFlush((Bid) any());

        assertEquals(123L, bid1.getId());
        assertEquals("Sender Name", bid1.getSenderName());
        assertEquals("Sender Depart", bid1.getSenderDepart());
        assertEquals(1L, bid1.getRecipientPhoneNumber());
        assertEquals("Recipient Name", bid1.getRecipientName());
        assertEquals("Recipient Depart", bid1.getRecipientDepart());
        assertEquals(LocalDateTime.of(1, 1, 1, 1, 1), bid1.getDateChange());
        assertEquals(LocalDateTime.of(1, 1, 1, 1, 1), bid1.getDateCreation());
        assertTrue(this.bidServiceImpl.getAllBids().isEmpty());
        assertEquals("New", bid.getStatus());
    }

    @Test
    void testGetBid() {
        Optional<Bid> ofResult = Optional.of(bid);
        when(this.bidRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(bid, this.bidServiceImpl.getBid(123L));
        verify(this.bidRepository).findById((Long) any());
    }

    @Test
    void testDeleteBid() {
        doNothing().when(this.notificationRepository).deleteById((Long) any());
        doNothing().when(this.bidRepository).deleteById((Long) any());
        this.bidServiceImpl.deleteBid(123L);
        verify(this.notificationRepository).deleteById((Long) any());
        verify(this.bidRepository).deleteById((Long) any());
        assertTrue(this.bidServiceImpl.getAllBids().isEmpty());
    }

    @Test
    void testPackagesWithStatusNew() {
        ArrayList<Bid> bidList = new ArrayList<>();
        when(this.bidRepository.packagesWithStatusNew()).thenReturn(bidList);
        List<Bid> actualPackagesWithStatusNewResult = this.bidServiceImpl.packagesWithStatusNew();
        assertSame(bidList, actualPackagesWithStatusNewResult);
        assertTrue(actualPackagesWithStatusNewResult.isEmpty());
        verify(this.bidRepository).packagesWithStatusNew();
    }

    @Test
    void testUpdateStatus() {
        when(this.bidRepository.save((Bid) any())).thenReturn(bid);
        this.bidServiceImpl.updateStatus(bid);
        verify(this.bidRepository).save((Bid) any());
        assertEquals("OLD", bid.getStatus());
    }
}

