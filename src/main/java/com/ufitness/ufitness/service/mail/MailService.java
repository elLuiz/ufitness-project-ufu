package com.ufitness.ufitness.service.mail;

import com.ufitness.ufitness.exception.EmailNotSendException;
import com.ufitness.ufitness.exception.InvalidTokenException;
import com.ufitness.ufitness.repository.mail.EmailStatus;
import com.ufitness.ufitness.repository.mail.MailEntity;
import com.ufitness.ufitness.repository.mail.MailRepository;
import com.ufitness.ufitness.repository.user.UserEntity;
import com.ufitness.ufitness.util.SecureTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MailService {
    @Value("${spring.mail.username}")
    private String emailSender;
    @Value("confirmation.address")
    private String linkAddress;
    private final MailRepository mailRepository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailService(MailRepository mailRepository, JavaMailSender javaMailSender) {
        this.mailRepository = mailRepository;
        this.javaMailSender = javaMailSender;
    }

    @Transactional
    public void sendConfirmationEmail(UserEntity userEntity) throws EmailNotSendException {
        MailEntity emailEntity = createEmailEntity(userEntity);
        String message = "Seja bem-vindo ao UFitness, " + userEntity.getName() + ".\n " +
                "Para confirmar seu cadastro, por favor clique no link: "+ linkAddress + "api/v1/ufitness/mail/confirm?token=" + emailEntity.getToken();
        emailEntity.setMessage(message);
        sendSimpleEmail(userEntity, emailEntity, message);
    }

    private MailEntity createEmailEntity(UserEntity userEntity) {
        MailEntity mailEntity = new MailEntity();
        mailEntity.setToken(SecureTokenGenerator.generateSecureToken());
        mailEntity.setUserEntity(userEntity);
        return mailEntity;
    }

    private void sendSimpleEmail(UserEntity userEntity, MailEntity emailEntity, String message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(emailSender);
            mailMessage.setTo(userEntity.getEmail());
            mailMessage.setSubject("Nova conta UFitness");
            mailMessage.setText(message);
            javaMailSender.send(mailMessage);
        } catch (MailException mailException) {
            emailEntity.setEmailStatus(EmailStatus.ERROR);
            throw new EmailNotSendException("EMAIL_NOT_SENT");
        } finally {
            mailRepository.save(emailEntity);
        }
    }

    @Transactional
    public void validateMailToken(String token)
        throws InvalidTokenException {
        Optional<MailEntity> mailEntityByToken = mailRepository.findMailEntityByToken(token);
        if (mailEntityByToken.isPresent()) {
            MailEntity mailEntity = mailEntityByToken.get();
            if (token.equals(mailEntity.getToken()) && !mailEntity.getExpiresAt().isBefore(LocalDateTime.now())) {
                mailEntity.setEmailStatus(EmailStatus.ANSWERED);
                mailEntity.getUserEntity().setEnabled(true);
                mailRepository.save(mailEntity);
            }
        } else
            throw new InvalidTokenException("INVALID_TOKEN_EXCEPTION");
    }
}