/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.Classroom;
import fpt.aptech.KSS.Entities.ClassroomUser;
import fpt.aptech.KSS.Entities.Exam;
import fpt.aptech.KSS.Entities.ModelString;
import fpt.aptech.KSS.Services.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Controller
public class ExamController {
    @Autowired
    IClassroomRepository classroomRepository;
    @Autowired
    IExam iExam; 
    @Autowired
    IAccountRepository accountRepository;
    @RequestMapping("/api/get/class")
     public void Test(HttpServletRequest request,HttpServletResponse response){
        ModelString modelString = new ModelString();
        modelString.setData2(request.getParameter("id"));
        Account a = accountRepository.findById(Integer.valueOf(modelString.getData2()));
        List<ClassroomUser> listClass = a.getClassroomUserList();
        List<ModelString> modelStringout= new ArrayList<>();
        for (int i = 0; i < listClass.size(); i++) {
            List<Exam> examlist = iExam.findListByClass(listClass.get(i).getIdClassroom());
            for (int i2 = 0; i2 < examlist.size(); i2++) {
            ModelString out = new ModelString();
            out.setData1(examlist.get(i).getIdCourse().getName());
            out.setData2(examlist.get(i).getStartDate().toString());
            modelStringout.add(out);
        }
        }
        //JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);
        if (modelStringout !=null){
                JsonServices.dd(JsonServices.ParseToJson(modelStringout),response);

        }else {
                modelString.setData4("Unsusssess");
                JsonServices.dd(JsonServices.ParseToJson(modelString),response);

        }
        
       
    }
    
}
