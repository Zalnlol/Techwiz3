/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.ClassroomUser;
import fpt.aptech.KSS.Entities.Exam;
import fpt.aptech.KSS.Entities.Mark;
import fpt.aptech.KSS.Entities.ModelString;
import fpt.aptech.KSS.ImpServices.ClassroomServices;
import fpt.aptech.KSS.ImpServices.MarkService;
import fpt.aptech.KSS.Services.ClassroomUserServiceImp;
import fpt.aptech.KSS.Services.IAccountRepository;
import fpt.aptech.KSS.Services.IExam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private ClassroomUserServiceImp classroomUserServices;
    @Autowired
    private MarkService markService; 
    @Autowired
    IExam iExam; 
    @RequestMapping(value = {"api/parent/register"}, method = RequestMethod.POST)
    public String Parent(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        ModelString modelStringout= new ModelString();


        modelString.setData1(request.getParameter("mail"));
        modelString.setData2(request.getParameter("password"));
        modelString.setData3(request.getParameter("code"));
        modelString.setData4(request.getParameter("phone"));
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
    @RequestMapping(value = {"api/parent/get/mark"}, method = RequestMethod.GET)
    public void ParentGetMark(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        List<ModelString> modelStringout= new ArrayList<>();


        modelString.setData1(request.getParameter("id"));
        Account account = accountRepository.findById(Integer.valueOf(modelString.getData1()));
        List<Mark> list = markService.findByAccount(account);
        for (int i = 0; i < list.size(); i++) {
            Mark get = list.get(i);
            ModelString out = new ModelString();
            out.setData1(get.getIdExam().getIdCourse().getName());
            out.setData2(get.getIdExam().getStartDate().toString());
            out.setData3(String.valueOf(get.getMark()));
            out.setData4(get.getRemark());
            modelStringout.add(out);
        }
        if (modelStringout!=null) {
           JsonServices.dd(JsonServices.ParseToJson(modelStringout),response); 
        }else{
            JsonServices.dd("faill",response);
        }
           
    }
    @RequestMapping(value = {"api/parent/get/class"}, method = RequestMethod.GET)
    public void ParentGetClass(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        List<ModelString> modelStringout= new ArrayList<>();


        modelString.setData1(request.getParameter("id"));
        Account account = accountRepository.findById(Integer.valueOf(modelString.getData1()));
        List<ClassroomUser> list = classroomUserServices.findClassesByUser(account);
        //List<Mark> list = markService.findByAccount(account);
        for (int i = 0; i < list.size(); i++) {
            ClassroomUser get = list.get(i);
            ModelString out = new ModelString();
            out.setData1(get.getIdClassroom().getName());
            out.setData2(get.getIdClassroom().getDuration());
            out.setData3(String.valueOf(get.getIdClassroom().getIdSemester()));
            out.setData4(get.getIdUser().getName());
            out.setData5(get.getIdClassroom().getImage());
            modelStringout.add(out);
        }
        if (modelStringout!=null) {
           JsonServices.dd(JsonServices.ParseToJson(modelStringout),response); 
        }else{
            JsonServices.dd("faill",response);
        }
           
    }
    @RequestMapping(value = {"api/parent/get/test"}, method = RequestMethod.GET)
    public void ParentGetExam(Model model, HttpServletResponse response, HttpServletRequest request) {

        ModelString modelString = new ModelString();
        modelString.setData2(request.getParameter("id"));
        Account a = accountRepository.findById(Integer.valueOf(modelString.getData2()));
        List<ClassroomUser> listClass = a.getClassroomUserList();
        List<ModelString> modelStringout= new ArrayList<>();
        for (int i = 0; i < listClass.size(); i++) {
            List<Exam> examlist = iExam.findListByClass(listClass.get(i).getIdClassroom());
            for (int i2 = 0; i2 < examlist.size(); i2++) {
            ModelString out = new ModelString();
            out.setData1(examlist.get(i2).getIdCourse().getName());
            out.setData2(examlist.get(i2).getStartDate().toString());
            out.setData3(examlist.get(i2).getIdClassroom().getName());
            out.setData4(examlist.get(i2).getIdCourse().getImage());
            modelStringout.add(out);
        }
        }
        //JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
        if (modelStringout !=null){
                JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);

        }else {
                modelString.setData5("Unsusssess");
                JsonServices.dd(JsonServices.ParseToJson(modelString),response);

        }
           
    }
    
}
