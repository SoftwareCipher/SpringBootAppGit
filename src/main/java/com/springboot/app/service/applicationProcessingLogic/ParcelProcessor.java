package com.springboot.app.service.applicationProcessingLogic;

import com.springboot.app.entities.Notification;
import com.springboot.app.entities.Bid;
import com.springboot.app.service.NotificationServiceImpl;
import com.springboot.app.service.BidServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Service
@EnableAsync
public class ParcelProcessor {

    BidServiceImpl bidService;
    NotificationServiceImpl notificationService;

    @Autowired
    public ParcelProcessor(BidServiceImpl bidService,
                           NotificationServiceImpl notificationService) {
        this.bidService = bidService;
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = 5000)
    @Async
    public void applicationStatusProcessing() {
        List<Bid> bidList = bidService.packagesWithStatusNew();
        for (int i = 0; i < bidList.size(); i++) {
            long id = bidList.get(i).getId();
            if (notificationService.getEntity(id).getStatus().equals("new")) {
                LocalTime timeGoods = LocalTime.from(bidService.getBid(id)
                        .getDateCreation());
                LocalTime newTime = timeGoods.plusHours(3).plusSeconds(5);
                compareOfSendingAndReceivingTimes(LocalTime.now(), newTime, id);
                bidService.updateStatus(bidList.get(i));
            }
        }
    }

    private void compareOfSendingAndReceivingTimes(LocalTime now,
                                                   LocalTime newTimePlusFive, long item) {
        if (now.isBefore(newTimePlusFive)) {
            if (packageStatus()) {
                notificationService.saveOrUpdateEntity(
                        new Notification(item, "Received", "sent"));
            } else {
                notificationService.saveOrUpdateEntity(
                        new Notification(item, "Overdue", "sent"));
            }
        } else {
            notificationService.saveOrUpdateEntity(
                    new Notification(item, "Overdue", "sent"));
        }
    }

    private boolean packageStatus() {
        Random random = new Random();
        return random.nextInt(5) == 0;
    }
}
