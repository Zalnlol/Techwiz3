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
//    @RequestMapping(value = "/api/token", method = RequestMethod.POST)
//    public String sendNotification() throws FirebaseMessagingException {
//       
//       firebaseService.sendNotification("fR2HKA40SfOcVmKiYcLhEc:APA91bFlPTzjyWZiJO9rptAabFw3-sxhQ2KRf2q2Yqy5H14C5u9F-1AWRIrlRrDrI4FMZZnWGO024hzDLjbYAZ9U8anK5_VccbFQhlXVdJ0GzTB-HfNbU-B4UVaAGA7zwMn_W6ZoeP0g", "Okela", "dươc chưa");
//       return "Thanh Cong";
//    }
//    @RequestMapping(value = "/api/token/get", method = RequestMethod.GET)
//    public ResponseEntity<AccountToken> GetToken(HttpServletRequest request,HttpServletResponse response){
//        String token = request.getParameter("token");
//        AccountToken at = accToken.GetToken(token);
//        if (at != null) {
//            return new ResponseEntity<>(at, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//        }
//        
//    }
//    @RequestMapping(value = "/api/token/add", method = RequestMethod.POST)
//    public ResponseEntity<AccountToken> addon(@RequestBody AccountToken accountToken){
//        AccountToken at = accToken.NewToken(accountToken);
//        if (at != null) {
//            return new ResponseEntity<>(at, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//        }
//        
//    }
//    @RequestMapping(value = "/api/token/get/demo", method = RequestMethod.GET)
//    public void demo(HttpServletRequest request,HttpServletResponse response){
//        String token = request.getParameter("token");
//        AccountToken at = accToken.GetToken(token);
//       JsonServices.dd(JsonServices.ParseToJson(at), response);
//        
//    }
    @RequestMapping(value = "/api/token/add", method = RequestMethod.GET)
    public void Test(HttpServletRequest request,HttpServletResponse response){
        ModelString modelString = new ModelString();
        ModelString modelStringout= new ModelString();
        modelString.setData1(request.getParameter("token"));
        modelString.setData2(request.getParameter("id"));
        int id = Integer.valueOf(modelString.getData2());
        AccountToken accountToken = new AccountToken();
        Account a = accountRepository.findById(id);
        accountToken.setToken(modelString.getData1());
        accountToken.setId(a);
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
//    @RequestMapping(value = "/api/token/add", method = RequestMethod.GET)
//    public String (HttpServletRequest request,HttpServletResponse response){
//        ModelString modelString = new ModelString();
//        ModelString modelStringout= new ModelString();
//        modelString.setData1(request.getParameter("token"));
//        modelString.setData2(request.getParameter("id"));
//        int id = Integer.valueOf(modelString.getData2());
//        AccountToken accountToken = new AccountToken();
//        Account a = accountRepository.findById(id);
//        accountToken.setToken(modelString.getData1());
//        accountToken.setId(a);
//        if (accountToken !=null){
//            if(!accountToken.getToken().equals(modelString.getData1())){
//                modelStringout.setData1("Registered token Susssess");
//                JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
//            }
//
//        }else {
//            modelStringout.setData1("Registered token Unsusssess");
//
//        }
//        JsonServices.dd(JsonServices.ParseToJson(modelString),response);
//        return "admin/account/index";
//    }
}
