/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.Mail;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface SendMailService {
    public void sendMail(Mail mail);
    List<Mail> findAll();
}
