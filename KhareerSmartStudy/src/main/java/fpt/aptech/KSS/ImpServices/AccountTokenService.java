/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.ImpServices;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.AccountToken;
import fpt.aptech.KSS.Repository.AccountTokenRepository;
import fpt.aptech.KSS.Services.IAccountRepository;
import fpt.aptech.KSS.Services.IAccountToken;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class AccountTokenService implements IAccountToken{
   @Autowired
   IAccountRepository accountRepository;
   @Autowired
   AccountTokenRepository accountTokenRepository;

    @Override
    public AccountToken NewToken(AccountToken accountToken) {
        return accountTokenRepository.save(accountToken);
    }

    @Override
    public List<AccountToken> GetTokenById(int id) {
        Account acc = accountRepository.findById(id);
        List<AccountToken> accountTokens = accountTokenRepository.findByAccount(acc);
//        List<String> listtoken = new List<>();
//        for (AccountToken acKen : accountTokens) {
//            listtoken.add(acKen.getToken());
//        }
        return accountTokens;
    }

    @Override
    public AccountToken GetToken(String token) {
        return accountTokenRepository.findByToken(token);
    }
   
   
}
