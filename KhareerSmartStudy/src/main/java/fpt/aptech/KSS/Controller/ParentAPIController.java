/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.ModelString;
import fpt.aptech.KSS.ImpServices.ClassroomServices;
import fpt.aptech.KSS.Services.IAccountRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Controller
public class ParentAPIController {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ClassroomServices classroomServices;
    
    @RequestMapping(value = {"/parent/register"}, method = RequestMethod.POST)
    public String Parent(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        ModelString modelStringout= new ModelString();


        modelString.setData1(request.getParameter("mail"));
        modelString.setData2(request.getParameter("password"));
        modelString.setData3(request.getParameter("code"));
        //modelString.setData4(request.getParameter(""));
        modelString.setData5(request.getParameter("name"));
        modelString.setData6(request.getParameter("dob"));

        Account account = accountRepository.checkUniqueCode(modelString.getData3());

        if (account !=null){

            Account account1 = accountRepository.findByMail(modelString.getData1());
            if (account1!=null){
                modelStringout.setData1("Mail already exists");
                JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
            }
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Account accountparent = new Account();
            accountparent.setMail(modelString.getData1());
            accountparent.setPassword( encoder.encode(modelString.getData2()));
            accountparent.setRole("Parent");
            accountparent.setCode(account.getCode());
            accountparent.setName(modelString.getData5());
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
    
}
