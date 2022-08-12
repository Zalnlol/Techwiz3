/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Repository;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.AccountToken;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface AccountTokenRepository extends JpaRepository<AccountToken, String> {
    @Query("SELECT d FROM AccountToken d WHERE d.id = :id")
    List<AccountToken> findByAccount(@PathVariable("id") Account id);
    @Query("SELECT a FROM AccountToken a WHERE a.token = :token")
    AccountToken findByToken(@PathVariable("token") String token);
}
