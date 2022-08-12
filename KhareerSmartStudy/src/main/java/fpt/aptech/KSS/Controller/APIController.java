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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

        }else {
            modelStringout.setData1("Invalid QR");

        }





        JsonServices.dd(JsonServices.ParseToJson(modelString),response);


        return "admin/account/index";
    }


}
