/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.AccountToken;
import fpt.aptech.KSS.Entities.ModelString;
import fpt.aptech.KSS.Services.IAccountRepository;
import fpt.aptech.KSS.Services.IAccountToken;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Controller
public class TokenController {
    
    @Autowired 
    IAccountToken accToken;
    @Autowired
    IAccountRepository accountRepository;
    @RequestMapping(value = "/api/token/add", method = RequestMethod.GET)
    public void Test(HttpServletRequest request,HttpServletResponse response){
        ModelString modelString = new ModelString();
        ModelString modelStringout= new ModelString();
        modelString.setData1(request.getParameter("token"));
        modelString.setData2(request.getParameter("id"));
        AccountToken accountToken = new AccountToken();
        accountToken.setToken(modelString.getData1());
        if (modelString.getData2().equals("null")) {
         accountToken.setId(null);
        }else{
        int id = Integer.valueOf(modelString.getData2());
        Account a = accountRepository.findById(id);
        accountToken.setId(a);
        }
        accToken.NewToken(accountToken);
        if (accountToken !=null){
            if(!accountToken.getToken().equals(modelString.getData1())){
                modelStringout.setData1("Registered token Susssess");
                JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
            }

        }else {
            modelStringout.setData1("Registered token Unsusssess");

        }
        JsonServices.dd(JsonServices.ParseToJson(modelString),response);
       
    }
}
