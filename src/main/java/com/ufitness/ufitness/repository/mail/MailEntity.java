package com.ufitness.ufitness.repository.mail;

import com.ufitness.ufitness.repository.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "mail_entity")
@Getter
@Setter
@AllArgsConstructor
public class MailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column(name = "email_status")
    @Enumerated(EnumType.STRING)
    private EmailStatus emailStatus = EmailStatus.SENT;
    @Column(name = "message", length = 500)
    private String message;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private UserEntity userEntity;
    @Column(name = "token")
    private String token;
    @Column
    private LocalDateTime sentAt;
    @Column
    private LocalDateTime expiresAt;

    public MailEntity() {
        this.sentAt = LocalDateTime.now();
        this.expiresAt = LocalDateTime.now().plusHours(2L);
    }
}
