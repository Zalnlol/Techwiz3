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
public class AccountServiceImp implements IAccountRepository {

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
        if (keyword != null) {
            return repository.search(keyword);
        }
        return repository.findAll();
    }

    @Override
    public Account checkUniqueCode(String code) {
        return repository.checkUniqueCode(code);
    }

    @Override
    public Account lastCreateAccount(Account account) {
        return repository.selectLatestRecord(account);
    }

    @Override
    public Account findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Account findByMailAdmin(String mail) {
        return repository.findByEmailAdmin(mail);
    }

    @Override
    public Account findByMailStudent(String mail) {
        return repository.findByEmailStudent(mail);
    }

    @Override
    public Account findByMailTeacher(String mail) {
        return repository.findByEmailTeacher(mail);
    }

    @Override
    public Account findByMail(String mail) {
        return repository.findByEmail(mail);
    }

    @Override
    public List<Account> listAllTeacher(String role) {
        return repository.findAllTeacherList(role);
    }

}
