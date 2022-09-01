/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.ImpServices;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.Notification;
import fpt.aptech.KSS.Entities.NotificationUser;
import fpt.aptech.KSS.Repository.NotificationRepository;
import fpt.aptech.KSS.Repository.NotificationUserRepository;
import fpt.aptech.KSS.Services.INotification;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class NotificationServices implements INotification{
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    NotificationUserRepository notificationUserRepository;
    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public List<NotificationUser> findListNotifacationByAccount(Account acccount) {
        return notificationUserRepository.findByAccount(acccount);
    }

    @Override
    public List<NotificationUser> findListNotifacationByNotification(Notification notification) {
        return notificationUserRepository.findByNotificationID(notification);
    }
    @Override
    public Notification AddNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public NotificationUser AddAccountNotification(NotificationUser accountNotification) {
        accountNotification.setCreateDate(new Date());
        return notificationUserRepository.save(accountNotification);
    }
}
