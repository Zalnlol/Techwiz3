/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.ModelString;
import fpt.aptech.KSS.Routes.RouteAPI;
import fpt.aptech.KSS.Routes.RouteWeb;
import fpt.aptech.KSS.Services.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Admin
 */
@Controller
public class APIController {
    @Autowired
    private IAccountRepository accountRepository;

    @RequestMapping(value = {RouteAPI.APICreateAccount}, method = RequestMethod.POST)
    public String AccountList(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        ModelString modelStringout= new ModelString();


        modelString.setData1(request.getParameter("mail"));
        modelString.setData2(request.getParameter("password"));
        modelString.setData3(request.getParameter("code"));
        modelString.setData4(request.getParameter("role"));
        modelString.setData5(request.getParameter("name"));
        modelString.setData6(request.getParameter("dob"));

        Account account = accountRepository.checkUniqueCode(modelString.getData3());

        if (account !=null){
            if(!account.getRole().equals(modelString.getData4())){
                modelStringout.setData1("Registered wrong Role");
                JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
            }

            Account account1 = accountRepository.findByMail(modelString.getData1());
            if (account1!=null){
                modelStringout.setData1("Mail already exists");
                JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
            }
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            account.setMail(modelString.getData1());
            account.setPassword( encoder.encode(modelString.getData2()));
            account.setRole(modelString.getData4());
            account.setName(modelString.getData5());
//            account.setDob(new Date(modelString.getData6()));

            try {
                Date date1=new SimpleDateFormat("MM-dd-yyyy").parse(modelString.getData6());

                account.setDob(date1);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            accountRepository.save(account);

//            JsonServices.dd(modelString.getData6(),response);
            modelStringout.setData1("Done");
            JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
        }else {
            modelStringout.setData1("Invalid QR");
            JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
        }





        JsonServices.dd(JsonServices.ParseToJson(modelString),response);


        return "admin/account/index";
    }


    @RequestMapping(value = {RouteAPI.APILogin}, method = RequestMethod.POST)
    public String APILogin(Model model, HttpServletResponse response, HttpServletRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        ModelString modelString = new ModelString();

//        JsonServices.dd(JsonServices.ParseToJson(modelString),response);
        String mail= request.getParameter("mail");
        String password= request.getParameter("password");

        Account account = new Account();
        account = accountRepository.findByMail(mail);

        if(account!=null){

//            JsonServices.dd(encoder.matches(password,account.getPassword()),response);

            if(encoder.matches(password,account.getPassword())){
                modelString.setData1("Done");
                JsonServices.dd(JsonServices.ParseToJson(modelString),response);

            }else {
                modelString.setData1("Mail or Password incorect!!");
                JsonServices.dd(JsonServices.ParseToJson(modelString),response);
            }


        }else {
            modelString.setData1("Mail or Password incorect!!");
            JsonServices.dd(JsonServices.ParseToJson(modelString),response);
        }

        return "admin/account/index";
    }


}
