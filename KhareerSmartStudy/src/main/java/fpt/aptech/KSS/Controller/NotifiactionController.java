/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.AccountToken;
import fpt.aptech.KSS.Entities.ModelString;
import fpt.aptech.KSS.Entities.NotificationUser;
import fpt.aptech.KSS.Services.IAccountRepository;
import fpt.aptech.KSS.Services.INotification;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Controller
public class NotifiactionController {
    @Autowired
    INotification iNotification;
    @Autowired
    IAccountRepository accountRepository;
    @RequestMapping(value = "/api/notification/get", method = RequestMethod.GET)
    public String Test(HttpServletRequest request,HttpServletResponse response){
        ModelString modelString = new ModelString();
        List<ModelString> modelStringout= new ArrayList<ModelString>();
        modelString.setData1(request.getParameter("id"));
        int id = Integer.valueOf(modelString.getData1());
        Account a = accountRepository.findById(id);
        List<NotificationUser>  list = iNotification.findListNotifacationByAccount(a);
        for (int i = 0; i < list.size(); i++) {
            ModelString modelOut = new ModelString();
            modelOut.setData1(list.get(i).getIdNotification().getName());
            modelOut.setData2(list.get(i).getIdNotification().getContent());
            modelOut.setData3(list.get(i).getIdNotification().getCreateDate().toString());
            modelStringout.add(modelOut);
        }
        JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
        return "admin/account/index";
    }
}