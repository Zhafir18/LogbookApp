package com.LogbookApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "LogId", insertable = false, updatable = false)
    private LogBook logBook;

    @Column(name = "LogId")
    private Long logId;

    @Column(name = "ClientApprovalNotification")
    private Boolean clientApprovalNotification;

    @Column(name = "HRDApprovalNotification")
    private Boolean hrdApprovalNotification;

    @Column(name = "IsRead")
    private Boolean isRead;

    @Column(name = "Message")
    private String message;

    @Column(name = "NotificationDate")
    private LocalDateTime notificationDate;
}
