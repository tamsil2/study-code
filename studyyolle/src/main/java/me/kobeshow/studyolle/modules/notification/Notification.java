package me.kobeshow.studyolle.modules.notification;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import me.kobeshow.studyolle.modules.account.Account;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Notification {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String link;

    private String message;

    private boolean checked;

    @ManyToOne
    private Account account;

    private LocalDateTime createdLocalDateTime;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
}
