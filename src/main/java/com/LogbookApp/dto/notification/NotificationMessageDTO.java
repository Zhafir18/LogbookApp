package com.LogbookApp.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class NotificationMessageDTO {
    private Long id;
    private String message;
    private Boolean isRead;
    private LocalDateTime date;
    private String dateTimeFormat;

    public NotificationMessageDTO(Long id, String message, Boolean isRead, LocalDateTime date) {
        this.id = id;
        this.message = message;
        this.isRead = isRead;
        this.date = date;
    }
}
