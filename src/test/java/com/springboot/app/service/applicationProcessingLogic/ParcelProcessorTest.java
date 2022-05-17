package com.springboot.app.service.applicationProcessingLogic;

import com.springboot.app.entities.Bid;
import com.springboot.app.entities.Notification;
import com.springboot.app.repository.BidRepository;
import com.springboot.app.repository.NotificationRepository;
import com.springboot.app.service.BidServiceImpl;
import com.springboot.app.service.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ParcelProcessorTest {
    Bid bid = new Bid();
    Notification notification = new Notification();

    @BeforeEach
    public void setUp() {
        bid.setDateChange(LocalDateTime.of(1, 1, 1, 1, 1));
        bid.setDateCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        bid.setId(123L);
        bid.setRecipientDepart("Recipient Depart");
        bid.setRecipientName("Recipient Name");
        bid.setRecipientPhoneNumber(1L);
        bid.setSenderDepart("Sender Depart");
        bid.setSenderName("Sender Name");
        bid.setStatus("Status");
        Notification notification = new Notification();
        notification.setId(123L);
        notification.setNotification("Notification");
        notification.setStatus("Status");
    }

    @Test
    void testApplicationStatusProcessing() {
        BidRepository bidRepository = mock(BidRepository.class);
        when(bidRepository.packagesWithStatusNew()).thenReturn(new ArrayList<>());
        BidServiceImpl bidService = new BidServiceImpl(bidRepository,
                mock(NotificationRepository.class));

        (new ParcelProcessor(bidService,
                new NotificationServiceImpl(mock(NotificationRepository.class))))
                .applicationStatusProcessing();
        verify(bidRepository).packagesWithStatusNew();
    }

    @Test
    void testApplicationStatusProcessing4() {
        BidServiceImpl bidServiceImpl = mock(BidServiceImpl.class);
        when(bidServiceImpl.packagesWithStatusNew()).thenReturn(new ArrayList<>());
        NotificationRepository notificationRepository = mock(NotificationRepository.class);
        when(notificationRepository.findById((Long) any()))
                .thenReturn(Optional.of(notification));
        (new ParcelProcessor(bidServiceImpl,
                new NotificationServiceImpl(notificationRepository)))
                .applicationStatusProcessing();
        verify(bidServiceImpl).packagesWithStatusNew();
    }

    @Test
    void testApplicationStatusProcessing5() {
        ArrayList<Bid> bidList = new ArrayList<>();
        bidList.add(bid);
        BidRepository bidRepository = mock(BidRepository.class);
        when(bidRepository.packagesWithStatusNew()).thenReturn(bidList);
        BidServiceImpl bidService = new BidServiceImpl(bidRepository,
                mock(NotificationRepository.class));

        Notification notification = mock(Notification.class);
        when(notification.getStatus()).thenReturn("Status");
        doNothing().when(notification).setId(anyLong());
        doNothing().when(notification).setNotification((String) any());
        doNothing().when(notification).setStatus((String) any());
        notification.setId(123L);
        notification.setNotification("Notification");
        notification.setStatus("Status");
        NotificationRepository notificationRepository = mock(NotificationRepository.class);
        when(notificationRepository.findById((Long) any()))
                .thenReturn(Optional.of(notification));
        (new ParcelProcessor(bidService, new NotificationServiceImpl(notificationRepository)))
                .applicationStatusProcessing();
        verify(bidRepository).packagesWithStatusNew();
        verify(notificationRepository).findById((Long) any());
        verify(notification).getStatus();
        verify(notification).setId(anyLong());
        verify(notification).setNotification((String) any());
        verify(notification).setStatus((String) any());
    }

    @Test
    void testApplicationStatusProcessing6() {
        ArrayList<Bid> bidList = new ArrayList<>();
        bidList.add(bid);
        Optional<Bid> ofResult = Optional.of(bid);
        BidRepository bidRepository = mock(BidRepository.class);
        when(bidRepository.save((Bid) any())).thenReturn(bid);
        when(bidRepository.findById((Long) any())).thenReturn(ofResult);
        when(bidRepository.packagesWithStatusNew()).thenReturn(bidList);
        BidServiceImpl bidService = new BidServiceImpl(bidRepository,
                mock(NotificationRepository.class));

        Notification notification = mock(Notification.class);
        when(notification.getStatus()).thenReturn("new");
        doNothing().when(notification).setId(anyLong());
        doNothing().when(notification).setNotification((String) any());
        doNothing().when(notification).setStatus((String) any());
        notification.setId(123L);
        notification.setNotification("Notification");
        notification.setStatus("Status");
        Optional<Notification> ofResult1 = Optional.of(notification);

        Notification notification1 = new Notification();
        notification1.setId(123L);
        notification1.setNotification("Notification");
        notification1.setStatus("Status");
        NotificationRepository notificationRepository = mock(NotificationRepository.class);
        when(notificationRepository.saveAndFlush((Notification) any()))
                .thenReturn(notification1);
        when(notificationRepository.findById((Long) any())).thenReturn(ofResult1);
        (new ParcelProcessor(bidService, new NotificationServiceImpl(notificationRepository)))
                .applicationStatusProcessing();
        verify(bidRepository).save((Bid) any());
        verify(bidRepository).packagesWithStatusNew();
        verify(bidRepository).findById((Long) any());
        verify(notificationRepository).saveAndFlush((Notification) any());
        verify(notificationRepository).findById((Long) any());
        verify(notification).getStatus();
        verify(notification).setId(anyLong());
        verify(notification).setNotification((String) any());
        verify(notification).setStatus((String) any());
    }
}