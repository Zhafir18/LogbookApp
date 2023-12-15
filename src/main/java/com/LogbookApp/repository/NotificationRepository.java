package com.LogbookApp.repository;

import com.LogbookApp.dto.notification.NotificationMessageDTO;
import com.LogbookApp.dto.notification.TotalNotificationDTO;
import com.LogbookApp.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("""
            SELECT COUNT(not.id)
            FROM Notification AS not
                JOIN not.logBook AS log
            WHERE log.username = :username
                AND not.isRead = FALSE
            """)

    public Integer getTotalNotification(@Param("username") String username);

    @Query("""
            SELECT new com.LogbookApp.dto.notification.NotificationMessageDTO(
                not.id,
                not.message,
                not.isRead,
                not.notificationDate
            )
            FROM Notification AS not
                JOIN not.logBook AS log
            WHERE log.username = :username
            """)
    public List<NotificationMessageDTO> getMessage(@Param("username") String username);
}
