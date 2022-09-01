/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Repository;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.Classroom;
import fpt.aptech.KSS.Entities.Notification;
import fpt.aptech.KSS.Entities.NotificationUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface NotificationUserRepository extends JpaRepository<NotificationUser, Integer> {
    @Query("SELECT n FROM NotificationUser n WHERE n.idUser = :idUser ORDER BY n.createDate DESC")
    List<NotificationUser> findByAccount(@PathVariable("idUser") Account idUser);
    @Query("SELECT n FROM NotificationUser n WHERE n.idNotification = :idNotification ORDER BY n.createDate DESC")
    List<NotificationUser> findByNotificationID(@PathVariable("idNotification") Notification idNotification );
}
