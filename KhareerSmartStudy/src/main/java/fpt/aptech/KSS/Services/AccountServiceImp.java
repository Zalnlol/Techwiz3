/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Repository.AccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jthie
 */
@Service
public class AccountServiceImp implements IAccountRepository{
    @Autowired
    private AccountRepository repository;
    
    @Override
    public List<Account> findAll() {
         return repository.findAll();
    }

    @Override
    public void save(Account account) {
       repository.save(account);
    }

    @Override
    public List<Account> listAll(String keyword) {
        return repository.search(keyword);
    }
    
}