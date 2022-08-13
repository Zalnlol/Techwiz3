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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    public void Test(HttpServletRequest request,HttpServletResponse response) throws ParseException{
        ModelString modelString = new ModelString();
        List<ModelString> modelStringout= new ArrayList<ModelString>();
        modelString.setData1(request.getParameter("id"));
        int id = Integer.valueOf(modelString.getData1());
        Account a = accountRepository.findById(id);
        List<NotificationUser>  list = iNotification.findListNotifacationByAccount(a);
        for (int i = 0; i < list.size(); i++) {
            ModelString modelOut = new ModelString();
            
            Date date = new Date() ;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String strDate= formatter.format(date);
            
            modelOut.setData1(list.get(i).getIdNotification().getName());
            modelOut.setData2(list.get(i).getIdNotification().getContent());
            String datel = list.get(i).getIdNotification().getCreateDate().toString();
            Date dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
            Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(datel);
            modelOut.setData3(String.valueOf(Numday(dateEnd, dateStart)));
            modelOut.setData4(list.get(i).getId().toString());
            modelOut.setData5(strDate);
            modelOut.setData6(datel.toString());
            modelOut.setData7(String.valueOf("false"));
            
            modelStringout.add(modelOut);
        }
        JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
        
    }
       private int Numday(Date st,Date end){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(st);
        c2.setTime(end);
        int noDay = (int) ((c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000));
        return noDay;
    }
}
