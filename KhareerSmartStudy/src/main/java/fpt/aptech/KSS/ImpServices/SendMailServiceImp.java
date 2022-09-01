/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.ImpServices;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.Mail;
import fpt.aptech.KSS.Repository.MailRepository;
import fpt.aptech.KSS.Services.IAccountRepository;
import fpt.aptech.KSS.Services.SendMailService;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author jthie
 */
@Service
public class SendMailServiceImp implements SendMailService {

    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    MailRepository mailRepository;

    private final JavaMailSender javaMailSender;

    public SendMailServiceImp(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(Mail mail) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(mail.getRecipient());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getContent(), true);
            helper.setSentDate(new Date());
//            ##Nếu muốn thêm attachment thì add dòng dưới này
//            helper.addAttachment("something.jpg", new ClassPathResource("./static/images/something.jpg"));
            mailRepository.save(mail);
            javaMailSender.send(msg);

        } catch (MessagingException ex) {
            Logger.getLogger(SendMailServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Mail> findAll() {
        return mailRepository.findAll();
    }



}
