///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package fpt.aptech.KSS.Controller;
//
//import fpt.aptech.KSS.Entities.Classroom;
//import fpt.aptech.KSS.Entities.Exam;
//import fpt.aptech.KSS.Entities.ModelString;
//import fpt.aptech.KSS.Services.*;
//import java.util.ArrayList;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// *
// * @author LÊ HỮU TÂM
// */
//@Controller
//public class ExamController {
//    @Autowired
//    IClassroomRepository classroomRepository;
//    @Autowired
//    IExam iExam; 
//    @RequestMapping("/api/get/class")
//     public void Test(HttpServletRequest request,HttpServletResponse response){
//        ModelString modelString = new ModelString();
//        List<ModelString> modelStringout= new ArrayList<>();
//        modelString.setData1(request.getParameter("class"));
//        //modelString.setData2(request.getParameter("id"));
//        //int id = Integer.valueOf(modelString.getData2());
//        Classroom classroom = classroomRepository.findOneByName(modelString.getData1());
//        List<Exam> examlist = iExam.findListByClass(classroom);
//        
//        for (int i = 0; i < examlist.size(); i++) {
//            ModelString out = new ModelString();
//            out.setData1(examlist.get(i).getIdCourse().getName());
//            out.setData2(examlist.get(i).getStartDate().toString());
//            out.setData3(examlist.get(i).);
//            
//        }
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
//       
//    }
//    
//}
