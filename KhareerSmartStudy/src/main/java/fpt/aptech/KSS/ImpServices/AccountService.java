/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.ImpServices;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Repository.AccountRepository;
import fpt.aptech.KSS.Services.IAccountRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author jthie
 */
@Service
public class AccountService implements UserDetailsService{

    @Autowired
    IAccountRepository repository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account adminAccount = repository.findByMailAdmin(username);
        Account studentAccount = repository.findByMailStudent(username);
        Account teacherAccount = repository.findByMailTeacher(username);


        if (adminAccount != null) {
            List<GrantedAuthority> grantListAdmin = new ArrayList<>();
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
            grantListAdmin.add(authority);
            UserDetails userDetails = new User(adminAccount.getMail(), adminAccount.getPassword(), grantListAdmin);
            return userDetails;
        } else if (studentAccount != null) {
            List<GrantedAuthority> grantListUser = new ArrayList<>();
            GrantedAuthority userAuthority = new SimpleGrantedAuthority("ROLE_STUDENT");
            grantListUser.add(userAuthority);
            UserDetails userDetails = new User(studentAccount.getMail(), studentAccount.getPassword(), grantListUser);
            return userDetails;
        } else if (teacherAccount != null) {
            if (teacherAccount.getRole().equals("")) {
                List<GrantedAuthority> grantListAdmin = new ArrayList<>();
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_TEACHER");
                grantListAdmin.add(authority);
                UserDetails userDetails = new User(teacherAccount.getMail(), teacherAccount.getPassword(), grantListAdmin);
                return userDetails;
            }
        } else {
            new UsernameNotFoundException("Login failed!");
        }
        return null;
    }
}
