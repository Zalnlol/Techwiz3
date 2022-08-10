/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.Account;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jthie
 */
public interface IAccountRepository {

    public List<Account> findAll();

    public void save(Account account);
    
    public List<Account> listAll(String keyword);
}