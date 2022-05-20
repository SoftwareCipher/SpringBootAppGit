package com.springboot.app.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "pack")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "sender_name")
    private String senderName;
    @Column(name = "sender_depart")
    private String senderDepart;

    @Column(name = "recipient_name")
    private String recipientName;
    @Column(name = "recipient_depart")
    private String recipientDepart;
    @Column(name = "recipient_phone_number")
    private long recipientPhoneNumber;

    @Column(name = "status")
    private String status;
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;
    @Column(name = "date_change")
    private LocalDateTime dateChange;

    public Bid(
            long id,
            String senderName,
            String senderDepart,
            String recipientName,
            String recipientDepart,
            long recipientPhoneNumber
            ){
        this.id = id;
        this.senderName = senderName;
        this.senderDepart = senderDepart;
        this.recipientDepart = recipientDepart;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.recipientName = recipientName;
    }
}
