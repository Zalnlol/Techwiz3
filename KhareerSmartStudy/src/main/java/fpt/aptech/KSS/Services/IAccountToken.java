/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.AccountToken;
import java.util.List;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface IAccountToken {
    AccountToken NewToken(AccountToken accountToken);
    List<AccountToken> GetTokenById(int id);
    AccountToken GetToken(String token);
}
