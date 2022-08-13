/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.Notification;
import fpt.aptech.KSS.Entities.NotificationUser;
import java.util.List;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface INotification {
    List<Notification> findAll();
    List<NotificationUser> findListNotifacationByAccount(Account acccount);
    List<NotificationUser> findListNotifacationByNotification(Notification notification);
}
