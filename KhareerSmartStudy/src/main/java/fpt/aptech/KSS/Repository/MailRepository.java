/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Repository;

import fpt.aptech.KSS.Entities.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jthie
 */
public interface MailRepository extends JpaRepository<Mail, Integer> {
    
}
